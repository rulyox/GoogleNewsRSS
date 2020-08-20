package com.rulyox.googlenewsrss.activity

import com.rulyox.googlenewsrss.data.Article
import com.rulyox.googlenewsrss.parser.RSSParser
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainPresenter: MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun setView(view: MainContract.View) {

        this.view = view

    }

    override fun loadList() {

        val url = "https://news.google.com/rss"

        view?.showLoading()

        GlobalScope.launch {

            try {

                val articleList: List<Article> = RSSParser.parse(url)

                view?.let {

                    it.setList(articleList)
                    it.updateView()
                    it.hideLoading()

                }

            } catch (e: Exception) { e.printStackTrace() }

        }

    }

}
