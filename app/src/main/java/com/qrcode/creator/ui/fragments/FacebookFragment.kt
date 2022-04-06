package com.qrcode.creator.ui.fragments

import android.view.inputmethod.EditorInfo
import com.qrcode.creator.R
import com.qrcode.creator.databinding.FragmentFacebookBinding
import com.qrcode.creator.ui.base.BaseFragment
import com.qrcode.creator.utils.GenerateCode
import com.qrcode.creator.utils.PrintHelp
import com.qrcode.creator.utils.SavePhotoToStorage
import com.qrcode.creator.utils.Share

class FacebookFragment : BaseFragment<FragmentFacebookBinding>(FragmentFacebookBinding::inflate) {

    override fun setupUI() {
        initClicks()
    }

    private fun initClicks() {
        vb.imageMakeQrCodeAction.setOnClickListener { generateCodeAndHideKeyboard() }

        vb.etUrl.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_GO -> generateCodeAndHideKeyboard()
            }
            false
        }

        vb.buttonShare.setOnClickListener { Share.shareImage(context, vb.imageQrCode, getString(R.string.facebook)) }

        vb.buttonPrint.setOnClickListener { PrintHelp.doPhotoPrint(activity, vb.imageQrCode) }

        vb.buttonSaveToGallery.setOnClickListener { SavePhotoToStorage.save(context, vb.imageQrCode) }

        vb.imageBack.setOnClickListener { navigateUp() }
    }

    private fun generateCodeAndHideKeyboard() {
        GenerateCode.generate(context, "https://facebook.com/${vb.etUrl.text.toString().trim()}", vb.imageQrCode)
        hideKeyBoard()
    }
}