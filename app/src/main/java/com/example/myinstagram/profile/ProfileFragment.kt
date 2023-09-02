package com.example.myinstagram.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myinstagram.R

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rv = view.findViewById<RecyclerView>(R.id.rv_profile_grid)
        rv.layoutManager  = GridLayoutManager(requireContext(), 3)
        rv.adapter = PostAdapter()


    }
    private class PostAdapter: RecyclerView.Adapter<PostAdapter.PostViewHolder>(){
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
            val inflater =LayoutInflater.from(parent.context).inflate(R.layout.item_profile_grid, parent, false)
            return PostViewHolder(inflater)
        }

        override fun getItemCount(): Int {
            return 30
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            holder.bind(R.drawable.ic_insta_add)
        }

        private class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            fun bind(image: Int){
                itemView.findViewById<ImageView>(R.id.img_profile_photo).setImageResource(image)
            }
        }
    }
}