package com.geovanni.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.top_scorers.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LeagueTableActivity : AppCompatActivity() {
    val tableList = ArrayList<teamPosition>()
    val adapter = LeagueTableRecylerAdapter(this,tableList)

    private val BASE_URL = "https://api.football-data.org/v2/"
    private val KEY ="51106764eb43472dae47e6d193bca5ee"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val statService = retrofit.create(StatService::class.java)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.league_positions)

        val leagueID = intent.getStringExtra("league")
        val leagueSeason = intent.getStringExtra("season")

        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)


        genTable(leagueID,leagueSeason)


        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recycler_view.addItemDecoration(dividerItemDecoration)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        supportActionBar?.hide()
    }


    fun genTable(id:String,year:String) {
        statService.standing(" $KEY", id, year).enqueue(object : Callback<LeagueTable> {
            override fun onFailure(call: Call<LeagueTable>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<LeagueTable>,
                response: Response<LeagueTable>
            ) {
                val body = response.body()
                if (body == null) {

                    return
                }
                for (i in 0..body.standings[0].table.size-1) {
                    tableList.add(body.standings[0].table[i])
                }
                adapter.notifyDataSetChanged()

            }
        })
    }
}
