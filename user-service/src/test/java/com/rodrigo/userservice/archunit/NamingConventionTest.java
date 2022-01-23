package com.rodrigo.userservice.archunit;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;


@AnalyzeClasses(packages = "java.com.rodrigo.userservice")
public class NamingConventionTest {

    @ArchTest
    static ArchRule SERVICES_SHOULD_BE_PREFIXED_AND_BE_ANNOTATED =
            classes()
                    .that().resideInAPackage("..service..")
                    .should().haveSimpleNameEndingWith("Service")
                    .andShould().beAnnotatedWith(Component.class);

    @ArchTest
    static ArchRule MAPPERS_SHOULD_BE_PREFIXED =
            classes()
                    .that().resideInAPackage("..mapper..")
                    .should().haveSimpleNameEndingWith("Mapper");

    @ArchTest
    static ArchRule PORTS_SHOULD_BE_PREFIXED =
            classes()
                    .that().resideInAPackage("..port..out..")
                    .should().haveSimpleNameEndingWith("Port");

    @ArchTest
    static ArchRule USECASES_SHOULD_BE_PREFIXED =
            classes()
                    .that().resideInAPackage("..port..in..")
                    .should().haveSimpleNameEndingWith("UseCase");

    @ArchTest
    static ArchRule REQUEST_DTOS_SHOULD_BE_PREFIXED =
            classes()
                    .that().resideInAPackage("..dto.request..")
                    .and().haveSimpleNameNotEndingWith("Builder")
                    .should().haveSimpleNameEndingWith("RequestDto");

    @ArchTest
    static ArchRule RESPONSE_DTOS_SHOULD_BE_PREFIXED =
            classes()
                    .that().resideInAPackage("..dto.response..")
                    .and().haveSimpleNameNotEndingWith("Builder")
                    .should().haveSimpleNameEndingWith("ResponseDto");

    @ArchTest
    static ArchRule ENTITIES_SHOULD_BE_PREFIXED_AND_BE_ANNOTATED =
            classes()
                    .that().haveSimpleNameEndingWith("Entity")
                    .should().beAnnotatedWith(Document.class)
                    .andShould().resideInAPackage("..entity..");

    @ArchTest
    static ArchRule CLASSES_NAMED_CONTROLLER_SHOULD_BE_IN_ADAPTER_WEB_PACKAGE =
            classes()
                    .that().haveSimpleNameContaining("Controller")
                    .should().resideInAPackage("..adapter.web..");


}
