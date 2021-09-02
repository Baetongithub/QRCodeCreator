package com.geektech.qrcodecreater.utils

import android.app.Activity
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.print.PrintHelper

object PrintHelp {
    fun doPhotoPrint(activity: Activity?, imageView: ImageView) {
        activity?.also { context ->
            PrintHelper(context).apply {
                scaleMode = PrintHelper.SCALE_MODE_FIT
            }.also { printHelper ->
                val bitmap = (GetDrawable.drawable(imageView) as BitmapDrawable).bitmap
                printHelper.printBitmap("droids.jpg - test print", bitmap)
            }
        }
    }
}