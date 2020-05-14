package com.geovanni.finalproject

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.scorer.view.*

class TopScorerRecyclerAdapter (val context: Context, private val ScorerList: ArrayList<scorers>)
    : RecyclerView.Adapter<TopScorerRecyclerAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.scorer, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val currentItem = ScorerList[position]

        holder.name.text = currentItem.player.name
        holder.goals.text = currentItem.numberOfGoals.toString()
        holder.club.text = currentItem.team.name
        holder.position.text = currentItem.player.position
        holder.nationality.text = currentItem.player.nationality
        holder.age.text = "none given"
        //holder.profileImage.setImageResource(currentItem.profileImage)






    }

    override fun getItemCount(): Int {
        return ScorerList.size
    }


    inner class MyViewHolder (itemView: View): RecyclerView.ViewHolder (itemView){

        val name = itemView.Name
        val goals = itemView.goal
        val club = itemView.club
        val position = itemView.position
        val age = itemView.age
        val nationality = itemView.nationality
        val profileImage = itemView.image_profile



    }
}