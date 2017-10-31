package com.guardafilme.base

/**
 * Created by lucassantos on 30/10/17.
 */
interface BasePresenter<V> {
    fun attach(view: V)
    fun dettach()
}