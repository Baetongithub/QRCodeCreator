package com.qrcode.creator.ui.fragments

import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.qrcode.creator.R
import com.qrcode.creator.databinding.FragmentInstagramBinding
import com.qrcode.creator.ui.base.BaseFragment
import com.qrcode.creator.utils.GenerateCode
import com.qrcode.creator.utils.PrintHelp
import com.qrcode.creator.utils.SavePhotoToStorage
import com.qrcode.creator.utils.Share

class InstagramFragment : BaseFragment<FragmentInstagramBinding>(FragmentInstagramBinding::inflate) {

    override fun setupUI() {
        initClicks()
    }

    private fun initClicks() {
        vb.imageMakeQrCodeAction.setOnClickListener { generateCodeAndHideKeyboard(vb.tvQrCodeValue) }

        vb.etUrl.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_GO -> generateCodeAndHideKeyboard(vb.tvQrCodeValue)
            }
            false
        }

        vb.buttonShare.setOnClickListener { Share.shareImage(context, vb.imageQrCode, getString(R.string.instagram)) }

        vb.buttonPrint.setOnClickListener { PrintHelp.doPhotoPrint(activity, vb.imageQrCode) }

        vb.buttonSaveToGallery.setOnClickListener { SavePhotoToStorage.save(context, vb.imageQrCode) }

        vb.imageBack.setOnClickListener { navigateUp() }
    }

    private fun generateCodeAndHideKeyboard(tv: TextView) {
        GenerateCode.generate(context, "https://instagram.com/${vb.etUrl.text.toString().trim()}", vb.imageQrCode)
        tv.text = ("https://instagram.com/${vb.etUrl.text.toString().trim()}")
        hideKeyBoard()
    }
}