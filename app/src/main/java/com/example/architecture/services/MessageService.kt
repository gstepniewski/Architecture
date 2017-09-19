package com.example.architecture.services

import io.reactivex.subjects.BehaviorSubject

class MessageService {

    val mainScreenName = BehaviorSubject.createDefault(generateMainScreenName())

    private var count: Int = 0
    set(value) {
        field = value
        mainScreenName.onNext(generateMainScreenName())
    }

    fun getDialogContent(): Pair<String, String> {
        count += 1
        return Pair("Navigation", "You will be navigated")
    }

    private fun generateMainScreenName() = "Dialog was displayed $count times"
}