package com.example.doantotnghiepandroid.store

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.doantotnghiepandroid.R
import com.example.doantotnghiepandroid.store.download.DownloadFragment
import com.example.doantotnghiepandroid.store.history.HistoryFragment
import kotlinx.android.synthetic.main.fragment_store.*

class StoreFragment : Fragment() {
    lateinit var adapter: MyFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_store,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MyFragment(childFragmentManager)
        adapter.addFragment(HistoryFragment(),"Lịch Sử")
        adapter.addFragment(DownloadFragment(),"Tải Về")
        viewPagerStore.adapter = adapter
        tabLayoutStore.setupWithViewPager(viewPagerStore)
    }

    inner class MyFragment(manager: FragmentManager): FragmentPagerAdapter(manager){
        private val fragmentList: MutableList<Fragment> = ArrayList()
        private val titleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }
        fun addFragment(fragment: Fragment,title: String){
            fragmentList.add(fragment)
            titleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return titleList[position]
        }

    }
}