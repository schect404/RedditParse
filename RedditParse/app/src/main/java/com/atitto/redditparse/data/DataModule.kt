package com.atitto.redditparse.data

import com.atitto.redditparse.data.posts.PostsApi
import com.atitto.redditparse.data.posts.PostsRepository
import com.atitto.redditparse.data.posts.PostsRepositoryImpl
import com.atitto.redditparse.data.retrofit.RetrofitFactory
import com.atitto.redditparse.data.retrofit.RetrofitFactoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

val dataModule = Kodein.Module {

    bind<Gson>() with singleton { GsonBuilder().setLenient().create() }
    bind<OkHttpClient>() with singleton { OkHttpClient() }
    bind<RetrofitFactory>() with singleton { RetrofitFactoryImpl() }
    bind<Retrofit>() with singleton { instance<RetrofitFactory>().createRetrofit(instance(), instance()) }
    bind<PostsApi>() with singleton { instance<Retrofit>().create(PostsApi::class.java) }
    bind<PostsRepository>() with singleton { PostsRepositoryImpl(instance()) }

}