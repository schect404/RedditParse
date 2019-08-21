package com.atitto.redditparse.data.posts

import com.atitto.redditparse.data.posts.model.ApiPostList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface PostsApi {

    @GET(UrlValues.GET_TOP_POSTS)
    fun getTopPosts(
        @Query(UrlValues.QUERY_AFTER) after: String? = null,
        @Query(UrlValues.QUERY_LIMIT) limit: Int = UrlValues.QUERY_LIMIT_VALUE,
        @Query(UrlValues.QUERY_T) t: String? = UrlValues.QUERY_T_VALUE): Single<ApiPostList>

}