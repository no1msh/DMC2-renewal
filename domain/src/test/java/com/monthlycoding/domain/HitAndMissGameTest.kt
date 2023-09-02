package com.monthlycoding.domain


import com.monthlycoding.domain.hitandmiss.HitAndMissGame
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HitAndMissGameTest {
    @Test
    fun `복불복은 총 9장의 카드로 구성된다`() {
        // given
        val hitCount = 0
        val game = HitAndMissGame(hitCount)

        // when
        val actual = game.cards.size

        // then
        assertThat(actual).isEqualTo(9)
    }

    @Test
    fun `당첨 인원 만큼 당첨 카드가 생성된다`() {
        // given
        val hitCount = 5
        val game = HitAndMissGame(hitCount)

        // when
        val actualHitCount = game.cards.count { it.isHit }

        // then
        assertThat(hitCount).isEqualTo(actualHitCount)
    }

    @Test
    fun `카드를 뒤집은 상태로 만들 수 있다`() {
        // given
        val hitCount = 3
        val cardIndex = 4
        val game = HitAndMissGame(hitCount)

        // when
        game.flipCard(cardIndex)

        // then
        assertThat(game.cards[cardIndex].isFlipped).isTrue
    }
}
