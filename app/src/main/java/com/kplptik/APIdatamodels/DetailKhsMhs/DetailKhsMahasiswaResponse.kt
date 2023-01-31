package com.kplptik.APIdatamodels.DetailKhsMhs

import com.google.gson.annotations.SerializedName

data class DetailKhsMahasiswaResponse(

    @field:SerializedName("datetime")
    val datetime: String? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("detail")
    val detail: List<DetailItem?>? = null
)

data class DetailItem(

    @field:SerializedName("nilai_angka")
    val nilaiAngka: String? = null,

    @field:SerializedName("nilai_huruf")
    val nilaiHuruf: String? = null,

    @field:SerializedName("nama_mk")
    val namaMk: String? = null,

    @field:SerializedName("sks")
    val sks: String? = null,
)
