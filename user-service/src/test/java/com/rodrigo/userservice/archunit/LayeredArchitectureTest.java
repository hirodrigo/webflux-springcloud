package com.rodrigo.userservice.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "com.rodrigo.userservice")
public class LayeredArchitectureTest {

    @ArchTest
    static final ArchRule LAYER_DEPENDENCIES_ARE_RESPECTED = layeredArchitecture()
            .layer("DatabaseAdapter").definedBy("..adapter.database..")
            .layer("WebAdapter").definedBy("..adapter.web..")
            .layer("Services").definedBy("..core.service..")
            .layer("Ports").definedBy("..core.port..out..")
            .layer("UseCases").definedBy("..core.port..in..")

            .whereLayer("DatabaseAdapter").mayNotBeAccessedByAnyLayer()
            .whereLayer("WebAdapter").mayNotBeAccessedByAnyLayer()
            .whereLayer("Services").mayNotBeAccessedByAnyLayer()
            .whereLayer("Ports").mayOnlyBeAccessedByLayers("DatabaseAdapter", "Services")
            .whereLayer("UseCases").mayOnlyBeAccessedByLayers("WebAdapter", "Services");


}
