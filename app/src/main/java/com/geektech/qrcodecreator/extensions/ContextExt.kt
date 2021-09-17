package com.geektech.qrcodecreator.extensions

import android.content.Context
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

fun Context.toast(txt: String) = Toast.makeText(this, txt, Toast.LENGTH_SHORT).show()