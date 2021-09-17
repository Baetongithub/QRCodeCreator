package com.geektech.qrcodecreator.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.geektech.qrcodecreator.R
import com.geektech.qrcodecreator.databinding.FragmentFacebookBinding
import com.geektech.qrcodecreator.ui.base.BaseFragment
import com.geektech.qrcodecreator.utils.*

class FacebookFragment : BaseFragment<FragmentFacebookBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentFacebookBinding =
        FragmentFacebookBinding.inflate(inflater, container, false)

    override fun setupUI() {
        initClicks()
    }

    private fun initClicks() {
        vb.imageMakeQrCodeAction.setOnClickListener {
            GenerateCode.generate(context, "https://facebook.com/" + vb.etUrl.text.toString().trim(), vb.imageQrCode)
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

        vb.buttonShare.setOnClickListener {
            ShareImage.share(
                context,
                vb.imageQrCode,
                getString(R.string.facebook)
            )
        }

        vb.buttonPrint.setOnClickListener { PrintHelp.doPhotoPrint(activity, vb.imageQrCode) }

        vb.buttonSaveToGallery.setOnClickListener { SavePhotoToStorage.save(context, vb.imageQrCode) }

        vb.imageBack.setOnClickListener { navigateUp() }
    }
}