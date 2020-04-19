package com.marzec.cv.ui.home

import com.marzec.cv.domain.model.Cv
import com.marzec.cv.views.model.Image
import com.marzec.cv.views.model.ListItemView
import com.marzec.cv.views.model.ListItemViewStub
import com.marzec.cv.views.model.UrlImage
import javax.inject.Inject

interface HomeScreenMapper {

    fun mapItems(cv: Cv): HomeScreenContent
}
class HomeScreenMapperImpl @Inject constructor(): HomeScreenMapper {
    override fun mapItems(cv: Cv): HomeScreenContent {
        return HomeScreenContent(createHeaderModel(cv), listOf(ListItemViewStub("")))
    }

    private fun createHeaderModel(cv: Cv): HeaderModel {
        return HeaderModel(
            UrlImage(cv.imageUrl),
            "${cv.firstName} ${cv.lastName}",
            cv.email,
            cv.phone
        )
    }


}

data class HomeScreenContent(
    val header: HeaderModel,
    val viewItems: List<ListItemView>
)

data class HeaderModel(
    val image: Image,
    val fulName: String,
    val email: String,
    val phoneNumber: String
)