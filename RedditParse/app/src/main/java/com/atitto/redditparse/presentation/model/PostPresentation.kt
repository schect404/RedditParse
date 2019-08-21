package com.atitto.redditparse.presentation.model

data class PostPresentation(
    val id: String,
    val title: String,
    val author: AuthorPresenation,
    val date: Long,
    val rate: String,
    val comments: String,
    val url: String
)

data class AuthorPresenation(
    val login: String,
    val avatar: String?,
    val subreddit: String?
)