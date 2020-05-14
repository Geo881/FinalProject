package com.geovanni.finalproject
import com.google.gson.annotations.SerializedName

data class Competitions (
    val competitions: List<competitionTFC>

)

data class competitionTFC(
    val id: Int,
    val area: area,
    val name: String,
    val code: String,
    @SerializedName("plan")val tier: String,
    val currentSeason: currentSeason

)

data class competition(
    val id: Int,
    val area: area,
    val name: String,
    val code: String,
    @SerializedName("plan")val tier: String,
    val currentSeason: currentSeason,
    val season: List<season>

)

data class teamsFromCompetition(
    val count: Int,
    @SerializedName("competition")val competitionTFC: competitionTFC,
    val season: season,
    val teams: List<team>

)

data class LeagueTable(
    val competition: competitionTFC,
    val season: season,
    //val standings: standing
    val standings: List<standing>
)

data class matchPeriod(
    val homeTeam: Int,
    val awayTeam: Int
)


data class score(

    val winner:String,
    val duration: String,
    val fullTime: matchPeriod,
    val halfTime: matchPeriod,
    val extraTime: matchPeriod,
    val penalties: matchPeriod
)


data class Player(
    val id: Int,
    val name:String ,
    val dateOfBirth:String,
    val countryOfBirth:String,
    val nationality:String,
    val position:String
)


data class scorers(
    val player : Player,
    val team: team2,
    val numberOfGoals: Int

)


data class topScorers(
    val scorers: List<scorers>
)

data class area(
    val id:Int,
    val name: String
)

data class team(
    val id:Int,
    val area: area,
    val name:String,
    val shortName:String,
    val tla:String,
    val crestUrl:String,
    val address:String,
    val phone:String,
    val website:String,
    val email:String,
    val founded:Int,
    val clubColors:String,
    val venue:String,
    val squad: List<Player>
)

data class teamPosition(
    val position:Int,
    val team:team3,
    val playedGames: Int,
    val won: Int,
    val draw: Int,
    val lost: Int,
    val points: Int,
    val goalsFor: Int,
    val goalsAgainst: Int,
    val goalDifference: Int
)

data class standing(
    val stage:String,
    val type:String,
    val group:String,
    val table: List<teamPosition>
)
data class team3(
    val id:Int,
    val name:String,
    val crestUrl:String
)

data class team2(
    val id:Int,
    val name:String
    //val crestUrl:String
)

data class teamForGoal(
    val id:Int,
    val name:String
)

data class season(
    val id: String,
    val startDate:String,
    val endDate: String,
    val currentMatchday:Int
)

data class teamx(

    val id: Int,
    val area: area,
    val name:String,
    val shortName:String,
    val tla:String,
    val crestUrl:String,
    val address:String,
    val phone: String,
    val website:String,
    val email:String,
    val founded:Int,
    val clubColors:String,
    val venue:String
)
data class TeamSimp(
    val wins:Int,
    val draws:Int,
    val losses:Int
)

data class head2head(
    val numberOfMatches:Int,
    val totalGoals:Int ,
    val homeTeam:TeamSimp,
    val awayTeam: TeamSimp
)

data class competitionForMatch(
    val id:Int,
    val name:String
)

data class matchForMatch(
    val id:Int,
    val competition: competitionForMatch,
    val season: season,
    val utcDate: String,
    val status:String,
    val minute:String,
    val attendance:Int ,
    val venue:String,
    val matchday:Int,
    val stage:String,
    val group:String,
    val lastUpdated:String,
    val homeTeam: teamComplex,
    val awayTeam: teamComplex

)

data class player(
    val id:Int,
    val name:String,
    val position:String,
    val shirtNumber:Int

)
data class captain(
    val id:Int,
    val name:String,
    val shirtNumber:Int

)
data class teamComplex(
    val id:Int,
    val name:String,
    val captain: captain,
    val lineup: List<Player>,
    val bench: List<Player>
)

data class playerForGoal(
    val id: Int,
    val name:String
)

data class goal(
    val minute: Int,
    val extraTime: String,
    val type: String,
    val team: teamForGoal,
    val scorer:playerForGoal,
    val assist:playerForGoal

)


data class booking(
    val minute:Int,
    val team:teamForGoal,
    val player:playerForGoal,
    val card:String
)

data class oneMatch(
    val head2head: head2head,
    val match:matchForMatch,
    val score: score,
    val goals: List<goal>,
    val bookings: List<booking>
)

data class matchesAcrossComp(
    val matches: List<oneMatch>
)





data class currentSeason(
    val id: String,
    val startDate:String,
    val endDate: String,
    val currentMatchday:Int
)



