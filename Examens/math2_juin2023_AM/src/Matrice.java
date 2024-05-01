import java.util.Arrays ;
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
	
	// Renvoie le nombre de colonne nulle (ne contenant que des 0)
	// Exemple : voir énoncé
    public int nbColonnesNulles() {
        int nbColonnesNulles = 0;
        for (int i = 0; i < nbColonnes; i++) {
            double somme = 0;
            if (nbLignes == 1) {
                somme = data[0][i];

            } else {
                for (int j = 0; j < nbLignes-1; j++) {
                    somme += data[j][i];
                }
            }

            if (somme == 0) {
                nbColonnesNulles++;
            }
        }

        return nbColonnesNulles;
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
