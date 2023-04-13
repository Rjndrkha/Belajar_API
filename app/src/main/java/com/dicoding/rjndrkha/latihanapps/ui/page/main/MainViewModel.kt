package com.dicoding.rjndrkha.latihanapps.ui.page.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.rjndrkha.latihanapps.networking.UserRepository
import com.dicoding.rjndrkha.latihanapps.networking.UserResponse
import kotlinx.coroutines.launch

class MainViewModel() : ViewModel() {

    val user: LiveData<ArrayList<UserResponse>?> = UserRepository.user
    val searchUser: LiveData<ArrayList<UserResponse>?> = UserRepository.userSearch
    val isLoading: LiveData<Boolean> = UserRepository.isLoading
    val isDataFailed: LiveData<Boolean> = UserRepository.isDataFailed

    init {
        viewModelScope.launch{
            UserRepository.getListUser()
        }

    }
//    fun getThemeSettings(): LiveData<Boolean> {
//        return pref.getThemeSetting().asLiveData()
//    }
    override fun onCleared() {
        super.onCleared()
        UserRepository.viewModelJob.cancel()
    }
}
