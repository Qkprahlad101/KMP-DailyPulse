package com.example.kmp_daily_pulse

import android.content.res.Resources
import android.os.Build
import android.util.Log
import kotlin.math.roundToInt

actual class Platform() {
    actual val osName: String
        get() = "Android"
    actual val osVersion: String
        get() = "${Build.VERSION.SDK_INT}"
    actual val deviceModel: String
        get() = "${Build.MANUFACTURER} ${Build.MODEL}"
    actual val density: Int
        get() = Resources.getSystem().displayMetrics.density.roundToInt()

    actual fun logSystemInfo() {
        Log.d("DailyPulse", "OS Name: $osName, OS Version: $osVersion, Device Model: $deviceModel, Density: $density")
    }

}