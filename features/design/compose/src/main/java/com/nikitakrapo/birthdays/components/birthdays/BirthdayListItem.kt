package com.nikitakrapo.birthdays.components.birthdays

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.nikitakrapo.birthdays.components.shimmer.ShimmerTextLine
import com.nikitakrapo.birthdays.model.Birthday
import com.nikitakrapo.birthdays.theme.BirthdaysTheme
import com.valentinilk.shimmer.Shimmer
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer
import kotlinx.datetime.LocalDate

@Composable
fun BirthdayListItem(
    birthday: Birthday,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier
            .clickable(onClick = onClick),
        leadingContent = birthday.imageUrl?.let {
            {
                if (LocalInspectionMode.current) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(BirthdaysTheme.shapes.medium)
                            .background(BirthdaysTheme.colorScheme.secondaryContainer),
                    )
                } else {
                    AsyncImage(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(BirthdaysTheme.shapes.medium),
                        model = it,
                        placeholder = ColorPainter(BirthdaysTheme.colorScheme.outline),
                        error = ColorPainter(BirthdaysTheme.colorScheme.outline),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
            }
        },
        headlineContent = {
            Text(text = birthday.title)
        },
        supportingContent = {
            Text(text = birthday.date.toString())
        },
        trailingContent = {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.NavigateNext,
                contentDescription = null,
            )
        }
    )
}

@Composable
fun BirthdayListItemShimmer(
    shimmer: Shimmer,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier
            .shimmer(shimmer),
        leadingContent = {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(BirthdaysTheme.shapes.medium)
                    .background(BirthdaysTheme.colorScheme.outline),
            )
        },
        headlineContent = {
            ShimmerTextLine(
                shimmer = shimmer,
                modifier = Modifier
                    .width(200.dp),
            )
        },
        supportingContent = {
            ShimmerTextLine(
                shimmer = shimmer,
                modifier = Modifier
                    .width(100.dp),
            )
        },
        trailingContent = {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.NavigateNext,
                contentDescription = null,
            )
        }
    )
}

@Preview
@Composable
private fun BirthdayListItemPreview() {
    BirthdaysTheme {
        Surface {
            BirthdayListItem(
                birthday = Birthday(
                    id = "123",
                    title = "Michael Jackson's birthday",
                    date = LocalDate(2024, 5, 5),
                    imageUrl = "",
                ),
                onClick = {},
            )
        }
    }
}

@Preview
@Composable
private fun BirthdayListItemShimmerPreview() {
    BirthdaysTheme {
        Surface {
            val shimmer = rememberShimmer(ShimmerBounds.Window)
            BirthdayListItemShimmer(shimmer)
        }
    }
}

@Preview
@Composable
private fun BirthdayListItemShimmerTransparentPreview() {
    BirthdaysTheme {
        Surface {
            Box {
                BirthdayListItem(
                    birthday = Birthday(
                        id = "123",
                        title = "Michael Jackson's birthday",
                        date = LocalDate(2024, 5, 5),
                        imageUrl = "",
                    ),
                    onClick = {},
                )
                val shimmer = rememberShimmer(ShimmerBounds.Window)
                BirthdayListItemShimmer(shimmer)
            }
        }
    }
}


