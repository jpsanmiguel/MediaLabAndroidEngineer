package sanmi.labs.medialabandroidengineer.feature_user.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import sanmi.labs.medialabandroidengineer.databinding.UserProfileFragmentBinding
import sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model.UserProfileViewModel

class UserProfileFragment : Fragment() {

    private lateinit var binding: UserProfileFragmentBinding

    private val viewModel: UserProfileViewModel by viewModel()
    private val args: UserProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserProfileFragmentBinding.inflate(inflater)
        setUpUi()
        return binding.root
    }

    private fun setUpUi() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.initViewModel(args.user)
    }
}