package com.geektech.qrcodecreater.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.geektech.qrcodecreater.databinding.FragmentYouTubeBinding
import com.geektech.qrcodecreater.extensions.toast
import com.geektech.qrcodecreater.ui.base.BaseFragment
import com.geektech.qrcodecreater.utils.GenerateCode
import com.geektech.qrcodecreater.utils.PrintHelp
import com.geektech.qrcodecreater.utils.ShareImage

class YouTubeFragment : BaseFragment<FragmentYouTubeBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentYouTubeBinding =
        FragmentYouTubeBinding.inflate(inflater, container, false)

    override fun setupUI() {
        vb.imageScanAction.setOnClickListener {
            GenerateCode.generate(context, vb.etUrl.text.toString(), vb.imageQrCode)
        }

        vb.buttonShare.setOnClickListener { ShareImage.share(context, vb.imageQrCode) }

        vb.buttonPrint.setOnClickListener { PrintHelp.doPhotoPrint(activity, vb.imageQrCode) }

        vb.buttonSaveToGallery.setOnClickListener { toast("Feature is going to be developed asap") }
    }
}