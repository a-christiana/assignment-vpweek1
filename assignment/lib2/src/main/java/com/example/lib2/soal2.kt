package com.example.lib2

fun main() {
    var run = true

    do{
        println("----- WIZARD ADVENTURE -----")
        print("What's your name? ")
        val name = readln()
        val wiz = Wizard(name)
        val manage = Manage(wiz)
        println("Goodluck ${wiz.nama}! You're gonna need it!")
        var start = true

        do{
           print("""
               What are you going to do?
               1. View Stats
               2. Enter Battle
               Choose: 
           """.trimIndent())
           val input = readln().toIntOrNull()

           if(input == 1){
               var stats = true

               do{
                   manage.showStats()
                   print("""
                       a. Drink Mana Potion
                       b. Drink Health Potion
                       c. Rename Self
                       d. Back
                   """.trimIndent())
                   val instat = readln()

                   if(instat.equals("a", ignoreCase = true)){
                       wiz.dmnpot()
                   } else if(instat.equals("b", ignoreCase = true)){
                       wiz.dhppot()
                   } else if(instat.equals("c", ignoreCase = true)){
                       print("Input new name: ")
                       wiz.nama = readln()
                       println("Name changed.")
                   } else if(instat.equals("d", ignoreCase = true)){
                       stats = false
                   } else{
                       println("Invalid input.")
                   }
               } while(stats)
           } else if (input == 2){
               val monst = manage.generateMonst()
               println("${monst.nama} ${monst.type} is coming!")
               var battleon = true

               do{
                   manage.battleStat(monst)
                   print("""
                       a. Fire Attack
                       b. Water Attack
                       c. Grass Attack
                       d. Drink Potion
                       e. Run
                       Choose Action: 
                   """.trimIndent())
                   val inbatt = readln()

                   var wizattacked = false
                   if(inbatt.equals("a", ignoreCase = true)){
                        wizattacked = manage.attack(monst, "Fire")
                   } else if(inbatt.equals("b", ignoreCase = true)){
                       wizattacked = manage.attack(monst, "Water")
                   } else if(inbatt.equals("c", ignoreCase = true)){
                       wizattacked = manage.attack(monst, "Grass")
                   } else if(inbatt.equals("d", ignoreCase = true)){
                       print("""
                           1. HP Potion
                           2. Mana Potion
                           Choose Potion: 
                       """.trimIndent())
                       val inpot = readln()
                       if(inpot == "1") wiz.dhppot()
                       else if(inpot == "2") wiz.dmnpot()
                   } else if(inbatt.equals("e", ignoreCase = true)){
                       println("You escaped.")
                       battleon = false
                   } else{
                       println("Invalid input.")
                   }

                   if(monst.hp <= 0){
                       manage.monstDef(monst)
                       battleon = false
                   } else if(wizattacked && battleon){
                       manage.monstAtt(monst)
                   }

                   if(wiz.hp <= 0){
                       println("YOU DIED! GAME OVER.")
                       battleon = false
                       start = false
                   }
               } while(battleon && monst.hp > 0 && wiz.hp > 0)
           } else {
               println("Wrong choice.")
           }
        } while(start && wiz.hp>0)
    } while(run)
}