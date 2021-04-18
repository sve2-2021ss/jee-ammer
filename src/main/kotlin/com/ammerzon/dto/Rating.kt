package com.ammerzon.dto

import io.quarkus.mongodb.panache.MongoEntity
import org.bson.types.ObjectId
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@MongoEntity(collection = "ratings")
data class Rating(
        var id: ObjectId,
        @field:Min(message = "The minimum rating can only be one", value = 1)
        @field:Max(message = "The maximum rating can only be five", value = 5)
        var rating: Int
)