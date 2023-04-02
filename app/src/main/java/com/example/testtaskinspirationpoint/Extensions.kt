package com.example.testtaskinspirationpoint

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

fun Context.customToast(messageText: String) {
    Toast.makeText(this, messageText, Toast.LENGTH_SHORT).show()
}