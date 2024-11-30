plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "content"

include(":api")
include(":batch")
include(":consumer")
include(":domain")
