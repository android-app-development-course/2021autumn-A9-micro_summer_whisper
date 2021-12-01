package com.micro_summer_whisper.flower_supplier.me

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.alibaba.android.arouter.launcher.ARouter
import com.micro_summer_whisper.flower_supplier.common.longToast
import com.micro_summer_whisper.flower_supplier.common.shortToast
import com.micro_summer_whisper.flower_supplier.me.databinding.FragmentMeBinding

class MeFragment : Fragment() {

    private var _binding: FragmentMeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMeBinding.inflate(inflater, container, false)
        val view = binding.root


        val _inflate = inflater.inflate(R.layout.view_dialog_custom, null, false)

        binding.meAccount.setOnClickListener {
            "你点击了账户".shortToast()
        }
        binding.meAccountAndSafe.setOnClickListener {
            "你点击了账户与安全".shortToast()
        }
        binding.meCollection.setOnClickListener {
            "你点击了收款".shortToast()
        }
        binding.meCertificate.setOnClickListener {
            "你点击了我的证书".shortToast()
        }
        binding.meHelp.setOnClickListener {
            "你点击了帮助与反馈".shortToast()
        }
        binding.meAbout.setOnClickListener {
            "你点击了关于".shortToast()
        }
        binding.meSetting.setOnClickListener {
            "你点击了设置".shortToast()
        }

        binding.meChangeAccount.setOnClickListener {
            "你点击了切换账户".shortToast()
        }


        val builder = AlertDialog.Builder(binding.root.context)
        builder.setView(_inflate)
        builder.setCancelable(false)
        val alert = builder.create()

        val yes: Button =  _inflate.findViewById(R.id.btn_blog)
        yes.setOnClickListener {


            alert.dismiss()
        }
        val no: Button =  _inflate.findViewById(R.id.btn_close)
        no.setOnClickListener {
            "对话框已关闭~".shortToast()
            alert.dismiss()
        }

        binding.meOutAccount.setOnClickListener {
            "你点击了退出账户".shortToast()
            alert.show()
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() =
            MeFragment().apply {

            }
    }
}