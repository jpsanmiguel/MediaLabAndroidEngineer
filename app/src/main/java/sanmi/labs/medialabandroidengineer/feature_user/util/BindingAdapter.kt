package sanmi.labs.medialabandroidengineer.feature_user.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User
import sanmi.labs.medialabandroidengineer.feature_user.presentation.adapter.UserAdapter

@BindingAdapter("userList")
fun bindUserListToRecyclerView(recyclerView: RecyclerView, userList: List<User>?) {
    val adapter = recyclerView.adapter as UserAdapter
    adapter.submitList(userList)
}

@BindingAdapter("imageUriString")
fun bindImageUriStringToImageView(imageView: ImageView, imageUri: String) {
    imageView.setImageURI(imageUri.toUri())
}