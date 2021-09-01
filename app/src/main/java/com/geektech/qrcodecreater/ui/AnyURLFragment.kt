package com.geektech.qrcodecreater.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.FileProvider
import com.geektech.qrcodecreater.BuildConfig
import com.geektech.qrcodecreater.R
import com.geektech.qrcodecreater.databinding.FragmentAnyURLBinding
import com.geektech.qrcodecreater.extensions.toast
import com.geektech.qrcodecreater.ui.base.BaseFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.io.File
import java.io.FileOutputStream

class AnyURLFragment : BaseFragment<FragmentAnyURLBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAnyURLBinding =
        FragmentAnyURLBinding.inflate(inflater, container, false)

    override fun setupUI() {
        initClicks()
    }

    @SuppressLint("ResourceType")
    private fun initClicks() {
        vb.imageScanAction.setOnClickListener { generateCode() }

        vb.etUrl.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> generateCode()
            }
            false
        }

        vb.buttonShare.setOnClickListener {

            val drawable: Drawable = vb.imageQrCode.drawable
            val bitmap: Bitmap = (drawable as BitmapDrawable).bitmap
            try {
                val file =
                    File(
                        requireActivity().applicationContext.externalCacheDir,
                        File.separator + "image file youtube"
                    )
                val fileOutputStream = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                fileOutputStream.flush()
                fileOutputStream.close()
                file.setReadable(true, false)
                val intent = Intent(Intent.ACTION_SEND)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                val photoUri: Uri =
                    FileProvider.getUriForFile(
                        requireContext().applicationContext,
                        BuildConfig.APPLICATION_ID + ".provider",
                        file
                    )
                intent.putExtra(Intent.EXTRA_STREAM, photoUri)
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                intent.type = "image/jpg"

                startActivity(Intent.createChooser(intent, "Share QR code via"))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        vb.buttonSaveToGallery.setOnClickListener {

            requireActivity().toast(vb.buttonSaveToGallery.text.toString())


//                val bitmap: Bitmap = (drawable as BitmapDrawable).bitmap
//
//                CapturePhotoUtils.insertImage(
//                    requireActivity().contentResolver,
//                    bitmap,
//                    "Title any qr codes",
//                    "Title any qr codes"
//                )
        }
    }

    private fun generateCode() {
        val encoder = BarcodeEncoder()
        try {
            val bitmap = encoder.encodeBitmap(
                vb.etUrl.text.toString(),
                BarcodeFormat.QR_CODE, 250, 250
            )
            vb.imageQrCode.setImageBitmap(bitmap)
        } catch (e: WriterException) {
            requireActivity().toast(getString(R.string.something_went_wrong) + " $e")
        }
    }
}