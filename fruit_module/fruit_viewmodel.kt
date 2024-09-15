package com.example.androiddev.fruit_module

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FruitViewModel : ViewModel() {

    private val _results = mutableStateListOf<FruitModel>()
    var errorMessage: String by mutableStateOf("")
    var successMessage: String by mutableStateOf("")
    var isLoading: Boolean by mutableStateOf(false)
    val results: List<FruitModel>
        get() = _results

    var selectedFruit by mutableStateOf(FruitModel())
        private set

    fun setFruit(fruit: FruitModel){
        selectedFruit = fruit
    }

    var stateFruitList : MutableState<MutableList<FruitModel>> = mutableStateOf(_results)
    var stateSearchFruitList : MutableState<MutableList<FruitModel>> = mutableStateOf(_results)

//    fun searchFruit(name : String) {
//        viewModelScope.launch {
//            isLoading = true
//            val service = APIService.getInstance()
//            try {
//                val response = service.getFruits().toString()
//
//                if (response.isNotEmpty()) {
////                    successMessage = "Success"
//                    getResultList() // Refresh the product list after insertion
//                } else {
//                    errorMessage = "Error: $response"
//                }
//            } catch (e: Exception) {
//                errorMessage = "Exception: ${ e.message.toString()}"
//            } finally {
//                isLoading = false
//            }
//        }
//    }

    fun getResultList() {
        stateFruitList.value = _results
        viewModelScope.launch {
            isLoading = true
            val apiService = APIService.getInstance()
            try {
                _results.clear()
                _results.addAll(apiService.getFruits())
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }

//    fun getSearchResultList(name: String = "apple") {
//        stateFruitList.value = _results
//        viewModelScope.launch {
//            isLoading = true
//            val apiService = APIService.getInstance()
//            try {
//                _results.clear()
//                _results.addAll(apiService.searchFruits(name))
//            } catch (e: Exception) {
//                errorMessage = e.message.toString()
//            } finally {
//                isLoading = false
//            }
//        }
//    }


    fun searchFruit(name: String) {

        viewModelScope.launch {
            isLoading = true
            val service = SearchService.getInstance()
            try {
                val response: MutableState<List<FruitModel>> = mutableStateOf(service.getsearchFruits(name))

                if (response.value.isNotEmpty()) {
//                    successMessage = "\" Result Found ${response.value.numFound} \""
                    getSearchResultList(name) // Refresh the product list after insertion
                } else {
                    successMessage = "\"${name}\" returns 0 results"
                }
            } catch (e: Exception) {
                errorMessage = "Exception: ${ e.message.toString()}"
            } finally {
                isLoading = false
            }
        }
    }


    fun getSearchResultList(name: String = "") {
        stateSearchFruitList.value = _results
        viewModelScope.launch {
            isLoading = true
            val apiSearchService = SearchService.getInstance()
            try {
                _results.clear()
                _results.addAll(apiSearchService.getsearchFruits(name))

            } catch (e: Exception) {
                errorMessage = e.message.toString()
            } finally {
                isLoading = false
            }
        }
    }

    fun resetStates(){
        stateFruitList.value.clear()
        stateSearchFruitList.value.clear()
        errorMessage = ""
        successMessage = ""
        isLoading = false
        selectedFruit = FruitModel(name = "")
    }

    var isAscendingOrder: MutableState<Boolean> = mutableStateOf(value = true)

    fun toggleSort() {
        var newList = _results.toMutableList()

        if (isAscendingOrder.value){
            newList.sortBy {it.name}
        }
        else{
            newList.sortByDescending {it.name}
        }

        _results.clear()
        _results.addAll(newList)

//        stateFruitList.value = newList

        isAscendingOrder.value = !isAscendingOrder.value
    }

}

