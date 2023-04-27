package com.example.rbcassignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rbcassignment.R
import com.example.rbcassignment.databinding.LayoutAccountDetailBinding
import com.example.rbcassignment.databinding.LayoutAccountListBinding
import com.example.rbcassignment.utils.Utils
import com.rbc.rbcaccountlibrary.Account
import com.rbc.rbcaccountlibrary.AccountType
import com.rbc.rbcaccountlibrary.Transaction
import java.util.TreeMap

class TransactionListAdapter (private val context: Context, private val accountList: TreeMap<String, MutableList<Transaction>>) : RecyclerView.Adapter<TransactionViewHolder>(){

    private lateinit var itemDetailAdapter : TransactionDetailAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutAccountListBinding.inflate(inflater,parent,false)
        return TransactionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return accountList.size
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {

        holder.binding.txtAccountType.text = accountList.keys.elementAt(position)
        this.itemDetailAdapter = TransactionDetailAdapter(context, accountList.getValue(accountList.keys.elementAt(position)))
        holder.binding.rvDetails.adapter = itemDetailAdapter
        holder.binding.rvDetails.layoutManager = LinearLayoutManager(context)
    }

}

class TransactionViewHolder(val binding: LayoutAccountListBinding) :
    RecyclerView.ViewHolder(binding.root)