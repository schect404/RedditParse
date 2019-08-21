package com.atitto.redditparse.data.posts.model

import com.google.gson.annotations.SerializedName

data class ApiPostList(
    @SerializedName("data")
    val data: ApiListData
)

data class ApiListData(
    @SerializedName("after")
    val nextPage: String?,
    @SerializedName("children")
    val children: List<ApiListItem>
)

data class ApiListItem(
    @SerializedName("data")
    val data: ApiListItemData
)

data class ApiListItemData(
    @SerializedName("subreddit")
    val subreddit: String?,
    @SerializedName("author")
    val login: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("thumbnail")
    val thumbnail: String?,
    @SerializedName("score")
    val rate: Int?,
    @SerializedName("created_utc")
    val createDate: Long?,
    @SerializedName("num_comments")
    val comments: Int?,
    @SerializedName("permalink")
    val url: String?
)