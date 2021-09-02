package com.geektech.qrcodecreater.utils

import android.content.Context
import android.widget.ImageView
import com.geektech.qrcodecreater.R
import com.geektech.qrcodecreater.extensions.toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder

object GenerateCode {
    fun generate(context: Context?, txt: String, imageView: ImageView) {
        val encoder = BarcodeEncoder()
        try {
            val bitmap = encoder.encodeBitmap(
                txt,
                BarcodeFormat.QR_CODE, 250, 250
            )
            imageView.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            context?.toast(context.getString(R.string.something_went_wrong) + " $e")
        }
    }
}