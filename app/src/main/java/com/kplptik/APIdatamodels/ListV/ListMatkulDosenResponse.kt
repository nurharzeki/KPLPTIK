package com.kplptik.APIdatamodels.ListV

import com.google.gson.annotations.SerializedName

data class ListMatkulDosenResponse(

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class DataItem(

    @field:SerializedName("id_matkul")
    val idMatkul: Int? = null,

    @field:SerializedName("nama_mk")
    val namaMk: String? = null,

    @field:SerializedName("reg_mk")
    val regMk: String? = null,
)
