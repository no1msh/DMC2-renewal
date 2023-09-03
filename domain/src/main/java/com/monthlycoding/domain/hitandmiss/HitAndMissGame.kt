package com.monthlycoding.domain.hitandmiss

class HitAndMissGame(hitCount: Int, private val totalCardsCount: Int = DEFAULT_GAME_SIZE) {

    private val _cards: MutableList<HitAndMissCard> = mutableListOf()
    val cards: List<HitAndMissCard> get() = _cards

    init {
        initCards(hitCount)
    }

    private fun initCards(hitCount: Int) {
        repeat(hitCount) {
            _cards.add(HitAndMissCard(isHit = true))
        }
        repeat(totalCardsCount - hitCount) {
            _cards.add(HitAndMissCard(isHit = false))
        }
        _cards.shuffle()
    }

    fun flipCard(cardIndex: Int) {
        _cards[cardIndex] = _cards[cardIndex].copy(isFlipped = true)
    }

    companion object {
        private const val DEFAULT_GAME_SIZE = 9
    }
}
