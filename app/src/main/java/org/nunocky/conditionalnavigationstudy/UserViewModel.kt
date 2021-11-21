package org.nunocky.conditionalnavigationstudy

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException

data class User(
    val username: String
)

class UserViewModel : ViewModel() {
    val user = MutableLiveData<User?>(null)

//    private val _user = MutableLiveData<User?>(null)
//    val user: LiveData<User?> = _user.distinctUntilChanged()

    fun login(username: String, password: String): LiveData<Result<User>> {
        val retVal = MutableLiveData<Result<User>>()
        viewModelScope.launch(Dispatchers.IO) {
            // ログイン成功したことにして適当なデータを返す
            delay(1000)

            when ((0..1).random()) {
                0 -> {
                    retVal.postValue(Result.failure(IOException("unknown error")))
                }
                else -> {
                    val newUser = User(username = "foobar")
                    user.postValue(newUser)
                    retVal.postValue(Result.success(newUser))
                }
            }
        }
        return retVal
    }
}