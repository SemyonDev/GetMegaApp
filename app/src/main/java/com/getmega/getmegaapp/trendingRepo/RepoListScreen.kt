package com.getmega.getmegaapp.trendingRepo

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.getmega.entities.Repo
import com.getmega.getmegaapp.HomeActivityViewModel
import com.getmega.getmegaapp.R
import com.getmega.getmegaapp.ui.theme.cardCollapsedBackgroundColor
import com.getmega.getmegaapp.ui.theme.cardExpandedBackgroundColor
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun RepoListScreen(viewModel: HomeActivityViewModel) {
    val cards = viewModel.repoList.collectAsState()
    val expandedRepoIdList = viewModel.expandedRepoIdList.collectAsState()
    Scaffold(
        backgroundColor = Color(
            ContextCompat.getColor(
                LocalContext.current,
                R.color.colorDayNightWhite
            )
        )
    ) {
        LazyColumn {
            itemsIndexed(cards.value) { _, repo ->
                ExpandableTrendingRepo(
                    repo = repo,
                    onCardArrowClick = { viewModel.onExpandArrowClicked(repo.id) },
                    expanded = expandedRepoIdList.value.contains(repo.id),
                )
            }
        }
    }
}

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun ExpandableTrendingRepo(
    repo: Repo,
    onCardArrowClick: () -> Unit,
    expanded: Boolean,
) {
    val transitionState = remember {
        MutableTransitionState(expanded).apply {
            targetState = !expanded
        }
    }
    val transition = updateTransition(transitionState, label = "transition")
    val cardBgColor by transition.animateColor({
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    }, label = "bgColorTransition") {
        if (expanded) cardExpandedBackgroundColor else cardCollapsedBackgroundColor
    }
    val cardPaddingHorizontal by transition.animateDp({
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    }, label = "paddingTransition") {
        if (expanded) 48.dp else 24.dp
    }
    val cardElevation by transition.animateDp({
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    }, label = "elevationTransition") {
        if (expanded) 24.dp else 4.dp
    }
    val cardRoundedCorners by transition.animateDp({
        tween(
            durationMillis = EXPAND_ANIMATION_DURATION,
            easing = FastOutSlowInEasing
        )
    }, label = "cornersTransition") {
        if (expanded) 0.dp else 16.dp
    }
    val arrowRotationDegree by transition.animateFloat({
        tween(durationMillis = EXPAND_ANIMATION_DURATION)
    }, label = "rotationDegreeTransition") {
        if (expanded) 0f else 180f
    }

    Card(
        backgroundColor = cardBgColor,
        contentColor = Color(
            ContextCompat.getColor(
                LocalContext.current,
                R.color.colorDayNightPurple
            )
        ),
        elevation = cardElevation,
        shape = RoundedCornerShape(cardRoundedCorners),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = cardPaddingHorizontal,
                vertical = 8.dp
            )
    ) {
        Column {
            Box {
                CardArrow(
                    degrees = arrowRotationDegree,
                    onClick = onCardArrowClick
                )
                CardTitle(title = repo.author)
            }
            ExpandableContent(visible = expanded)
        }
    }
}

@Composable
fun CardArrow(
    degrees: Float,
    onClick: () -> Unit
) {
    IconButton(
        onClick = onClick,
        content = {
            Icon(
                painter = painterResource(id = R.drawable.ic_expand_less_24),
                contentDescription = "Expandable Arrow",
                modifier = Modifier.rotate(degrees),
            )
        }
    )
}

@Composable
fun CardTitle(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        textAlign = TextAlign.Center,
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExpandableContent(
    visible: Boolean = true,
) {
    val enterFadeIn = remember {
        fadeIn(
            animationSpec = TweenSpec(
                durationMillis = FADE_IN_ANIMATION_DURATION,
                easing = FastOutLinearInEasing
            )
        )
    }
    val enterExpand = remember {
        expandVertically(animationSpec = tween(EXPAND_ANIMATION_DURATION))
    }
    val exitFadeOut = remember {
        fadeOut(
            animationSpec = TweenSpec(
                durationMillis = FADE_OUT_ANIMATION_DURATION,
                easing = LinearOutSlowInEasing
            )
        )
    }
    val exitCollapse = remember {
        shrinkVertically(animationSpec = tween(COLLAPSE_ANIMATION_DURATION))
    }
    AnimatedVisibility(
        visible = visible,
        enter = enterExpand + enterFadeIn,
        exit = exitCollapse + exitFadeOut
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Spacer(modifier = Modifier.heightIn(100.dp))
            Text(
                text = "Expandable content here",
                textAlign = TextAlign.Center
            )
        }
    }
}
