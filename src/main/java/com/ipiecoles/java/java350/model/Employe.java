package com.ipiecoles.java.java350.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Employe {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;

    private String prenom;

    private String matricule;

    private LocalDate dateEmbauche;

    private Double salaire = Entreprise.SALAIRE_BASE;

    private Integer performance = Entreprise.PERFORMANCE_BASE;

    private Double tempsPartiel = 1.0;

    public Employe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer performance, Double tempsPartiel) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.dateEmbauche = dateEmbauche;
        this.salaire = salaire;
        this.performance = performance;
        this.tempsPartiel = tempsPartiel;
    }

    /**
     * Méthode calculant le nombre d'années d'ancienneté à partir de la date d'embauche
     * @return
     */
    public Integer getNombreAnneeAnciennete() {
        if (dateEmbauche == null || (LocalDate.now().getYear() - dateEmbauche.getYear() < 0))
            return (null);
        else
            return (LocalDate.now().getYear() - dateEmbauche.getYear() == 0) ? 0 : LocalDate.now().getYear() - dateEmbauche.getYear();
    }

    public Integer getNbConges() {
        return Entreprise.NB_CONGES_BASE + this.getNombreAnneeAnciennete();
    }

    public Integer getNbRtt(){
        return getNbRtt(LocalDate.now());
    }

    public Integer getNbRtt(LocalDate date){
        int nbDayInTheYear = date.isLeapYear() ? 366 : 365;
        int nbWeekEndDayInTheYear = 104;

        switch (LocalDate.of(date.getYear(),1,1).getDayOfWeek()){
            case THURSDAY:
                if(date.isLeapYear())
                    nbWeekEndDayInTheYear = nbWeekEndDayInTheYear + 1;
                break;
            case FRIDAY:
                if(date.isLeapYear())
                    nbWeekEndDayInTheYear = nbWeekEndDayInTheYear + 2;
                else
                    nbWeekEndDayInTheYear = nbWeekEndDayInTheYear + 1;
                break;
            case SATURDAY:
                nbWeekEndDayInTheYear = nbWeekEndDayInTheYear + 1;
                break;
            default:
                break;
        }
        int monInt = (int) Entreprise.joursFeries(date).stream().filter(localDate ->
                localDate.getDayOfWeek().getValue() <= DayOfWeek.FRIDAY.getValue()).count();
        return (int) Math.ceil((nbDayInTheYear - Entreprise.NB_JOURS_MAX_FORFAIT - nbWeekEndDayInTheYear - Entreprise.NB_CONGES_BASE - monInt) * tempsPartiel);
    }

    /**
     * Calcul de la prime annuelle selon la règle :
     * Pour les managers : Prime annuelle de base bonnifiée par l'indice prime manager
     * Pour les autres employés, la prime de base plus éventuellement la prime de performance calculée si l'employé
     * n'a pas la performance de base, en multipliant la prime de base par un l'indice de performance
     * (égal à la performance à laquelle on ajoute l'indice de prime de base)
     *
     * Pour tous les employés, une prime supplémentaire d'ancienneté est ajoutée en multipliant le nombre d'année
     * d'ancienneté avec la prime d'ancienneté. La prime est calculée au pro rata du temps de travail de l'employé
     *
     * @return la prime annuelle de l'employé en Euros et cents
     */
    //Matricule, performance, date d'embauche, temps partiel, prime
    public Double getPrimeAnnuelle(){
        if (this.tempsPartiel == null) {
            return null;
        }
        if (this.getNombreAnneeAnciennete() == null) {
            return (0.0);
        }
        //Calcule de la prime d'ancienneté
        Double primeAnciennete = Entreprise.PRIME_ANCIENNETE * this.getNombreAnneeAnciennete();
        Double prime;
        //Prime du manager (matricule commençant par M) : Prime annuelle de base multipliée par l'indice prime manager
        //plus la prime d'anciennté.
        if(matricule != null && matricule.startsWith("M")) {
            prime = Entreprise.primeAnnuelleBase() * Entreprise.INDICE_PRIME_MANAGER + primeAnciennete;
        }
        //Pour les autres employés en performance de base, uniquement la prime annuelle plus la prime d'ancienneté.
        else if (this.performance == null || Entreprise.PERFORMANCE_BASE.equals(this.performance)){
            prime = Entreprise.primeAnnuelleBase() + primeAnciennete;
        }
        //Pour les employés plus performants, on bonnifie la prime de base en multipliant par la performance de l'employé
        // et l'indice de prime de base.
        else {
            prime = Entreprise.primeAnnuelleBase() * (this.performance + Entreprise.INDICE_PRIME_BASE) + primeAnciennete;
        }
        //Au pro rata du temps partiel.
        return prime * this.tempsPartiel;
    }

    //Augmenter salaire
    //public void augmenterSalaire(double pourcentage){}

    public Long getId() {
        return id;
    }

    public Long setId(Long id) {
        this.id = id;
        return this.id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public String setNom(String nom) {
        this.nom = nom;
        return this.nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public String setPrenom(String prenom) {
        this.prenom = prenom;
        return this.prenom;
    }

    /**
     * @return the matricule
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * @param matricule the matricule to set
     */
    public String setMatricule(String matricule) {
        this.matricule = matricule;
        return this.matricule;
    }

    /**
     * @return the dateEmbauche
     */
    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    /**
     * @param dateEmbauche the dateEmbauche to set
     */
    public LocalDate setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
        return this.dateEmbauche;
    }

    /**
     * @return the salaire
     */
    public Double getSalaire() {
        return salaire;
    }

    /**
     * @param salaire the salaire to set
     */
    public Double setSalaire(Double salaire) {
        this.salaire = salaire;
        return this.salaire;
    }

    public Integer getPerformance() {
        return performance;
    }

    public Integer setPerformance(Integer performance) {
        this.performance = performance;
        return this.performance;
    }

    public Double getTempsPartiel() {
        return tempsPartiel;
    }

    public Double setTempsPartiel(Double tempsPartiel) {
        this.tempsPartiel = tempsPartiel;
        return this.tempsPartiel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employe)) return false;
        Employe employe = (Employe) o;
        return  Objects.equals(id, employe.id) &&
                Objects.equals(nom, employe.nom) &&
                Objects.equals(prenom, employe.prenom) &&
                Objects.equals(matricule, employe.matricule) &&
                Objects.equals(dateEmbauche, employe.dateEmbauche) &&
                Objects.equals(salaire, employe.salaire) &&
                Objects.equals(performance, employe.performance) &&
                Objects.equals(tempsPartiel, employe.tempsPartiel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, matricule, dateEmbauche, salaire, performance);
    }
}
