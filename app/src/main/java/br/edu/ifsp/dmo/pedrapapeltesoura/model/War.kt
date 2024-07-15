package br.edu.ifsp.dmo.pedrapapeltesoura.model

class War(battles: Int, player1: String, player2: String) {
    private var battle: Int = 0
    private var foughtbattle: Int = 0
    val opponent1: Player = Player(player1)
    val opponent2: Player = Player(player2)

    init {
        battle =  (if(battles > 0) battles else 1)
    }

    fun getWinner(): Player{
        if(hasBattles()){
            throw NoWarFinishException("The war did not finish.")
        }else{
            return if (opponent1.points >= opponent2.points){
                opponent1
            }else{
                opponent2
            }
        }
    }

    fun toBattle(player1Weapon: Weapon, player2Weapon: Weapon): Player?{
        var winner: Player? = null
        if(hasBattles()){
            if (player1Weapon != player2Weapon){
                winner = when{
                    player1Weapon is Rock && player2Weapon is Scissors
                    -> opponent1
                    player1Weapon is Scissors && player2Weapon is Paper
                    -> opponent1
                    player1Weapon is Paper && player2Weapon is Rock
                    -> opponent1
                    else -> opponent2
                }
                winner.recordPoint()
                foughtbattle += 1
            }else{
                winner = null
            }
        }
        return winner
    }

    fun hasBattles() = foughtbattle < battle
}
class NoWarFinishException(msg: String) : Exception(msg)