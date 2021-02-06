package io.r4v3npr0.favorites.favorites.framework.android.ui.addfavorite

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.r4v3npr0.favorites.R
import io.r4v3npr0.favorites.databinding.ActivityAddFavoriteBinding
import io.r4v3npr0.favorites.favorites.application.model.FavoriteModel
import io.r4v3npr0.favorites.favorites.application.usecase.AddFavoriteUseCase
import io.r4v3npr0.favorites.favorites.framework.realm.adapter.RealmFavoritesDataGateway
import io.r4v3npr0.favorites.favorites.framework.retrofit.adapter.RetrofitFavoritesServicesGateway
import io.r4v3npr0.favorites.favorites.interfaceadapter.AddFavoritePresenter
import io.r4v3npr0.favorites.favorites.interfaceadapter.AddFavoritePresenterImpl
import io.r4v3npr0.favorites.favorites.interfaceadapter.AddFavoriteView
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddFavoriteActivity: AppCompatActivity(), AddFavoriteView {
    private lateinit var binding: ActivityAddFavoriteBinding
    private lateinit var presenter: AddFavoritePresenter

    private val accountNumberTextWatcher = object: TextWatcher {
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
            presenter.onNameTextChange()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_favorite)
        binding.accountNumberEditText.addTextChangedListener(accountNumberTextWatcher)
        binding.nameEditText.addTextChangedListener(nameTextWatcher)

        val viewModel = ViewModelProvider(this).get(AddFavoriteViewModel::class.java)
        viewModel.favorite.observe(this, favoriteObserver)

        val realmFavoritesDataGateway = RealmFavoritesDataGateway()
        val retrofitFavoritesServicesGateway = RetrofitFavoritesServicesGateway()

        presenter = AddFavoritePresenterImpl(
            AddFavoriteUseCase(
                realmFavoritesDataGateway,
                retrofitFavoritesServicesGateway,
                retrofitFavoritesServicesGateway
            ),
            this,
            viewModel
        )
    }

    override fun back() {
        TODO("Not yet implemented")
    }

    override fun getAccountNumber() = binding.accountNumberEditText.text.toString()

    override fun getName() = binding.nameEditText.text.toString()
}