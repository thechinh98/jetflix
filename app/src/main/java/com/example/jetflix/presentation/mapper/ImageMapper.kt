package com.example.jetflix.presentation.mapper

import com.example.jetflix.data.model.Image
import com.example.jetflix.domain.entities.ImagesEntity
import com.example.jetflix.util.Mapper
import com.example.jetflix.util.toOriginalUrl
import javax.inject.Inject

class ImageMapper @Inject constructor() : Mapper<ImagesEntity, List<Image>> {
    override fun map(input: ImagesEntity): List<Image> {
        return buildList {
            addAll(input.backdrops.map { Image(it.filePath.toOriginalUrl(), it.voteCount) })
            addAll(input.posters.map { Image(it.filePath.toOriginalUrl(), it.voteCount) })
            sortByDescending { it.voteCount }
        }
    }
}
