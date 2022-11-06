package com.indrajeetsinhchauhan.gitusers.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.indrajeetsinhchauhan.gitusers.R
import com.indrajeetsinhchauhan.gitusers.databinding.ActivityUserDetailsBinding
import com.indrajeetsinhchauhan.gitusers.viewmodel.UserViewModel
import com.squareup.picasso.Picasso

class UserDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailsBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val extras = intent.extras
        viewModel.fetchUser(extras!!.getString("user", ""))
        viewModel.userModelLiveData?.observe(this) {
            if (it != null) {
                binding.tvUserName.text = it.login
                binding.tvFollowers.text = it.followers
                binding.tvFollowing.text = it.following
                if (it.bio == null || it.bio == "") {
                    binding.llBio.visibility = View.GONE
                    binding.tvBioLbl.visibility = View.GONE
                    binding.tvBio.visibility = View.GONE
                } else {
                    binding.tvBio.text = it.bio
                }
                if (it.email == null || it.email == "") {
                    binding.llEmail.visibility = View.GONE
                    binding.tvEmailLbl.visibility = View.GONE
                    binding.tvEmail.visibility = View.GONE
                }else{
                    binding.tvEmail.text = it.email
                }
                if (it.avatar_url != null) {
                    Picasso.with(binding.root.context)
                        .load(it.avatar_url)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(binding.ivAvatar)
                } else {
                    showToast("Image url is null or empty")
                }
            } else {
                showToast("Something went wrong!!")
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}