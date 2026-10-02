package com.example.eksamen_h2025.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.eksamen_h2025.screens.animecreate.AnimeCreateScreen
import com.example.eksamen_h2025.screens.animecreate.AnimeCreateViewModel
import com.example.eksamen_h2025.screens.animedetails.AnimeDetailsScreen
import com.example.eksamen_h2025.screens.animedetails.AnimeDetailsViewModel
import com.example.eksamen_h2025.screens.animeedit.AnimeEditScreen
import com.example.eksamen_h2025.screens.animeedit.AnimeEditViewModel
import com.example.eksamen_h2025.screens.animelist.AnimeListScreen
import com.example.eksamen_h2025.screens.animelist.AnimeListViewModel
import com.example.eksamen_h2025.screens.animesearch.AnimeSearchScreen
import com.example.eksamen_h2025.screens.animesearch.AnimeSearchViewModel
import com.example.eksamen_h2025.screens.home.HomeScreen
import com.example.eksamen_h2025.screens.home.HomeViewModel

@Composable
fun AppNavigation(
    animeListViewModel: AnimeListViewModel,
    animeDetailsViewModel: AnimeDetailsViewModel,
    animeSearchViewModel: AnimeSearchViewModel,
    homeViewModel: HomeViewModel,
    animeCreateViewModel: AnimeCreateViewModel,
    animeEditViewModel: AnimeEditViewModel
) {

    val navController = rememberNavController()
    var activeItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    val navigationTheme = NavigationBarItemDefaults.colors(
        indicatorColor = Color(0xFFebdcff),
        selectedIconColor = Color(0xFF503c74),
        selectedTextColor = Color(0xFF503c74),
        unselectedIconColor = Color(0xFF49454e),
        unselectedTextColor = Color(0xFF49454e)
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFFf8f1fa)
            ) {
                NavigationBarItem(
                    selected = activeItem == 0,
                    onClick = {
                        activeItem = 0
                        navController.navigate(NavRoutes.HomeRoute)
                    },
                    label = {
                        Text("Home")
                    },
                    icon = {
                        if ( activeItem == 0 ) {
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.Home,
                                contentDescription = null
                            )
                        }
                    },
                    colors = navigationTheme
                ) // End Home

                NavigationBarItem(
                    selected = activeItem == 1,
                    onClick = {
                        activeItem = 1
                        navController.navigate(NavRoutes.AnimeListRoute)
                    },
                    label = {
                        Text("List")
                    },
                    icon = {
                        if ( activeItem == 1 ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.List,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.List,
                                contentDescription = null
                            )
                        }
                    },
                    colors = navigationTheme
                ) // End AnimeList

                NavigationBarItem(
                    selected = activeItem == 2,
                    onClick = {
                        activeItem = 2
                        navController.navigate(NavRoutes.AnimeSearchRoute)
                    },
                    label = {
                        Text("Search")
                    },
                    icon = {
                        if ( activeItem == 2 ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.Search,
                                contentDescription = null
                            )
                        }
                    },
                    colors = navigationTheme
                ) // End AnimeSearch

                NavigationBarItem(
                    selected = activeItem == 3,
                    onClick = {
                        activeItem = 3
                        navController.navigate(NavRoutes.AnimeCreateRoute)
                    },
                    label = {
                        Text("Create")
                    },
                    icon = {
                        if ( activeItem == 3 ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Outlined.Add,
                                contentDescription = null
                            )
                        }
                    },
                    colors = navigationTheme
                ) // End Create

            }
        }

    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            NavHost(
                navController = navController,
                startDestination = NavRoutes.HomeRoute
            ) {
                composable<NavRoutes.AnimeListRoute> {
                    AnimeListScreen(
                        animeListViewModel,
                        navController
                    )
                }

                composable<NavRoutes.AnimeDetailsRoute> { backStackEntry ->
                    val args = backStackEntry.toRoute<NavRoutes.AnimeDetailsRoute>()
                    AnimeDetailsScreen(
                        animeDetailsViewModel,
                        navController,
                        args.animeId
                    )
                }

                composable<NavRoutes.AnimeSearchRoute> {
                    AnimeSearchScreen(
                        animeSearchViewModel,
                        navController
                    )
                }

                composable<NavRoutes.HomeRoute> {
                    HomeScreen(
                        homeViewModel
                    )
                }

                composable<NavRoutes.AnimeCreateRoute> {
                    AnimeCreateScreen(
                        animeCreateViewModel,
                        navController
                    )
                }

                composable<NavRoutes.AnimeEditRoute> { backStackEntry ->
                    val args = backStackEntry.toRoute<NavRoutes.AnimeEditRoute>()
                    AnimeEditScreen(
                        animeId = args.animeId,
                        viewModel = animeEditViewModel,
                        navController = navController
                    )
                }
            }
        }

    }
}