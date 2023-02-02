package com.kplptik.APIdatamodels.KhsDosenModel

import com.google.gson.annotations.SerializedName

data class KhsDosenResponse(

    @field:SerializedName("count")
    val count: Int? = null,

    @field:SerializedName("detail")
    val detail: List<DetailItem?>? = null
)

data class DetailItem(

    @field:SerializedName("jumlah_sks")
    val jumlahSks: String? = null,

    @field:SerializedName("nim")
    val nim: String? = null,

    @field:SerializedName("nama_mahasiswa")
    val namaMahasiswa: String? = null,

    @field:SerializedName("semester")
    val semester: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("ips")
    val ips: String? = null,

    @field:SerializedName("id_mhs")
    val idMhs: Int? = null
)
