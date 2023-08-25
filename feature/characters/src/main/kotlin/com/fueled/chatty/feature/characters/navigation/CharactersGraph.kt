package com.fueled.chatty.feature.characters.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.fueled.chatty.core.ui.navigation.Destination
import com.fueled.chatty.core.ui.navigation.Graph
import com.fueled.chatty.feature.characters.navigation.CharactersDestination.CharacterDetail
import com.fueled.chatty.feature.characters.navigation.CharactersDestination.CharacterList
import com.fueled.chatty.feature.characters.presentation.details.CharacterDetailScreenArgs
import com.fueled.chatty.feature.characters.presentation.details.CharacterDetailsScreen
import com.fueled.chatty.feature.characters.presentation.list.CharactersScreen

object CharactersGraph : Graph("characters")

sealed class CharactersDestination {

    object CharacterList : Destination("list")

    object CharacterDetail : Destination("detail/{${CharacterDetailScreenArgs.EXTRA_ID}}") {
        fun createRoute(graph: Graph, id: Long): String {
            return "${graph.route}/detail/$id"
        }
    }
}

fun NavGraphBuilder.addCharactersScreen(
    openCharacterDetail: (Long) -> Unit,
    setToolbarTitle: (String) -> Unit,
    graph: Graph,
) {
    composable(route = CharacterList.createRoute(graph)) {
        CharactersScreen(
            openCharacterDetail = openCharacterDetail,
            setToolbarTitle = setToolbarTitle,
        )
    }
}

fun NavGraphBuilder.addCharacterDetailScreen(
    setToolbarTitle: (String) -> Unit,
    graph: Graph,
) {
    composable(
        route = CharacterDetail.createRoute(graph),
        arguments = listOf(
            navArgument(CharacterDetailScreenArgs.EXTRA_ID) {
                type = NavType.LongType
            },
        ),
    ) {
        CharacterDetailsScreen(setToolbarTitle)
    }
}
