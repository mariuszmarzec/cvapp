package com.marzec.cv.repositories

import com.marzec.cv.api.CvApi
import com.marzec.cv.api.model.toDomain
import com.marzec.cv.domain.model.Cv
import com.marzec.cv.domain.model.Resource
import com.marzec.cv.extensions.onIo
import io.reactivex.Observable
import javax.inject.Inject

interface CvRepository {

    fun observeCv(): Observable<Resource<Cv>>
}

class CvRepositoryImpl @Inject constructor(
    private val api: CvApi
) : CvRepository {

    override fun observeCv(): Observable<Resource<Cv>> {
        return api.getCv()
            .toObservable()
            .map { Resource.Data(it.toDomain()) as Resource<Cv> }
            .onErrorReturn { throwable ->
                Resource.Error(throwable)
            }
            .startWith(Resource.Loading())
            .onIo()
    }
}