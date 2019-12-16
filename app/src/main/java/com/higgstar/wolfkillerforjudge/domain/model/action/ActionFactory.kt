package com.higgstar.wolfkillerforjudge.domain.model.action

class ActionFactory {
    companion object {
        fun createFirstNightAction(): List<Action> {
            return listOf(
                Action("狼人請睜眼,狼王請豎起大拇指,請選擇你要殺的對象,狼人請閉眼"),
                Action("女巫請睜眼，今晚她死了，你有一瓶解藥你要用嗎?，你有一瓶毒藥要用嗎?女巫請閉眼"),
                Action("預言家請睜眼，請選擇你要查驗的對象，預言家請閉眼"),
                Action("獵人請睜眼，獵人請閉眼"),
                Action("白癡請睜眼，白癡請閉眼")
            )
        }

        fun createFirstDayAction(): List<Action> {
            return listOf(Action("第一位開始發言"), Action("請投票"))
        }

        fun createNightAction(): List<Action> {
            return listOf(Action("狼人請睜眼,狼王請豎起大拇指,請選擇你要殺的對象,狼人請閉眼"))
        }

        fun createDayAction(): List<Action> {
            return listOf(Action("第一位開始發言"), Action("請投票"))
        }


    }

}