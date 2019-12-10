package com.higgstar.wolfkillerforjudge.domain.interactor

import com.higgstar.wolfkillerforjudge.domain.model.InitSetting
import com.higgstar.wolfkillerforjudge.domain.model.WolfKillerGame
import io.reactivex.Single

class GameManager {
    enum class DayAndNight {
        DayBreak, DayOff
    }

    fun createGame(initSetting: InitSetting): WolfKillerGame {
        return WolfKillerGame.Builder()
            .setBadSide(initSetting.badGuys)
            .setGoodSide(initSetting.goodGuys)
            .build()
    }


    fun startGame(game: WolfKillerGame): Single<GameRound<*>> {
        return Single.just(GameRound.InitRoundNight())
    }

    sealed class GameRound<T>(val round: Int = 1, val dayAndNight: DayAndNight) {
        class InitRoundNight : GameRound<String>(dayAndNight = DayAndNight.DayOff) {
            override fun onNext(trigger: Single<String>): Single<GameRound<String>> {
                return trigger.flatMap { Single.just(InitRoundDay()) }
            }
        }

        class InitRoundDay : GameRound<String>(dayAndNight = DayAndNight.DayBreak) {
            override fun onNext(trigger: Single<String>): Single<GameRound<String>> {
                return trigger.flatMap { Single.just(RoundNight(2)) }
            }
        }

        class RoundNight(currentRound: Int) : GameRound<String>(currentRound, DayAndNight.DayOff) {
            override fun onNext(trigger: Single<String>): Single<GameRound<String>> {
                return trigger.flatMap { Single.just(RoundDay(round)) }
            }
        }

        class RoundDay(currentRound: Int) : GameRound<String>(currentRound, DayAndNight.DayBreak) {
            override fun onNext(trigger: Single<String>): Single<GameRound<String>> {
                return trigger.flatMap { Single.just(JudgeRound(round)) }
            }
        }

        class JudgeRound(currentRound: Int) :
            GameRound<String>(currentRound, DayAndNight.DayBreak) {
            override fun onNext(trigger: Single<String>): Single<GameRound<String>> {
                return Single.never()
            }
        }


        abstract fun onNext(trigger: Single<T>): Single<GameRound<T>>
    }


}