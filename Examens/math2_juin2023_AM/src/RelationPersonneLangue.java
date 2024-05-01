import java.util.Iterator;

public class RelationPersonneLangue {

    private EnsembleLangues ensembleLangues;
    private EnsemblePersonnes ensemblePersonnes;
    private EnsembleCouplesPL ensembleCouplesPL;


    public RelationPersonneLangue() {
        this.ensemblePersonnes = new EnsemblePersonnes();
        this.ensembleLangues = new EnsembleLangues();
        this.ensembleCouplesPL = new EnsembleCouplesPL();
    }


    /** renvoie true si la relation courante est vide */
    public boolean estVide() {
        return ensembleCouplesPL.estVide();
    }


    /** renvoie true si la relation courante contient le couple (p, l)*/
    public boolean contient(Personne p, Langue l) {
        if (p==null) throw new IllegalArgumentException("");
        if (l==null) throw new IllegalArgumentException("");
       return contient(new CouplePL(p,l));
    }

    public boolean contient(CouplePL cpl) {
        if (cpl==null) throw new IllegalArgumentException("");
        return ensembleCouplesPL.contient(cpl);
    }

    /**
     * ajoute le couple (p,l) a la relation courante.
     * p est la personne, l est la langue
     * sans effet si le couple est deja present
     * ajoute (eventuellement) p dans l'ensemble des personnes
     * ajoute (eventuellement) l dans l'ensemble des langues
     */
    public void ajouter(Personne p, Langue l) {
        ajouter(new CouplePL(p,l));
    }

    public void ajouter(CouplePL cpl) {
        if (cpl==null) throw new IllegalArgumentException("");
        ensemblePersonnes.ajouter(cpl.getPersonne());
        ensembleLangues.ajouter(cpl.getLangue());
        ensembleCouplesPL.ajouter(cpl);
    }

    //renvoie l'ensemble des personnes qui sont bilingues
    //ces personnes connaissent exactement 2 langues (pas plus)!
    public EnsemblePersonnes bilingues(){
        EnsemblePersonnes bilingues = new EnsemblePersonnes();

        for (Personne personne : ensemblePersonnes) {
            if (nombreLanguesPersonne(personne) == 2) {
                bilingues.ajouter(personne);
            }
        }
        return bilingues;
    }

    private int nombreLanguesPersonne(Personne personne) {
        int nombreLangues = 0;

        for (CouplePL couple : ensembleCouplesPL) {
            if (couple.getPersonne().equals(personne)) {
                nombreLangues++;
            }
        }
        return nombreLangues;
    }

    //verifie si les 2 personnes ont au moins une langue en commun
    public boolean peuventCommuniquer(Personne p1, Personne p2){
        if(p1==null || p2==null || !ensemblePersonnes.contient(p1) || !ensemblePersonnes.contient(p2))
            throw new IllegalArgumentException();

        for (CouplePL couple : ensembleCouplesPL) {
            if (couple.getPersonne().equals(p1) && ensembleCouplesPL.contient(new CouplePL(p2, couple.getLangue()))) {
                return true;
            }
        }
        return false;
    }

    public String toString(){
        return ensemblePersonnes+ "\n"+ensembleLangues+ "\n"+ensembleCouplesPL;
    }

}
