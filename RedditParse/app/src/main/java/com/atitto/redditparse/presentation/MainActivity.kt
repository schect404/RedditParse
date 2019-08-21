package com.atitto.redditparse.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.atitto.redditparse.R
import com.atitto.redditparse.data.dataModule
import com.atitto.redditparse.databinding.ItemPostBinding
import com.atitto.redditparse.presentation.common.ObservablePageableList
import com.atitto.redditparse.presentation.common.bindDataTo
import com.atitto.redditparse.presentation.common.paging
import com.atitto.redditparse.presentation.helpers.DialogHelper
import com.atitto.redditparse.presentation.model.PostPresentation
import com.github.nitrico.lastadapter.LastAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import android.content.Intent
import android.net.Uri
import android.view.View
import com.atitto.redditparse.presentation.common.decorate
import com.github.nitrico.lastadapter.BR

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein = Kodein {
        import(dataModule)
        bind<MainViewModel>() with provider { MainViewModelImpl(instance()) }
    }

    private val posts: ObservablePageableList<PostPresentation> = ObservablePageableList()

    private val adapter = LastAdapter(posts.items, BR.item).map<PostPresentation, ItemPostBinding>(R.layout.item_post) {
        onClick {
            val url = it.binding.item?.url
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        }
    }

    private val viewModel: MainViewModel by kodein.instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        prepareRecyclerView()
        bindViewModel()
        vProgress.visibility = View.VISIBLE
        viewModel.fetchPosts(null)
    }

    private fun prepareRecyclerView() {
        rvPosts.adapter = adapter
        rvPosts.paging { viewModel.fetchPosts(posts.nextPage) }
        rvPosts.decorate()
    }

    private fun bindViewModel() {
        bindDataTo(viewModel.postsLiveData, ::consumePosts)
        bindDataTo(viewModel.newPageLivaData) { posts.loaded(it) }
        bindDataTo(viewModel.errorLivaData) { DialogHelper.showErrorAlert(this, it) }
    }

    private fun consumePosts(newPosts: List<PostPresentation>) {
        posts.items.addAll(newPosts)
        vProgress.visibility = View.GONE
    }
}
