package com.example.poptestluis.ui.repos

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    var uiCommunicatorInterface: CommunicatorsInterface? = null

    interface CommunicatorsInterface {
        fun showProgressBar()
        fun hideProgressBar()
    }

}