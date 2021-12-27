package com.micro_summer_whisper.flower_supplier.me

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.micro_summer_whisper.flower_supplier.common.FlowerSupplierApplication
import com.micro_summer_whisper.flower_supplier.common.MyDatabaseHelper
import com.micro_summer_whisper.flower_supplier.common.network.ApiResponse
import com.micro_summer_whisper.flower_supplier.common.network.ApiService
import com.micro_summer_whisper.flower_supplier.common.network.ServiceCreator
import com.micro_summer_whisper.flower_supplier.common.pojo.Person
import com.micro_summer_whisper.flower_supplier.common.shortToast
import com.micro_summer_whisper.flower_supplier.me.databinding.FragmentMeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MeFragment : Fragment() {

    private var _binding: FragmentMeBinding? = null

    val dbHelper = context?.let { MyDatabaseHelper(it, "flower_supplier", 1) }//取得可写的数据库
    val db = dbHelper?.writableDatabase

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.O)
    private val apiService = ServiceCreator.create(ApiService::class.java)

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
            val intent = Intent(binding.root.context, AccountUpdateActivity::class.java)
            startActivity(intent)
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

        /*binding.meChangeAccount.setOnClickListener {
            "你点击了切换账户".shortToast()
        }*/


        val builder = AlertDialog.Builder(binding.root.context)
        builder.setView(_inflate)
        builder.setCancelable(false)
        val alert = builder.create()

        val yes: Button = _inflate.findViewById(R.id.btn_blog)
        yes.setOnClickListener {


            alert.dismiss()
        }
        val no: Button = _inflate.findViewById(R.id.btn_close)
        no.setOnClickListener {
            "对话框已关闭~".shortToast()
            alert.dismiss()
        }

        binding.meOutAccount.setOnClickListener {
            /*if (db != null) {
                db.delete(
                    "USERID",
                    "userid = ?",
                    arrayOf(FlowerSupplierApplication.UserId.toString())
                )
            }*/

            FlowerSupplierApplication.UserId = -1
            FlowerSupplierApplication.isLogin = false
            val editor = this.activity?.getSharedPreferences("data", Context.MODE_PRIVATE)?.edit()
            editor?.remove("userid")
            editor?.apply()
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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        initOrders()
    }

    //獲取用戶信息
    @RequiresApi(Build.VERSION_CODES.O)
    private fun initOrders() {
        /*if (db != null) {
            val cursor = db.query("USERID", null, null, null, null, null, null)
            if (cursor.moveToFirst()) {
                do {
                    //遍历Cursor
                    val userid = cursor.getString(cursor.getColumnIndex("userid")).toInt()
                    FlowerSupplierApplication.UserId = userid
                } while (cursor.moveToNext())
            }
            cursor.close()
        }*/

        var userid = -1
        val prefs = this.activity?.getSharedPreferences("data", Context.MODE_PRIVATE)
        if (prefs != null) {
            userid = prefs.getInt("userid", -1)
        }

        FlowerSupplierApplication.UserId = userid

        if (!FlowerSupplierApplication.isLogin) {
            "请登录".shortToast()
           this.activity?.finish()
        }

        apiService.info(FlowerSupplierApplication.UserId)
            .enqueue(object : Callback<ApiResponse<Person>> {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onResponse(
                    call: Call<ApiResponse<Person>>,
                    response: Response<ApiResponse<Person>>
                ) {
                    val apiResponse = response.body() as ApiResponse<Person>
                    if (apiResponse.success) {
                        apiResponse.data?.let {
                            FlowerSupplierApplication.userInfo = it
                            binding.meName.text = it.name
                            it.name.toString().shortToast()
                            binding.meEmail.text = it.name

                            Log.d(javaClass.simpleName, it.toString())
                        }
                    } else {
                        Log.d(javaClass.simpleName, apiResponse.message)
                    }
                }

                override fun onFailure(call: Call<ApiResponse<Person>>, t: Throwable) {
                    Log.e(javaClass.simpleName, "获取订单失败")
                    Log.e(javaClass.simpleName, t.stackTraceToString())
                }
            })
    }

}