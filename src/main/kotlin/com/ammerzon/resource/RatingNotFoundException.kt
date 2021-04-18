package com.ammerzon.resource

class RatingNotFoundException(id: String) : RuntimeException("Rating with $id does not exist!")