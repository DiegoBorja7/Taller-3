plugins {
    id("java")
}

group = "ec.edu.uce.taller3"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    // Java Tokenizer Kit
    // Source: https://mvnrepository.com/artifact/com.knuddels/jtokkit
    implementation("com.knuddels:jtokkit:1.1.0")
}

tasks.test {
    useJUnitPlatform()
}