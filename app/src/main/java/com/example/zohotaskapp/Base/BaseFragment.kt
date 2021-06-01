package com.example.zohotaskapp.Base

import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {
    fun getActionBar() : ActionBar? {
        return (this.activity as AppCompatActivity).supportActionBar
    }

    fun updateActionBarTitle(title: String) {
        getActionBar()?.title = title
    }

    fun enableBackButtonOfActionBar() {
        enableOrDisableBackButton(true)
    }

    fun disableBackButtonOfActionBar() {
        enableOrDisableBackButton(false)
    }

    fun enableOrDisableBackButton(isEnabled: Boolean) {
        getActionBar()?.setDisplayHomeAsUpEnabled(isEnabled)
    }
}