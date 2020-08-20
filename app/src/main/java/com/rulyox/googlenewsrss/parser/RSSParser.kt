package com.rulyox.googlenewsrss.parser

import com.rulyox.googlenewsrss.data.Article
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.lang.Exception

object RSSParser {

    @Throws(Exception::class)
    fun parse(url: String): List<Article> {

        val articleList = mutableListOf<Article>()

        val rssDoc: Document = Jsoup.connect(url).get()

        val itemTagList: Elements = rssDoc.select("item")

        for(itemTag in itemTagList) {

            val title: String = itemTag.select("title").text()
            val link: String = itemTag.select("link").text()

            articleList.add(Article(title, link, null, null))

        }

        return articleList

    }

}
