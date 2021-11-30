package org.coderpwh.micro_summer_whisper.fragments.start

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_fragment4s3.*
import org.coderpwh.micro_summer_whisper.MainActivity
import org.coderpwh.micro_summer_whisper.R
import org.coderpwh.micro_summer_whisper.activity.LoginActivity


/**
 * A simple [Fragment] subclass.
 * Use the [Fragment4s3.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment4s3 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment4s3, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startm.setOnClickListener {
            var intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }
}