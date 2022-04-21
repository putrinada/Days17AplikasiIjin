package com.adl.days17aplikasiijin.background

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.adl.days17aplikasiijin.R
import com.adl.days17aplikasiijin.model.TrackingPostResponse
import com.adl.days17aplikasiijin.service.TrackingConfig
import com.adl.days17aplikasiijin.untility.LocationHelper
import com.adl.days17aplikasiijin.untility.MyLocationListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class LocationService : Service() {

//TRAKING LOCATION

    companion object{
        var mLocation: Location?=null
        var isServiceStarted = false
    }
//nyalain Notification
    private val NOTIFICATION_CHANNEL_ID = "notifikasi channel"
    private val TAG = "LOCATION_SERVICE"

    override fun onCreate() {
        super.onCreate()
        isServiceStarted = true
        val builder : NotificationCompat.Builder = NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID)
            .setOngoing(false)
            .setSmallIcon(R.drawable.ic_launcher_background)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val notificationManager: NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val notificationChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID,NOTIFICATION_CHANNEL_ID,NotificationManager.IMPORTANCE_LOW)

            notificationChannel.description = NOTIFICATION_CHANNEL_ID
            notificationChannel.setSound(null,null)
            notificationManager.createNotificationChannel(notificationChannel)
            startForeground(1,builder.build())

        }
    }
////Tracking
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        LocationHelper().startListeningUserLocation(this,object :MyLocationListener {
            override fun `onLocationChanged`(location: Location) {
                if (isServiceStarted) {
                    mLocation = location
                    mLocation?.let {
                        //Log.d(TAG,"SERVICE SEDANG BERJALAN LOKASINYA ADALAH ${it?.longitude} -  ${it?.latitude} ")
                        val dateNow = Calendar.getInstance()
                        val formatDate = "yyyy-MM-dd hh:mm:ss"
                        val sdf = SimpleDateFormat(formatDate, Locale.US)

                        var time = sdf.format(dateNow.time)

                        TrackingConfig().getTracking().addDataTracking(
                            "uti", it.latitude.toString(),
                            it.longitude.toString(), time
                        ).enqueue(object :
                            Callback<TrackingPostResponse> {
                            override fun onResponse(
                                call: Call<TrackingPostResponse>,
                                response: Response<TrackingPostResponse>
                            ) {
                                Log.d("Response", response.body().toString())
                            }

                            override fun onFailure(call: Call<TrackingPostResponse>, t: Throwable) {
                                Log.e("error request", t.localizedMessage.toString())
                            }

                        })

                    }
                }
            }

        })
        return START_STICKY
    }
    override fun onBind(intent: Intent?): IBinder? {

        return null
    }
    override fun onDestroy() {
        super.onDestroy()
        isServiceStarted=false
    }
}

