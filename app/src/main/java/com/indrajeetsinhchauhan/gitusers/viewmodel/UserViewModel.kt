package com.indrajeetsinhchauhan.gitusers.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.indrajeetsinhchauhan.gitusers.data.UserModel
import com.indrajeetsinhchauhan.gitusers.data.UserRepository

class UserViewModel(application: Application): AndroidViewModel(application) {
    private var userRepository: UserRepository?=null
    var userModelListLiveData : LiveData<List<UserModel>>?=null
    var userModelLiveData: LiveData<UserModel>?=null

    init {
        userRepository = UserRepository()
        userModelListLiveData = MutableLiveData()
        userModelLiveData = MutableLiveData()
    }

    fun fetchAllUsers(){
        userModelListLiveData = userRepository?.fetchAllUsers()
    }

    fun fetchUser(user:String) {
        userModelLiveData = userRepository?.fetchUser(user)
    }
}