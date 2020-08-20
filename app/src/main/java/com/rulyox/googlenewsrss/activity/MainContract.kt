package com.rulyox.googlenewsrss.activity

import com.rulyox.googlenewsrss.data.Article

interface MainContract {

    interface View {

        fun setList(articleList: ArrayList<Article>)

        fun updateView()

    }

    interface Presenter {

        fun setView(view: View)

        fun loadList()

    }

}
