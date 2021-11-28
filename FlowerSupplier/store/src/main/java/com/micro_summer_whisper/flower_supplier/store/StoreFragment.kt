package com.micro_summer_whisper.flower_supplier.store

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.micro_summer_whisper.flower_supplier.store.databinding.FragmentStoreBinding


class StoreFragment : Fragment() {


    private var _binding: FragmentStoreBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoreBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.text1.setText("~店铺组件~")
        binding.text1.setOnClickListener{
            Toast.makeText(activity,"~店铺组件~", Toast.LENGTH_SHORT).show()
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance() =
            StoreFragment().apply {

            }
    }
}