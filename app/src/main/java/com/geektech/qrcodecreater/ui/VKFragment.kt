package com.geektech.qrcodecreater.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.geektech.qrcodecreater.databinding.FragmentVKBinding
import com.geektech.qrcodecreater.ui.base.BaseFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder

class VKFragment : BaseFragment<FragmentVKBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentVKBinding =
        FragmentVKBinding.inflate(inflater, container, false)

    override fun setupUI() {
        vb.imageScanAction.setOnClickListener {
            val encoder = BarcodeEncoder()
            try {
                val bitmap = encoder.encodeBitmap(
                    "https://vk.com/" + vb.etUrl.text.toString(),
                    BarcodeFormat.QR_CODE, 250, 250
                )
                vb.imageQrCode.setImageBitmap(bitmap)
            } catch (e: WriterException) {
                e.printStackTrace()
            }
        }
    }
}