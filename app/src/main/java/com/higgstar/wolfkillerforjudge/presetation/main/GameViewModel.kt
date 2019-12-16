package com.higgstar.wolfkillerforjudge.presetation.main

import androidx.lifecycle.ViewModel
import com.higgstar.wolfkillerforjudge.domain.interactor.GameManager
import com.higgstar.wolfkillerforjudge.domain.model.InitSetting
import com.higgstar.wolfkillerforjudge.domain.model.action.Action
import com.higgstar.wolfkillerforjudge.domain.model.role.PlayRole
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.PublishSubject

class GameViewModel : ViewModel() {
    val roundAction: PublishSubject<List<Action>> = PublishSubject.create()
    val initNight: PublishSubject<String> = PublishSubject.create()
    val initDay: PublishSubject<String> = PublishSubject.create()

    val secondNight: PublishSubject<String> = PublishSubject.create()
    val secondDay: PublishSubject<String> = PublishSubject.create()
    private val gameManager: GameManager = GameManager()
    fun start(initRole: List<PlayRole>): Single<String> {
        val game = gameManager.createGame(InitSetting(0, emptyList(), emptyList()))
        return gameManager.startGame(game).flatMap { playGameRound(it) }
    }

    private fun playGameRound(round: GameManager.GameRound<*>): Single<String> {
        println("${round.round}:${round.dayAndNight.name}")
        roundAction.onNext(round.createAction())
        return when (round) {
            is GameManager.GameRound.InitRoundNight -> round.onNext(initNight.firstOrError()).flatMap(this::playGameRound)
            is GameManager.GameRound.InitRoundDay -> round.onNext(initDay.firstOrError()).flatMap(this::playGameRound)
            is GameManager.GameRound.RoundNight -> round.onNext(secondNight.firstOrError()).flatMap(this::playGameRound)
            is GameManager.GameRound.RoundDay -> round.onNext(secondDay.firstOrError()).flatMap(this::playGameRound)
            is GameManager.GameRound.JudgeRound -> return Single.just("Finished")
        }
    }


}
