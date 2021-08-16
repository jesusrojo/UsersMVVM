package com.jesusrojo.usersmvvm.presentation.ui.activity

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import com.jesusrojo.usersmvvm.R
import com.jesusrojo.usersmvvm.data.model.User
import com.jesusrojo.usersmvvm.databinding.ItemLayoutBinding

class UsersViewHolder(
    private val binding: ItemLayoutBinding,
    resources: Resources?,
    private val listener: (User) -> Unit
): RecyclerView.ViewHolder(binding.root) {

    private var name =  resources?.getString(R.string.name)
    private var userName = resources?.getString(R.string.user_name)
    private var email = resources?.getString(R.string.email)
    private var website = resources?.getString(R.string.website)

    fun bindMyHolder(entyData: User, position: Int) {

        binding.linearContainerItem.setOnClickListener { listener(entyData) }

        binding.positionTv.text = position.toString()

        var textUi = "$name:\t\t ${entyData.name}"
        binding.nameTv.text = textUi

        textUi = "$userName:\t\t ${entyData.userName}"
        binding.userNameTv.text = textUi

        textUi = "$email:\t\t ${entyData.email}"
        binding.emailTv.text = textUi

        textUi = "$website:\t\t ${entyData.website}"

        binding.websiteTv.text = textUi

//        if (avatarUrl != null && avatarUrl.isNotEmpty()) {
//            // Glide.with(binding.ivAvatar.context) // without HILT
//            GlideApp.with(binding.ivAvatar.context) // with HILT
//                .load(avatarUrl)
//                .into(binding.ivAvatar)
//        }
    }
}