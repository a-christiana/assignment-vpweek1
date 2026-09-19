package com.example.lib2

data class Monster(
    val nama: String,
    var hp: Int,
    val maxHp: Int,
    val type: String
)

data class Wizard(var nama: String){
    var maxhp = 50
    var hp = 50
    var maxmana = 30
    var mana = 30
    var kill = 0
    var needkill = 5
    var hppot = 5
    var mnpot = 5
    var evolve = false
    var stealife = 0

    fun dhppot(){
        if(hppot>0){
            hppot--
            hp += 25
            if(hp>maxhp){
                hp = maxhp
            }
            println("25 HP added")
        } else{
            println("HP potion unavailable")
        }
    }

    fun dmnpot(){
        if(mnpot>0){
            mnpot--
            mana += 25
            if(mana>maxmana){
                mana = maxmana
            }
            println("15 mana added")
        } else{
            println("Mana potion unavailable")
        }
    }

    fun cekEvolve(){
        if(!evolve && kill >= needkill){
            evolve = true
            maxhp = (maxhp*1.5).toInt()
            hp = maxhp
            maxmana = (maxmana*1.5).toInt()
            mana = maxmana
            stealife = 1
            println("CONGRATS! YOU'VE EVOLVED TO STRONG WIZARD")
        }
    }
}