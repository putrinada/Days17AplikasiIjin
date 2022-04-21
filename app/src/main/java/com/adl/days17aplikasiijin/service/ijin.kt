package com.adl.days17aplikasiijin.service

import com.adl.days17aplikasiijin.model.GetIJinResponse
import com.adl.days17aplikasiijin.model.ResponsePostData

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ijin {
//ambil get
    @Headers("X-Api-Key:36BEE1EAA95D4DC0317F64F0B9B811E2")
    @GET("api/ijin/all")
    fun getAllIjin(): Call<GetIJinResponse>
//post data
   @FormUrlEncoded
    @Headers("X-Api-Key:36BEE1EAA95D4DC0317F64F0B9B811E2")
    @POST("api/ijin/add")
    fun addData(@Field("kategori") kategori:String,@Field("dari")dari:String,
    @Field("sampai") sampai:String,@Field("perihal") perihal:String,
                @Field("keterangan") keterangan:String): Call<ResponsePostData>


    @Multipart
    @Headers("X-Api-Key:36BEE1EAA95D4DC0317F64F0B9B811E2")
    @POST("api/ijin/add")
    fun addDataAndImage(@Part("kategori") kategori: RequestBody, @Part("dari")dari:RequestBody,
                        @Part("sampai") sampai:RequestBody, @Part("perihal") perihal:RequestBody,
                        @Part("keterangan") keterangan:RequestBody,
                        @Part file:MultipartBody.Part): Call<ResponsePostData>

}