package com.aston5

import android.content.Context


fun Context.isTablet(): Boolean {
    return this.resources.getBoolean(R.bool.isTablet)
}