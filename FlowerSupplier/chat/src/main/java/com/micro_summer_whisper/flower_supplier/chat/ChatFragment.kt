package com.micro_summer_whisper.flower_supplier.chat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.micro_summer_whisper.flower_supplier.chat.databinding.FragmentChatBinding


class ChatFragment : Fragment() {

    private var _binding: FragmentChatBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.text1.setText("~聊天组件~")
        binding.text1.setOnClickListener{
            Toast.makeText(activity,"~聊天组件~",Toast.LENGTH_SHORT).show()
            Log.d(javaClass.simpleName,"text1 click")
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(): ChatFragment {
            return ChatFragment().apply {

            }
        }
    }
}