public class RelationPersonneConference {

    private EnsembleConferences ensembleConferences;
    private EnsemblePersonnes ensemblePersonnes;
    private EnsembleCouplesPC ensembleCouplesPC;


    public RelationPersonneConference() {
        this.ensemblePersonnes = new EnsemblePersonnes();
        this.ensembleConferences = new EnsembleConferences();
        this.ensembleCouplesPC = new EnsembleCouplesPC();
    }


    /**
     * renvoie true si la relation courante est vide
     */
    public boolean estVide() {
        return ensembleCouplesPC.estVide();
    }

    /**
     * renvoie le nombre de couples
     */
    public int cardinal(){
        return ensembleCouplesPC.cardinal();
    }

    /**
     * renvoie true si l'ensemble des personnes de la relation courante contient p
     */
    public boolean contientPersonne(Personne p){
        if (p == null) throw new IllegalArgumentException("");
        return ensemblePersonnes.contient(p);
    }

    /**
     * renvoie true si l'ensemble des conferences de la relation courante contient c
     */
    public boolean contientConference(Conference c){
        if (c == null) throw new IllegalArgumentException("");
        return ensembleConferences.contient(c);
    }

    /**
     * ajoute (eventuellement) c a l'ensemble des conferences
     */
    public void ajouterConference(Conference c){
        if (c == null) throw new IllegalArgumentException("");
        ensembleConferences.ajouter(c);
    }

    /**
     * renvoie true si la relation courante contient le couple (p, c)
     */
    public boolean contient(Personne p, Conference c) {
        if (p == null) throw new IllegalArgumentException("");
        if (c == null) throw new IllegalArgumentException("");
        return contient(new CouplePC(p, c));
    }

    public boolean contient(CouplePC cpc) {
        if (cpc == null) throw new IllegalArgumentException("");
        return ensembleCouplesPC.contient(cpc);
    }

    /**
     * ajoute le couple (p,c) a la relation courante.
     * p est la personne, c est la conference
     * sans effet si le couple est deja present
     * ajoute (eventuellement) p dans l'ensemble des personnes
     */
    public void ajouter(Personne p, Conference c) {
        if (p == null) throw new IllegalArgumentException("");
        if (c == null) throw new IllegalArgumentException("");
        ajouter(new CouplePC(p,c));
    }

    public void ajouter(CouplePC cpc) {
        if (cpc == null) throw new IllegalArgumentException("");
        if(!contientConference(cpc.getConference()))throw new IllegalArgumentException("");
        ensemblePersonnes.ajouter(cpc.getPersonne());
        ensembleCouplesPC.ajouter(cpc);
    }


    /**
     * supprime le couple (p,c) de la relation courante.
     * p est la personne, c est la conference
     * supprime p de l'ensemble des personnes
     * si p n'est plus inscrit a aucune conference apres suppression du couple
     * renvoie true si la suppression a ete faite, false sinon
     */

    public boolean desinscrire(Personne p, Conference c) {
        if (p==null||c==null) throw new IllegalArgumentException("");
        if(!ensemblePersonnes.contient(p) || !ensembleConferences.contient(c))
            throw new IllegalArgumentException();

        if (!this.contient(p, c)) {
            return false;
        }

        this.ensembleCouplesPC.enlever(new CouplePC(p, c));
        for (CouplePC couple : ensembleCouplesPC) {
            if (couple.getPersonne().equals(p))
                return true;
        }
        this.ensemblePersonnes.enlever(p);
        return true;
    }

    //renvoie l'ensemble des conferences auxquelles p1 et p2 se sont tous les 2 inscrits
    //il faut que p1 et p2 soient inscrits a la conference pour que cette conference se trouve dans cet ensemble
    //l'ensemble renvoye pourrait etre vide

    public EnsembleConferences conferencesCommunes(Personne p1, Personne p2){
        if(p1==null || p2==null)
            throw new IllegalArgumentException();
        if(!ensemblePersonnes.contient(p1) || !ensemblePersonnes.contient(p2))
            throw new IllegalArgumentException();
        if(p1.equals(p2))
            throw new IllegalArgumentException();

        EnsembleConferences conferences = new EnsembleConferences();
        for (Conference c : ensembleConferences) {
            if (this.ensembleCouplesPC.contient(new CouplePC(p1, c)) && this.ensembleCouplesPC.contient(new CouplePC(p2, c)))
                conferences.ajouter(c);
        }
        return conferences;
    }

    public String toString(){
        return ensemblePersonnes+ "\n"+ ensembleConferences + "\n"+ ensembleCouplesPC;
    }

}
