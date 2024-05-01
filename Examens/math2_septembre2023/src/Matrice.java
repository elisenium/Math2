public class Matrice {
    private final int nbLignes;              // nombre de lignes
    private final int nbColonnes;            // nombre de colonnes
    private final double[][] data;           // matrice (nbLignes,nbColonnes)

    // ce constructeur cree la matrice nulle de genre (a,b)
    public Matrice(int a, int b) throws IllegalArgumentException {
        if (a<=0 || b<=0)
            throw new IllegalArgumentException("a ou b négatif") ;
        data = new double[a][b] ;
        nbLignes = a ;
        nbColonnes = b ;
    }
	// Renvoie true si d est un état stable de la matrice courante donc si this*d = d
    // Lance une MathException si la matrice courant n'est pas carrée
    // Lance une IllegalArgumentException si d est null
    // Lance une IllegalArgumentException si d n'a pas les bonnes dimensions (this*d impossible)
    public boolean aPourEtatStable(Matrice d) {
        // Vérifie si la matrice courante est carrée
        if (this.nbColonnes != this.nbLignes)
            throw new MathException("Cette matrice n'est pas carrée");

        // Vérifie si la matrice d est null
        if (d == null)
            throw new IllegalArgumentException("La matrice d est invalide");

        // Vérifie si les dimensions de d sont compatibles avec la multiplication (this*d)
        if (d.nbLignes != this.nbColonnes || d.nbColonnes != 1)
            throw new IllegalArgumentException("");

        // Parcours de chaque ligne de la matrice courante
        for (int i = 0; i < nbLignes; i++) {
            double di = 0;
            // Calcul de la valeur di correspondant à la multiplication de la ligne i de la matrice courante par d
            for (int j = 0; j < nbColonnes; j++) {
                di += this.data[i][j] * d.data[j][0];
            }
            // Vérifie si la valeur calculée est égale à la valeur correspondante dans d
            if (di != d.data[i][0]) {
                // Si la valeur est différente, d n'est pas un état stable, donc renvoie false
                return false;
            }
        }
        // Si toutes les valeurs calculées sont égales à celles de d, renvoie true
        return true;
    }

    
    // affiche la matrice en format standard //NE PAS MODIFIER CETTE METHODE !!!
    public String toString(){
        String st = "" ;
        int tmax = 0 ;
        for (int i=0 ; i<nbLignes ; i++) {
            for (int j=0 ; j<nbColonnes ;j++) {
                String s = "" + data[i][j] ;
                if (data[i][j]>=0)
                    s = " "+s ;
                if (s.length()>tmax)
                    tmax = s.length() ;
            }
        }
        for (int i=0 ; i<nbLignes ; i++) {
            for (int j=0 ; j<nbColonnes ;j++) {
                String s = "" + data[i][j] ;
                if (data[i][j]>=0)
                    s = " "+s ;
                st = st + s ;
                int nbBlanc = tmax-s.length()+2;
                for (int k=0 ; k<nbBlanc ; k++)
                    st = st + " " ;
            }
            if (i<nbLignes-1)
                st = st+'\n' ;
        }
        return st ;
    }

}
