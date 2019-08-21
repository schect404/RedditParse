package com.atitto.redditparse.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.atitto.redditparse.data.posts.PostsRepository
import com.atitto.redditparse.presentation.converters.asPresentationPostList
import com.atitto.redditparse.presentation.converters.getNextPage
import com.atitto.redditparse.presentation.model.PostPresentation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

interface MainViewModel {

    val postsLiveData: LiveData<List<PostPresentation>>
    val newPageLivaData: LiveData<String?>
    val errorLivaData: LiveData<String>

    fun fetchPosts(page: String?)

}

class MainViewModelImpl(private val postsRepository: PostsRepository) : ViewModel(), MainViewModel {

    override val postsLiveData: MutableLiveData<List<PostPresentation>> = MutableLiveData()
    override val newPageLivaData: MutableLiveData<String?> = MutableLiveData()
    override val errorLivaData: MutableLiveData<String> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    override fun fetchPosts(page: String?) {
        compositeDisposable.add(postsRepository.getTopPosts(page)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                postsLiveData.postValue(it.asPresentationPostList())
                newPageLivaData.postValue(it.getNextPage())
            }, {
                errorLivaData.postValue(it.message)
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}