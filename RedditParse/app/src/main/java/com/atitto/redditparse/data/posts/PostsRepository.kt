package com.atitto.redditparse.data.posts

import com.atitto.redditparse.data.posts.model.ApiPostList
import io.reactivex.Single

interface PostsRepository {

    fun getTopPosts(nextPage: String?): Single<ApiPostList>

}