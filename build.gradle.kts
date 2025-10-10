/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
import com.diffplug.gradle.spotless.SpotlessExtension
import com.diffplug.gradle.spotless.SpotlessExtensionPredeclare
import com.diffplug.spotless.LineEnding
import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.spotless)
    id("com.google.devtools.ksp") version "2.2.20-2.0.3" apply false
    id("androidx.room") version "2.8.2" apply false
}

// Read versions from the version catalog for Spotless, Detekt, and the JVM target.
val ktfmtVersion = libs.versions.ktfmt.get()
val detektVersion = libs.versions.detekt.get()
val jvmTargetVersion = libs.versions.jvmTarget.get()

// The `allprojects` block applies the following configuration to the root project and all
// subprojects.
// This is used for Spotless because it formats a wide range of files, including build scripts
// (`.kts`),
// documentation (`.md`), and `.gitignore` files that exist in both the root and subprojects.
// Applying it to `allprojects` ensures consistent formatting across the entire codebase.
allprojects {
    apply(plugin = "com.diffplug.spotless")
    val spotlessFormatters: SpotlessExtension.() -> Unit = {
        // Enforce native line endings for all files.
        lineEndings = LineEnding.PLATFORM_NATIVE

        // Format Markdown and .gitignore files.
        format("misc") {
            target("*.md", ".gitignore")
            trimTrailingWhitespace()
            endWithNewline()
        }

        // Format Kotlin source files using ktfmt with Google's style guide.
        kotlin {
            target("src/**/*.kt")
            ktfmt(libs.versions.ktfmt.get()).kotlinlangStyle()
            trimTrailingWhitespace()
            endWithNewline()
            targetExclude("**/spotless.kt") // Exclude the spotless configuration file itself.
        }

        // Format Gradle Kotlin script files (*.kts).
        kotlinGradle {
            target("*.kts")
            ktfmt(ktfmtVersion).kotlinlangStyle()
            trimTrailingWhitespace()
            endWithNewline()
            // Applies a license header to the top of Gradle files.
            // 1. The first argument (`rootProject.file("spotless/spotless.kt")`) points to the
            // template
            // file containing the license text.
            // 2. The second argument is a regular expression that tells Spotless where the license
            // should
            // end.
            //    It looks for the first line that starts with `import`, `plugins`, `buildscript`,
            // etc.,
            // and places the license before it.
            licenseHeaderFile(
                rootProject.file("spotless/spotless.kt"),
                "(import|plugins|buildscript|dependencies|pluginManagement|dependencyResolutionManagement)",
            )
        }

        // Apply license formatting to Kotlin source files separately.
        // This allows for more granular control and excludes specific files.
        format("license") {
            licenseHeaderFile(rootProject.file("spotless/spotless.kt"), "(package|@file:)")
            target("src/**/*.kt")
            // Exclude files that are copied from other sources or should not have a license header.
            targetExclude()
        }
    }
    // Configure Spotless for the current project.
    configure<SpotlessExtension> {
        spotlessFormatters()
        // Pre-declare dependencies for the root project to improve performance.
        if (project.rootProject == project) {
            predeclareDeps()
        }
    }
    // Configure Spotless predeclaration for the root project.
    if (project.rootProject == project) {
        configure<SpotlessExtensionPredeclare> { spotlessFormatters() }
    }
}

// The `subprojects` block applies the following configuration only to the subprojects, excluding
// the root project.
// This is used for Detekt because its job is to perform static analysis on Kotlin *source code*
// (e.g., in `src/main/kotlin`).
// The root project only contains configuration files, not application source code, so there is
// nothing for Detekt to analyze there.
subprojects {
    project.apply(plugin = "io.gitlab.arturbosch.detekt")
    // Configure the Detekt extension.
    configure<DetektExtension> {
        toolVersion = detektVersion
        allRules = true // Enable all rules for analysis.
        config.from(
            rootProject.file("config/detekt/detekt.yml")
        ) // Use a custom Detekt config file.
        buildUponDefaultConfig = true
    }
    val buildDir = project.layout.buildDirectory.asFile.get().canonicalPath
    // Configure Detekt tasks for each project.
    tasks.withType<Detekt>().configureEach {
        jvmTarget = jvmTargetVersion
        exclude {
            it.file.canonicalPath.startsWith(buildDir)
        } // Exclude the build directory from analysis.
        // Configure the output reports for Detekt.
        reports {
            html.required.set(true)
            xml.required.set(true)
            txt.required.set(true)
        }
    }
}
