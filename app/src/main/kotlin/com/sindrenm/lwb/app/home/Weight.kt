package com.sindrenm.lwb.app.home

@JvmInline
value class Weight(val grams: Int) {
    companion object {
        val Int.kg: Weight
            get() = Weight(grams = this * 1_000)
    }
}

fun Weight.inKg(): Float = grams / 1000f
