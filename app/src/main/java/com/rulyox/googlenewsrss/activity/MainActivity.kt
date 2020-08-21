package com.rulyox.googlenewsrss.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rulyox.googlenewsrss.R
import com.rulyox.googlenewsrss.adapter.ArticleAdapter
import com.rulyox.googlenewsrss.data.Article
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(), MainContract.View {

    private val presenter = MainPresenter()
    private val articleAdapter = ArticleAdapter(this)
    private var articleList: List<Article>? = null

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

        main_recycler.layoutManager = LinearLayoutManager(this)
        main_recycler.adapter = articleAdapter

    }

    private fun initPresenter() {

        presenter.setView(this)

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

        main_loading.visibility = View.VISIBLE

    }

    override fun hideLoading() {

        runOnUiThread {

            main_loading.visibility = View.GONE

        }

    }

}
