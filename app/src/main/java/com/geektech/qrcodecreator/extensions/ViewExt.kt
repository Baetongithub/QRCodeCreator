package com.geektech.qrcodecreator.extensions

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Fragment.toast(txt: String) = Toast.makeText(this.context, txt, Toast.LENGTH_SHORT).show()

fun Fragment.getDrawable(drawable: Int): Drawable? = ContextCompat.getDrawable(context!!, drawable)

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        if (value) View.VISIBLE else View.INVISIBLE
    }

var View.invisible: Boolean
    get() = visibility == View.INVISIBLE
    set(value) {
        if (value) View.INVISIBLE else View.VISIBLE
    }
