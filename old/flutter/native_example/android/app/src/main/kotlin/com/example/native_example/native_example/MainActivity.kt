package com.example.native_example.native_example

import android.os.Build
import android.util.Base64
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL)
            .setMethodCallHandler { call, result ->
                if (call.method == "getDeviceInfo") {
                    val deviceInfo = getDeviceInfo()
                    result.success(deviceInfo)
                }
            }

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL2)
            .setMethodCallHandler { call, result ->
                if (call.method == "getEncrypto") {
                    val data = call.arguments.toString().toByteArray()
                    val encoded = Base64.encodeToString(data, Base64.DEFAULT)
                    result.success(encoded)
                }
            }
    }

    private fun getDeviceInfo() =
        listOf(Build.DEVICE, Build.BRAND, Build.MODEL).joinToString("\n")

    companion object {
        private const val CHANNEL = "com.flutter.dev/info";
        private const val CHANNEL2 = "com.flutter.dev/encrpyto";
    }
}
