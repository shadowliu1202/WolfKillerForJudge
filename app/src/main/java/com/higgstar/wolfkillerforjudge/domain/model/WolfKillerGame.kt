package com.higgstar.wolfkillerforjudge.domain.model

import com.higgstar.wolfkillerforjudge.domain.model.role.BadSide
import com.higgstar.wolfkillerforjudge.domain.model.role.GoodSide

class WolfKillerGame private constructor() {
    enum class Judgement {
        Good, Bad, NotYet
    }

    private val goodSides: MutableList<GoodSide> = mutableListOf()
    private val badSides: MutableList<BadSide> = mutableListOf()

    class Builder {
        private val game: WolfKillerGame = WolfKillerGame()
        private var goodSide: List<GoodSide> = mutableListOf()
        private var badSide: List<BadSide> = mutableListOf()

        fun setBadSide(bad: List<BadSide>): Builder {
            badSide = bad
            return this
        }

        fun setGoodSide(good: List<GoodSide>): Builder {
            goodSide = good
            return this
        }

        fun build(): WolfKillerGame {
            game.goodSides.addAll(goodSide)
            game.badSides.addAll(badSide)
            return game
        }
    }

    fun judgeWinner(): Judgement {
        return when {
            goodSides.isEmpty() -> Judgement.Bad
            badSides.isEmpty() -> Judgement.Good
            else -> Judgement.NotYet
        }
    }
}
