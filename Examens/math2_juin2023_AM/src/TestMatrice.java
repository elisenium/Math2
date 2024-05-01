import java.util.*;
import java.lang.reflect.Field;

public class TestMatrice {
    private static Scanner scanner = new Scanner(System.in);
    private static int temps = 300;
    private static Class classe = Matrice.class;
    private static Field data;
    private static Field nbL;
    private static Field nbC;
    private static String[] NOMS_METHODES = {"nbColonnesNulles"};

    private static void preparerField() {
        Field[] champs = classe.getDeclaredFields();
        for (Field f : champs) {
            if (f.getName().equals("data")) {
                data = f;
                data.setAccessible(true);
            } else if (f.getName().equals("nbLignes")) {
                nbL = f;
                nbL.setAccessible(true);
            } else if (f.getName().equals("nbColonnes")) {
                nbC = f;
                nbC.setAccessible(true);
            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        System.out.println("*****************************************");
        System.out.println("* Programme Test pour la classe Matrice *");
        System.out.println("*****************************************");
        preparerField();
        int choix = 0;
        do {
            System.out.println("1 -> Tester la méthode nbColonnesNulles()");
            System.out.println();
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            System.out.println() ;
            boolean testOK = true;
            switch (choix) {
                case 1:
                    testOK = testNbColonnesNulles();
                    break;
                default:
                    return;
            }
            if (testOK)
                System.out.println("Les tests de la méthode " + NOMS_METHODES[choix - 1]
                        + " ont réussi.");
            else
                System.out.println("Les tests de la méthode " + NOMS_METHODES[choix - 1]
                        + " ont échoué.");
            System.out.println();
        } while (choix >= 1 && choix <= 4);

    }

    public static boolean testNbColonnesNulles() throws IllegalAccessException {
        boolean testOK = true ;
        System.out.println() ;

        System.out.print("Test 1 : ");

        double[][] dTest = {{0, 0, 3}, {-1, 0, 0}, {1,0,-1} } ;
        double[][] dTestI = {{0, 0, 3}, {-1, 0, 0}, {1,0,-1} } ;
        Matrice m = new Matrice(3,3);

        try {
            data.set(m,dTest) ;
            int nbCNulles= m.nbColonnesNulles() ;
            double[][] dataR = (double[][]) data.get(m);
            if (nbCNulles!=1) {
                System.out.println("Votre méthode dit que la matrice ") ;
                System.out.println(m) ;
                System.out.println("a "+nbCNulles+" colonne(s) nulle(s) mais elle n'en a qu'une.") ;
                testOK = false ;
            } else if (!Arrays.deepEquals(dTestI,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(IllegalArgumentException e) {
            System.out.println("KO : Il ne fallait pas d'Exception :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println() ;

        System.out.print("Test 2 : ");

        double[][] dTest2 = {{0, 0, 3, 0, 1}, {0, 0, 1, 0, -2}, {0,0,0,0,3} , {0, 0, -4, 0,4} } ;
        double[][] dTestI2 = {{0, 0, 3, 0, 1}, {0, 0, 1, 0, -2}, {0,0,0,0,3} , {0, 0, -4, 0,4} };
        m = new Matrice(4,5);

        try {
            data.set(m,dTest2) ;
            int nbCNulles= m.nbColonnesNulles() ;
            double[][] dataR = (double[][]) data.get(m);
            if (nbCNulles!=3) {
                System.out.println("Votre méthode dit que la matrice ") ;
                System.out.println(m) ;
                System.out.println("a "+nbCNulles+" colonne(s) nulle(s) mais elle en 3.") ;
                testOK = false ;
            } else if (!Arrays.deepEquals(dTestI2,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=4) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=5) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(IllegalArgumentException e) {
            System.out.println("KO : Il ne fallait pas d'Exception :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println() ;

        System.out.print("Test 3 : ");

        double[][] dTest3 = {{0, 0, 0, 1}, {0.5, 0, 0, 2}, {0,0,0,0} , {-2, 0,0,4},  {-2, 0,0,1} } ;
        double[][] dTestI3 = {{0, 0, 0, 1}, {0.5, 0, 0, 2}, {0,0,0,0} , {-2, 0,0,4},  {-2, 0,0,1} } ;
        m = new Matrice(5,4);

        try {
            data.set(m,dTest3) ;
            int nbCNulles= m.nbColonnesNulles() ;
            double[][] dataR = (double[][]) data.get(m);
            if (nbCNulles!=2) {
                System.out.println("Votre méthode dit que la matrice ") ;
                System.out.println(m) ;
                System.out.println("a "+nbCNulles+" colonne(s) nulle(s) mais elle en 2.") ;
                testOK = false ;
            } else if (!Arrays.deepEquals(dTestI3,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=5) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=4) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(IllegalArgumentException e) {
            System.out.println("KO : Il ne fallait pas d'Exception :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println() ;

        System.out.print("Test 4 : ");

        double[][] dTest4 = {{0, 1, 0, 1}, {0.5, 0, 1, 2}, {0,0,0,0} , {-2, 0,0,-4},  {-2, 0,0,1} } ;
        double[][] dTestI4 = {{0, 1, 0, 1}, {0.5, 0, 1, 2}, {0,0,0,0} , {-2, 0,0,-4},  {-2, 0,0,1} } ;
        m = new Matrice(5,4);

        try {
            data.set(m,dTest4) ;
            int nbCNulles= m.nbColonnesNulles() ;
            double[][] dataR = (double[][]) data.get(m);
            if (nbCNulles!=0) {
                System.out.println("Votre méthode dit que la matrice ") ;
                System.out.println(m) ;
                System.out.println("a "+nbCNulles+" colonne(s) nulle(s) mais elle en 0.") ;
                testOK = false ;
            } else if (!Arrays.deepEquals(dTestI4,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=5) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=4) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(IllegalArgumentException e) {
            System.out.println("KO : Il ne fallait pas d'Exception :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println() ;

        System.out.print("Test 5 : ");

        double[][] dTest5 = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0,0,0,0,0} , {0, 0, 0, 0,0} } ;
        double[][] dTestI5 = {{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0,0,0,0,0} , {0, 0, 0, 0,0} };
        m = new Matrice(4,5);

        try {
            data.set(m,dTest5) ;
            int nbCNulles= m.nbColonnesNulles() ;
            double[][] dataR = (double[][]) data.get(m);
            if (nbCNulles!=5) {
                System.out.println("Votre méthode dit que la matrice ") ;
                System.out.println(m) ;
                System.out.println("a "+nbCNulles+" colonne(s) nulle(s) mais elle en 5.") ;
                testOK = false ;
            } else if (!Arrays.deepEquals(dTestI5,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=4) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=5) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(IllegalArgumentException e) {
            System.out.println("KO : Il ne fallait pas d'Exception :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println() ;

        System.out.print("Test 6 : ");

        double[][] dTest6 = {{0, -1, 0, 0.5, 0} } ;
        double[][] dTestI6 = {{0, -1, 0, 0.5, 0}};
        m = new Matrice(1,5);

        try {
            data.set(m,dTest6) ;
            int nbCNulles= m.nbColonnesNulles() ;
            double[][] dataR = (double[][]) data.get(m);
            if (nbCNulles!=3) {
                System.out.println("Votre méthode dit que la matrice ") ;
                System.out.println(m) ;
                System.out.println("a "+nbCNulles+" colonne(s) nulle(s) mais elle en 3.") ;
                testOK = false ;
            } else if (!Arrays.deepEquals(dTestI6,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=1) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=5) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(IllegalArgumentException e) {
            System.out.println("KO : Il ne fallait pas d'Exception :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println() ;

        System.out.print("Test 7 : ");

        double[][] dTest7 = {{0}, {0}, {0} , {0} } ;
        double[][] dTestI7 = {{0}, {0}, {0} , {0} };
        m = new Matrice(4,1);

        try {
            data.set(m,dTest7) ;
            int nbCNulles= m.nbColonnesNulles() ;
            double[][] dataR = (double[][]) data.get(m);
            if (nbCNulles!=1) {
                System.out.println("Votre méthode dit que la matrice ") ;
                System.out.println(m) ;
                System.out.println("a "+nbCNulles+" colonne(s) nulle(s) mais elle en 1.") ;
                testOK = false ;
            } else if (!Arrays.deepEquals(dTestI7,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=4) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=1) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(IllegalArgumentException e) {
            System.out.println("KO : Il ne fallait pas d'Exception :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println() ;

        System.out.print("Test 8 : ");

        double[][] dTest8 = {{0}, {0}, {-1.5} , {0} } ;
        double[][] dTestI8 = {{0}, {0}, {-1.5} , {0} };
        m = new Matrice(4,1);

        try {
            data.set(m,dTest8) ;
            int nbCNulles= m.nbColonnesNulles() ;
            double[][] dataR = (double[][]) data.get(m);
            if (nbCNulles!=0) {
                System.out.println("Votre méthode dit que la matrice ") ;
                System.out.println(m) ;
                System.out.println("a "+nbCNulles+" colonne(s) nulle(s) mais elle en 0.") ;
                testOK = false ;
            } else if (!Arrays.deepEquals(dTestI8,dataR)) {
                System.out.println("KO : vous avez modifié la matrice mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=4) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=1) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch(IllegalArgumentException e) {
            System.out.println("KO : Il ne fallait pas d'Exception :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println() ;

        return testOK ;
    }
}
