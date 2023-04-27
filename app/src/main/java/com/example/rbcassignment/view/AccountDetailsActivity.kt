package com.example.rbcassignment.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rbcassignment.R
import com.example.rbcassignment.adapter.TransactionListAdapter
import com.example.rbcassignment.databinding.ActivityAccountDetailsBinding
import com.example.rbcassignment.utils.Constants
import com.example.rbcassignment.utils.Utils
import com.example.rbcassignment.viewModel.MainViewModel
import com.rbc.rbcaccountlibrary.Transaction
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

class AccountDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountDetailsBinding
    private var accountName : String = ""
    private var accountNumber : String = ""
    private var accountBalance : String = ""
    private lateinit var accountAdapter : TransactionListAdapter
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivityAccountDetailsBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        initUI()

        setObservables()
        this.mainViewModel.getCreditTransactionList(accountNumber)
        this.mainViewModel.getTransactionList(accountNumber)
    }

    private fun setObservables(){
        this.mainViewModel.transactionListResponse.observe(this){
            this.binding.progressBar.visibility = View.GONE
            this.binding.llTransaction.visibility = View.VISIBLE
            if (it.isNotEmpty()) {
                try {
                    this.accountAdapter = TransactionListAdapter(this, mainViewModel.getSortedList(it))
                    this.binding.rvTransactions.adapter = this.accountAdapter
                    val linearLayoutManager = LinearLayoutManager(this)
                    linearLayoutManager.reverseLayout = true
                    this.binding.rvTransactions.layoutManager = linearLayoutManager
                }
                catch (e : Exception){
                    Log.e("Exception",e.message.toString())
                    this.binding.txtNoTransaction.visibility = View.VISIBLE
                    this.binding.rvTransactions.visibility = View.GONE
                }
            }
            else{
                this.binding.txtNoTransaction.visibility = View.VISIBLE
                this.binding.rvTransactions.visibility = View.GONE
            }
        }

        this.mainViewModel.creditTransactionListResponse.observe(this){
            if (it.isNotEmpty()) {
                try {
                    this.accountAdapter = TransactionListAdapter(this, mainViewModel.getSortedList(it))
                    this.binding.rvCreditTransactions.adapter = this.accountAdapter
                    val linearLayoutManager = LinearLayoutManager(this)
                    linearLayoutManager.reverseLayout = true
                    this.binding.rvCreditTransactions.layoutManager = linearLayoutManager
                    this.binding.llCreditTransaction.visibility = View.VISIBLE
                }catch (e : Exception){
                    Log.e("Exception",e.message.toString())
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

    private fun initUI(){
        accountName = intent.getStringExtra(Constants.ACCOUNT_HOLDER_NAME).toString()
        accountNumber = intent.getStringExtra(Constants.ACCOUNT_NUMBER).toString()
        accountBalance = intent.getStringExtra(Constants.ACCOUNT_BALANCE).toString()
        this.binding.toolbarView.txtTitle.text = accountName
        this.binding.toolbarView.txtNumber.visibility = View.VISIBLE
        this.binding.toolbarView.txtNumber.text = accountNumber
        this.binding.txtBalance.text = getString(R.string.dollar_sign)+accountBalance
        this.binding.toolbarView.ivBack.visibility = View.VISIBLE
        this.binding.toolbarView.ivBack.setOnClickListener {
            finish()
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
        }
    }
}