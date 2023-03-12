import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.gradle.internal.impldep.org.eclipse.jgit.lib.ObjectChecker.type

fun task() {

}



plugins {
  java
  application
  id("com.github.johnrengelman.shadow") version "7.1.2"
  id("org.gretty") version "3.0.5"
}

gretty {
  httpPort = 5050 // set the port number here
  contextPath = "/" // set the context path if necessary
  servletContainer = "jetty9" // use Jetty as the web server
}


group = "Dawit"
version = "1.0.0-SNAPSHOT"

repositories {
  mavenCentral()
}

val vertxVersion = "4.3.8"
val junitJupiterVersion = "5.9.1"

val mainVerticleName = "Dawit.learningvertx2.verticles.MainVerticle"
val launcherClassName = "io.vertx.core.Launcher"

val watchForChange = "src/**/*"
val doOnChange = "${projectDir}/gradlew classes"

application {
  mainClass.set(launcherClassName)
}

dependencies {
  implementation(platform("io.vertx:vertx-stack-depchain:$vertxVersion"))
  implementation("io.vertx:vertx-web")
  testImplementation("io.vertx:vertx-junit5")
  testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
  implementation("javax.servlet:javax.servlet-api:4.0.1")
}

java {
  sourceCompatibility = JavaVersion.VERSION_11
  targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
  manifest {
    attributes(mapOf("Main-Verticle" to mainVerticleName))
  }
  mergeServiceFiles()
}

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
  manifest {
    attributes(mapOf("MyFirstVerticle" to "Dawit.learningvertx2.verticles.MyFirstVerticle"))
  }
  mergeServiceFiles()
}

tasks.withType<ShadowJar> {
  archiveClassifier.set("fat")
  manifest {
    attributes(mapOf("FormVerticle" to "Dawit.learningvertx2.verticles.FormVerticle"))
  }
  mergeServiceFiles()
}


tasks.withType<Test> {
  useJUnitPlatform()
  testLogging {
    events = setOf(PASSED, SKIPPED, FAILED)
  }
}

tasks.withType<JavaExec> {
  args = listOf("run", mainVerticleName, "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
}

tasks.withType<JavaExec> {
  args = listOf("run", "Dawit.learningvertx2.verticles.MyFirstVerticle", "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
}

tasks.withType<JavaExec> {
  args = listOf("run", "Dawit.learningvertx2.verticles.FormVerticle", "--redeploy=$watchForChange", "--launcher-class=$launcherClassName", "--on-redeploy=$doOnChange")
}



