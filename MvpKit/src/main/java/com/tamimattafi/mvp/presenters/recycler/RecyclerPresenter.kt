package com.tamimattafi.mvp.presenters.recycler

import com.tamimattafi.mvp.MvpBaseContract
import com.tamimattafi.mvp.MvpBaseContract.*
import com.tamimattafi.mvp.presenters.recycler.global.BaseRecyclerPresenter

abstract class RecyclerPresenter<T, H : Holder, V : ListenerView<H>, R : ListRepository<T>>(
    view: V,
    repository: R
) : BaseRecyclerPresenter<T, H, V, R>(view, repository), MvpBaseContract.RecyclerPresenter<H> {

    override fun loadRepositoryData() {

        view.tryCall {
            with(getAdapter()) {
                setTotalDataCount(0)
                repository.getDataList().addSuccessListener { data ->
                    handleData(data)
                }.addFailureListener { message ->
                    handleError(message)
                }.addCompleteListener {
                    isLoading = false
                }.start()
            }
        }


    }

}