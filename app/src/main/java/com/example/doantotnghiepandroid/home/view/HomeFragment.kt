package com.example.doantotnghiepandroid.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doantotnghiepandroid.R
import com.example.doantotnghiepandroid.home.detail.DetailFragment
import com.example.doantotnghiepandroid.home.model.Home
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.HashMap


class HomeFragment  : Fragment(),HomeAdapter.onClickItemHome {

    var home: ArrayList<Home> =  arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private lateinit var adapterHome: HomeAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapterHome = HomeAdapter(home,this)
        rcvHome.layoutManager = LinearLayoutManager(context)
        rcvHome.adapter = adapterHome
        getLastKey()
    }
// data cua detail em truyen data tu man home saang ok e
    private fun getLastKey() {
        val rootRef = FirebaseDatabase.getInstance().reference
        val tripsRef = rootRef.child("Home")
        val valueEventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                home.clear()
                for (ds in dataSnapshot.children) {
                    val date = (ds.value as HashMap<*, *>)["date"]
                    val image = (ds.value as HashMap<*, *>)["image"]
                    val title = (ds.value as HashMap<*, *>)["title"]
                    val content = (ds.value as HashMap<*, *>)["content"]
                    val keyComment = (ds.value as HashMap<*, *>)["keyComments"]
                    home.add(Home(date.toString(), image.toString(), title.toString(),content.toString(), keyComment.toString()))
                }
                adapterHome.setData(home)
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        }
        tripsRef.addListenerForSingleValueEvent(valueEventListener)
    }

    override fun onClickItemHome(home: Home) {
        findNavController().navigate(R.id.action_nav_home_to_detailFragment, Bundle().apply {
            putParcelable(DetailFragment.KEY_ID, home)
        })
    }
}