package com.kawaki.postit.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kawaki.postit.model.PushNotification
import com.kawaki.postit.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotificationViewModel: ViewModel() {

    fun sendNotification(
        notification: PushNotification,
        headers: HashMap<String, String>,
        onError: (String) -> Unit,
        onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.api.postNotification(headers, notification)
                if(response.isSuccessful) {
                    onSuccess.invoke()
                } else {
                    onError(response.errorBody().toString())
                    Log.d("ERRORR", "sendNotification: ${response.errorBody().toString()}")
                }
            } catch(e: Exception) {
                onError(e.message.toString())
            }
        }
    }
}