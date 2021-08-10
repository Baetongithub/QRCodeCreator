package com.geektech.qrcodecreater.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.geektech.qrcodecreater.databinding.FragmentYouTubeBinding
import com.geektech.qrcodecreater.ui.base.BaseFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder

class YouTubeFragment : BaseFragment<FragmentYouTubeBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentYouTubeBinding =
        FragmentYouTubeBinding.inflate(inflater, container, false)

    override fun setupUI() {
        vb.imageScanAction.setOnClickListener {
            val encoder = BarcodeEncoder()
            try {
                val bitmap = encoder.encodeBitmap(
                    vb.etUrl.text.toString(),
                    BarcodeFormat.QR_CODE, 250, 250
                )
                vb.imageQrCode.setImageBitmap(bitmap)
            } catch (e: WriterException) {
                e.printStackTrace()
            } }
    }
}