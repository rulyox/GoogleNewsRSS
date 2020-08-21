package com.rulyox.googlenewsrss.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.rulyox.googlenewsrss.R
import com.rulyox.googlenewsrss.data.Article

class ArticleAdapter(private val context: Context): RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder?>() {

    private var articleList: List<Article>? = null

    fun setList(articleList: List<Article>) {

        this.articleList = articleList

    }

    class ArticleViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val title: TextView = view.findViewById(R.id.item_article_title)
        val description: TextView = view.findViewById(R.id.item_article_description)
        val image: ImageView = view.findViewById(R.id.item_article_image)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ArticleViewHolder {

        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_article, viewGroup, false)

        return ArticleViewHolder(view)

    }

    override fun onBindViewHolder(viewholder: ArticleViewHolder, position: Int) {

        val roundCorner = ContextCompat.getDrawable(context, R.drawable.bg_round_corner) as GradientDrawable

        articleList?.let {

            viewholder.title.text = it[position].title
            viewholder.description.text = it[position].description
            viewholder.image.setImageDrawable(it[position].image)
            viewholder.image.background = roundCorner
            viewholder.image.clipToOutline = true


        }

    }

    override fun getItemCount(): Int = articleList?.size ?: 0

}
