package com.nikitakrapo.trips.bottomnav

import android.content.Context
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AirplaneTicket
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.nikitakrapo.trips.design.theme.TripsTheme

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    component: BottomNavigationComponent,
) {
    val context = LocalContext.current
    val state by component.state.collectAsState()
    val elements = key(state.items) {
        state.items.toBottomBarElements(context)
    }
    GenericBottomNavigationBar(
        modifier = modifier,
        elements = elements,
        selectedItem = state.selectedItem,
        onItemClick = component::onTabSelected,
    )
}

private fun List<BottomNavigationItem>.toBottomBarElements(
    context: Context,
): List<BottomBarElement> {
    return map {
        when (it) {
            BottomNavigationItem.Trips -> BottomBarElement(
                title = context.getString(strings.R.string.trips),
                icon = Icons.Outlined.AirplaneTicket,
            )
            BottomNavigationItem.Profile -> BottomBarElement(
                title = context.getString(strings.R.string.profile),
                icon = Icons.Outlined.Person,
            )
        }
    }
}

@Composable
internal fun GenericBottomNavigationBar(
    modifier: Modifier = Modifier,
    elements: List<BottomBarElement>,
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

internal data class BottomBarElement(
    val title: String,
    val icon: ImageVector,
)

@Preview(
    widthDp = 360,
)
@Composable
private fun BottomNavigationBar_Preview() {
    TripsTheme {
        BottomNavigationBar(
            modifier = Modifier
                .fillMaxWidth(),
            component = BottomNavigationComponentImpl(
                items = listOf(
                    BottomNavigationItem.Trips,
                    BottomNavigationItem.Profile,
                ),
                selectedItem = 0,
            ),
        )
    }
}
