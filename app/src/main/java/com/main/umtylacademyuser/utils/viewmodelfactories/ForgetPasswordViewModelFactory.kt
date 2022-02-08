package com.main.umtylacademyuser.utils.viewmodelfactories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.main.umtylacademyuser.utils.viewmodels.ForgetPasswordViewModel

class ForgetPasswordViewModelFactory(application: Application): ViewModelProvider.Factory {
    val app = application
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ForgetPasswordViewModel(app) as T
    }
}