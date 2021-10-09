package com.geektech.qrcodecreator.ui.fragments

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.activity.result.contract.ActivityResultContracts
import com.geektech.qrcodecreator.R
import com.geektech.qrcodecreator.databinding.FragmentHomeBinding
import com.geektech.qrcodecreator.extensions.getDrawable
import com.geektech.qrcodecreator.extensions.toast
import com.geektech.qrcodecreator.ui.base.BaseFragment
import com.geektech.qrcodecreator.utils.GenerateCode
import com.geektech.qrcodecreator.utils.PrintHelp
import com.geektech.qrcodecreator.utils.SavePhotoToStorage
import com.geektech.qrcodecreator.utils.ShareImage
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)

    private val getContent =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult?.resultCode == Activity.RESULT_OK) {
                val intentResult: IntentResult =
                    IntentIntegrator.parseActivityResult(activityResult.resultCode, activityResult.data)
                if (intentResult.contents != null)
                    toast(intentResult.contents)
                    vb.etUrl.setText(intentResult.contents)
            }
        }

    override fun setupUI() {

        val toolbar: Toolbar? = activity?.findViewById(R.id.toolbar_custom)
        activity?.setActionBar(toolbar)

        vb.imageScanQrCode.setOnClickListener {
            val integrator = IntentIntegrator(activity)
            integrator.initiateScan()
           // getContent.launch(null)
        }

        initClicks()
        onClickButtons()
    }

    private fun onClickButtons() {
        vb.buttonShare.setOnClickListener { ShareImage.share(context, vb.imageQrCode, getString(R.string.app_name)) }

        vb.buttonPrint.setOnClickListener { PrintHelp.doPhotoPrint(activity, vb.imageQrCode) }

        vb.buttonSaveToGallery.setOnClickListener { SavePhotoToStorage.save(context, vb.imageQrCode) }
    }

    private fun initClicks() {

        vb.etUrl.onFocusChangeListener = View.OnFocusChangeListener { _, onFocus ->
            if (onFocus) {
                vb.rlOther.background = getDrawable(R.drawable.backgr_rl_white_other)
                vb.rlOther.elevation = 0f
                vb.imageOthers.visibility = GONE
                vb.imageMakeQrCodeAction.visibility = VISIBLE
            } else {
                vb.rlOther.background = getDrawable(R.drawable.backgr_status_bar_gradient)
                vb.rlOther.elevation = R.dimen.elevation_ten_dp.toFloat()
                vb.imageOthers.visibility = VISIBLE
                vb.imageMakeQrCodeAction.visibility = GONE
            }
        }

        vb.imageMakeQrCodeAction.setOnClickListener {
            GenerateCode.generate(context, vb.etUrl.text.toString().trim(), vb.imageQrCode)
            vb.groupQrCodeImagesButtons.visibility = VISIBLE
            hideKeyBoard()
        }

        vb.rlYoutube.setOnClickListener { navigate(R.id.action_homeFragment_to_youTubeFragment) }

        vb.rlInstagram.setOnClickListener { navigate(R.id.action_homeFragment_to_instagramFragment) }

        vb.rlTg.setOnClickListener { navigate(R.id.action_homeFragment_to_TGFragment) }

        vb.rlFacebook.setOnClickListener { navigate(R.id.action_homeFragment_to_facebookFragment) }
    }
}