package com.rulyox.googlenewsrss.activity

import com.rulyox.googlenewsrss.data.Article
import com.rulyox.googlenewsrss.parser.ArticleParser
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
                val loadedArticleList = mutableListOf<Article>()

                for(article in articleList) {

                    ArticleParser.parse(article)
                    loadedArticleList.add(article)

                    view?.let {

                        it.setList(loadedArticleList)
                        it.updateView()

                    }

                }

                view?.hideLoading()

            } catch (e: Exception) { e.printStackTrace() }

        }

    }

}
