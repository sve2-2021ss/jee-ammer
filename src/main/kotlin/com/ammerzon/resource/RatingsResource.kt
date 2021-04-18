package com.ammerzon.resource

import com.ammerzon.dto.Rating
import com.ammerzon.repository.RatingsRepository
import org.bson.types.ObjectId
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.slf4j.LoggerFactory
import javax.enterprise.inject.Default
import javax.inject.Inject
import javax.transaction.Transactional
import javax.validation.Valid
import javax.validation.Validator
import javax.ws.rs.*
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

@Path("/ratings")
@Tag(name = "RatingsResource", description = "The ratings endpoint")
class RatingsResource {

    private val logger = LoggerFactory.getLogger(RatingsResource::class.java)

    @Inject
    @field: Default
    lateinit var validator: Validator

    @Inject
    @field: Default
    lateinit var repository: RatingsRepository

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun all(): List<Rating> {
        return repository
                .findAll()
                .list()
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getById(@PathParam("id") id: String): Rating {
        logger.debug("Get element with id: $id")
        val ratingId = ObjectId(id)
        val persistedRating = repository.findById(ratingId) ?: throw RatingNotFoundException(id)
        return persistedRating
    }

    @GET
    @Path("avg")
    @Produces(MediaType.APPLICATION_JSON)
    fun average(): Double {
        return repository
                .findAll()
                .stream()
                .mapToInt { it.rating }
                .average()
                .orElse(0.0)
    }

    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    fun count(): Int {
        return repository
                .findAll()
                .list()
                .count()
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    fun add(@Valid rating: Rating): Response {
        logger.debug("Added rating: ${rating.rating}")
        repository.persist(rating)
        return Response.ok(rating).status(Response.Status.CREATED).build()
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun update(@Valid rating: Rating, @PathParam("id") id: String): Rating {
        val ratingId = ObjectId(id)
        val persistedRating = repository.findById(ratingId) ?: throw RatingNotFoundException(id)
        logger.debug("Update rating ($id) from  ${persistedRating.rating} to ${rating.rating}")

        persistedRating.rating = rating.rating
        repository.update(persistedRating)
        return persistedRating
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    fun delete(@PathParam("id") id: String): Response {
        logger.debug("Delete rating with id: $id")
        val ratingId = ObjectId(id)
        if (repository.findById(ratingId) == null) {
            throw RatingNotFoundException(id)
        }
        repository.deleteById(ratingId)
        return Response.noContent().build()
    }
}