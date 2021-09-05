package com.geektech.qrcodecreater.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.geektech.qrcodecreater.databinding.FragmentTGBinding
import com.geektech.qrcodecreater.ui.base.BaseFragment
import com.geektech.qrcodecreater.utils.*

class TGFragment : BaseFragment<FragmentTGBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentTGBinding =
        FragmentTGBinding.inflate(inflater, container, false)

    override fun setupUI() {
        vb.imageScanAction.setOnClickListener {
            HideKeyboard.hide(activity)
            GenerateCode.generate(context, "https://t.me/" + vb.etUrl.text.toString(), vb.imageQrCode)
        }

        vb.buttonShare.setOnClickListener { ShareImage.share(context, vb.imageQrCode, "Telegram") }

        vb.buttonPrint.setOnClickListener { PrintHelp.doPhotoPrint(activity, vb.imageQrCode) }

        vb.buttonSaveToGallery.setOnClickListener { SavePhotoToStorage.save(context, vb.imageQrCode) }

        vb.imageBack.setOnClickListener { navigateUp() }
    }
}