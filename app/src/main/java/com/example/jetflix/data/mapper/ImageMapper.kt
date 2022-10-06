package com.example.jetflix.data.mapper

import com.example.jetflix.domain.entities.ImageEntities
import com.example.jetflix.data.models.ImagesModel
import com.example.jetflix.util.Mapper
import com.example.jetflix.util.toOriginalUrl
import javax.inject.Inject

class ImageMapper @Inject constructor() : Mapper<ImagesModel, List<ImageEntities>> {
    override fun map(input: ImagesModel): List<ImageEntities> {
        return buildList {
            addAll(input.backdrops.map { ImageEntities(it.filePath.toOriginalUrl(), it.voteCount) })
            addAll(input.posters.map { ImageEntities(it.filePath.toOriginalUrl(), it.voteCount) })
            sortByDescending { it.voteCount }
        }
    }
}
