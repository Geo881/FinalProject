package com.geovanni.finalproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.top_scorers.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TopScorerActivity : AppCompatActivity() {

    val ScorerList = ArrayList<scorers>()
    val adapter = TopScorerRecyclerAdapter(this,ScorerList)

    private val BASE_URL = "https://api.football-data.org/v2/"
    private val KEY ="51106764eb43472dae47e6d193bca5ee"

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val statService = retrofit.create(StatService::class.java)





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.top_scorers)

        val leagueID = intent.getStringExtra("league")
        val leagueSeason = intent.getStringExtra("season")



        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        genScorers(leagueID,leagueSeason)
        adapter.notifyDataSetChanged()

        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recycler_view.addItemDecoration(dividerItemDecoration)
    }




    fun genScorers(id:String,year:String) {
        statService.scorers(" $KEY", id, year).enqueue(object : Callback<topScorers> {
            override fun onFailure(call: Call<topScorers>, t: Throwable) {
            }

            override fun onResponse(
                call: Call<topScorers>,
                response: Response<topScorers>
            ) {
                val body = response.body()
                if (body == null) {

                    return
                }
                for (i in 0..body.scorers.size - 1) {
                    ScorerList.add(body.scorers[i])
                }
                adapter.notifyDataSetChanged()

            }
        })
    }
}
