package com.geektech.qrcodecreater.utils

import android.app.Activity
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.print.PrintHelper
import com.geektech.qrcodecreater.R
import com.geektech.qrcodecreater.extensions.toast

object PrintHelp {
    fun doPhotoPrint(activity: Activity?, imageView: ImageView) {
        activity?.also { context ->
            PrintHelper(context).apply {
                scaleMode = PrintHelper.SCALE_MODE_FIT
            }.also { printHelper ->
                if (imageView.drawable != null) {
                    val bitmap = (GetDrawable.drawable(imageView) as BitmapDrawable).bitmap
                    printHelper.printBitmap("droids.jpg - test print", bitmap)
                } else activity.toast(context.getString(R.string.code_not_found))
            }
        }
    }
}