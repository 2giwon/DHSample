package com.egiwon.common.wrapper

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object SchedulersExt {
    val mainThreadSchedulers: Scheduler = AndroidSchedulers.mainThread()

    val ioThreadSchedulers: Scheduler = Schedulers.io()
}