import com.ipiecoles.java.java350.model.Employe;
import com.thoughtworks.gauge.Step;
import org.assertj.core.api.Assertions;

import java.time.LocalDate;

public class EmployeStepImplementation {
    Employe employe;
    Integer nbAnneeAnciennete;

    @Step("Soit un employé embauché cette année")
    public void createEmploye() {
        employe = new Employe("Doe", "John", "M12345", LocalDate.now(), 2500d, 1, 1.0);
    }

    @Step("Lorsque je calcule son nombre d'année d'ancienneté")
    public void getNbAnneeAnciennete() {
        nbAnneeAnciennete = employe.getNombreAnneeAnciennete();
    }

    @Step("J'obtiens <0>")
    public void verifAnciennete(Integer expected) {
        Assertions.assertThat(nbAnneeAnciennete).isEqualTo(expected);
    }
}
