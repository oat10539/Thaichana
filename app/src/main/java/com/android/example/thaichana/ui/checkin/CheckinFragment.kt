package com.android.example.thaichana.ui.checkin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.example.thaichana.R
import com.android.example.thaichana.Web
import com.android.example.thaichana.database.User
import com.android.example.thaichana.database.UserRoomDatabase
import com.android.example.thaichana.databinding.FragmentCheckinBinding
import com.android.example.thaichana.ui.BaseFragment
import com.android.example.thaichana.ui.UserAdapter2
import com.android.example.thaichana.ui.checkoutClickListener
import kotlinx.android.synthetic.main.fragment_checkin.*
import kotlinx.coroutines.launch


class CheckinFragment : BaseFragment() ,checkoutClickListener{


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentCheckinBinding>(
            inflater,
            R.layout.fragment_checkin, container, false
        )

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recycler_view_user2.setHasFixedSize(true)
        recycler_view_user2.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)



        launch {
            context.let {
                val user = UserRoomDatabase(requireActivity()).userDao().getOne()

                val adapter = UserAdapter2(user,this@CheckinFragment)
                recycler_view_user2.adapter = adapter
            }
        }


    }

    override fun onItemClick(item: User, position: Int) {

        val intent = Intent(getActivity(), Web::class.java)
        val url = "https://qr.thaichana.com/?appId=0001&shopId=${item.shopid}"

        intent.putExtra("key",url)
        startActivity(intent)

        launch {
            context.let {
                UserRoomDatabase(requireActivity()).userDao().checkout()
                view?.findNavController()!!.navigate(R.id.checkinFragment)
            }
        }
    }


}
