package com.rulyox.googlenewsrss.parser

import android.graphics.drawable.Drawable
import com.rulyox.googlenewsrss.data.Article
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.InputStream
import java.lang.Exception
import java.net.URL

object ArticleParser {

    @Throws(Exception::class)
    fun parse(article: Article) {

        val link: String = article.link ?: return

        val articleDoc: Document = Jsoup.connect(link).get()
        val metaTagList: Elements = articleDoc.select("meta")

        // get metadata
        for(metaTag in metaTagList) {

            if(metaTag.attr("property") == "og:description") {

                // get description
                try{

                    // remove unnecessary white space
                    val description: String = metaTag.attr("content").replace("\\s+".toRegex(), " ")

                    article.description = description

                } catch (e: Exception) { e.printStackTrace() }

            } else if(metaTag.attr("property") == "og:image") {

                // get image
                try{

                    val imageLink = metaTag.attr("content")

                    val imgInputStream = URL(imageLink).content as InputStream
                    val imgDrawable: Drawable = Drawable.createFromStream(imgInputStream, imageLink)
                    imgInputStream.close()

                    article.image = imgDrawable

                } catch (e: Exception) { e.printStackTrace() }

            }

        }

    }

}
