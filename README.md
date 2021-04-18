# Ratings Admin Service

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/ratings-admin-1.0.0-SNAPSHOT-runner`

# Kotlin Data classes

Kotlin data classes are a very convenient way of defining data carrier classes, making them a great match to define an entity class.

But this type of class comes with some limitations: all fields needs to be initialized at construction time or be marked as nullable, and the generated constructor needs to have as parameters all the fields of the data class.

MongoDB with Panache uses the PojoCodec, a MongoDB codec which mandates the presence of a parameterless constructor.

Therefore, if you want to use a data class as an entity class, you need a way to make Kotlin generate an empty constructor. To do so, you need to provide default values for all the fields of your classes. The following sentence from the Kotlin documentation explains it:

The last option is to the use the no-arg compiler plugin. This plugin is configured with a list of annotations, and the end result is the generation of no-args constructor for each class annotated with them.

For MongoDB with Panache, you could use the @MongoEntity annotation on your data class for this:

