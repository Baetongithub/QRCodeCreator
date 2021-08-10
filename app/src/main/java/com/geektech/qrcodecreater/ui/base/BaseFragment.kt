package com.geektech.qrcodecreater.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.geektech.qrcodecreater.R

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
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        navController.navigateUp()
    }
}