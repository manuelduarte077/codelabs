package dev.donmanuel.bmicalculator.domain

fun BodyMeasurements.toBMI(): Float {
    return this.weightKg / (this.heightCm * this.heightCm * 0.0001f)
}
