package com.atitto.redditparse.data.posts

import com.atitto.redditparse.data.posts.model.ApiPostList
import io.reactivex.Single

class PostsRepositoryImpl(private val postsApi: PostsApi): PostsRepository {

    override fun getTopPosts(nextPage: String?): Single<ApiPostList> = postsApi.getTopPosts(after = nextPage)

}