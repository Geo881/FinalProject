package com.geovanni.finalproject

import retrofit2.Call
import retrofit2.http.*

private const val API_KEY ="51106764eb43472dae47e6d193bca5ee"

private const val Token_Name =  "geovanni881"//"geo881"
private const val Token_ID = "936308c7a233085f90f5c280a3bc03e3 X-Auths-Token"

interface StatService {


    @GET("competitions/{id}")
    fun searchLeagues(@Header("X-Auth-Token") authHeader: String,
                      @Path("id")id:String) : Call<Any>

    @GET("competitions/2021")
    fun premeirLeague(@Header("X-Auth-Token") authHeader: String)
            : Call<competitionTFC>

    @GET("competitions/BL1")
    fun gerLeague(@Header("X-Auth-Token") authHeader: String)
            : Call<competitionTFC>

    @GET("competitions/PD")
    fun spanLeague(@Header("X-Auth-Token") authHeader: String)
            : Call<competitionTFC>

    @GET("competitions/{id}/teams")
    fun teams(@Header("X-Auth-Token") authHeader: String,
              @Path("id")id:String,
              @Query("season") year: String) : Call<teamsFromCompetition>

    @GET("competitions/{id}/scorers")
    fun scorers(@Header("X-Auth-Token") authHeader: String,
                @Path("id")id:String,
                @Query("season") year: String) : Call<topScorers>

    @GET("competitions/{id}/standings")
    fun standing(@Header("X-Auth-Token") authHeader: String,
                 @Path("id")id:String,
                 @Query("season") year: String) : Call<LeagueTable>

    @GET("/v2/teams/{id}/matches/")
    fun allMatchFrTeam(@Header("X-Auth-Token") authHeader: String,
                       @Path("id")id:String,
                       @Query("dateFrom") dateFrom: String,
                       @Query("dateTo") dateTo: String) : Call<Any>















}