package com.example.noteapp.UI.Adapters

import android.graphics.Color
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.noteapp.Data.models.NoteModel
import com.example.noteapp.R
import com.example.noteapp.UI.interfaces.OnClickItem
import com.example.noteapp.databinding.ItemNoteBinding
import com.example.noteapp.databinding.ItemNoteGridBinding

class NoteAdapter(
    private val onLongClick: OnClickItem,
    private val onClick: OnClickItem
):ListAdapter<NoteModel, RecyclerView.ViewHolder>(DiffCallback()) {


    var isGridMode = false

    companion object {
        private const val VIEW_TYPE_LIST = 0
        private const val VIEW_TYPE_GRID = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (isGridMode) VIEW_TYPE_GRID else VIEW_TYPE_LIST
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_GRID -> GridViewHolder(
                ItemNoteGridBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> ListViewHolder(
                ItemNoteBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ListViewHolder -> holder.bind(getItem(position))
            is GridViewHolder -> holder.bind(getItem(position))
        }

        holder.itemView.setOnLongClickListener {
            onLongClick.onLongClick(getItem(position))
            true
        }

        holder.itemView.setOnClickListener {
            onClick.onCLick(getItem(position))
        }
    }

    inner class ListViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.txtTitle.text = item.title
            binding.txtDescription.text = item.description
            binding.txtDate.text = item.date
            binding.txtTime.text = item.time
            binding.noteContainer.setBackgroundColor(item.color)
        }
    }

    inner class GridViewHolder(private val binding: ItemNoteGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoteModel) {
            binding.txtTitle.text = item.title
            binding.txtDescription.text = item.description
            binding.txtDate.text = item.date
            binding.txtTime.text = item.time
            binding.noteContainer.setBackgroundColor(item.color)
        }
    }
    class DiffCallback:DiffUtil.ItemCallback<NoteModel>() {
        override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
            return oldItem == newItem
        }
    }


}