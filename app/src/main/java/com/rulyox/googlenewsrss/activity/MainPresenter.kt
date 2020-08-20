package com.rulyox.googlenewsrss.activity

import com.rulyox.googlenewsrss.data.Article

class MainPresenter: MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun setView(view: MainContract.View) {

        this.view = view

    }

    override fun loadList() {

        val articleList = ArrayList<Article>()
        articleList.add(Article("test 1", "", "description 1", null))
        articleList.add(Article("test 2", "", "description 2", null))
        articleList.add(Article("test 3", "", "description 3", null))

        view?.let {

            it.setList(articleList)
            it.updateView()

        }

    }

}
