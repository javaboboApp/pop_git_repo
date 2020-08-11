package com.example.poqtest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.poqtest.R
import com.example.poqtest.ui.repos.BaseFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), BaseFragment.CommunicatorsInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
       //We are using navigation components so , not fragment manager is needed .
    }

    //showProgressBar() is called from the  fragment
    override fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }
    //hideProgressBar()is called from the fragment.
    override fun hideProgressBar() {
        progressBar.visibility = View.GONE

    }
}