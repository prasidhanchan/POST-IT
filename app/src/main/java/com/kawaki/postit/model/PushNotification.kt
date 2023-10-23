package com.kawaki.postit.model

data class PushNotification(
    val data: HashMap<String, String>,
    val to: String
)

