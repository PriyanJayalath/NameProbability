package com.priyan.nameprobability.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.priyan.nameprobability.data.models.Country
import com.priyan.nameprobability.util.DataResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NameProbabilityViewModel @Inject constructor(private val nameProbabilityRepository:NameProbabilityRepository):ViewModel() {

    sealed class ProbabilityEvent {
        class Success(val countryList: List<Country>?): ProbabilityEvent()
        class Failure(val errorText: String): ProbabilityEvent()
        object Loading : ProbabilityEvent()
        object Empty : ProbabilityEvent()
    }

    private val _conversion = MutableStateFlow<ProbabilityEvent>(ProbabilityEvent.Empty)
    val conversion: StateFlow<ProbabilityEvent> = _conversion

    fun getData(name:String){

        viewModelScope.launch(Dispatchers.IO) {
            _conversion.value = ProbabilityEvent.Loading
            val response = nameProbabilityRepository.getData(name)

            try {
                if (response.isSuccessful){

                    response.body()?.let {
                        _conversion.value = ProbabilityEvent.Success(response.body()?.country)
                    }

                }else{

                    _conversion.value = ProbabilityEvent.Failure(response.message())

                }
            }  catch (e: Exception) {
                _conversion.value = ProbabilityEvent.Failure("Exception $e")
            }

        }

    }


}