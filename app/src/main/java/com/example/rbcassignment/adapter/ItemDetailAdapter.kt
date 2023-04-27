package com.example.rbcassignment.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rbcassignment.R
import com.example.rbcassignment.databinding.LayoutAccountDetailBinding
import com.example.rbcassignment.utils.Constants
import com.example.rbcassignment.view.AccountDetailsActivity
import com.rbc.rbcaccountlibrary.Account

class ItemDetailAdapter (private val context: Activity, private val accountList: List<Account>) : RecyclerView.Adapter<DetailViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutAccountDetailBinding.inflate(inflater,parent,false)
        return DetailViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return accountList.size
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.binding.txtName.text = accountList[position].name
        holder.binding.txtNumber.text = accountList[position].number
        holder.binding.txtAmount.text = context.getString(R.string.dollar_sign)+accountList[position].balance
        if (position == accountList.size - 1){
            holder.binding.viewLine.visibility = View.GONE
        }
        holder.binding.llItem.setOnClickListener {
            context.startActivity(Intent(context,AccountDetailsActivity::class.java)
                    .putExtra(Constants.ACCOUNT_HOLDER_NAME,accountList[position].name)
                    .putExtra(Constants.ACCOUNT_NUMBER,accountList[position].number)
                    .putExtra(Constants.ACCOUNT_BALANCE,accountList[position].balance))
            context.overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }
    }

}

class DetailViewHolder(val binding: LayoutAccountDetailBinding) :
    RecyclerView.ViewHolder(binding.root)