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

    // Renvoie le nombre de lignes non nulles (contenant au moins un élément différent de 0)
    // Exemple : voir énoncé
    public int nbLignesNonNulles() {
        int nbLignesNonNulles = 0;

        for (int i = 0; i < nbLignes; i++) {
            boolean estDifferentDeZero = false;
            for (int j = 0; j < nbColonnes; j++) {
                if (data[i][j] != 0) {
                    estDifferentDeZero = true;
                }
            }
            if (estDifferentDeZero)
                nbLignesNonNulles++;
        }
        return nbLignesNonNulles ;
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
