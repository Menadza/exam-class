package com.kondratev.exam;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.kondratev.exam");

        noClasses()
            .that()
            .resideInAnyPackage("com.kondratev.exam.service..")
            .or()
            .resideInAnyPackage("com.kondratev.exam.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.kondratev.exam.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
