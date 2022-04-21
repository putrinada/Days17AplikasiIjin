package com.adl.days17aplikasiijin.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetIJinResponse(
	val total: Int? = null,
	val data: Data? = null,
	val message: String? = null,
	val status: Boolean? = null
) : Parcelable

@Parcelize
data class Data(
	val ijin: List<IjinItem?>? = null
) : Parcelable

@Parcelize
data class IjinItem(
	val keterangan: String? = null,
	val lampiran: String? = null,
	val kategori: String? = null,
	val dari: String? = null,
	val id: String? = null,
	val perihal: String? = null,
	val sampai: String? = null
) : Parcelable
