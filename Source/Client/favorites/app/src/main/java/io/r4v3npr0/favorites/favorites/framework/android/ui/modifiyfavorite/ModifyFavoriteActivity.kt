package io.r4v3npr0.favorites.favorites.framework.android.ui.modifiyfavorite

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.r4v3npr0.favorites.R
import io.r4v3npr0.favorites.databinding.ActivityModifyFavoriteBinding
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.application.usecase.GetFavoriteUseCase
import io.r4v3npr0.favorites.favorites.application.usecase.ModifyFavoriteUseCase
import io.r4v3npr0.favorites.favorites.framework.realm.adapter.RealmFavoritesDataGateway
import io.r4v3npr0.favorites.favorites.framework.retrofit.adapter.RetrofitFavoritesServicesGateway
import io.r4v3npr0.favorites.favorites.interfaceadapter.ModifyFavoritePresenter
import io.r4v3npr0.favorites.favorites.interfaceadapter.ModifyFavoritePresenterImpl
import io.r4v3npr0.favorites.favorites.interfaceadapter.ModifyFavoriteView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ModifyFavoriteActivity: AppCompatActivity(), ModifyFavoriteView {
    companion object {
        const val EXTRA_ID = "io.r4v3npr0.favorites.favorites.framework.android.ui.modifiyfavorite.EXTRA_ID"
    }

    private lateinit var binding: ActivityModifyFavoriteBinding
    private lateinit var presenter: ModifyFavoritePresenter

    private val accountNameTextWatcher = object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            GlobalScope.launch {
                presenter.onAccountNumberTextChange()
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    private val favoriteObserver = Observer<FavoriteModel> {
        if (binding.accountNumberEditText.text.toString() != it.accountNumber) {
            binding.accountNumberEditText.setText(it.accountNumber)
        }

        if (binding.nameEditText.text.toString() != it.name) {
            binding.nameEditText.setText(it.name)
        }
    }

    private val nameTextWatcher = object: TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            GlobalScope.launch {
                presenter.onNameTextChange()
            }
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    private val saveOnClickListener = View.OnClickListener {
        GlobalScope.launch {
            presenter.onSave()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_modify_favorite)
        binding.accountNumberEditText.addTextChangedListener(accountNameTextWatcher)
        binding.nameEditText.addTextChangedListener(nameTextWatcher)
        binding.saveButton.setOnClickListener(saveOnClickListener)

        val viewModel = ViewModelProvider(this).get(ModifyFavoriteViewModel::class.java)
        viewModel.favorite.observe(this, favoriteObserver)

        val realmFavoritesDataGateway = RealmFavoritesDataGateway()
        val retrofitFavoritesServicesGateway = RetrofitFavoritesServicesGateway()

        presenter = ModifyFavoritePresenterImpl(
            GetFavoriteUseCase(
                realmFavoritesDataGateway
            ),
            ModifyFavoriteUseCase(
                retrofitFavoritesServicesGateway,
                realmFavoritesDataGateway,
                retrofitFavoritesServicesGateway
            ),
            this,
            viewModel
        )

        GlobalScope.launch {
            presenter.onLoad(intent.getStringExtra(EXTRA_ID)!!)
        }
    }

    override fun getAccountNumber() = binding.accountNumberEditText.text.toString()

    override fun getName() = binding.nameEditText.text.toString()

    override fun showFavorites() {
        finish()
    }
}