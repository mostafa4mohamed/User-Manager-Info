package com.user.info.utils

import android.widget.RadioGroup
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.user.info.R

fun RadioGroup.validateValue(): Boolean {
    val msg = context.getString(R.string.required_field)
    return if (this.checkedRadioButtonId != -1) {
        true
    } else {
        (this.getChildAt(0) as? TextView)?.error = msg
        false
    }
}

fun TextInputEditText.validateValue(): Boolean {
    val msg = context.getString(R.string.required_field)
    return if (!this.text.isNullOrEmpty()) {
        true
    } else {
        this.error = msg
        false
    }
}