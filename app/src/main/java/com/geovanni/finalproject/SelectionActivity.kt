package com.geovanni.finalproject

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.selection.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SelectionActivity : AppCompatActivity() {
    var teamList = arrayListOf<String>("fans")

    val leagueYear = mutableListOf("2017/18","2018/19")
    val leagueList = mutableListOf("English Premier League",
        "Bundesliga", "La Liga")


    private val BASE_URL = "https://api.football-data.org/v2/"
    private val KEY ="51106764eb43472dae47e6d193bca5ee"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val statService = retrofit.create(StatService::class.java)

    var leagueID = "PL"
    var leagueSeason = "2018"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selection)


        LeagueTable.setOnClickListener {
            val myIntent = Intent(this, LeagueTableActivity::class.java)
            myIntent.putExtra("league", leagueID)
            myIntent.putExtra("season", leagueSeason)
            startActivity(myIntent)
        }

        topScorer.setOnClickListener {
            val myIntent = Intent(this, TopScorerActivity::class.java)
            myIntent.putExtra("league", leagueID)
            myIntent.putExtra("season", leagueSeason)
            startActivity(myIntent)
        }
        signOut.setOnClickListener{
            FirebaseAuth.getInstance().signOut();
            startActivity(Intent(this, Login::class.java))
            finish()
        }



        val myAdopter3 = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, teamList)
        val myAdopter2 = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, leagueYear)
        val myAdopter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, leagueList)




        Teams.adapter = myAdopter3




        league.adapter = myAdopter

        league.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueID = when(position){
                    0 -> "PL"
                    1 -> "BL1"
                    2 -> "PD"
                    else -> "PL"

                }
                teamList.clear()
                genTeams()
                myAdopter3.notifyDataSetChanged()

            }
        }



        Season.adapter = myAdopter2

        Season.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueSeason = when(position){
                    0 -> "2018"
                    1 -> "2019"
                    else -> "2018"


                }


                teamList.clear()
                genTeams()
                myAdopter3.notifyDataSetChanged()


            }


        }






        Teams.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //open activity
            }
        }





    }



    fun genTeams(){
        val teams = ArrayList<String>()
        statService.teams(" $KEY",leagueID,leagueSeason).enqueue(object : Callback<teamsFromCompetition> {
            override fun onFailure(call: Call<teamsFromCompetition>, t: Throwable) {
            }
            override fun onResponse(call: Call<teamsFromCompetition>, response: Response<teamsFromCompetition>) {
                val body = response.body()
                if (body == null) {

                    return
                }

                for(i in 0..body.teams.size -1){
                    teams.add(body.teams[i].name)
                    val inter = body.teams[i].name
                    teamList.add(body.teams[i].name)

                }
            }
        })

    }


    fun genFixtures(){
        val teams = ArrayList<String>()
        statService.teams(" $KEY",leagueID,leagueSeason).enqueue(object : Callback<teamsFromCompetition> {
            override fun onFailure(call: Call<teamsFromCompetition>, t: Throwable) {
            }
            override fun onResponse(call: Call<teamsFromCompetition>, response: Response<teamsFromCompetition>) {
                val body = response.body()
                if (body == null) {

                    return
                }

                for(i in 0..body.teams.size -1){
                    teams.add(body.teams[i].name)
                    val inter = body.teams[i].name
                    teamList.add(body.teams[i].name)

                }
            }
        })

    }





}