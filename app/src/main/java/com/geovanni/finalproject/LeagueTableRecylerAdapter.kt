package com.geovanni.finalproject

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_position.view.*

class LeagueTableRecylerAdapter(val context: Context, private val standingList: ArrayList<teamPosition>)
: RecyclerView.Adapter<LeagueTableRecylerAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueTableRecylerAdapter.MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.team_position, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val currentItem = standingList[position]
        
        holder.teamName.text = currentItem.team.name
        holder.gameStats.text = "Games Played:${currentItem.playedGames.toString()}  " +
                "Won:${currentItem.won.toString()}  " +
                "Draw:${currentItem.draw.toString()}  " +
                "Lose:${currentItem.lost.toString()}"
        holder.points.text = "Points: ${currentItem.points.toString()}"
        holder.goalForward.text = "Goal Scored: ${currentItem.goalsFor.toString()}"
        holder.goalAgainst.text = "Goal Conceded: ${currentItem.goalsAgainst.toString()}"
        holder.goalDifference.text = "Goal Difference: ${currentItem.goalDifference.toString()}"
        GlideToVectorYou
            .init()
            .with(this.context)
            .load(Uri.parse(currentItem.team.crestUrl), holder.profileImage)









    }

    override fun getItemCount(): Int {
        return standingList.size
    }


    inner class MyViewHolder (itemView: View): RecyclerView.ViewHolder (itemView){

        val teamName = itemView.teamName
        val gameStats = itemView.gameStats
        val points = itemView.points
        val goalForward = itemView.goalFoward
        val goalAgainst = itemView.goalsAgainst
        val goalDifference = itemView.goalDifference
        val profileImage = itemView.image_profile



    }





}