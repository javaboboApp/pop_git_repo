package com.example.poptestluis.ui.repos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.poptestluis.R
import com.example.poptestluis.models.GitRepository
import kotlinx.android.synthetic.main.item_adapter_git_repos.view.*

class RepoListAdapter : PagingDataAdapter<GitRepository, RepoListAdapter.RepoListViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_adapter_git_repos, parent, false)


        return RepoListViewHolder(view)
    }



    override fun onBindViewHolder(holder: RepoListViewHolder, position: Int) {
        getItem(position)?.let { gitRepository ->
            holder.bind(gitRepository)
        }
    }

    inner   class  RepoListViewHolder(val repo_view: View): RecyclerView.ViewHolder(repo_view){
      fun bind(gitRepository: GitRepository) = with(itemView){
          title.text = gitRepository.name
          desc.text = gitRepository.description

      }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<GitRepository>() {
        override fun areItemsTheSame(oldItem: GitRepository, newItem: GitRepository): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GitRepository, newItem: GitRepository): Boolean {
            return oldItem == newItem

        }

    }

}