package com.geektech.qrcodecreater.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.geektech.qrcodecreater.databinding.FragmentAnyURLBinding
import com.geektech.qrcodecreater.ui.base.BaseFragment
import com.geektech.qrcodecreater.utils.*

class AnyURLFragment : BaseFragment<FragmentAnyURLBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAnyURLBinding =
        FragmentAnyURLBinding.inflate(inflater, container, false)

    override fun setupUI() {
        initClicks()
    }

    private fun initClicks() {
        vb.imageScanAction.setOnClickListener {
            HideKeyboard.hide(activity)
            GenerateCode.generate(context, vb.etUrl.text.toString(), vb.imageQrCode)
        }

        vb.etUrl.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE ->
                    GenerateCode.generate(context, vb.etUrl.text.toString(), vb.imageQrCode)
            }
            false
        }

        vb.buttonShare.setOnClickListener { ShareImage.share(context, vb.imageQrCode, "Other QR codes") }

        vb.buttonPrint.setOnClickListener { PrintHelp.doPhotoPrint(activity, vb.imageQrCode) }

        vb.buttonSaveToGallery.setOnClickListener { SavePhotoToStorage.save(context, vb.imageQrCode) }

        vb.imageBack.setOnClickListener { navigateUp() }
    }
}