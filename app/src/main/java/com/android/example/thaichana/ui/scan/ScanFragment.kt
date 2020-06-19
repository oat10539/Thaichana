package com.android.example.thaichana.ui.scan

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.android.example.thaichana.R
import com.android.example.thaichana.Web
import com.android.example.thaichana.database.User
import com.android.example.thaichana.database.UserRoomDatabase
import com.android.example.thaichana.databinding.FragmentScanBinding
import com.android.example.thaichana.retrofit.JsonPlaceHolderAPI
import com.android.example.thaichana.retrofit.Shop
import com.android.example.thaichana.ui.BaseFragment
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class ScanFragment : BaseFragment() {

    private lateinit var codeScanner: CodeScanner
    var placename = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentScanBinding>(inflater, R.layout.fragment_scan, container, false)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val scannerView = view.findViewById<CodeScannerView>(R.id.scanner_view)
        val activity = requireActivity()
        var url = "kiki"
        val check = true
        codeScanner = CodeScanner(activity, scannerView)

        //scan complete
        codeScanner.decodeCallback = DecodeCallback {
            activity.runOnUiThread {

                //url from qrcode
                url = it.text

                //get only shopID from URL
                val cutid = url.substringAfter("shopId=")


                //put shopID to api
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://api-customer.thaichana.com/shop/0001/")
                    .build()


                val jsonPlaceHolderAPI = retrofit.create(JsonPlaceHolderAPI::class.java)
                val mycall: Call<Shop> = jsonPlaceHolderAPI.getShopName(cutid)

                //gennerate id of user
                val id = Math.random().toInt()

                //time
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy")
                val formatteddate = current.format(formatter)

                //get placename form api
                mycall.enqueue(object : Callback<Shop> {
                    override fun onFailure(call: Call<Shop>, t: Throwable) {
                        Log.e("ERROR", t.message.toString())
                    }
                    override fun onResponse(call: Call<Shop>, response: Response<Shop>) {
                        val shopee: Shop = response.body()!!
                        placename = shopee.shopName

                        //save to roomdb
                        launch {
                            val user = User(id,cutid, placename, formatteddate, check)
                            context.let {
                                UserRoomDatabase(requireActivity()).userDao().insert(user)
                                view?.findNavController()!!.navigate(R.id.action_navigation_scan_to_checkinFragment)

                            }
                        }
                    }
                })

                // go to web thaichana
                val intent = Intent(getActivity(), Web::class.java)
                intent.putExtra("key", url)
                startActivity(intent)
            }

        }
        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }


}


