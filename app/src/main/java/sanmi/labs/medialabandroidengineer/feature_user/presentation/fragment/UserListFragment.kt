package sanmi.labs.medialabandroidengineer.feature_user.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import sanmi.labs.medialabandroidengineer.databinding.UserListFragmentBinding
import sanmi.labs.medialabandroidengineer.feature_user.presentation.adapter.UserAdapter
import sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model.UserListViewModel

class UserListFragment : Fragment() {

    private val viewModel: UserListViewModel by lazy {
        ViewModelProvider(this).get(UserListViewModel::class.java)
    }

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
            Log.d("UserListFragment", "Clicked on user ${it.name}")
        })
    }

    private fun subscribeUi() {
        viewModel.users.observe(viewLifecycleOwner) {
            Log.d("UserListFragment", "Loaded ${it.size} users!")
        }
    }
}