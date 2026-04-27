package com.elf.square.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Helper class to check the network connectivity status of the device.
 */
class NetworkHelper(private val context: Context) {
    /**
     * Checks if the device is currently connected to the internet.
     * Returns true if connected via WIFI, Cellular, or Ethernet.
     */
    fun isNetworkConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        // Get the active network
        val network = connectivityManager.activeNetwork ?: return false
        // Get capabilities of the active network
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            // Check for common transport types
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}
