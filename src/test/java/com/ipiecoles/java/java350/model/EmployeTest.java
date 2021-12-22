package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

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

    @ParameterizedTest(name = "getId: Expect : {1}")
    @CsvSource({
            "1, 1"
    })
    void getId(@ConvertWith(NullableConverter.class) Long id,
               @ConvertWith(NullableConverter.class) Long expected) {
        // given
        Employe employe = new Employe("Doe", "John", "E31250", LocalDate.now(), 2500.0, 1, 1.0);
        employe.setId(id);

        // when
        Long result = employe.getId();

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "setId: Given {0} - Expect : {1}")
    @CsvSource({
            "2, 2",
            "86382538323, 86382538323",
            "-628323232, -628323232",
            "null, null"
    })
    void setId(@ConvertWith(NullableConverter.class) Long id,
               @ConvertWith(NullableConverter.class) Long expected) {
        // given
        Employe employe = new Employe("Doe", "John", "E31250", LocalDate.now(), 2500.0, 1, 1.0);

        // when
        Long result = employe.setId(id);

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "getPrenom: Given {0} - Expect : {1}")
    @CsvSource({
            "'John', 'John'",
            "'Philip', 'Philip'",
            "null, null"
    })
    void getPrenom(@ConvertWith(NullableConverter.class) String prenom,
                   @ConvertWith(NullableConverter.class) String expected) {
        // given
        Employe employe = new Employe("Doe", prenom, "E31250", LocalDate.now(), 2500.0, 1, 1.0);

        // when
        String result = employe.getPrenom();

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "setPrenom: Given {0} - Expect : {1}")
    @CsvSource({
            "'John', 'John'",
            "'Philip', 'Philip'",
            "null, null"
    })
    void setPrenom(@ConvertWith(NullableConverter.class) String prenom,
                   @ConvertWith(NullableConverter.class) String expected) {
        // given
        Employe employe = new Employe("Doe", "John", "E31250", LocalDate.now(), 2500.0, 1, 1.0);

        // when
        String result = employe.setPrenom(prenom);

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "getNom: Given {0} - Expect : {1}")
    @CsvSource({
            "'Doe', 'Doe'",
            "'Philip', 'Philip'",
            "null, null"
    })
    void getNom(@ConvertWith(NullableConverter.class) String nom,
                @ConvertWith(NullableConverter.class) String expected) {
        // given
        Employe employe = new Employe(nom, "John", "E31250", LocalDate.now(), 2500.0, 1, 1.0);

        // when
        String result = employe.getNom();

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "setNom: Given {0} - Expect : {1}")
    @CsvSource({
            "'Doe', 'Doe'",
            "'Philip', 'Philip'",
            "null, null"
    })
    void setNom(@ConvertWith(NullableConverter.class) String nom,
                @ConvertWith(NullableConverter.class) String expected) {
        // given
        Employe employe = new Employe("Doe", "John", "E31250", LocalDate.now(), 2500.0, 1, 1.0);

        // when
        String result = employe.setNom(nom);

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "getMatricule: Given {0} - Expect : {1}")
    @CsvSource({
            "'L12345', 'L12345'",
            "'M00001', 'M00001'",
            "null, null"
    })
    void getMatricule(@ConvertWith(NullableConverter.class) String given,
                @ConvertWith(NullableConverter.class) String expected) {
        // given
        Employe employe = new Employe("Doe", "John", given, LocalDate.now(), 2500.0, 1, 1.0);

        // when
        String result = employe.getMatricule();

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }

    @ParameterizedTest(name = "setMatricule: Given {0} - Expect : {1}")
    @CsvSource({
            "'M12345', 'M12345'",
            "'L09876', 'L09876'",
            "null, null"
    })
    void setMatricule(@ConvertWith(NullableConverter.class) String given,
                @ConvertWith(NullableConverter.class) String expected) {
        // given
        Employe employe = new Employe("Doe", "John", "E31250", LocalDate.now(), 2500.0, 1, 1.0);

        // when
        String result = employe.setNom(given);

        //then
        Assertions.assertThat(result).isEqualTo(expected);
    }
    @Test
    void getDateEmbauche() {
        // given
        LocalDate time = LocalDate.now();
        Employe employe = new Employe("Doe", "John", "M10000", time, 2500.0, 1, 1.0);

        // when
        LocalDate result = employe.getDateEmbauche();

        //then
        Assertions.assertThat(result).isEqualTo(time);
    }

    @Test
    void setDateEmbauche() {
        // given
        Employe employe = new Employe("Doe", "John", "E31250", LocalDate.now(), 2500.0, 1, 1.0);
        LocalDate time = LocalDate.now();

        // when
        LocalDate result = employe.setDateEmbauche(time);

        //then
        Assertions.assertThat(result).isEqualTo(time);
    }

    @Test
    void getSalaire() {
        // given
        Employe employe = new Employe("Doe", "John", "M10000", LocalDate.now(), 2500.0, 1, 1.0);

        // when
        Double result = employe.getSalaire();

        //then
        Assertions.assertThat(result).isEqualTo(2500.0);
    }

    @Test
    void setSalaire() {
        // given
        Employe employe = new Employe("Doe", "John", "E31250", LocalDate.now(), 2500.0, 1, 1.0);

        // when
        Double result = employe.setSalaire(1500.0);

        //then
        Assertions.assertThat(result).isEqualTo(1500.0);
    }

    @Test
    void getPerformance() {
        // given
        Employe employe = new Employe("Doe", "John", "M10000", LocalDate.now(), 2500.0, 1, 1.0);

        // when
        Integer result = employe.getPerformance();

        //then
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    void setPerformance() {
        // given
        Employe employe = new Employe("Doe", "John", "E31250", LocalDate.now(), 2500.0, 1, 1.0);

        // when
        Integer result = employe.setPerformance(15);

        //then
        Assertions.assertThat(result).isEqualTo(15);
    }

    @Test
    void getTempsPartiel() {
        // given
        Employe employe = new Employe("Doe", "John", "M10000", LocalDate.now(), 2500.0, 1, 1.0);

        // when
        Double result = employe.getTempsPartiel();

        //then
        Assertions.assertThat(result).isEqualTo(1.0);
    }

    @Test
    void setTempsPartiel() {
        // given
        Employe employe = new Employe("Doe", "John", "E31250", LocalDate.now(), 2500.0, 1, 1.0);

        // when
        Double result = employe.setTempsPartiel(0.5);

        //then
        Assertions.assertThat(result).isEqualTo(0.5);
    }

    /*
    ** Si la date d'entrée de l'employé est anterieur de 5 ans à la date du jour,
    ** Lorsque l'ont demande à l'application de nous fournir le nombre d'année d'ancienneté
    ** Alors elle nous retourne 5 ans d'ancienneté.
    *
    ** Si la date d'entrée de l'employé est posterieur à la date du jour,
    ** Lorsque l'ont demande à l'application de nous fournir le nombre d'année d'ancienneté
    ** Alors elle nous retourne une valeur nulle / une erreur.
    *
    ** Si la date d'entrée de l'employé est égale à la date du jour,
    ** Lorsque l'ont demande à l'application de nous fournir le nombre d'année d'ancienneté
    ** Alors elle nous retourne 0 année d'ancienneté.
    */

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

    /*
    ** Pour un employé s'appellant John Doe, ayant pour matricule M00001, un an d'ancienneté, et travaillant en temps plein à un taux d'efficacité normale.
    ** Lorsque l'ont demande à l'application de nous fournir la prime annuelle pour cet employé,
    ** Alors elle nous retourne 1800.00 euros de prime annuelle.
    */

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

            "'T00002', 0, 1, 1.0, 1000.0",
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

    @Test
    void equalsWithEqualsEmploye() {
        //given
        Employe a = new Employe("Doe", "John", "M00001", LocalDate.now(), 1000.0, 1, 1.0);
        Employe b = new Employe("Doe", "John", "M00001", LocalDate.now(), 1000.0, 1, 1.0);
        //when
        Boolean result = a.equals(b);
        //then
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void equalsWithSameEmploye() {
        //given
        Employe a = new Employe("Doe", "John", "M00001", LocalDate.now(), 1000.0, 1, 1.0);
        //when
        Boolean result = a.equals(a);
        //then
        Assertions.assertThat(result).isTrue();
    }

    @ParameterizedTest(name = "equalsWithNonEqualsEmploye: [{0}, {1}, {2}, {3}, {4}, {5}, {6}] - Expect : false")
    @CsvSource({
            "'Silverhand', 'John', 'M12345', 0, 1000.0, 1, 1.0",
            "'Doe', 'Johnny', 'M12345', 0, 1000.0, 1, 1.0",
            "'Doe', 'John', 'M00001', 0, 1000.0, 1, 1.0",
            "'Doe', 'John', 'M12345', 1, 1000.0, 1, 1.0",
            "'Doe', 'John', 'M12345', 0, 1500.0, 1, 1.0",
            "'Doe', 'John', 'M12345', 0, 1000.0, 2, 1.0",
            "'Doe', 'John', 'M12345', 0, 1000.0, 1, 0.5",
    })
    void equalsWithNonEqualsEmploye(String nom, String prenom, String matricule, Integer anciennete, Double salaire, Integer performance, Double tempsPartiel) {
        //given
        Employe a = new Employe("Doe", "John", "M12345", LocalDate.now(), 1000.0, 1, 1.0);
        Employe b = new Employe(nom, prenom, matricule, LocalDate.now().minusYears(anciennete), salaire, performance, tempsPartiel);
        //when
        Boolean result = a.equals(b);
        //then
        Assertions.assertThat(result).isFalse();
    }

    @Test
    void equalsWithDifferentObject() {
        //given
        Employe a = new Employe("Doe", "John", "M00001", LocalDate.now(), 1000.0, 1, 1.0);
        String b = "lol";
        //when
        Boolean result = a.equals(b);
        //then
        Assertions.assertThat(result).isFalse();
    }


    // Tests de la méthode augmenterSalaire.

    @ParameterizedTest(name = "augmenterSalaireWithValuedPercentage: Pourcentage d'augmentation: {0} - Resultat attendu : {1}")
    @CsvSource({
            "8.0, 1080.0",
            "-10.0, 900.0",
            "0, 1000.0",
            "1.5, 1015.0"
    })
    void augmenterSalaireWithValuedPercentage(Double pourcentage, Double expected)
    {
        //given
        Employe a = new Employe("Doe", "John", "M12345", LocalDate.now(), 1000.0, 1, 1.0);

        //when
        Double salaire = a.augmenterSalaire(pourcentage);

        //then
        Assertions.assertThat(salaire).isEqualTo(expected);
    }
}