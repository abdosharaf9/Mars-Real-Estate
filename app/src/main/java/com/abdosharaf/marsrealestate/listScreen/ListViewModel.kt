package com.abdosharaf.marsrealestate.listScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdosharaf.marsrealestate.network.MarsItem
import com.abdosharaf.marsrealestate.network.RetrofitService
import kotlinx.coroutines.launch
import com.abdosharaf.marsrealestate.utils.Constants.Companion.MarsApiStatus
import com.abdosharaf.marsrealestate.utils.Constants.Companion.MarsApiFilter

class ListViewModel : ViewModel() {

    private val _propertiesList = MutableLiveData<List<MarsItem>>()
    val propertiesList: LiveData<List<MarsItem>>
        get() = _propertiesList

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status

    init {
        getProperties(MarsApiFilter.SHOW_ALL)
    }

    fun getProperties(filter: MarsApiFilter) {
        viewModelScope.launch {
            _status.value = MarsApiStatus.LOADING
            try {
                _propertiesList.value = RetrofitService.apiService.getRealEstate(filter.value)
                _status.value = MarsApiStatus.DONE
            } catch (e: Exception) {
                _propertiesList.value = listOf()
                _status.value = MarsApiStatus.ERROR
            }
        }
    }
}