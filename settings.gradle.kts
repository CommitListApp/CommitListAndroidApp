/*
 * Copyright (C) 2025 Kaushik Saurabh
 * SPDX-License-Identifier: Apache-2.0
 */
pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CommitListAndrorid"

include(":app")

include(":core:data")

include(":core:domain")

include(":core:database")
