package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.converter.DefaultArgumentConverter;

final class NullableConverter extends SimpleArgumentConverter {
    @Override
    protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        if ("null".equals(source)) {
            return null;
        }
        return DefaultArgumentConverter.INSTANCE.convert(source, targetType);
    }
}

class EmployeTest {

    @ParameterizedTest(name = "getNombreAnneeAnciennete: LocalDate.now() + {0} années - Expect : {1}")
    @CsvSource({
            "0, 0",
            "-5, 5",
            "5, null"
    })
    void getNombreAnneeAnciennete(Integer offset,
                                  @ConvertWith(NullableConverter.class) Integer expected) {
        // given
        Employe employe = new Employe("Doe", "John", "E31250", LocalDate.now().plusYears(offset), 2500.0, 1, 1.0);

        // When
        Integer nbAnnees = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnnees).isEqualTo(expected);
    }

    @Test
    void getNombreAnneeAncienneteWithDateEmbaucheIsNull() {
        // given
        Employe employe = new Employe("Doe", "John", "E31250", null, 2500.0, 1, 1.0);

        // When
        Integer nbAnnees = employe.getNombreAnneeAnciennete();

        // Then
        Assertions.assertThat(nbAnnees).isNull();
    }

    @ParameterizedTest(name = "getPrimeAnnuelle: Employee: [matricule: {0}, anciennete: {1}, performance: {2}, tempsTravail: {3}] - Expect : {4}")
    @CsvSource({
            "'M00001', 0, 1, 1.0, 1700.0",
            "'M00001', 1, 1, 1.0, 1800.0",
            "'M00001', 0, 2, 1.0, 1700.0",
            "'M00001', 0, -1, 1.0, 1700.0",
            "'M00001', 0, 1, 0.5, 850.0",

            "'M00001', -1, 1, 1.0, 0.0",
            "'M00001', 0, null, 1.0, 1700",
            "'M00001', 0, 1, null, null",
            "null, 0, 1, 1.0, 1000.0",

            "'E00002', 0, 1, 1.0, 1000.0",
            "'E00002', 1, 1, 1.0, 1100.0",
            "'E00002', 0, 2, 1.0, 2300.0",
            "'E00002', 0, 1, 0.5, 500.0",
    })
    void getPrimeAnnuelle(@ConvertWith(NullableConverter.class) String matricule,
                          @ConvertWith(NullableConverter.class) Integer anneeAnciennete,
                          @ConvertWith(NullableConverter.class) Integer performance,
                          @ConvertWith(NullableConverter.class) Double tempsDeTravail,
                          @ConvertWith(NullableConverter.class) Double expected) {
        // given
        Employe employe = new Employe("Doe", "John", matricule, LocalDate.now().minusYears(anneeAnciennete), 2500.0, performance, tempsDeTravail);

        // When
        Double prime = employe.getPrimeAnnuelle();

        //Then
        Assertions.assertThat(prime).isEqualTo(expected);
    }
}