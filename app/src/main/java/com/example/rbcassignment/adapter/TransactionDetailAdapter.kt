package com.example.rbcassignment.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rbcassignment.R
import com.example.rbcassignment.databinding.LayoutAccountDetailBinding
import com.example.rbcassignment.databinding.LayoutAccountListBinding
import com.example.rbcassignment.utils.Utils
import com.example.rbcassignment.view.AccountDetailsActivity
import com.rbc.rbcaccountlibrary.Account
import com.rbc.rbcaccountlibrary.AccountType
import com.rbc.rbcaccountlibrary.Transaction

class TransactionDetailAdapter (private val context: Context, private val accountList: MutableList<Transaction>) : RecyclerView.Adapter<TransactionDetailViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionDetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutAccountDetailBinding.inflate(inflater,parent,false)
        return TransactionDetailViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return accountList.size
    }

    override fun onBindViewHolder(holder: TransactionDetailViewHolder, position: Int) {
        holder.binding.txtName.text = accountList[position].description
        holder.binding.txtNumber.visibility = View.GONE
        holder.binding.ivNext.visibility = View.GONE
        holder.binding.txtAmount.text = context.getString(R.string.dollar_sign)+accountList[position].amount
        if (position == accountList.size - 1){
            holder.binding.viewLine.visibility = View.GONE
        }
    }

}

class TransactionDetailViewHolder(val binding: LayoutAccountDetailBinding) :
    RecyclerView.ViewHolder(binding.root)