package com.shaparapatah.poplibs.screens

import com.github.terrakok.cicerone.Screen

interface IScreens {


    fun users(): Screen

    fun userInfo(userLogin: String): Screen

}