package com.dheepak.giphytrending.mockData

import com.dheepak.giphytrending.common.model.ApiResponse
import com.google.gson.Gson
import java.io.File

val file = File("src/test/java/com/dheepak/giphytrending/mockData/mockResponse.json")

val dummyResponse: ApiResponse =
    Gson().fromJson(file.readText(), ApiResponse::class.java)
