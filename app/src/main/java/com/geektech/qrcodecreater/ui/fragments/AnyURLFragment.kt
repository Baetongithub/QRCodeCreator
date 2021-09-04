package com.geektech.qrcodecreater.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.commit
import com.geektech.qrcodecreater.databinding.FragmentAnyURLBinding
import com.geektech.qrcodecreater.extensions.toast
import com.geektech.qrcodecreater.ui.base.BaseFragment
import com.geektech.qrcodecreater.utils.GenerateCode
import com.geektech.qrcodecreater.utils.HideKeyboard
import com.geektech.qrcodecreater.utils.PrintHelp
import com.geektech.qrcodecreater.utils.ShareImage

class AnyURLFragment : BaseFragment<FragmentAnyURLBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentAnyURLBinding =
        FragmentAnyURLBinding.inflate(inflater, container, false)

    override fun setupUI() {
        initClicks()
    }

    private fun initClicks() {
        vb.imageScanAction.setOnClickListener {
            HideKeyboard.hide(activity)
            GenerateCode.generate(context, vb.etUrl.text.toString(), vb.imageQrCode)
        }

        vb.etUrl.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE ->
                    GenerateCode.generate(context, vb.etUrl.text.toString(), vb.imageQrCode)
            }
            false
        }

        vb.buttonShare.setOnClickListener { ShareImage.share(context, vb.imageQrCode) }

        vb.buttonPrint.setOnClickListener { PrintHelp.doPhotoPrint(activity, vb.imageQrCode) }

        vb.buttonSaveToGallery.setOnClickListener { toast("Feature is going to be developed asap") }

        vb.imageBack.setOnClickListener { navigateUp() }
    }
}