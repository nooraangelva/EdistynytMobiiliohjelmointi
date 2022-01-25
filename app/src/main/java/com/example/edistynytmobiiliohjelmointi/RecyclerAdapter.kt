package com.example.edistynytmobiiliohjelmointi

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.edistynytmobiiliohjelmointi.databinding.RecyclerviewItemRowBinding
import com.example.edistynytmobiiliohjelmointi.dataclass.TodoItem

class RecyclerAdapter(private val todos: List<TodoItem>) : RecyclerView.Adapter<RecyclerAdapter.TextItemViewHolder>() {

    private var _binding: RecyclerviewItemRowBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {

        _binding = RecyclerviewItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return TextItemViewHolder(binding)

    }

    override fun getItemCount() = todos.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val item = todos[position]
        holder.bindTodo(item)

    }

    //1
    class TextItemViewHolder(v: RecyclerviewItemRowBinding) : RecyclerView.ViewHolder(v.root), View.OnClickListener {
        //2
        private var view: RecyclerviewItemRowBinding = v
        private var todo: TodoItem? = null

        init {
            v.root.setOnClickListener(this)
        }

        fun bindTodo(todo: TodoItem){

            this.todo = todo

            view.recyclerViewData.text = todo.title
            if(todo.completed) {
                view.recyclerViewImageData.setImageResource(R.drawable.done)
            }
            else{
                view.recyclerViewImageData.setImageResource(R.drawable.not_done)
            }
        }

        //4
        override fun onClick(v: View) {
            Log.d("RecyclerView", "CLICK!")
            val todo : TodoItem = this.todo!!
            val action = DataFragmentDirections.actionDataFragmentToDetailFragment(todo.id)
            v.findNavController().navigate(action)
        }
/*
        companion object {
            //5
            private val TODO_KEY =
        }*/
    }


}