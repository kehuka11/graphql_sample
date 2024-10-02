plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.netflix.dgs.codegen") version "6.2.1"
    id("nu.studer.jooq") version "8.0"
    id("org.flywaydb.flyway") version "8.0.1"
}

group = "graphql.sample"
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
val jooqVersion: String by project
val dbName: String by project
val dbLocalHost: String by project
val dbLocalPort: String by project
val dbLocalPassword: String by project
val dbLocalUser: String by project
val dbLocalUrl: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-jooq")
    runtimeOnly("com.mysql:mysql-connector-j")
    jooqGenerator("mysql:mysql-connector-java:8.0.33")
    jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
    implementation("org.flywaydb:flyway-mysql")
}



tasks.withType<Test> {
    useJUnitPlatform()
}


jooq {
    configurations {
        create("main") {
            jooqConfiguration.apply {
                jdbc.apply {
                    url = "jdbc:mysql://$dbLocalHost:$dbLocalPort/$dbName"
                    user = dbLocalUser
                    password = dbLocalPassword
                }
                generator.apply {
                    name = "org.jooq.codegen.DefaultGenerator"
                    database.apply {
                        name = "org.jooq.meta.mysql.MySQLDatabase"
                        inputSchema = dbName
                        excludes = "flyway_schema_history"
                    }
                    generate.apply {
                        isDeprecated = false
                        isTables = true
                    }
                    target.apply {
                        packageName = "jooq.gen"
                        directory = "$projectDir/src/main/java"
                    }
                }
            }
        }
    }
}

flyway {
    url = "jdbc:mysql://$dbLocalHost:$dbLocalPort/$dbName"
    user = dbLocalUser
    password = dbLocalPassword
}
