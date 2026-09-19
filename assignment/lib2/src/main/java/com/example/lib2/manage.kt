package com.example.lib2

import kotlin.random.Random

class Manage(val wiz: Wizard){
    fun showStats() {
        println("""
            \n -----${wiz.nama}'s Stats-----
            HP: ${wiz.hp}/ ${wiz.maxhp}
            Mana: ${wiz.mana}/ ${wiz.maxmana}
            Kills needed: ${wiz.kill}/ ${wiz.needkill}
            HP Potions: ${wiz.hppot}
            Mana Potions: ${wiz.mnpot}
        """.trimIndent())
        if (wiz.evolve) {
            println("Lifesteal: ${wiz.stealife}")
        }
        println("-------------------")
    }

    fun generateMonst(): Monster{
        val type = listOf("Fire", "Water", "Grass")
        val rantype = type[Random.nextInt(type.size)]
        val monstName = rantype + "mon"
        val monstHp = 30
        return Monster(monstName, monstHp, monstHp, rantype)
    }

    fun battleStat(monst: Monster){
        println("""
            \n --- BATTLE ---
            HP: ${wiz.hp}/ ${wiz.maxhp}
            Mana: ${wiz.mana}/ ${wiz.maxmana}
            HP Potions: ${wiz.hppot}
            Mana Potions: ${wiz.mnpot}
            ----------------------
            ${monst.nama}
            HP: ${monst.hp}/ ${monst.maxHp}
            Type: ${monst.type}
            ----------------------
        """.trimIndent())
    }

    fun attack(monst: Monster, attype: String): Boolean {
        if (wiz.mana < 10) {
            println("Mana is not enough.")
            return false
        }
        wiz.mana -= 10

        var dmg = 10
        if(wiz.evolve){
            dmg = (dmg*1.5).toInt()
        }

        var multi = 1
        if(attype == "Fire" && monst.type == "Grass") multi = 2
        else if(attype == "Water" && monst.type == "Fire") multi = 2
        else if(attype == "Grass" && monst.type == "Water") multi = 2

        val totaldmg = (dmg * multi).toInt()
        monst.hp -= totaldmg
        println("${monst.nama} ${attype} was attacked with ${totaldmg} damage.")

        if(wiz.evolve && wiz.stealife > 0){
            wiz.hp += wiz.stealife
            if(wiz.hp > wiz.maxhp){
                wiz.hp = wiz.maxhp
                println("Lifesteal activated. You steal ${wiz.stealife} HP.")
            }
        }
        return true
    }

    fun monstAtt(monst: Monster){
        println("\n${monst.nama} attack 10 damage.")
        wiz.hp -= 10
    }

    fun monstDef(monst: Monster){
        println("You defeat ${monst.nama}")
        wiz.kill++
        if(wiz.evolve){
            wiz.stealife++
            println("You gained ${wiz.stealife}.")
        }
        wiz.cekEvolve()
    }
}