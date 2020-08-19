package com.rulyox.googlenewsrss

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rulyox.googlenewsrss.adapter.ArticleAdapter
import com.rulyox.googlenewsrss.data.Article
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {

    private val articleAdapter = ArticleAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        initUI()

    }

    private fun initUI() {

        main_recycler.layoutManager = LinearLayoutManager(this)
        main_recycler.adapter = articleAdapter

        val articleList = ArrayList<Article>()
        articleList.add(Article("test 1", "", "description 1", null))
        articleList.add(Article("test 2", "", "description 2", null))
        articleList.add(Article("test 3", "", "description 3", null))

        articleAdapter.setList(articleList)
        articleAdapter.notifyDataSetChanged()

    }

}
