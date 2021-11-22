package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeTest {

    @Test
    void getNombreAnneeAncienneteWithDateEmbaucheNow() {
        // given
        Employe employe = new Employe("Doe", "John", "E31250", LocalDate.now(), 2500.0, 1, 1.0);

        // When
        Integer nbAnnees = employe.getNombreAnneeAnciennete();

        // Then
        Assertions.assertThat(nbAnnees).isEqualTo(0);
    }

    @Test
    void getNombreAnneeAncienneteWithDateEmbaucheInPast() {
        // given
        Employe employe = new Employe("Doe", "John", "E31250", LocalDate.now().minusYears(5), 2500.0, 1, 1.0);

        // When
        Integer nbAnnees = employe.getNombreAnneeAnciennete();

        // Then
        Assertions.assertThat(nbAnnees).isEqualTo(5);
    }

    @Test
    void getNombreAnneeAncienneteWithDateEmbaucheInFutur() {
        // given
        Employe employe = new Employe("Doe", "John", "E31250", LocalDate.now().plusYears(5), 2500.0, 1, 1.0);

        // When
        Integer nbAnnees = employe.getNombreAnneeAnciennete();

        // Then
        Assertions.assertThat(nbAnnees).isEqualTo(0);
    }

    @Test
    void getNombreAnneeAncienneteWithDateEmbaucheIsNull() {
        // given
        Employe employe = new Employe("Doe", "John", "E31250", null, 2500.0, 1, 1.0);

        // When
        Integer nbAnnees = employe.getNombreAnneeAnciennete();

        // Then
        Assertions.assertThat(nbAnnees).isNotNull();
        Assertions.assertThat(nbAnnees).isNotNegative();
        Assertions.assertThat(nbAnnees).isEqualTo(0);
    }
}