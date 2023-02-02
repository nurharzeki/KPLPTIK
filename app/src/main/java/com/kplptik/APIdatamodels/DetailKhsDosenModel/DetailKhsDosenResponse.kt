package com.kplptik.APIdatamodels.DetailKhsDosenModel

import com.google.gson.annotations.SerializedName

data class DetailKhsDosenResponse(

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("detail_khs")
    val detailKhs: List<DetailKhsItem?>? = null
)

data class DetailKhsItem(

    @field:SerializedName("nilai_angka")
    val nilaiAngka: String? = null,

    @field:SerializedName("nilai_huruf")
    val nilaiHuruf: String? = null,

    @field:SerializedName("reg_mk")
    val regMk: String? = null,

    @field:SerializedName("nama_mk")
    val namaMk: String? = null,

    @field:SerializedName("sks")
    val sks: Int? = null,

    @field:SerializedName("id")
    val id: Int? = null
)
