package com.qrcode.creator.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context

object Copy {
    fun copyText(context: Context?, text: String) {
        val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("simple text", text)
        clipboard.setPrimaryClip(clip)
    }
}