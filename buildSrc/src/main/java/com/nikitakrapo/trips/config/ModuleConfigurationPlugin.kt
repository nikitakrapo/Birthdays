package com.nikitakrapo.trips.config

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

class ModuleConfigurationPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create("moduleConfigurationPlugin", ModuleConfigurationExtension::class, project)
    }
}