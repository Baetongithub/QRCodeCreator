package com.qrcode.creator.utils

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.widget.ImageView
import androidx.core.content.FileProvider
import com.qrcode.creator.BuildConfig
import com.qrcode.creator.R
import com.qrcode.creator.extensions.toast
import java.io.File
import java.io.FileOutputStream

object Share {

    fun shareImage(context: Context?, imageView: ImageView, name: String) {
        try {
            val file =
                File(
                    context?.applicationContext?.externalCacheDir,
                    File.separator + name
                )
            val fileOutputStream = FileOutputStream(file)
            val bitmap: Bitmap = (GetDrawable.drawable(imageView) as BitmapDrawable).bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
            fileOutputStream.flush()
            fileOutputStream.close()
            file.setReadable(true, false)
            val intent = Intent(Intent.ACTION_SEND)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            val photoUri: Uri =
                FileProvider.getUriForFile(
                    context?.applicationContext!!,
                    BuildConfig.APPLICATION_ID + ".provider",
                    file
                )
            intent.putExtra(Intent.EXTRA_STREAM, photoUri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.type = "image/jpg"

            context.startActivity(Intent.createChooser(intent, context.getString(R.string.share_qr_code_via)))
        } catch (e: Exception) {
            context?.toast(context.getString(R.string.something_went_wrong) + " ${e.localizedMessage}")
        }
    }

    fun shareText(context: Context?, text: String) {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(Intent.EXTRA_TEXT, text)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.type = "text/plain"

        context?.startActivity(Intent.createChooser(intent, context.getString(R.string.share_text_via)))
    }
}