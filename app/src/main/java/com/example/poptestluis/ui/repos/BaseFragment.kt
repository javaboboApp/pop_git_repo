package com.example.poptestluis.ui.repos

import android.content.Context
import androidx.fragment.app.Fragment
import java.lang.ClassCastException

open class BaseFragment : Fragment() {

    var uiCommunicatorInterface: CommunicatorsInterface? = null

    interface CommunicatorsInterface {
        fun showProgressBar()
        fun hideProgressBar()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
         uiCommunicatorInterface =   context as CommunicatorsInterface
        }catch (ex: ClassCastException){
            throw ClassCastException("$context must implement ComnunicatorInterface")
        }
    }
}