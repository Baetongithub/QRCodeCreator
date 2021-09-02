package com.geektech.qrcodecreater.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import androidx.navigation.Navigation
import com.geektech.qrcodecreater.R
import com.geektech.qrcodecreater.databinding.FragmentHomeBinding
import com.geektech.qrcodecreater.extensions.toast
import com.geektech.qrcodecreater.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun setupUI() {
        setStatusBarGradient()

        initClicks()
    }

    private fun initClicks() {

        vb.rlYoutube.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.youTubeFragment)
            toast(vb.tvYoutube.text.toString())
        }
        vb.rlInstagram.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.instagramFragment)
            toast(vb.tvInstagram.text.toString())
        }
        vb.rlTg.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.TGFragment)
            toast(vb.tvTg.text.toString())
        }
        vb.rlVk.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.VKFragment)
            toast(vb.tvVk.text.toString())
        }
        vb.rlOther.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.anyURLFragment)
            toast(vb.tvAnyUrls.text.toString())
        }
    }

    private fun setStatusBarGradient() {
        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }
}