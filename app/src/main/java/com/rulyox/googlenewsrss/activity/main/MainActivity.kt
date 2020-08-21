package com.rulyox.googlenewsrss.activity.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rulyox.googlenewsrss.R
import com.rulyox.googlenewsrss.activity.read.ReadActivity
import com.rulyox.googlenewsrss.adapter.ArticleAdapter
import com.rulyox.googlenewsrss.data.Article
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(), MainContract.View {

    private val presenter = MainPresenter()
    private lateinit var articleAdapter: ArticleAdapter
    private var articleList: List<Article>? = null
    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        initUI()
        initPresenter()

        presenter.loadList()

    }

    private fun initUI() {

        // adapter
        val clickListener = object: ArticleAdapter.ItemClickListener {

            override fun onItemClick(position: Int) {

                articleList?.let {

                    val article = it[position]

                    val readIntent = Intent(this@MainActivity, ReadActivity::class.java)
                    readIntent.putExtra("title", article.title)
                    readIntent.putExtra("link", article.link)
                    startActivity(readIntent)

                }

            }

        }
        articleAdapter = ArticleAdapter(this, clickListener)

        // recycler
        main_recycler.layoutManager = LinearLayoutManager(this)
        main_recycler.adapter = articleAdapter

        // swipe refresh
        main_swipe.setOnRefreshListener {

            if(!isLoading) {
                clearList()
                presenter.loadList()

            }

            main_swipe.isRefreshing = false

        }

    }

    private fun initPresenter() {

        presenter.setView(this)

    }

    private fun clearList() {

        articleList = listOf()
        articleAdapter.setList(articleList!!)
        articleAdapter.notifyDataSetChanged()

    }

    override fun setList(articleList: List<Article>) {

        this.articleList = articleList

    }

    override fun updateView() {

        runOnUiThread {

            articleList?.let {

                articleAdapter.setList(it)
                articleAdapter.notifyDataSetChanged()

            }

        }

    }

    override fun showLoading() {

        isLoading = true
        main_loading.visibility = View.VISIBLE

    }

    override fun hideLoading() {

        runOnUiThread {

            isLoading = false
            main_loading.visibility = View.GONE

        }

    }

}
