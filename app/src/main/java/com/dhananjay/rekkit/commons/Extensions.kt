@file:JvmName("ExtensionUtils")

package com.dhananjay.rekkit.commons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by dhananjay on 5/6/17.
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}
