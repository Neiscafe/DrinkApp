package com.example.carapp.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carapp.database.dao.UserDao
import com.example.carapp.model.User
import kotlinx.coroutines.launch

class SignActivityViewModel(
    val dao: UserDao,
    val userName: String,
    val password: String
) : ViewModel() {

    var existsUser: LiveData<Boolean> = dao.checkIfExists(userName, password)

}