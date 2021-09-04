package com.geektech.qrcodecreater.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.geektech.qrcodecreater.R
import com.geektech.qrcodecreater.databinding.FragmentHomeBinding
import com.geektech.qrcodecreater.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeBinding =
        FragmentHomeBinding.inflate(inflater, container, false)

    override fun setupUI() {

        initClicks()
    }

    private fun initClicks() {

        vb.rlYoutube.setOnClickListener { navigate(R.id.action_homeFragment_to_youTubeFragment) }

        vb.rlInstagram.setOnClickListener { navigate(R.id.action_homeFragment_to_instagramFragment) }

        vb.rlTg.setOnClickListener { navigate(R.id.action_homeFragment_to_TGFragment) }

        vb.rlVk.setOnClickListener { navigate(R.id.action_homeFragment_to_VKFragment) }

        vb.rlOther.setOnClickListener { navigate(R.id.action_homeFragment_to_anyURLFragment) }
    }
}