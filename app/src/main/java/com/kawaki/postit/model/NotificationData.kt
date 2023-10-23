package com.kawaki.postit.model

/** for 5 key and value */
data class NotificationData(
    val key1: String,
    val value1: String,
    val key2: String,
    val value2: String,
    val key3: String,
    val value3: String,
    val key4: String,
    val value4: String,
    val key5: String,
    val value5: String
) {
    fun convertToMap(): HashMap<String, String> {
        return hashMapOf(
            this.key1 to  this.value1,
            this.key2 to  this.value2,
            this.key3 to  this.value3,
            this.key4 to  this.value4,
            this.key5 to  this.value5
        )
    }
}