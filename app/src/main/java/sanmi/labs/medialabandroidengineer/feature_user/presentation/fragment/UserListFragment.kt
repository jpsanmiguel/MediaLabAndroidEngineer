package sanmi.labs.medialabandroidengineer.feature_user.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import sanmi.labs.medialabandroidengineer.databinding.UserListFragmentBinding
import sanmi.labs.medialabandroidengineer.feature_user.domain.model.User
import sanmi.labs.medialabandroidengineer.feature_user.presentation.adapter.UserAdapter
import sanmi.labs.medialabandroidengineer.feature_user.presentation.view_model.UserListViewModel
import androidx.recyclerview.widget.RecyclerView
import sanmi.labs.medialabandroidengineer.R
import sanmi.labs.medialabandroidengineer.feature_user.util.CustomDividerItemDecorator


class UserListFragment : Fragment() {

    private val viewModel: UserListViewModel by sharedViewModel()

    private lateinit var binding: UserListFragmentBinding

    private lateinit var userAdapter: UserAdapter

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

        binding.userListFragmentAddNewUserButton.setOnClickListener {
            viewModel.navigateToSelectedUser(User())
        }

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        userAdapter = UserAdapter(UserAdapter.OnClickListener {
            viewModel.navigateToSelectedUser(it)
        })

        binding.userListFragmentRecyclerView.adapter = userAdapter

        val itemTouchHelper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                viewModel.removeUser(viewHolder.adapterPosition)
                userAdapter.notifyDataSetChanged()
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.userListFragmentRecyclerView)

        binding.userListFragmentRecyclerView.addItemDecoration(
            CustomDividerItemDecorator(R.drawable.divider)
        )
    }

    private fun subscribeUi() {
        viewModel.users.observe(viewLifecycleOwner) {
            Log.d("Users", "${it.size}")
        }

        viewModel.navigateToSelectedUser.observe(viewLifecycleOwner) {
            it?.let {
                findNavController().navigate(UserListFragmentDirections.actionUserListFragmentToUserProfileFragment(it))
                viewModel.finishedNavigateToSelectedUser()
            }
        }
    }
}