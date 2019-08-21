package com.atitto.redditparse.presentation.converters

import com.atitto.redditparse.data.posts.model.ApiListItemData
import com.atitto.redditparse.data.posts.model.ApiPostList
import com.atitto.redditparse.presentation.model.AuthorPresenation
import com.atitto.redditparse.presentation.model.PostPresentation

fun ApiPostList.asPresentationPostList(): List<PostPresentation> = data.children.map { it.data.asPresentationPost() }

fun ApiPostList.getNextPage(): String? = data.nextPage

fun  ApiListItemData.asPresentationPost(): PostPresentation =
        PostPresentation(
            id = "",
            title = title ?: "",
            date = createDate ?: 0,
            rate = rate?.toString() ?: "0" ,
            comments = comments?.toString() ?: "0",
            author = AuthorPresenation(
                login = login ?: "",
                subreddit = subreddit ?: "",
                avatar = thumbnail
            ),
            url = url?.let { REDDIT_BASE.format(it) } ?: ""
        )

private const val REDDIT_BASE = "https://www.reddit.com%s"