package com.example.lib

class Manage{
    val menuList = mutableListOf<Menu>()
    val orderList = mutableListOf<Receipt>()

    fun addMenu(nama: String, harga: Int, deskripsi: String){
        val menuBaru = Menu(nama, harga, deskripsi)
        menuList.add(menuBaru)
        println("New menu added successfully.")
    }

    fun showMenu(){
        if(menuList.size == 0){
            println("No data found.")
        } else{
            println("\n=== DAFTAR MENU ===")
            for(i in 0 until menuList.size){
                val m = menuList[i]
                println("${i+1}. ${m.nama} - Rp.${m.harga}")
                println("${m.deskripsi}")
            }
        }
    }

    fun editMenu(search: String, namaBaru: String, hargaBaru: Int, deskripsiBaru: String){
        var found = false
        for(i in 0 until menuList.size){
            if(menuList[i].nama.equals(search, ignoreCase = true)){
                menuList[i] = Menu(namaBaru, hargaBaru, deskripsiBaru)
                found = true
                println("Menu updated.")
            }
        }
        if(found == false) {
            println("Menu not found.")
        }
    }
    fun deleteMenu(search: String){
        var del = -1
        for(i in 0 until menuList.size){
            if(menuList[i].nama.equals(search, ignoreCase = true)){
                del = i
                break
            }
        }
        if(del != -1){
            menuList.removeAt(del)
            println("Menu deleted.")
        } else{
            println("Menu not found.")
        }
    }

    fun addReceipt(receipt: Receipt) {
        orderList.add(receipt)
        println("Order created.")
    }

    fun viewOrder(){
        if(orderList.size == 0){
            println("Cart is empty.")
        } else{
            for(i in orderList){
                println("\n-- ${i.pembeli}'s Order --")
                var total = 0
                for(j in 0 until i.pesanan.size){
                    val mnu = i.pesanan[j]
                    var price = 0
                    for(k in menuList){
                        if(k.nama.equals(mnu.namaMenu, ignoreCase = true)){
                            price = k.harga
                        }
                    }

                    val subtotal = price * mnu.qty
                    total += subtotal
                    println("${j+1}. ${mnu.namaMenu} x${mnu.qty}     Rp.${subtotal}")
                }
                println("==============================")
                println("TOTAL              Rp.${total}")
            }
        }
    }
}
