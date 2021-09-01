package com.geektech.qrcodecreater.utils

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Matrix
import android.net.Uri
import android.provider.MediaStore.Images
import java.io.FileNotFoundException
import java.io.IOException
import java.io.OutputStream

class CapturePhotoUtils {

    companion object {
        fun insertImage(cr: ContentResolver, source: Bitmap?, title: String, desc: String): String {

            val values = ContentValues()
            values.put(Images.Media.TITLE, title)
            values.put(Images.Media.DISPLAY_NAME, title)
            values.put(Images.Media.DESCRIPTION, desc)
            values.put(Images.Media.MIME_TYPE, "image/jpeg")
            values.put(Images.Media.DATE_ADDED, System.currentTimeMillis())
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                values.put(Images.Media.DATE_TAKEN, System.currentTimeMillis())
            }

            var uri: Uri? = null
            var stringUri: String? = null

            try {
                uri = cr.insert(Images.Media.EXTERNAL_CONTENT_URI, values)
                if (source != null) {
                    val imageOut: OutputStream? = cr.openOutputStream(uri!!)
                    imageOut.use {
                        source.compress(Bitmap.CompressFormat.JPEG, 50, imageOut)
                    }
                    val id: Long = ContentUris.parseId(uri)
                    // Wait until MINI_KIND thumbnail is generated.
                    val miniThumb: Bitmap = Images.Thumbnails.getThumbnail(cr, id, Images.Thumbnails.MINI_KIND, null)
                    // This is for backward compatibility.
                    storeThumbnail(cr, miniThumb, id, 50f, 50f, Images.Thumbnails.MICRO_KIND)
                } else {
                    cr.delete(uri!!, null, null)
                }

            } catch (e: Exception) {
                if (uri != null) {
                    cr.delete(uri, null, null)
                }
            }
            if (uri != null) {
                stringUri = uri.toString()
            }
            return stringUri!!
        }

        private fun storeThumbnail(
            cr: ContentResolver,
            source: Bitmap,
            id: Long,
            width: Float,
            height: Float,
            kind: Int
        ): Bitmap? {

            // create the matrix to scale it
            val matrix = Matrix()
            val scaleX = width / source.width
            val scaleY = height / source.height
            matrix.setScale(scaleX, scaleY)
            val thumb = Bitmap.createBitmap(
                source, 0, 0,
                source.width,
                source.height, matrix,
                true
            )
            val values = ContentValues(4)
            values.put(Images.Thumbnails.KIND, kind)
            values.put(Images.Thumbnails.IMAGE_ID, id.toInt())
            values.put(Images.Thumbnails.HEIGHT, thumb.height)
            values.put(Images.Thumbnails.WIDTH, thumb.width)
            val url = cr.insert(Images.Thumbnails.EXTERNAL_CONTENT_URI, values)
            return try {
                val thumbOut = cr.openOutputStream(url!!)
                thumb.compress(Bitmap.CompressFormat.JPEG, 100, thumbOut)
                thumbOut!!.close()
                thumb
            } catch (ex: FileNotFoundException) {
                null
            } catch (ex: IOException) {
                null
            }
        }
    }
}