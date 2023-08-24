package com.monthlycoding.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import java.time.LocalTime

class TimeTest {
    @ParameterizedTest
    @ValueSource(
        ints = [0, 1, 22, 23]
    )
    fun `현재시각을 "H" 형태로 포멧팅해서 가져온다`(hour: Int) {
        // given

        // when
        val nowHour = Time.getFormattedHour {
            LocalTime.of(hour, 1)
        }

        // then
        assertThat(nowHour).isEqualTo(hour)
    }

    @ParameterizedTest
    @CsvSource(
        value = [
            "6,MORNING", "10,MORNING",
            "11,LUNCH", "13,LUNCH",
            "14,LATE_LUNCH", "17,LATE_LUNCH",
            "18,NIGHT", "20,NIGHT",
            "21,LATE_NIGHT", "23,LATE_NIGHT",
            "0,DAWN", "5,DAWN"
        ],
    )
    fun `현재시각으로 시간대를 가져온다`(hour: Int, time: Time) {
        // given

        // when
        val result = Time.getCurrentTime(hour)

        // then
        assertThat(result).isEqualTo(time)
    }

    @ParameterizedTest
    @ValueSource(ints = [-1, 24])
    fun `-1시 24시 는 예외처리한다`(value: Int) {
        // given

        // when
        val throws = assertThrows<IllegalArgumentException> { Time.getCurrentTime(value) }

        // then
        assertThat(throws.message).isEqualTo("$value : 잘못된 시간 입력입니다.")
    }
}
