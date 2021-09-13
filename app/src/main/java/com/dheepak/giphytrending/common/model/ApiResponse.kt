package com.dheepak.giphytrending.common.model

import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
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

@Entity(tableName = "gifTable", primaryKeys = ["id"])
data class DataItem(

	@Embedded
	@SerializedName("images")
	@Json(name="images")
	val images: Images,

	@SerializedName("embed_url")
	@Json(name="embed_url")
	val embedUrl: String = "",

	@SerializedName("bitly_url")
	@Json(name="bitly_url")
	val bitlyUrl: String = "",

	@SerializedName("type")
	@Json(name="type")
	val type: String = "",

	@SerializedName("bitly_gif_url")
	@Json(name="bitly_gif_url")
	val bitlyGifUrl: String = "",

	@SerializedName("id")
	@Json(name="id")
	val id: String
)

@Entity
data class Images(
	@Embedded
	@SerializedName("preview_gif")
	@Json(name="preview_gif")
	val previewGif: PreviewGif
)

@Entity
data class PreviewGif(
	@SerializedName("gif_url")
	@Json(name="url")
	val url: String,
)

data class Meta(

	@Json(name="msg")
	val msg: String? = null,

	@Json(name="response_id")
	val responseId: String? = null,

	@Json(name="status")
	val status: Int? = null
)
