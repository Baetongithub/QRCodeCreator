package com.qrcode.creator.ui.fragments

import android.view.inputmethod.EditorInfo
import com.qrcode.creator.R
import com.qrcode.creator.databinding.FragmentYouTubeBinding
import com.qrcode.creator.ui.base.BaseFragment
import com.qrcode.creator.utils.GenerateCode
import com.qrcode.creator.utils.PrintHelp
import com.qrcode.creator.utils.SavePhotoToStorage
import com.qrcode.creator.utils.Share

class YouTubeFragment : BaseFragment<FragmentYouTubeBinding>(FragmentYouTubeBinding::inflate) {

    override fun setupUI() {
        initClicks()
    }

    private fun initClicks() {
        vb.imageMakeQrCodeAction.setOnClickListener {
            GenerateCode.generate(context, vb.etUrl.text.toString().trim(), vb.imageQrCode)
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

        vb.buttonShare.setOnClickListener { Share.shareImage(context, vb.imageQrCode, getString(R.string.youtube)) }

        vb.buttonPrint.setOnClickListener { PrintHelp.doPhotoPrint(activity, vb.imageQrCode) }

        vb.buttonSaveToGallery.setOnClickListener { SavePhotoToStorage.save(context, vb.imageQrCode) }

        vb.imageBack.setOnClickListener { navigateUp() }
    }
}