package com.geektech.qrcodecreater.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.geektech.qrcodecreater.databinding.FragmentInstagramBinding
import com.geektech.qrcodecreater.ui.base.BaseFragment
import com.geektech.qrcodecreater.utils.*

class InstagramFragment : BaseFragment<FragmentInstagramBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentInstagramBinding =
        FragmentInstagramBinding.inflate(inflater, container, false)

    override fun setupUI() {
        initClicks()
    }

    private fun initClicks() {
        vb.imageScanAction.setOnClickListener {
            HideKeyboard.hide(activity)
            GenerateCode.generate(context, "https://instagram.com/" + vb.etUrl.text.toString(), vb.imageQrCode)
        }

        vb.buttonShare.setOnClickListener { ShareImage.share(context, vb.imageQrCode, "Instagram") }

        vb.buttonPrint.setOnClickListener { PrintHelp.doPhotoPrint(activity, vb.imageQrCode) }

        vb.buttonSaveToGallery.setOnClickListener { SavePhotoToStorage.save(context, vb.imageQrCode) }

        vb.imageBack.setOnClickListener { navigateUp() }
    }
}