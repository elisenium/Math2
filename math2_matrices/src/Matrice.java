 public class Matrice {
    private final int nbLignes;              // nombre de lignes
    private final int nbColonnes;            // nombre de colonnes
    private final double[][] data;           // matrice (nbLignes,nbColonnes)

    // ce constructeur cree la matrice nulle de genre (a,b)
    public Matrice(int a, int b) throws IllegalArgumentException { 
        if (a <= 0 || b <= 0)
            throw new IllegalArgumentException();
        nbLignes=a ;
        nbColonnes=b ;
        data = new double[a][b];
    }

    //  Ce constructeur permet de construire la matrice correspondant 
    //  au tableau en parametre. 
    public Matrice(double[][] tab)  throws IllegalArgumentException {
        if (tab == null || tab.length == 0 || tab[0] == null || tab[0].length == 0)
            throw new IllegalArgumentException();

        nbLignes = tab.length;
        nbColonnes = tab[0].length;
        data = new double[nbLignes][nbColonnes];

        for (int i = 0; i < nbLignes; i++) {
            if (tab[i] == null || tab[i].length != nbColonnes)
                throw new IllegalArgumentException();

            for (int j = 0; j < nbColonnes; j++) {
                data[i][j] = tab[i][j];
            }
        }
    }

    // constructeur par recopie
    public Matrice(Matrice a)  throws IllegalArgumentException {
        if (a == null || a.equals(""))
            throw new IllegalArgumentException();

        nbLignes = a.nbLignes;
        nbColonnes = a.nbColonnes;
        data = new double[nbLignes][nbColonnes];

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                data[i][j] = a.data[i][j];
            }
        }
	}

    // cree la matrice identite d'ordre a
    public static Matrice identite(int a)  throws IllegalArgumentException {
        if (a < 1) throw new IllegalArgumentException();
        Matrice matrice = new Matrice(a, a);

        for (int i = 0; i < a; i++) {
            matrice.data[i][i] = 1;
        }

    	return matrice;
    }
    
    //Cette methode renvoie l'element de la ligne numLigne et de la 
    //colonne numColonne de la matrice. Si cet element n'existe pas, la 
    //methode lance une IllegalArgumentException 
	public double getElement(int numLigne, int numColonne) throws IllegalArgumentException {
        if (numLigne < 1 || numColonne < 1 || numLigne >= nbLignes + 1 || numColonne >= nbColonnes + 1)
            throw new IllegalArgumentException();

		return data[numLigne-1][numColonne-1];
	 }
    
    // ajoute b a la matrice courante si c'est possible
    public Matrice somme(Matrice b)  throws IllegalArgumentException {
        if (b == null || b.equals(null) || nbLignes != b.nbLignes || nbColonnes != b.nbColonnes)
            throw new IllegalArgumentException();

        Matrice somme = new Matrice(nbLignes, nbColonnes);

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                somme.data[i][j] = data[i][j] + b.data[i][j];
            }
        }

    	return somme;
    }

    // calcule le produit scalaire.this de la matrice courante avec scalaire
    public Matrice produitParScalaire(double scalaire){
        Matrice produitScalaire = new Matrice(nbLignes, nbColonnes);

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                produitScalaire.data[i][j] = scalaire * data[i][j];
            }
        }
    	return produitScalaire;
    }

    // calcule le produit this*b de la matrice courante avec b si possible
    public Matrice produitAGauche(Matrice b)  throws IllegalArgumentException {
        if (b == null || b.equals("")) throw new IllegalArgumentException();
        if (nbColonnes != b.nbLignes) throw new IllegalArgumentException();

        Matrice matrice = new Matrice(nbLignes, b.nbColonnes);
        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < b.nbColonnes; j++) {
                double somme = 0;
                for (int k = 0; k < nbColonnes; k++) {
                    somme += data[i][k] * b.data[k][j];
                }
                matrice.data[i][j] = somme;
            }
        }
    	return matrice;
    }
    
	// calcule le produit b*this de b avec la matrice courante si possible
    public Matrice produitADroite(Matrice b)  throws IllegalArgumentException {
        if (b == null || b.equals("")) throw new IllegalArgumentException();
        if (b.nbColonnes != nbLignes) throw new IllegalArgumentException();

        Matrice matrice = new Matrice(b.nbLignes, nbColonnes);
        for (int i = 0; i < b.nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                double somme = 0;
                for (int k = 0; k < nbLignes; k++) {
                    somme += b.data[i][k] * data[k][j];
                }
                matrice.data[i][j] = somme;
            }
        }
        return matrice;
    }
	 
   // renvoie true si la matrice courante est carrée
	 public boolean carree(){
		 return nbLignes == nbColonnes;
    }
    
    // Calcule this^n. Lance une Mathexception si this n'est pas carrée
    public Matrice puissance(int n) throws  IllegalArgumentException {
        if (!carree()) throw new MathException();
        if (n < 0) throw new IllegalArgumentException();

        if (n == 0) {
            return identite(nbLignes);
        }

        Matrice matrice = identite(nbLignes);
        Matrice matriceDeBase = new Matrice(this);

        while (n > 0) {
            if (n % 2 == 1) {
                matrice = matrice.produitAGauche(matriceDeBase);
            }
            matriceDeBase = matriceDeBase.produitAGauche(matriceDeBase);
            n /= 2;
        }
        return matrice;
    }
    
	//Calcule this^T : la tranposée de this
	public Matrice transposee() {
        Matrice matrice = new Matrice(nbColonnes, nbLignes);

        for (int i = 0; i < nbLignes; i++) {
            for (int j = 0; j < nbColonnes; j++) {
                matrice.data[j][i] = data[i][j];
            }
        }
        return matrice;
	}
	 
    // affiche la matrice en format standard
    public String toString(){
        StringBuilder s = new StringBuilder();
        int width = 0;

        for (int i = 0; i < this.nbLignes; i++) {
            for (int j = 0; j < this.nbColonnes; j++) {
                width = Math.max(width, String.valueOf(this.data[i][j]).length());
            }
        }

        for (int i = 0; i < this.nbLignes; i++) {
            for (int j = 0; j < this.nbColonnes; j++) {
                double val = this.data[i][j];
                s.append(String.format("%-" + (width+3) + "s", val < 0 ? val : " " + val));
            }
            s.append("\n");
        }

        return s.toString();
    }

    public Matrice pageRank() throws MathException {
        if (!carree())
            throw new MathException("La matrice n'est pas carrée.");
        if (!estUneMatriceDAdjacence())
            throw new MathException("Coefficient(s) différent(s) de 0 ou 1.");

        double alpha = 0.85;
        int nbIterations = 85;
        Matrice google = matriceGoogle(matriceDeTransition(), alpha);
        Matrice d = new Matrice(nbLignes, 1);

        for (int i = 0; i < nbLignes; i++) {
            d.data[i][0] = 1.0/nbLignes;
        }
        for (int i = 0; i < nbIterations; i++) {
            d = google.produitAGauche(d);
            normalisation(d);
        }
        return d;
    }

     public boolean estUneMatriceDAdjacence() {
         for (int i = 0; i < nbLignes; i++) {
             for (int j = 0; j < nbColonnes; j++) {
                 if (data[i][j] != 0 && data[i][j] != 1)
                     return false;
             }
         }
         return true;
     }

     public Matrice matriceDeTransition() {
         // Somme des valeurs par colonne
         double[] sommeColonne = new double[nbLignes];
         for (int i = 0; i < nbLignes; i++) {
             for (int j = 0; j < nbColonnes; j++) {
                 sommeColonne[i] += data[j][i];
             }
         }
         // Nombre de 1 par colonne
         int[] nb1Colonne = new int[nbColonnes];
         for (int i = 0; i < nbColonnes; i++) {
             for (int j = 0; j < nbLignes; j++) {
                 if (data[j][i] == 1) {
                     nb1Colonne[i]++;
                 }
             }
         }
         Matrice transition = new Matrice(nbColonnes, nbColonnes);
         for (int i = 0; i < nbLignes; i++) {
             if (sommeColonne[i] == 0) {
                 for (int j = 0; j < nbColonnes; j++) {
                     transition.data[j][i] = (1.0 / nbColonnes);
                 }
             } else {
                 for (int j = 0; j < nbColonnes; j++) {
                     if (data[j][i] == 1) {
                         transition.data[j][i] = (1.0 / nb1Colonne[i]);
                     }
                 }
             }
         }
         return transition;
     }

     public Matrice matriceGoogle(Matrice transition, double alpha) {
        Matrice google = new Matrice(nbLignes, nbColonnes);
        double aleatoire = (1.0 - alpha) / nbLignes;

         for (int i = 0; i < nbLignes; i++) {
             for (int j = 0; j < nbColonnes; j++) {
                 google.data[i][j] = alpha * transition.data[i][j] + aleatoire;
             }
         }
         return google;
     }

     private void normalisation(Matrice d) {
        double norme1 = 0;
         for (int i = 0; i < nbLignes; i++) {
             norme1 += d.data[i][0];
         }
         for (int i = 0; i < nbLignes; i++) {
             d.data[i][0] /= norme1;
         }
     }
  }


