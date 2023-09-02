package com.monthlycoding.domain

class Counter(
    initCount: Int = DEFAULT_INIT_COUNT,
    private val maxCount: Int = DEFAULT_MAX_COUNT,
) {
    var count = initCount
        private set

    fun increase() {
        if (count == maxCount) return
        count++
    }

    fun decrease() {
        if (count == DEFAULT_INIT_COUNT) return
        count--
    }

    companion object {
        private const val DEFAULT_INIT_COUNT = 0
        private const val DEFAULT_MAX_COUNT = 8
    }
}
