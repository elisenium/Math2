public class RelationPersonneConference {

    private EnsembleConferences ensembleConferences;
    private EnsemblePersonnes ensemblePersonnes;
    private EnsembleCouplesPC ensembleCouplesPC;


    public RelationPersonneConference() {
        this.ensemblePersonnes = new EnsemblePersonnes();
        this.ensembleConferences = new EnsembleConferences();
        this.ensembleCouplesPC = new EnsembleCouplesPC();
    }


    /** renvoie true si la relation courante est vide */
    public boolean estVide() {
        return ensembleCouplesPC.estVide();
    }


    /** renvoie true si la relation courante contient le couple (p, c)*/
    public boolean contient(Personne p, Conference c) {
        if (p==null) throw new IllegalArgumentException("");
        if (c==null) throw new IllegalArgumentException("");
       return contient(new CouplePC(p,c));
    }

    public boolean contient(CouplePC cpc) {
        if (cpc==null) throw new IllegalArgumentException("");
        return ensembleCouplesPC.contient(cpc);
    }

    /**
     * ajoute le couple (p,c) a la relation courante.
     * p est la personne, c est la conference
     * sans effet si le couple est deja present
     * ajoute (eventuellement) p dans l'ensemble des personnes
     * ajoute (eventuellement) c dans l'ensemble des conferences
     */
    public void ajouter(Personne p, Conference c) {
        ajouter(new CouplePC(p,c));
    }

    public void ajouter(CouplePC cpc) {
        if (cpc==null) throw new IllegalArgumentException("");
        ensemblePersonnes.ajouter(cpc.getPersonne());
        ensembleConferences.ajouter(cpc.getConference());
        ensembleCouplesPC.ajouter(cpc);
    }

    //renvoie l'ensemble des personnes qui se sont inscrites a 2 conferences
    public EnsemblePersonnes inscritesA2Conferences(){
        EnsemblePersonnes ensPersonnes = new EnsemblePersonnes();
        for (Personne personne : ensemblePersonnes) {
            if (nombreConferences(personne) == 2)
                ensPersonnes.ajouter(personne);
        }

      return ensPersonnes;

    }

    private int nombreConferences(Personne personne) {
        int nombreConferences = 0;
        for (CouplePC couple : ensembleCouplesPC) {
            if (couple.getPersonne().equals(personne))
                nombreConferences++;
        }
        return nombreConferences;
    }

    //verifie si les 2 personnes ont au moins une conference en commun
    public boolean vontSeCroiser(Personne p1, Personne p2){
        if(p1==null || p2==null || !ensemblePersonnes.contient(p1) || !ensemblePersonnes.contient(p2))
            throw new IllegalArgumentException();

        for (CouplePC couple : ensembleCouplesPC) {
            if (couple.getPersonne().equals(p1) && ensembleCouplesPC.contient(new CouplePC(p2, couple.getConference())))
                return true;
        }

        return false;
    }

    public String toString(){
        return ensemblePersonnes+ "\n"+ ensembleConferences + "\n"+ ensembleCouplesPC;
    }

}
