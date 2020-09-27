package com.raywenderlich.podplay.repository

import com.raywenderlich.podplay.service.ItunesService
import com.raywenderlich.podplay.service.PodcastResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ItunesRepo(private val itunesService: ItunesService) {
    fun searchByTerm(term: String,
                     callBack: (List<PodcastResponse.ItunesPodcast>?) -> Unit) {
        val podcastCall = itunesService.searchPodcastByTerm(term)
        podcastCall.enqueue(object : Callback<PodcastResponse> {
            override fun onFailure(call: Call<PodcastResponse>?,
                                   t: Throwable?) {
                callBack(null)
            }
            override fun onResponse(
                call: Call<PodcastResponse>?,
                response: Response<PodcastResponse>?) {
                // 8
                val body = response?.body()
                // 9
                callBack(body?.results)
            }
        })
    }
}