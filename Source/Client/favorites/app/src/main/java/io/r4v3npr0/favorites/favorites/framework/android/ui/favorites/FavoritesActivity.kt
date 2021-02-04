package io.r4v3npr0.favorites.favorites.framework.android.ui.favorites

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.r4v3npr0.favorites.R
import io.r4v3npr0.favorites.databinding.ActivityFavoritesBinding
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.application.usecase.DeleteFavoriteUseCase
import io.r4v3npr0.favorites.favorites.application.usecase.GetFavoritesUseCase
import io.r4v3npr0.favorites.favorites.framework.android.base.RecyclerViewBase
import io.r4v3npr0.favorites.favorites.framework.android.ui.modifiyfavorite.ModifyFavoriteActivity
import io.r4v3npr0.favorites.favorites.framework.realm.adapter.RealmFavoritesDataGateway
import io.r4v3npr0.favorites.favorites.framework.retrofit.adapter.RetrofitFavoritesServicesGateway
import io.r4v3npr0.favorites.favorites.interfaceadapter.FavoritesPresenter
import io.r4v3npr0.favorites.favorites.interfaceadapter.FavoritesPresenterImpl
import io.r4v3npr0.favorites.favorites.interfaceadapter.FavoritesView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FavoritesActivity: AppCompatActivity(), FavoritesView {
    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var presenter: FavoritesPresenter

    private val favoritesObserver = Observer<List<FavoriteModel>> {
        binding.favoritesRecyclerView.adapter = FavoritesAdapter(
            it,
            LayoutInflater.from(this),
            favoritesOnItemLongClickListener
        )
    }

    private val favoritesOnItemLongClickListener = RecyclerViewBase.OnItemLongClickListener {
        AlertDialog.Builder(this)
            .setItems(R.array.favorites_context_options) { _, position ->
                GlobalScope.launch {
                    when (position) {
                        0 -> presenter.onModifyFavorite(it)
                        1 -> presenter.onDeleteFavorite(it)
                    }
                }
            }
            .create()
            .show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_favorites)
        binding.favoritesRecyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )

        val viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        viewModel.favorites.observe(this, favoritesObserver)

        val realmFavoritesDataGateway = RealmFavoritesDataGateway()
        val retrofitFavoritesServicesGateway = RetrofitFavoritesServicesGateway()

        presenter = FavoritesPresenterImpl(
            DeleteFavoriteUseCase(
                realmFavoritesDataGateway,
                retrofitFavoritesServicesGateway
            ),
            GetFavoritesUseCase(
                realmFavoritesDataGateway,
                retrofitFavoritesServicesGateway,
                realmFavoritesDataGateway,
                realmFavoritesDataGateway
            ),
            this,
            viewModel
        )

        GlobalScope.launch {
            presenter.onLoad()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_favorites, menu)
        return true
    }

    override fun showModifyFavorites(id: String) {
        val intent = Intent(this, ModifyFavoriteActivity::class.java)
        intent.putExtra(ModifyFavoriteActivity.EXTRA_ID, id)

        startActivity(intent)
    }
}