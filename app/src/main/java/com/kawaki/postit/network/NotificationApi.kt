package com.kawaki.postit.network

import com.kawaki.postit.model.PushNotification
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface NotificationAPI {

    @POST("fcm/send")
    suspend fun postNotification(
        @HeaderMap headers: Map<String, String>,
        @Body notification: PushNotification
    ): Response<ResponseBody>
}