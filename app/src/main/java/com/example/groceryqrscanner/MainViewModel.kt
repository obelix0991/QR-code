package com.example.groceryqrscanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MainViewModel : ViewModel() {
    private val repository = GroceryRepository()
    
    private val _groceryInfo = MutableLiveData<GroceryInfo>()
    val groceryInfo: LiveData<GroceryInfo> = _groceryInfo
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchGroceryInfo(url: String) {
        _isLoading.value = true
        _error.value = ""
        
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.getGroceryInfoFromUrl(url)
                if (response.isSuccessful && response.body() != null) {
                    _groceryInfo.postValue(response.body())
                } else {
                    _error.postValue("Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: IOException) {
                _error.postValue("Network error: ${e.message}")
            } catch (e: Exception) {
                _error.postValue("Error: ${e.message}")
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
