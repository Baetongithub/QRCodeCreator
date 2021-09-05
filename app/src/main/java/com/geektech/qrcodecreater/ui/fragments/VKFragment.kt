package com.geektech.qrcodecreater.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.geektech.qrcodecreater.databinding.FragmentVKBinding
import com.geektech.qrcodecreater.ui.base.BaseFragment
import com.geektech.qrcodecreater.utils.*

class VKFragment : BaseFragment<FragmentVKBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentVKBinding =
        FragmentVKBinding.inflate(inflater, container, false)

    override fun setupUI() {
        vb.imageScanAction.setOnClickListener {
            HideKeyboard.hide(activity)
            GenerateCode.generate(context, "https://vk.com/" + vb.etUrl.text.toString(), vb.imageQrCode)
        }

        vb.buttonShare.setOnClickListener { ShareImage.share(context, vb.imageQrCode, "VK") }

        vb.buttonPrint.setOnClickListener { PrintHelp.doPhotoPrint(activity, vb.imageQrCode) }

        vb.buttonSaveToGallery.setOnClickListener { SavePhotoToStorage.save(context, vb.imageQrCode) }

        vb.imageBack.setOnClickListener { navigateUp() }
    }
}