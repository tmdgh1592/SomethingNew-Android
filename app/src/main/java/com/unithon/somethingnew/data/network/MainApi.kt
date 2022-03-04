package com.unithon.somethingnew.data.network

import com.dnd.sixth.lmsservice.data.preference.PreferenceManager
import com.dnd.sixth.lmsservice.data.preference.PreferenceManager.Companion.KEY_USER_NAME
import com.unithon.somethingnew.App
import com.unithon.somethingnew.data.network.base.BaseApi
import com.unithon.somethingnew.data.network.response.LocationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainApi : BaseApi() {
    private val iODispatcher = Dispatchers.IO
    private val preferenceManager = PreferenceManager(App.instance)

    suspend fun loginKakao(accessToken: String, fcmToken: String): Boolean = withContext(iODispatcher) {
        val loginApi = retrofit.create(ApiService::class.java)
        val loginResponse = loginApi.loginKakao(accessToken, fcmToken)

        if (loginResponse.isSuccessful) {
            val loginModel = loginResponse.body()
            preferenceManager.setString(KEY_USER_NAME, loginModel?.userName)
            true
        } else {
            false
        }
    }

    suspend fun loginNaver(accessToken: String, fcmToken: String): Boolean = withContext(iODispatcher) {
        val loginApi = retrofit.create(ApiService::class.java)
        val loginResponse = loginApi.loginNaver(accessToken, fcmToken)

        if (loginResponse.isSuccessful) {
            val loginModel = loginResponse.body()
            preferenceManager.setString(KEY_USER_NAME, loginModel?.userName)
            true
        } else {
            false
        }
    }

}