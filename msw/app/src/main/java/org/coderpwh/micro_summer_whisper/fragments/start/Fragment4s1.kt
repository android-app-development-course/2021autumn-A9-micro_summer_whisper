package org.coderpwh.micro_summer_whisper.fragments.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.coderpwh.micro_summer_whisper.R


/**
 * A simple [Fragment] subclass.
 * Use the [Fragment4s1.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment4s1 : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment4s1, container, false)
    }

}