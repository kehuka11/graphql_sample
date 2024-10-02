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
	implementation("org.springframework.boot:spring-boot-starter-graphql")
	implementation("org.springframework.boot:spring-boot-starter-jooq")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.mysql:mysql-connector-j")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework:spring-webflux")
	testImplementation("org.springframework.graphql:spring-graphql-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	jooqGenerator("mysql:mysql-connector-java:8.0.33")
	jooqGenerator("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")
	implementation(project(":jooq"))
}


tasks.withType<Test> {
	useJUnitPlatform()
}




