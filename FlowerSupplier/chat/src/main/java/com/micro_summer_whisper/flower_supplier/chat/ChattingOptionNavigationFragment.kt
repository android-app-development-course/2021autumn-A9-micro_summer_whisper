package com.micro_summer_whisper.flower_supplier.chat

import android.R.attr
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.micro_summer_whisper.flower_supplier.chat.databinding.FragmentChatBinding
import com.micro_summer_whisper.flower_supplier.chat.databinding.FragmentChattingOptionNavigationBinding
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import android.provider.MediaStore
import android.R.attr.path

import android.graphics.BitmapFactory

import android.graphics.Bitmap

import android.R.attr.data
import java.lang.Exception


class ChattingOptionNavigationFragment : Fragment() {

    private var _binding: FragmentChattingOptionNavigationBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChattingOptionNavigationBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.chatttingOptionNavAlbum.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            activity?.let {
                it.startActivityForResult(intent, IMAGE_REQUEST_CODE)
            }
        }

        return view
    }


    companion object {
        const val IMAGE_REQUEST_CODE = 1
        fun newInstance() =
            ChattingOptionNavigationFragment().apply {
            }
    }
}