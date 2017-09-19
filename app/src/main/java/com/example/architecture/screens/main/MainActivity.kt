package com.example.architecture.screens.main

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.example.architecture.R
import com.example.architecture.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.okButton
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : BaseActivity<MainViewState, MainViewModel>() {

    override lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this)[MainViewModel::class.java]

        main_button.onClick { viewModel.buttonClicked() }
    }

    override fun render(viewState: MainViewState) {
        main_button.text = viewState.name

        viewState.dialog?.let {
            viewModel.dialogDisplayed()
            alert(it.title, it.message) {
                okButton { viewModel.okClicked() }
            }.show()
        }

        viewState.navigation?.let {
            viewModel.navigationComplete()
            startActivity(Intent(this, it.target))
        }
    }
}
