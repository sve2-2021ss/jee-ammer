package com.ammerzon.repository

import com.ammerzon.dto.Rating
import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class RatingsRepository : PanacheMongoRepository<Rating>