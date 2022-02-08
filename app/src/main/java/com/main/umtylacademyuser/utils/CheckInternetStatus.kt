package com.main.umtylacademyuser.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log

class CheckInternetStatus {
//    private var mContext: Context? = null
//    private var is_internet_connected = false
//    private var mConnectivityManager: ConnectivityManager? = null

    // create a method to check internet connectivity
//    fun isInternetConnected(): Boolean {
//        try {
//            mConnectivityManager =
//                mContext!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//            val mNetworkInfo = mConnectivityManager!!.activeNetworkInfo
//            is_internet_connected =
//                mNetworkInfo != null && mNetworkInfo.isAvailable && mNetworkInfo.isConnected
//            return is_internet_connected
//        } catch (ex: Exception) {
//            Log.e("error", ex.message.toString())
//        }
//        return is_internet_connected
//    }


    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }
}