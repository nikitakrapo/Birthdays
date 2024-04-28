package com.nikitakrapo.trips.design.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AirplaneTicket
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.trips.design.theme.TripsTheme
import strings.R

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    items: List<BottomBarItem>,
    selectedItem: Int,
    onItemClick: (index: Int) -> Unit,
) {
    GenericBottomNavigationBar(
        modifier = modifier,
        elements = items,
        selectedItem = selectedItem,
        onItemClick = onItemClick,
    )
}

@Composable
internal fun GenericBottomNavigationBar(
    modifier: Modifier = Modifier,
    elements: List<BottomBarItem>,
    selectedItem: Int,
    onItemClick: (index: Int) -> Unit,
) {
    require(selectedItem in elements.indices) {
        "Selected element index out of bounds"
    }

    NavigationBar(
        modifier = modifier,
    ) {
        elements.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = index == selectedItem,
                label = {
                    Text(
                        text = item.title,
                    )
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                    )
                },
                onClick = {
                    onItemClick(index)
                }
            )
        }
    }
}

data class BottomBarItem(
    val title: String,
    val icon: ImageVector,
)

@Preview(
    widthDp = 360,
)
@Composable
private fun BottomNavigationBar_Preview() {
    TripsTheme {
        val context = LocalContext.current
        BottomNavigationBar(
            modifier = Modifier
                .fillMaxWidth(),
            items = listOf(
                BottomBarItem(
                    title = context.getString(R.string.trips),
                    icon = Icons.Outlined.AirplaneTicket,
                ),
                BottomBarItem(
                    title = context.getString(R.string.profile),
                    icon = Icons.Outlined.Person,
                )
            ),
            selectedItem = 0,
            onItemClick = {},
        )
    }
}
