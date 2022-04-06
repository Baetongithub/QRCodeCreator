package com.geektech.qrcodecreator.utils

import android.app.Activity
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LifecycleOwner
import com.google.zxing.integration.android.IntentIntegrator

class ResultLauncher(
    private val activity: Activity?,
    lifecycleOwner: LifecycleOwner,
    activityResultRegistry: ActivityResultRegistry,
    callback: (resultCode: ActivityResult) -> Unit,
) {

    private val registerActivityResult =
        activityResultRegistry.register(
            REGISTRY_KEY,
            lifecycleOwner,
            ActivityResultContracts.StartActivityForResult(),
            callback
        )

    fun scanQRCodeButton() {
        val integrator = IntentIntegrator(activity)
        registerActivityResult.launch(integrator.createScanIntent())
    }

    private companion object {
        private const val REGISTRY_KEY = "intent_settings"
    }
}