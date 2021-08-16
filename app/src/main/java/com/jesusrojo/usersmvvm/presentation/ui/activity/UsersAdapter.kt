package com.jesusrojo.usersmvvm.presentation.ui.activity

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jesusrojo.usersmvvm.R
import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.databinding.ItemLayoutBinding


class UsersAdapter (private var values: ArrayList<User>,
                    private val resources: Resources?,
                    private val listener: (User) -> Unit
) : RecyclerView.Adapter<UsersViewHolder>() {

    private lateinit var usersHolder: UsersViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ItemLayoutBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_layout, parent, false)

        return UsersViewHolder(binding, resources, listener)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        usersHolder = holder
        val item = values[position]
        holder.bindMyHolder(item, position)
    }

    override fun getItemCount(): Int = values.size

    fun setNewValues(newValues: ArrayList<User>) {
        values.clear()
        values.addAll(newValues)
        notifyDataSetChanged()
    }

    fun deleteAll() {
        values.clear()
        notifyDataSetChanged()
    }

    fun orderByName() {
        values.sortWith(
                compareBy(String.CASE_INSENSITIVE_ORDER, { it.name })
        )
        notifyDataSetChanged()
    }

    fun orderByUserName() {
        values.sortWith(
            compareBy(String.CASE_INSENSITIVE_ORDER, { it.userName })
        )
        notifyDataSetChanged()
    }

    fun orderByEmail() {
        values.sortWith(
            compareBy(String.CASE_INSENSITIVE_ORDER, { it.email })
        )
        notifyDataSetChanged()
    }

    fun orderByWebsite() {
        values.sortWith(
            compareBy(String.CASE_INSENSITIVE_ORDER, { it.website })
        )
        notifyDataSetChanged()
    }
}