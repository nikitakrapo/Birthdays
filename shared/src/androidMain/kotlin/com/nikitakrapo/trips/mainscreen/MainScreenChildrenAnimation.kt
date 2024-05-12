package com.nikitakrapo.trips.mainscreen

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.FaultyDecomposeApi
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.Direction
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.StackAnimation
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.StackAnimator
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.isBack
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.nikitakrapo.trips.mainscreen.MainComponent.MainChild

@OptIn(FaultyDecomposeApi::class)
@Composable
internal fun mainScreenChildrenAnimation(): StackAnimation<Any, MainChild> =
    stackAnimation { child, otherChild, direction ->
        if (child.instance is MainChild.BottomBarChild && otherChild.instance is MainChild.BottomBarChild) {
            val index = child.instance.index
            val otherIndex = otherChild.instance.index
            val anim = slide()
            if ((index > otherIndex) == !direction.isBack) anim else anim.flipSide()
        } else {
            slide().let { if (direction.isBack) it.flipSide() else it }
        }
    }

val MainChild.index get() = when (this) {
    is MainChild.BottomBarChild.BirthdaysFeed -> 1
    is MainChild.BottomBarChild.TripsFeed -> 2
    is MainChild.BottomBarChild.Profile -> 3
    else -> 0
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