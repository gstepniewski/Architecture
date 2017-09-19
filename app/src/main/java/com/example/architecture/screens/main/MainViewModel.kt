package com.example.architecture.screens.main

import com.example.architecture.ArchitectureApp
import com.example.architecture.BaseViewModel
import com.example.architecture.DialogRequest
import com.example.architecture.NavigationRequest
import com.example.architecture.screens.second.SecondActivity
import com.example.architecture.services.MessageService
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MainViewModel: BaseViewModel<MainViewState>() {

    override val viewState = BehaviorSubject.createDefault(MainViewState("Hello"))

    @Inject lateinit var messageService: MessageService

    init {
        ArchitectureApp.mainComponent.inject(this)

        bind(
            messageService.mainScreenName.subscribe { viewState.onNext(viewState.value.name(it)) }
        )
    }

    fun buttonClicked() {
        val (title, message) = messageService.getDialogContent()
        val request = DialogRequest(title, message)
        viewState.onNext(viewState.value.dialog(request))
    }

    fun okClicked() {
        val request = NavigationRequest(SecondActivity::class.java)
        viewState.onNext(viewState.value.navigation(request))
    }

    fun dialogDisplayed() {
        viewState.onNext(viewState.value.clearDialog())
    }

    fun navigationComplete() {
        viewState.onNext(viewState.value.clearNavigation())
    }
}