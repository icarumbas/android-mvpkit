package com.tamimattafi.mvp.presenters.recycler.global

import com.tamimattafi.mvp.MvpBaseContract.*
import com.tamimattafi.mvp.presenters.BasePresenter

abstract class BaseRecyclerPresenter<T, H : Holder, V : ListenerView<H>, R : DataSource>(
    view: V,
    repository: R
) : BasePresenter<V, R>(view, repository), RecyclerPresenter<H> {

    protected val data: ArrayList<T> = ArrayList()
    abstract fun loadRepositoryData()

    override fun loadData() {
        view.tryCall {
            getAdapter().apply {
                if (!isLoading) {
                    hasError = false
                    isLoading = true
                    notifyChanges()
                    loadRepositoryData()
                }
            }
        }
    }

    open fun handleError(message: String) {
        this.data.clear()

        view.tryCall {
            getAdapter().apply {
                hasError = true
                setTotalDataCount(0)
            }
            showMessage(message)
        }
    }

    open fun handleData(data: ArrayList<T>) {
        this.data.apply {
            clear()
            addAll(data)
        }

        view.tryCall {
            getAdapter().apply {
                hasError = false
                setTotalDataCount(data.size)
            }
        }
    }

    override fun refresh() {
        view.tryCall {
            getAdapter().apply {
                if (!isLoading) {
                    setTotalDataCount(0)
                    data.clear()
                    loadData()
                }
            }
        }
    }

}