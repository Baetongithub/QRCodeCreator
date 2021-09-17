package com.geektech.qrcodecreator.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    abstract fun viewBinding(inflater: LayoutInflater, container: ViewGroup?): VB
    abstract fun setupUI()

    fun navigateUp() {
        findNavController().navigateUp()
    }

    fun navigate(fragmentId: Int) {
        findNavController().navigate(fragmentId)
    }

    fun hideKeyBoard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity?.currentFocus
        if (view == null) view = View(activity)
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}