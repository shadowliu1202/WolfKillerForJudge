package com.higgstar.wolfkillerforjudge.domain.interactor

import com.higgstar.wolfkillerforjudge.domain.model.InitSetting
import com.higgstar.wolfkillerforjudge.domain.model.WolfKillerGame

class GameManager {
    fun createGame(initSetting: InitSetting): WolfKillerGame {
        return WolfKillerGame.Builder().setBadSide(initSetting.badGuys)
            .setGoodSide(initSetting.goodGuys).build()
    }
}