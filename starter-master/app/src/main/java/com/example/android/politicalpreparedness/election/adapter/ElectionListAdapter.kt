package com.example.android.politicalpreparedness.election.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.politicalpreparedness.databinding.ElectionListItemBinding
import com.example.android.politicalpreparedness.network.models.Election

class ElectionListAdapter(private val clickListener: ElectionClickListener): ListAdapter<Election, ElectionViewHolder>(DiffCallback) {

    companion object DiffCallback : DiffUtil.ItemCallback<Election>() {
        override fun areItemsTheSame(oldItem: Election, newItem: Election): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Election, newItem: Election): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ElectionViewHolder {
        return ElectionViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ElectionViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

}

class ElectionViewHolder(val viewDataBinding: ElectionListItemBinding):
    RecyclerView.ViewHolder(viewDataBinding.root) {

    companion object {
        fun from(parent: ViewGroup): ElectionViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ElectionListItemBinding.inflate(layoutInflater, parent, false)
            return ElectionViewHolder(binding)
        }
    }

    fun bind(election: Election, listener: ElectionClickListener) {
        viewDataBinding.election = election
        viewDataBinding.clickListener = listener
        viewDataBinding.executePendingBindings()
    }
}

class ElectionClickListener (val clickListener: (election: Election) -> Unit) {
    fun onClick(election: Election) = clickListener(election)
}