package com.example.doantotnghiepandroid.home.comment.view

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doantotnghiepandroid.R
import com.example.doantotnghiepandroid.home.comment.model.Comment
import com.example.doantotnghiepandroid.home.model.AccountManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_comment.*
import java.util.HashMap

class CommentFragment : Fragment(), CommentAdapter.onClickItemMessenger {
    private lateinit var adapter: CommentAdapter
    var comment: ArrayList<Comment> = arrayListOf()
    val dtb = FirebaseDatabase.getInstance().reference.child("home")

    companion object {
        const val ID = "ID_COMMENT"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_comment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
        dataList()
        adapter = CommentAdapter(mlistener = this)
        rcvComment.layoutManager = LinearLayoutManager(context)
        rcvComment.adapter = adapter
    }

    private fun onClick() {
        imgSendComment.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() { // cho luu data login dau e
        val comment = edtComment.text.toString().trim()
        if (!TextUtils.isEmpty(comment)) {
            val id = dtb.push().key // chua login :v
            AccountManager.user?.email?.let {
                val comment = Comment(id, it, System.currentTimeMillis(), comment)
                arguments?.getString(ID)?.let {
                    dtb.
                    child(it).
                    child(id.toString()).
                    setValue(comment).
                    addOnSuccessListener {
                        edtComment.setText("")
                        dataList()
                    }
                }
            }
        }
    }

    private fun dataList() { // lâu lâu k làm quên hết r :)) để a tìm lại xem tnao đãoke a
        arguments?.getString(ID)?.let {
            dtb.
            child(it).
            addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                } // oke a oi em cam onw a ma' cai recycle a tuong loi~ get data :v em nhieu khi cung quen ko cho layout manager
                // khi nào chú muốn để riêng. thì phải tạo cho nó 1 key id riêng. thì data sẽ riêng
                //oke e hieeur roi aj ok e a off day vang e cam on a

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    comment.clear()
                    for (ds in dataSnapshot.children) {
                        val data = ds.getValue(Comment::class.java)
                        data?.let { comment.add(it) }
                    }
                    activity?.runOnUiThread {
                        adapter.setData(comment)
                    }
                }
            })
        }
    }

    override fun onClickItemMain(comment: Comment) {
    }
} // chờ e gửi video cho a xem nhá ok e