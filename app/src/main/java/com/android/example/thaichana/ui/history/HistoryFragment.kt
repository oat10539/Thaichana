package com.android.example.thaichana.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.example.thaichana.R
import com.android.example.thaichana.database.UserRoomDatabase
import com.android.example.thaichana.ui.BaseFragment
import com.android.example.thaichana.ui.UserAdapter
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.launch

class HistoryFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_history, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //set recycleview layout
        recycler_view_user.setHasFixedSize(true)
        recycler_view_user.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        //show history from database
        launch {
            context.let {
                val user = UserRoomDatabase(requireActivity()).userDao().getAll()
                val adapter = UserAdapter(user)
                recycler_view_user.adapter = adapter
            }
        }
    }
}

