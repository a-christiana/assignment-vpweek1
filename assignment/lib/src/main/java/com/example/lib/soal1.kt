package com.example.lib

fun main() {
    val manage = Manage()
    var run = true

    do{
        println("""
            ORDER SYSTEM
            1. Make order 
            2. View Orders 
            3. View Menu 
            4. Add Menu 
            5. Edit Menu 
            6. Delete Menu 
            7. Exit
        """.trimIndent())

        print("Choose menu: ")
        val input = readln()!!.toInt()

        if(input == 1){
            if(manage.menuList.size == 0){
                println("No available menu.")
            } else{
                print("Customer name: ")
                val cust = readln()
                val orItem = mutableListOf<Order>()
                var addtc = true

                do{
                    manage.showMenu()
                    print("Add Menu: ")
                    val ordr = readln()
                    print("Quantity: ")
                    val qtys = readln()!!.toInt()

                    var menuavail = false
                    for(i in manage.menuList){
                        if(i.nama.equals(ordr, ignoreCase = true)){
                            menuavail = true
                        }
                    }
                    if(menuavail){
                        orItem.add(Order(ordr, qtys))
                        println("Menu added to cart.")
                    } else{
                        println("Menu is not available")
                    }

                    print("Add another? (Y/N): ")
                    val ans = readln()
                    if(ans.lowercase() != "y"){
                        addtc = false
                    }
                } while(addtc)

                if(orItem.size > 0){
                    val newRec = Receipt(cust, orItem)
                    manage.addReceipt(newRec)
                }
            }
        } else if (input == 2){
            manage.viewOrder()
        } else if (input == 3){
            manage.showMenu()
        } else if (input == 4){
            print("Item name: ")
            val nama = readln()
            print("Item price: ")
            val harga = readln().toInt()
            print("Item description: ")
            val deskripsi = readln()
            manage.addMenu(nama, harga, deskripsi)
        } else if (input == 5){
            print("Search item: ")
            val search = readln()
            print("Item name: ")
            val namaBaru = readln()
            print("Item price: ")
            val hargaBaru = readln().toInt()
            print("Item description: ")
            val deskripsiBaru = readln()
            manage.editMenu(search, namaBaru, hargaBaru, deskripsiBaru)
        } else if (input == 6){
            print("Items to delete: ")
            val del = readln()
            manage.deleteMenu(del)
        } else if (input == 7){
            print("Thank You.")
            run = false
        } else{
            println("Invalid Input")
        }
    } while(run)
}