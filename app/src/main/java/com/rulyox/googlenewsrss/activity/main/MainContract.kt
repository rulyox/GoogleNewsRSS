package com.rulyox.googlenewsrss.activity.main

import com.rulyox.googlenewsrss.data.Article

interface MainContract {

    interface View {

        fun setList(articleList: List<Article>)

        fun updateView()

        fun showLoading()

        fun hideLoading()

    }

    interface Presenter {

        fun setView(view: View)

        fun loadList()

    }

}
