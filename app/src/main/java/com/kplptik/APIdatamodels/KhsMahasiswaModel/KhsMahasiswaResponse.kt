package com.kplptik.APIdatamodels.KhsMahasiswaModel

import com.google.gson.annotations.SerializedName

data class KhsMahasiswaResponse(

    @field:SerializedName("datetime")
    val datetime: String? = null,

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("detail")
    val detail: List<DetailItem?>? = null
)

data class DetailItem(

    @field:SerializedName("jumlah_sks")
    val jumlahSks: String? = null,

    @field:SerializedName("semester")
    val semester: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("ips")
    val ips: String? = null,

    @field:SerializedName("id_mhs")
    val idMhs: Int? = null
)
