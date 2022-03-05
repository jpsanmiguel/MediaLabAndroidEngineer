package sanmi.labs.medialabandroidengineer.feature_user.presentation.fragment

import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.github.dhaval2404.imagepicker.ImagePicker
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import sanmi.labs.medialabandroidengineer.R
import sanmi.labs.medialabandroidengineer.databinding.UserProfileFragmentBinding
import sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model.UserListViewModel
import sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model.UserProfileViewModel

class UserProfileFragment : Fragment() {

    private lateinit var binding: UserProfileFragmentBinding

    private val viewModel: UserProfileViewModel by viewModel()
    private val userListViewModel: UserListViewModel by sharedViewModel()
    private val args: UserProfileFragmentArgs by navArgs()

    private val startForProfileImageResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            val resultCode = result.resultCode
            val data = result.data

            when (resultCode) {
                Activity.RESULT_OK -> {
                    //Image Uri will not be null for RESULT_OK
                    val fileUri = data?.data!!

                    viewModel.setUserImage(fileUri.toString())
                    binding.imageView.setImageURI(fileUri)
                }
                ImagePicker.RESULT_ERROR -> {
                    Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserProfileFragmentBinding.inflate(layoutInflater)
        setUpUi()
        subscribeUi()
        return binding.root
    }

    private fun setUpUi() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.initViewModel(args.user)

        binding.userProfileFragmentAddPhotoButton.setOnClickListener {
            ImagePicker.with(this)
                .cropSquare()
                .createIntent {
                    startForProfileImageResult.launch(it)
                }
        }

        setHasOptionsMenu(true)
    }

    private fun subscribeUi() {
        viewModel.nameEditTextError.observe(viewLifecycleOwner) {
            it?.let {
                binding.userProfileFragmentNameTextInputEditText.error =
                    it.asString(requireContext())
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.user_profile_save_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.user_profile_save_menu_save -> {
                if (viewModel.isFormComplete()) {
                    userListViewModel.saveUser(viewModel.getSelectedUser())
                    findNavController().navigateUp()
                }
            }
            else -> findNavController().navigateUp()
        }
        return true
    }
}