package com.monthlycoding.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CounterTest {
    @Test
    fun `카운터의 값을 증가시킬 수 있다`() {
        // given
        val counter = Counter()

        // when
        counter.increase()

        // then
        assertThat(counter.count).isEqualTo(2)
    }

    @Test
    fun `지정한 최대값을 넘어서 증가시키지 못한다`() {
        // given
        val maxCount = 8
        val counter = Counter(8, maxCount)

        // when
        counter.increase()

        // then
        assertThat(counter.count).isEqualTo(maxCount)
    }

    @Test
    fun `카운터의 값을 감소시킬 수 있다`() {
        // given
        val counter = Counter(2)

        // when
        counter.decrease()

        // then
        assertThat(counter.count).isEqualTo(1)
    }

    @Test
    fun `카운터의 값은 0 보다 더 감소 될 수 없다`() {
        // given
        val counter = Counter(1)

        // when
        counter.decrease()

        // then
        assertThat(counter.count).isEqualTo(1)
    }

    @Test
    fun `카운터의 값이 최소 값인지 확인할 수 있다`() {
        // given
        val counter = Counter(1)

        // when
        val actual = counter.isMinCount()

        // then
        assertThat(actual).isTrue
    }

    @Test
    fun `카운터의 값이 최대 값인지 확인할 수 있다`() {
        // given
        val counter = Counter(8)

        // when
        val actual = counter.isMaxCount()

        // then
        assertThat(actual).isTrue
    }



}
