package com.example.rbcassignment.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rbcassignment.R
import com.example.rbcassignment.databinding.LayoutAccountListBinding
import com.rbc.rbcaccountlibrary.Account
import com.rbc.rbcaccountlibrary.AccountType

class AccountListAdapter (private val context: Activity, private val accountList: List<Account>,private val accountType : Array<AccountType>) : RecyclerView.Adapter<AccountViewHolder>(){

    private lateinit var itemDetailAdapter : ItemDetailAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutAccountListBinding.inflate(inflater,parent,false)
        return AccountViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return accountType.size
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.binding.txtAccountType.visibility = View.VISIBLE
        holder.binding.txtAccountType.text = accountType[position].name.replace("_"," ")
        var typeList : ArrayList<Account> = arrayListOf()
        for (i in 0 until accountList.size) {
            if (accountType[position].name == accountList[i].type.name){
                typeList.add(accountList[i])
            }
        }
        if (typeList.size > 0){
        this.itemDetailAdapter = ItemDetailAdapter(context,typeList)
        holder.binding.rvDetails.adapter = itemDetailAdapter
        holder.binding.rvDetails.layoutManager = LinearLayoutManager(context)
        }
        else{
            holder.binding.txtAccountType.visibility = View.GONE
        }
    }

}

class AccountViewHolder(val binding: LayoutAccountListBinding) :
    RecyclerView.ViewHolder(binding.root)