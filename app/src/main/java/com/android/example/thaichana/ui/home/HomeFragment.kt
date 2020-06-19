package com.android.example.thaichana.ui.home

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.android.example.thaichana.MainActivity
import com.android.example.thaichana.R
import com.android.example.thaichana.databinding.FragmentCheckinBinding
import com.android.example.thaichana.databinding.FragmentHomeBinding
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.EasyPermissions

class HomeFragment : Fragment() {

    companion object {
        const val REQUEST_CAMERA_PERMESSION = 100
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)

        //checkout button
        val btn = binding.checkoutbtnhomepage
        btn.setOnClickListener {
            view?.findNavController()!!.navigate(R.id.action_navigation_home_to_checkinFragment)
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //checkpermission
        checkCameraPermission()
    }


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }


    @AfterPermissionGranted(REQUEST_CAMERA_PERMESSION)
    private fun checkCameraPermission() {
        if (EasyPermissions.hasPermissions(requireActivity(), Manifest.permission.CAMERA)) {
            Log.d(ContentValues.TAG, "App has camera permission")
        } else {
            EasyPermissions.requestPermissions(
                requireActivity(), getString(R.string.camera_request_rationale)
                , REQUEST_CAMERA_PERMESSION, Manifest.permission.CAMERA
            )
        }
    }
}


