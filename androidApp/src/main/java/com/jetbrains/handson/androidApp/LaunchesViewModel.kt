package com.jetbrains.handson.androidApp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.handson.kmm.shared.SpaceXRepository
import com.jetbrains.handson.kmm.shared.uimain.RocketLaunch
import com.jetbrains.handson.kmm.shared.uimain.getLaunches
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaunchesViewModel @Inject constructor(
    private val spaceXRepository: SpaceXRepository
) : ViewModel() {

    private val _launches = MutableLiveData<List<RocketLaunch>>()
    val launches: LiveData<List<RocketLaunch>> = _launches

    init {
        displayLaunches(false)
    }

    fun onRefresh() {
        displayLaunches(true)
    }

    private fun displayLaunches(needReload: Boolean) {
        viewModelScope.launch {
            runCatching {
                spaceXRepository.getLaunches(needReload)
            }.onSuccess {
                _launches.value = it
            }.onFailure {
                _launches.value = emptyList()
            }
        }
    }
}