package com.example.poptestluis.ui.repos

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import java.lang.ClassCastException

private const val TAG = "BaseFragment"
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
            Log.i(TAG, "$context must implement ComnunicatorInterface")

        }
    }
}