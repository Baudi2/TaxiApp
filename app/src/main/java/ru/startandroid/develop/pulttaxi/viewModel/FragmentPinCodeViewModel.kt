package ru.startandroid.develop.pulttaxi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.startandroid.develop.pulttaxi.model.api.TaxiApi
import javax.inject.Inject

@HiltViewModel
class FragmentPinCodeViewModel @Inject constructor(
    val taxiApi: TaxiApi
) : ViewModel() {
    private val _seconds = MutableLiveData<Int>()
    val second: LiveData<Int>
        get() = _seconds

    private val _clickable = MutableLiveData<Boolean>()
    val clickable: LiveData<Boolean>
        get() = _clickable

    fun startCounting() {
        _seconds.value = 15
        _clickable.value = false
        var count = 15
        viewModelScope.launch(Dispatchers.IO) {
            while (count > 0) {
                delay(1000)
                count--
                withContext(Dispatchers.Main) {
                    _seconds.value = count
                }
            }
            withContext(Dispatchers.Main) {
                _clickable.value = true
            }
        }
    }

    fun requestCode(phoneNumber: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            taxiApi.getVerificationPin(phoneNumber)
        }
    }

    fun postUser(phoneNumber: Long, password: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            taxiApi.authenticateClient(phoneNumber, password)
        }
    }

    fun getUserData(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            taxiApi.clientInfo(token)
        }
    }
}