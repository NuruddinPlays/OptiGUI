plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ini4j)
    implementation(libs.jsonbuilder)
}
