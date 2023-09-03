package com.monthlycoding.domain

class Counter(
    initCount: Int = DEFAULT_INIT_COUNT,
    private val minCount: Int = DEFAULT_MIN_COUNT,
    private val maxCount: Int = DEFAULT_MAX_COUNT,
) {
    var count = initCount
        private set

    fun isMinCount() = count == DEFAULT_MIN_COUNT

    fun isMaxCount() = count == DEFAULT_MAX_COUNT

    fun increase() {
        if (count == maxCount) return
        count++
    }

    fun decrease() {
        if (count == minCount) return
        count--
    }

    companion object {
        private const val DEFAULT_INIT_COUNT = 1
        private const val DEFAULT_MIN_COUNT = 1
        private const val DEFAULT_MAX_COUNT = 8
    }
}
