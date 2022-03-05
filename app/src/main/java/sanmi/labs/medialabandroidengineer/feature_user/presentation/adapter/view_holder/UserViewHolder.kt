package sanmi.labs.medialabandroidengineer.feature_user.presentation.adapter.view_holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import sanmi.labs.medialabandroidengineer.databinding.UserItemBinding
import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User

class UserViewHolder private constructor(
    private val binding: UserItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    lateinit var user: User

    fun bind(user: User) {
        this.user = user
        binding.user = user

        binding.userItemAvatar.setImageURI(user.imageUri.toUri())

        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): UserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = UserItemBinding.inflate(layoutInflater, parent, false)

            return UserViewHolder(binding)
        }
    }
}