

package com.example.floor_shop.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.floor_shop.ui.components.JetsnackScaffold
import com.example.floor_shop.ui.components.JetsnackSnackbar
import com.example.floor_shop.ui.home.HomeSections
import com.example.floor_shop.ui.home.JetsnackBottomBar
import com.example.floor_shop.ui.home.addHomeGraph
import com.example.floor_shop.ui.home.diary.TodoViewModel
import com.example.floor_shop.ui.snackdetail.SnackDetail
import com.example.floor_shop.ui.theme.JetsnackTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.systemBarsPadding

@Composable
fun JetsnackApp(todoViewModel: TodoViewModel) {
    ProvideWindowInsets {
        JetsnackTheme {
            val appState = rememberJetsnackAppState()
            JetsnackScaffold(
                bottomBar = {
                    if (appState.shouldShowBottomBar) {
                        JetsnackBottomBar(
                            tabs = appState.bottomBarTabs,
                            currentRoute = appState.currentRoute!!,
                            navigateToRoute = appState::navigateToBottomBarRoute
                        )
                    }
                },
                snackbarHost = {
                    SnackbarHost(
                        hostState = it,
                        modifier = Modifier.systemBarsPadding(),
                        snackbar = { snackbarData -> JetsnackSnackbar(snackbarData) }
                    )
                },
                scaffoldState = appState.scaffoldState
            ) { innerPaddingModifier ->
                NavHost(
                    navController = appState.navController,
                    startDestination = MainDestinations.HOME_ROUTE,
                    modifier = Modifier.padding(innerPaddingModifier)
                ) {
                    jetsnackNavGraph(
                        onSnackSelected = appState::navigateToSnackDetail,
                        upPress = appState::upPress,
                        todoViewModel = todoViewModel
                    )
                }
            }
        }
    }
}

private fun NavGraphBuilder.jetsnackNavGraph(
    onSnackSelected: (Long, NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
    todoViewModel: TodoViewModel
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.FEED.route
    ) {
        addHomeGraph(onSnackSelected, todoViewModel =  todoViewModel)
    }
    composable(
        "${MainDestinations.SNACK_DETAIL_ROUTE}/{${MainDestinations.SNACK_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.SNACK_ID_KEY) { type = NavType.LongType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val snackId = arguments.getLong(MainDestinations.SNACK_ID_KEY)
        SnackDetail(snackId, upPress)
    }
}
