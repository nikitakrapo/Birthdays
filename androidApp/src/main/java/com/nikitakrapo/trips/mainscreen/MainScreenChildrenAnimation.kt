package com.nikitakrapo.trips.mainscreen

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.FaultyDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.Direction
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.StackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.StackAnimator
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.isBack
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation

@OptIn(FaultyDecomposeApi::class)
@Composable
internal fun mainScreenChildrenAnimation(): StackAnimation<Any, MainComponent.MainChild> =
    stackAnimation { child, otherChild, direction ->
        val index = child.instance.index
        val otherIndex = otherChild.instance.index
        val anim = slide()
        if ((index > otherIndex) == !direction.isBack) anim else anim.flipSide()
    }

val MainComponent.MainChild.index get() = when (this) {
    is MainComponent.MainChild.TripsFeed -> 1
    MainComponent.MainChild.Profile -> 2
}

private fun StackAnimator.flipSide(): StackAnimator =
    StackAnimator { direction, isInitial, onFinished, content ->
        invoke(
            direction = direction.flipSide(),
            isInitial = isInitial,
            onFinished = onFinished,
            content = content,
        )
    }

private fun Direction.flipSide(): Direction =
    when (this) {
        Direction.ENTER_FRONT -> Direction.ENTER_BACK
        Direction.EXIT_FRONT -> Direction.EXIT_BACK
        Direction.ENTER_BACK -> Direction.ENTER_FRONT
        Direction.EXIT_BACK -> Direction.EXIT_FRONT
    }