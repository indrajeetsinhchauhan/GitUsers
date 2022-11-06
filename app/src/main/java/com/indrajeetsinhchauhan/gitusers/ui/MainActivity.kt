package com.indrajeetsinhchauhan.gitusers.ui


import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.indrajeetsinhchauhan.gitusers.data.UserModel
import com.indrajeetsinhchauhan.gitusers.databinding.ActivityMainBinding
import com.indrajeetsinhchauhan.gitusers.viewmodel.UserViewModel

class MainActivity : AppCompatActivity(), UserAdapter.UserListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var vm: UserViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        vm = ViewModelProvider(this)[UserViewModel::class.java]

        initAdapter()

        vm.fetchAllUsers()


        vm.userModelListLiveData?.observe(this, Observer {
            if (it != null) {
                binding.rvHome.visibility = View.VISIBLE
                adapter.setData(it as ArrayList<UserModel>)
            } else {
                showToast("Something went wrong")
            }
            binding.progressHome.visibility = View.GONE
        })

    }

    private fun initAdapter() {
        adapter = UserAdapter()
        binding.rvHome.layoutManager = LinearLayoutManager(this)
        binding.rvHome.adapter = adapter
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}