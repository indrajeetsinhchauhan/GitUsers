package com.indrajeetsinhchauhan.gitusers.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.indrajeetsinhchauhan.gitusers.network.ApiClient
import com.indrajeetsinhchauhan.gitusers.network.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    private var apiInterface: ApiInterface? = null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
    }

    fun fetchAllUsers(): LiveData<List<UserModel>> {
        val data = MutableLiveData<List<UserModel>>()

        apiInterface?.fetchAllUsers()?.enqueue(object : Callback<List<UserModel>> {

            override fun onFailure(call: Call<List<UserModel>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<List<UserModel>>,
                response: Response<List<UserModel>>
            ) {

                val res = response.body()
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }
        })
        return data
    }

    fun fetchUser(user: String): LiveData<UserModel> {
        val data = MutableLiveData<UserModel>()

        apiInterface?.fetchUser(user)?.enqueue(object : Callback<UserModel> {

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<UserModel>,
                response: Response<UserModel>
            ) {

                val res = response.body()
                if (response.code() == 200 && res != null) {
                    data.value = res
                } else {
                    data.value = null
                }
            }
        })
        return data
    }
}