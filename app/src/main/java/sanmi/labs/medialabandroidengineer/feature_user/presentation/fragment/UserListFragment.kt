package sanmi.labs.medialabandroidengineer.feature_user.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import sanmi.labs.medialabandroidengineer.databinding.UserListFragmentBinding
import sanmi.labs.medialabandroidengineer.feature_user.presentation.adapter.UserAdapter
import sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model.UserListViewModel

class UserListFragment : Fragment() {

    private val viewModel: UserListViewModel by viewModel()

    private lateinit var binding: UserListFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserListFragmentBinding.inflate(inflater)
        setUpUi()
        subscribeUi()
        return binding.root
    }

    private fun setUpUi() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.userListFragmentRecyclerView.adapter = UserAdapter(UserAdapter.OnClickListener {
            viewModel.navigateToSelectedUser(it)
        })
    }

    private fun subscribeUi() {
        viewModel.navigateToSelectedUser.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(UserListFragmentDirections.actionUserListFragmentToUserProfileFragment(it))
                viewModel.finishedNavigateToSelectedUser()
            }
        }
    }
}