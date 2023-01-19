package com.kplptik.APIdatamodels.MatkulDiampuModel

import com.google.gson.annotations.SerializedName

data class DetailMatkulDiampuResponse(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class Data(

	@field:SerializedName("reg_mk")
	val regMk: String? = null,

	@field:SerializedName("id_matkul")
	val idMatkul: Int? = null,

	@field:SerializedName("nama_dosen")
	val namaDosen: String? = null,

	@field:SerializedName("nama_mk")
	val namaMk: String? = null,

	@field:SerializedName("waktu")
	val waktu: String? = null,

	@field:SerializedName("sks")
	val sks: String? = null,

	@field:SerializedName("kode_ruang")
	val kodeRuang: String? = null
)
