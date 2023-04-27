package com.example.rbcassignment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rbcassignment.R
import com.example.rbcassignment.adapter.AccountListAdapter
import com.example.rbcassignment.databinding.ActivityMainBinding
import com.example.rbcassignment.viewModel.MainViewModel
import com.rbc.rbcaccountlibrary.AccountType

class AccountListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var accountAdapter : AccountListAdapter
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        this.binding.toolbar.txtTitle.text = getString(R.string.accounts)
        setObservers()
        this.mainViewModel.getAccountsList()
    }

    private fun setObservers(){
        this.mainViewModel.accountListResponse.observe(this) {
            val accountType = AccountType.values()
            this.accountAdapter = AccountListAdapter(this,it,accountType)
            this.binding.rvAccounts.adapter = this.accountAdapter
            this.binding.rvAccounts.layoutManager = LinearLayoutManager(this)
        }
    }

}