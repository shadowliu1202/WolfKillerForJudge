package com.higgstar.wolfkillerforjudge.presetation.main

import androidx.lifecycle.ViewModel
import com.higgstar.wolfkillerforjudge.domain.interactor.GameManager
import com.higgstar.wolfkillerforjudge.domain.model.InitSetting
import com.higgstar.wolfkillerforjudge.domain.model.role.PlayRole
import io.reactivex.Completable

class GameViewModel(initRole: List<PlayRole>) : ViewModel() {
    private val gameManager:GameManager = GameManager()
    fun start() : Completable{
        gameManager.createGame(InitSetting())
    }
}
