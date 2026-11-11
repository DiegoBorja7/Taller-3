plugins {
    id("java")
}

group = "ec.edu.uce.taller3"
version = "unspecified"

repositories {
    mavenCentral()
}

val langchain4jVersion = "1.23.0"

dependencies {
    // Source: https://mvnrepository.com/artifact/dev.langchain4j/langchain4j
    implementation("dev.langchain4j:langchain4j:${langchain4jVersion}")
    implementation("dev.langchain4j:langchain4j-embeddings:${langchain4jVersion}-beta33")
}


