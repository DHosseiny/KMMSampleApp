package com.jetbrains.handson.androidApp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.handson.kmm.shared.PropertiesRepository
import com.jetbrains.handson.kmm.shared.SpaceXRepository
import com.jetbrains.handson.kmm.shared.entity.Properties
import com.jetbrains.handson.kmm.shared.entity.Property
import com.jetbrains.handson.kmm.shared.uimain.RocketLaunch
import com.jetbrains.handson.kmm.shared.uimain.getAllProperties
import com.jetbrains.handson.kmm.shared.uimain.getLaunches
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertiesViewModel @Inject constructor(
    private val propertiesRepository: PropertiesRepository
) : ViewModel() {

    private val _properties = MutableLiveData<List<Property>>()
    val properties: LiveData<List<Property>> = _properties

    init {
        displayLaunches()
    }

    fun onRefresh() {
        displayLaunches()
    }

    private fun displayLaunches() {
        viewModelScope.launch {
            runCatching {
                propertiesRepository.getAllProperties()
            }.onSuccess {
                _properties.value = it
            }.onFailure {
                _properties.value = emptyList()
            }
        }
    }
}