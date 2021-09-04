package com.geektech.qrcodecreater.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var binding: VB? = null
    val vb get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = viewBinding(inflater, container)
        setupUI()

        return vb.root
    }

    abstract fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    abstract fun setupUI()

    fun navigateUp() {
        findNavController().navigateUp()
    }

    fun navigate(fragmentId: Int) {
        findNavController().navigate(fragmentId)
    }
}