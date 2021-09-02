package com.geektech.qrcodecreater.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.geektech.qrcodecreater.databinding.FragmentInstagramBinding
import com.geektech.qrcodecreater.extensions.toast
import com.geektech.qrcodecreater.ui.base.BaseFragment
import com.geektech.qrcodecreater.utils.GenerateCode
import com.geektech.qrcodecreater.utils.PrintHelp
import com.geektech.qrcodecreater.utils.ShareImage

class InstagramFragment : BaseFragment<FragmentInstagramBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentInstagramBinding =
        FragmentInstagramBinding.inflate(inflater, container, false)

    override fun setupUI() {
        initClicks()
    }

    private fun initClicks() {
        vb.imageScanAction.setOnClickListener {
            GenerateCode.generate(context, "https://instagram.com/" + vb.etUrl.text.toString(), vb.imageQrCode)
        }

        vb.buttonShare.setOnClickListener { ShareImage.share(context, vb.imageQrCode) }

        vb.buttonPrint.setOnClickListener { PrintHelp.doPhotoPrint(activity, vb.imageQrCode) }

        vb.buttonSaveToGallery.setOnClickListener { toast("Feature is going to be developed asap") }
    }
}