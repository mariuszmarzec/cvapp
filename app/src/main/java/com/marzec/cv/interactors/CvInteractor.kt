package com.marzec.cv.interactors

import com.marzec.cv.domain.model.Cv
import com.marzec.cv.domain.model.Resource
import com.marzec.cv.repositories.CvRepository
import io.reactivex.Observable
import javax.inject.Inject

interface CvInteractor {
    fun observeCv(): Observable<Resource<Cv>>
}

class CvInteractorImpl @Inject constructor(
    private val repository: CvRepository
): CvInteractor {

    override fun observeCv() = repository.observeCv()

}