package com.manohar.colaninfotechtask.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.manohar.colaninfotechtask.R
import com.manohar.colaninfotechtask.util.TodoListAdapterClickListenerInterface
import com.manohar.colaninfotechtask.databinding.TodoListAdapterLayoutBinding
import com.manohar.colaninfotechtask.db.Todo

class TodoListAdapter(private val todoListAdapterClickListenerInterface : TodoListAdapterClickListenerInterface, val context: Context) : ListAdapter<Todo, TodoListAdapter.TodoListAdapterViewHolder>(diffUtil()) {

    class TodoListAdapterViewHolder(val todoListAdapterLayoutBinding: TodoListAdapterLayoutBinding) : RecyclerView.ViewHolder(todoListAdapterLayoutBinding.root){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListAdapterViewHolder {
        val binding = TodoListAdapterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoListAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodoListAdapterViewHolder, position: Int) {
        val item = getItem(position)

        holder.todoListAdapterLayoutBinding.titleTv.text = context.resources.getString(R.string.label_title_tv,item.title)
        holder.todoListAdapterLayoutBinding.descriptionTv.text = context.resources.getString(R.string.label_description_tv,item.description)
        holder.todoListAdapterLayoutBinding.timeTv.text = context.resources.getString(R.string.label_time_tv,item.time)
        holder.todoListAdapterLayoutBinding.dateTv.text = context.resources.getString(R.string.label_date_tv,item.date)
        holder.todoListAdapterLayoutBinding.alarmTypeTv.text = context.resources.getString(R.string.label_type_tv,item.alarmType)

        holder.todoListAdapterLayoutBinding.todoDeleteBtn.setOnClickListener {
            todoListAdapterClickListenerInterface.onItemDelete(item)
        }
    }

    class diffUtil : DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }
}