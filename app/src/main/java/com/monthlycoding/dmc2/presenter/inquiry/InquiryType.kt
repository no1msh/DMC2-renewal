package com.monthlycoding.dmc2.presenter.inquiry

enum class InquiryType(val id: Int) {
    SPOT(0),
    APP(1),
    SCHOOL(2),
    ETC(3);

    companion object {
        fun findById(id: Int): InquiryType = when (id) {
            0 -> SPOT
            1 -> APP
            2 -> SCHOOL
            3 -> ETC
            else -> throw IllegalArgumentException("[id: $id] 잘못된 id 입니다.")
        }
    }
}
