package com.adl.days17aplikasiijin.service

import com.adl.days17aplikasiijin.model.TrackingPostResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface tracking {
    @FormUrlEncoded
    @Headers("X-Api-Key:36BEE1EAA95D4DC0317F64F0B9B811E2")
    @POST("api/tracking/add")
    fun addDataTracking (
        @Field("id_user") id_user: String, @Field("latitude") latitude: String,
        @Field("longitude") longitude: String,
        @Field("timestamp")  time: String
    ): Call<TrackingPostResponse>
}