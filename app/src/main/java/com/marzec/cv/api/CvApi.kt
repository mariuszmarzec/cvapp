package com.marzec.cv.api

import com.marzec.cv.api.model.CvDto
import io.reactivex.Single
import retrofit2.http.GET

interface CvApi {

    @GET("cv")
    fun getCv(): Single<CvDto>
}
