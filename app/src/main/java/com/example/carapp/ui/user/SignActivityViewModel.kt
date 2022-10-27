package com.example.carapp.ui.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.carapp.database.dao.UserDao
import com.example.carapp.model.User
import kotlinx.coroutines.launch

class SignActivityViewModel(val dao: UserDao) : ViewModel() {

    fun save(user: User) {
        viewModelScope.launch {
            dao.save(user)
        }
    }

//    fun checkIfExists(user: User): Boolean{
//
//        viewModelScope.launch {
//            val checkInDB = dao.checkIfExists()
//
//
//        }
//        if(){
//
//        }
//        return false
//    }
}