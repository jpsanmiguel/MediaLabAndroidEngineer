package sanmi.labs.medialabandroidengineer.feature_user.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import sanmi.labs.medialabandroidengineer.databinding.UserProfileFragmentBinding

class UserProfileFragment : Fragment() {

    private lateinit var binding: UserProfileFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserProfileFragmentBinding.inflate(inflater)
        return binding.root
    }
}