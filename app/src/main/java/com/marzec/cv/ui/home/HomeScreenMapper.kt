package com.marzec.cv.ui.home

import com.marzec.cv.domain.model.Cv
import com.marzec.cv.views.model.ListItemView
import com.marzec.cv.views.model.ListItemViewStub
import javax.inject.Inject

interface HomeScreenMapper {

    fun mapItems(cv: Cv): HomeScreenContent
}
class HomeScreenMapperImpl @Inject constructor(): HomeScreenMapper {
    override fun mapItems(cv: Cv): HomeScreenContent {
        return HomeScreenContent(HeaderModel(""), listOf(ListItemViewStub("")))
    }


}

data class HomeScreenContent(
    val header: HeaderModel,
    val viewItems: List<ListItemView>
)

data class HeaderModel(val fulName: String)