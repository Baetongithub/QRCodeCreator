package com.geektech.qrcodecreator.ui.fragments

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.View
import android.widget.Toolbar
import com.geektech.qrcodecreator.R
import com.geektech.qrcodecreator.databinding.CustomToolbarBinding
import com.geektech.qrcodecreator.databinding.FragmentHomeBinding
import com.geektech.qrcodecreator.extensions.drawable
import com.geektech.qrcodecreator.extensions.gone
import com.geektech.qrcodecreator.extensions.toast
import com.geektech.qrcodecreator.extensions.visible
import com.geektech.qrcodecreator.ui.base.BaseFragment
import com.geektech.qrcodecreator.utils.*
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun setupUI() {

        val ctb = this.view?.let { CustomToolbarBinding.bind(it) }
        val toolbar: Toolbar? = ctb?.toolbarCustom
        activity?.setActionBar(toolbar)

        val getContent =
            ResultLauncher(activity, viewLifecycleOwner, activity?.activityResultRegistry!!) { activityResult ->
                if (activityResult.resultCode == Activity.RESULT_OK) {
                    val intentResult: IntentResult =
                        IntentIntegrator.parseActivityResult(activityResult.resultCode, activityResult.data)
                    if (intentResult.contents != null) {
                        toast(intentResult.contents)
                        vb.tvScanResult.text = intentResult.contents
                        vb.rlScanResult.visible()
                    } else vb.rlScanResult.gone()
                }
            }
        vb.imageScanQrCode.setOnClickListener { getContent.scanQRCodeButton() }

        initClicks()
        onClickButtons()
    }

    private fun onClickButtons() {
        vb.buttonShare.setOnClickListener { Share.shareImage(context, vb.imageQrCode, getString(R.string.app_name)) }

        vb.buttonPrint.setOnClickListener { PrintHelp.doPhotoPrint(activity, vb.imageQrCode) }

        vb.buttonSaveToGallery.setOnClickListener { SavePhotoToStorage.save(context, vb.imageQrCode) }
    }

    private fun initClicks() {

        vb.etUrl.onFocusChangeListener = View.OnFocusChangeListener { _, onFocus ->
            if (onFocus) {
                vb.rlOther.background = drawable(R.drawable.backgr_rl_white_other)
                vb.rlOther.elevation = 0f
                vb.imageOthers.gone()
                vb.imageMakeQrCodeAction.visible()
            } else {
                vb.rlOther.background = drawable(R.drawable.backgr_status_bar_gradient)
                vb.rlOther.elevation = R.dimen.elevation_ten_dp.toFloat()
                vb.imageOthers.visible()
                vb.imageMakeQrCodeAction.gone()
            }
        }

        vb.imageMakeQrCodeAction.setOnClickListener {
            GenerateCode.generate(context, vb.etUrl.text.toString().trim(), vb.imageQrCode)
            vb.groupQrCodeImagesButtons.visible()
            hideKeyBoard()
        }

        vb.imageBtnSend.setOnClickListener { Share.shareText(context, vb.tvScanResult.text.toString()) }

        vb.imageBtnCopy.setOnClickListener {
            Copy.copyText(context, vb.tvScanResult.text.toString())
            toast(getString(R.string.text_copied_to_clipboard))
        }

        vb.rlYoutube.setOnClickListener { navigate(R.id.action_homeFragment_to_youTubeFragment) }

        vb.rlInstagram.setOnClickListener { navigate(R.id.action_homeFragment_to_instagramFragment) }

        vb.rlTg.setOnClickListener { navigate(R.id.action_homeFragment_to_TGFragment) }

        vb.rlFacebook.setOnClickListener { navigate(R.id.action_homeFragment_to_facebookFragment) }
    }
}
