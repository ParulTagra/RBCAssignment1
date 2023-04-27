package com.example.rbcassignment.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rbcassignment.repository.Repository
import com.example.rbcassignment.utils.Utils
import com.rbc.rbcaccountlibrary.Account
import com.rbc.rbcaccountlibrary.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

class MainViewModel : ViewModel() {
    val accountListResponse = MutableLiveData<List<Account>>()
    val transactionListResponse = MutableLiveData<List<Transaction>>()
    val creditTransactionListResponse = MutableLiveData<List<Transaction>>()
    private val repository : Repository = Repository()

    fun getAccountsList(){
        viewModelScope.launch {
            val result = repository.getAccountList()
            accountListResponse.value = result
        }
    }

    fun getTransactionList(accountNumber : String){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getTransactionList(accountNumber)
            transactionListResponse.postValue(result)
        }
    }

    fun getCreditTransactionList(accountNumber : String){
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getCreditTransactionList(accountNumber)
            creditTransactionListResponse.postValue(result)
        }
    }

    fun getSortedList(transactionList : List<Transaction>) : TreeMap<String, MutableList<Transaction>> {
        val sortedList= HashMap<String, MutableList<Transaction>>() // create hashmap to store data
        var temp: MutableList<Transaction>?
        for (item in transactionList) {
            temp = sortedList[Utils.getDate(item.date)]

            if (temp != null)
                temp.add(item)
            else {
                temp = ArrayList<Transaction>()
                temp.add(item)
            }

            sortedList[Utils.getDate(item.date)] = temp
        }
        val sortedMap: TreeMap<String, MutableList<Transaction>> = TreeMap(Comparator.comparing { text ->
            YearMonth.parse(
                text,
                DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH)
            )
        })
        sortedMap.putAll(sortedList)
        return sortedMap
    }

}