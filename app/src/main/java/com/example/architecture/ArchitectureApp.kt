package com.example.architecture

import android.app.Application
import com.example.architecture.screens.main.MainViewModel
import com.example.architecture.services.MessageService
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MessageModule {
    @Provides @Singleton
    fun messageService() = MessageService()
}

@Singleton
@Component(modules = arrayOf(MessageModule::class))
interface MainComponent {
    fun inject(vm: MainViewModel)
}

class ArchitectureApp: Application() {

    companion object {
        lateinit var mainComponent: MainComponent
    }

    override fun onCreate() {
        super.onCreate()
        mainComponent = DaggerMainComponent.builder().build()
    }

}