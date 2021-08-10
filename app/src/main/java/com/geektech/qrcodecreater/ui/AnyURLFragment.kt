package com.geektech.qrcodecreater.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.navigation.Navigation
import com.geektech.qrcodecreater.R
import com.geektech.qrcodecreater.databinding.FragmentAnyURLBinding
import com.geektech.qrcodecreater.ui.base.BaseFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder

class AnyURLFragment : BaseFragment<FragmentAnyURLBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAnyURLBinding =
        FragmentAnyURLBinding.inflate(inflater, container, false)

    override fun setupUI() {
        vb.imageScanAction.setOnClickListener {
            generateCode()
        }

        vb.etUrl.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    generateCode()
                }
            }
            false
        }

        vb.tvBackHome.setOnClickListener {
            navigateUp()
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
            e.printStackTrace()
        }
    }
}