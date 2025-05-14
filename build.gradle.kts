plugins {
    java
    id("org.springframework.boot") version "3.4.5"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.testex"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
//    Spring
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework:spring-web:6.2.6")
    implementation("org.springframework.data:spring-data-jpa:3.4.5")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.5")
    implementation("org.springframework.boot:spring-boot-starter-validation")

//  Jakarta
    implementation("jakarta.validation:jakarta.validation-api:3.1.1")


//  Hibernate
    implementation("org.hibernate.orm:hibernate-core")
    implementation("org.postgresql:postgresql")

//  Lombok
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

//  MapStruct
    implementation("org.mapstruct:mapstruct:1.6.3")
    implementation("org.mapstruct:mapstruct-processor:1.6.3")
    annotationProcessor("org.mapstruct:mapstruct-processor:1.6.3")

//  MapStruct + Lombok
    implementation("org.projectlombok:lombok-mapstruct-binding:0.2.0")

//  OpenAPI Swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.7.0")

//   Logging
    implementation("org.slf4j:slf4j-api:2.0.17")

//  Test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation("org.mockito:mockito-core:5.17.0")


}

tasks.withType<Test> {
    useJUnitPlatform()
}



