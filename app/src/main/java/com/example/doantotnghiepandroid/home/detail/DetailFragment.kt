package com.example.doantotnghiepandroid.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.doantotnghiepandroid.R
import com.example.doantotnghiepandroid.home.comment.view.CommentFragment
import com.example.doantotnghiepandroid.home.model.Home
import com.example.doantotnghiepandroid.loadImage
import kotlinx.android.synthetic.main.fragment_home_detail.*
import kotlinx.android.synthetic.main.fragment_home_detail.imgBook
import kotlinx.android.synthetic.main.item_home.*

class DetailFragment : Fragment() {

    private var home: Home? = null
    companion object {
        const val KEY_ID = "key.type"
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_detail,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
        iniViews()
    }
    private fun iniViews() {
        arguments?.let { it ->
            if (!it.isEmpty)
                home = it.getParcelable(KEY_ID)
            home?.let {
                imgHome.loadImage(it.image)
                imgBook.loadImage(it.image)
                tvContent.text = it.content

            }
        }
    }
    private fun onClick() {
        tvCommentDetail.setOnClickListener {
            home?.idComment?.let {
                findNavController().navigate(R.id.action_detailFragment_to_commentFragment,
                    Bundle().apply { putString(CommentFragment.ID, it) })
            }
        }
    }
}