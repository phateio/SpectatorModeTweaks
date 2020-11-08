import net.minecrell.pluginyml.bukkit.BukkitPluginDescription.Permission.Default.OP

plugins {
    id("java")
    id("net.minecrell.plugin-yml.bukkit") version "0.3.0"
}

group = ofProp("package_group", "")
version = ofProp("plugin_version", "")

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
    jcenter()
}

repositories {
    mavenCentral()
//    maven {
//        // spigot
//        setUrl("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
//    }
//    maven {
//        // bungee
//        setUrl("https://oss.sonatype.org/content/groups/public/")
//    }
    maven {
        // paper
        setUrl("https://papermc.io/repo/repository/maven-public/")
    }
}

val spigotVersion = ofProp("spigot")

dependencies {
    compileOnly(group = "com.destroystokyo.paper", name = "paper-api", version = spigotVersion)
    testImplementation("junit", "junit", "4.12")
}

bukkit {
    author = "WhiteCat"
    description = ofProp("plugin_description", "")
    main = ofProp("plugin_main_class", "")
            .replace("\${group}", "$group", true)
            .replace("\${name}", "$name", true)
    apiVersion = "1.13"

    permissions {
        register("spectatormodetweaks.tpbypass") {
            default = OP
        }
    }
}

infix fun <A> A.toProp(name: String) = this to extra[name]
fun String.suffixIfNot(suffix: String) = if (this.endsWith(suffix)) this else "$this$suffix"
fun ofProp(propName: String, suffix: String = "_version", default: String = ""): String =
        extra[propName.suffixIfNot(suffix)] as? String ?: default

fun String.propVer(propName: String = split(":").last()) =
        "$this:${ofProp(propName)}"

fun ExternalModuleDependency.propVersion(propName: String) = version { prefer(ofProp(propName)) }
