package com.geektech.qrcodecreator.ui.fragments

import android.view.inputmethod.EditorInfo
import com.geektech.qrcodecreator.R
import com.geektech.qrcodecreator.databinding.FragmentFacebookBinding
import com.geektech.qrcodecreator.ui.base.BaseFragment
import com.geektech.qrcodecreator.utils.GenerateCode
import com.geektech.qrcodecreator.utils.PrintHelp
import com.geektech.qrcodecreator.utils.SavePhotoToStorage
import com.geektech.qrcodecreator.utils.Share

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