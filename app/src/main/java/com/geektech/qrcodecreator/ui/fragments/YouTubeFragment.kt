package com.geektech.qrcodecreator.ui.fragments

import android.view.inputmethod.EditorInfo
import com.geektech.qrcodecreator.R
import com.geektech.qrcodecreator.databinding.FragmentYouTubeBinding
import com.geektech.qrcodecreator.ui.base.BaseFragment
import com.geektech.qrcodecreator.utils.GenerateCode
import com.geektech.qrcodecreator.utils.PrintHelp
import com.geektech.qrcodecreator.utils.SavePhotoToStorage
import com.geektech.qrcodecreator.utils.Share

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