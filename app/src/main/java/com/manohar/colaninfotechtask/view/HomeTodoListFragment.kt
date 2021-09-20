package com.manohar.colaninfotechtask.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.manohar.colaninfotechtask.R
import com.manohar.colaninfotechtask.util.TodoListAdapterClickListenerInterface
import com.manohar.colaninfotechtask.adapter.TodoListAdapter
import com.manohar.colaninfotechtask.databinding.FragmentHomeTodoListBinding
import com.manohar.colaninfotechtask.db.Todo
import com.manohar.colaninfotechtask.db.TodoDatabase
import com.manohar.colaninfotechtask.repository.TodoRepository
import com.manohar.colaninfotechtask.viewmodel.HomeViewModel
import com.manohar.colaninfotechtask.viewmodel.HomeViewModelFactory

class HomeTodoListFragment : Fragment() , TodoListAdapterClickListenerInterface {

    private val TAG = "HomeTodoListFragment"
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val homeTodoListBinding = FragmentHomeTodoListBinding.inflate(inflater,container,false)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        val dao = TodoDatabase.getDatabase(requireActivity()).todoDao()
        val repository = TodoRepository(dao)
        homeViewModel = ViewModelProvider(this,HomeViewModelFactory(repository)).get(HomeViewModel::class.java)

        val todoListAdapter = context?.let { TodoListAdapter(this, it) }

        homeViewModel.getTodo().observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreateView: "+it)
            if (todoListAdapter != null) {
                todoListAdapter.submitList(it)
            }
            homeTodoListBinding.todoListRecyclerView.setHasFixedSize(true)
            homeTodoListBinding.todoListRecyclerView.adapter = todoListAdapter
        })

        homeTodoListBinding.addTodoBtn.setOnClickListener {
            navController.navigate(R.id.action_homeTodoListFragment_to_addUpdateTodoFragment)
        }

        return homeTodoListBinding.root
    }

    override fun onItemDelete(todo: Todo) {
        homeViewModel.deleteTodo(todo)
    }
}
