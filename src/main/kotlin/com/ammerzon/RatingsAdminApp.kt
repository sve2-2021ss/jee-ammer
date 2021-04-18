package com.ammerzon

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition
import org.eclipse.microprofile.openapi.annotations.info.Info
import javax.ws.rs.core.Application

@OpenAPIDefinition(info = Info(
        title = "Ratings Admin API",
        version = "1.0-SNAPSHOT",
        description = "A simple API to manage the ratings of the Istio bookinfo microservice application.")
)
class RatingsAdminApp : Application()