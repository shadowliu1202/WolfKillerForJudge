package com.higgstar.wolfkillerforjudge.domain.model

import com.higgstar.wolfkillerforjudge.domain.model.role.BadSide
import com.higgstar.wolfkillerforjudge.domain.model.role.GoodSide

data class InitSetting(
    val playerAmount: Int,
    val badGuys: List<BadSide>,
    val goodGuys: List<GoodSide>
) {


}
