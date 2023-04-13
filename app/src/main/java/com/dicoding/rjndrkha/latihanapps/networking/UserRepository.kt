package com.dicoding.rjndrkha.latihanapps.networking

import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object UserRepository {
    val user = MutableLiveData<ArrayList<UserResponse>?>()
    val userSearch = MutableLiveData<ArrayList<UserResponse>?>()
    val isLoading = MutableLiveData<Boolean>()
    val isDataFailed = MutableLiveData<Boolean>()
    var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
    private const val TAG = "UserRepo"
    suspend fun getListUser() {
        coroutineScope.launch {
            isLoading.value = true
            val getUserDeferred = ApiConfig.getApiService().getUserListAsync()
            try {
                isLoading.value = false
                isDataFailed.value = false
                user.postValue(getUserDeferred)
            } catch (e: Exception) {
                isLoading.value = false
                isDataFailed.value = true
                Log.e(TAG, "onFailure: ${e.message.toString()}")
            }
        }
    }
}