package com.kplptik.APIdatamodels.authentication

import com.google.gson.annotations.SerializedName

data class LogoutResponse(

    @field:SerializedName("message")
    val message: String? = null
)
