package com.example.shoppingapp.repositories

import com.example.shoppingapp.db.Stuff
import com.example.shoppingapp.db.StuffDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailStuffRepository@Inject constructor(
    val stuffDao: StuffDao
){
    fun update(stuff : Stuff) = stuffDao.update(stuff.uid)
    fun updateCancel(stuff : Stuff) = stuffDao.updateCancel(stuff.uid)
}