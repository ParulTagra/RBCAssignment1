package com.example.rbcassignment.repository

import com.rbc.rbcaccountlibrary.Account
import com.rbc.rbcaccountlibrary.AccountProvider
import com.rbc.rbcaccountlibrary.Transaction

class Repository {

    fun getAccountList(): List<Account> {
        return AccountProvider.getAccountsList()
    }

    fun getTransactionList(accountNumber : String): List<Transaction> {
        return try{
            AccountProvider.getTransactions(accountNumber)
        } catch (e : java.lang.Exception){
            arrayListOf()
        }
    }

    fun getCreditTransactionList(accountNumber : String): List<Transaction> {
        return try{
            AccountProvider.getAdditionalCreditCardTransactions(accountNumber)
        } catch (e : java.lang.Exception){
            arrayListOf()
        }
    }
}