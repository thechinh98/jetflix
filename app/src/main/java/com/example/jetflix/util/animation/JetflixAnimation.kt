package com.example.jetflix.util.animation

import androidx.compose.animation.core.*

val linearAnimation = InfiniteRepeatableSpec<Float>(
    animation = tween(3000, easing = LinearEasing),
    repeatMode = RepeatMode.Reverse
)

val keyframeAnimation: AnimationSpec<Float> = infiniteRepeatable(
    keyframes {
        durationMillis = 3000
        1f at 0
        1.1f at 1000
        1.6f at 2500
        2f at 3000
    },
    repeatMode = RepeatMode.Reverse
)

val springAnimation = spring(
    dampingRatio = Spring.DampingRatioMediumBouncy,
    stiffness = Spring.StiffnessLow,
    visibilityThreshold = 0.001f
)

val defaultScaleAnimation = infiniteRepeatable<Float>(
    tween(3000, easing = LinearEasing),
    repeatMode = RepeatMode.Reverse
)