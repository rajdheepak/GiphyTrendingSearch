package com.dheepak.giphytrending.model

import com.squareup.moshi.Json

data class ApiResponse(

	@Json(name="pagination")
	val pagination: Pagination? = null,

	@Json(name="data")
	val data: List<DataItem>,

	@Json(name="meta")
	val meta: Meta? = null
)

data class Pagination(

	@Json(name="offset")
	val offset: Int? = null,

	@Json(name="total_count")
	val totalCount: Int? = null,

	@Json(name="count")
	val count: Int? = null
)

data class DataItem(

	@Json(name="images")
	val images: Images? = null,

	@Json(name="embed_url")
	val embedUrl: String? = null,

	@Json(name="bitly_url")
	val bitlyUrl: String? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="bitly_gif_url")
	val bitlyGifUrl: String? = null,

	@Json(name="url")
	val url: String? = null,

	@Json(name="id")
	val id: String? = null
)

data class Images(
	@Json(name="preview_gif")
	val previewGif: PreviewGif? = null
)

data class PreviewGif(

	@Json(name="size")
	val size: String? = null,

	@Json(name="width")
	val width: String? = null,

	@Json(name="url")
	val url: String? = null,

	@Json(name="height")
	val height: String? = null
)

data class Meta(

	@Json(name="msg")
	val msg: String? = null,

	@Json(name="response_id")
	val responseId: String? = null,

	@Json(name="status")
	val status: Int? = null
)
