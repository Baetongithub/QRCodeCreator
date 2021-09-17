package com.geektech.qrcodecreator.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.geektech.qrcodecreator.R
import com.geektech.qrcodecreator.databinding.FragmentInstagramBinding
import com.geektech.qrcodecreator.ui.base.BaseFragment
import com.geektech.qrcodecreator.utils.GenerateCode
import com.geektech.qrcodecreator.utils.PrintHelp
import com.geektech.qrcodecreator.utils.SavePhotoToStorage
import com.geektech.qrcodecreator.utils.ShareImage

class InstagramFragment : BaseFragment<FragmentInstagramBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentInstagramBinding =
        FragmentInstagramBinding.inflate(inflater, container, false)

    override fun setupUI() {
        initClicks()
    }

    private fun initClicks() {
        vb.imageMakeQrCodeAction.setOnClickListener {
            GenerateCode.generate(context, "https://instagram.com/" + vb.etUrl.text.toString().trim(), vb.imageQrCode)
            hideKeyBoard()
        }

        vb.etUrl.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_GO -> {
                    GenerateCode.generate(context, vb.etUrl.text.toString().trim(), vb.imageQrCode)
                    hideKeyBoard()
                }
            }
            false
        }

        vb.buttonShare.setOnClickListener { ShareImage.share(context, vb.imageQrCode, getString(R.string.instagram)) }

        vb.buttonPrint.setOnClickListener { PrintHelp.doPhotoPrint(activity, vb.imageQrCode) }

        vb.buttonSaveToGallery.setOnClickListener { SavePhotoToStorage.save(context, vb.imageQrCode) }

        vb.imageBack.setOnClickListener { navigateUp() }
    }
}