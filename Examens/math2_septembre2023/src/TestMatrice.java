import java.util.*;
import java.lang.reflect.Field;

public class TestMatrice {
    private static Scanner scanner = new Scanner(System.in);
    private static int temps = 300;
    private static Class classe = Matrice.class;
    private static Field data;
    private static Field nbL;
    private static Field nbC;
    private static String[] NOMS_METHODES = {"aPourEtatStable"};

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
            System.out.println("1 -> Tester la méthode aPourEtatStable()");
            System.out.println();
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            System.out.println() ;
            boolean testOK = true;
            switch (choix) {
                case 1:
                    testOK = testAPourEtatStable();
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
        } while (choix >= 1 && choix <= 1);

    }

    public static boolean testAPourEtatStable() throws IllegalAccessException {
        boolean testOK = true ;
        System.out.println() ;

        System.out.print("Test 1 : ");

        double[][] thisTest = {{0, 0, 1}, {-1, 1, 0}} ;
        double[][] thisTestI = {{0, 0, 1}, {-1, 1, 0}} ;
        Matrice m = new Matrice(2,3);
        double[][] dTest = {{0}, {1}, {0}} ;
        double[][] dTestI = {{0}, {1}, {0}} ;
        Matrice d = new Matrice(3,1);

        try {
            data.set(m,thisTest) ;
            data.set(d,dTest) ;
            boolean estStable = m.aPourEtatStable(d) ;
            System.out.println("KO : Il fallait une MathException car la matrice courante n'est pas carrée") ;
            testOK=false ;
        } catch(MathException e) {
            double[][] dataR = (double[][]) data.get(m);
            double[][] dataD = (double[][]) data.get(d);
            if (!Arrays.deepEquals(thisTestI,dataR)) {
                System.out.println("KO : vous avez modifié la matrice courante mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=2) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } if (!Arrays.deepEquals(dTestI,dataD)) {
                System.out.println("KO : vous avez modifié la matrice d passée en paramètre mais il ne fallait pas.") ;
                testOK = false ;
            }else {
                System.out.println("OK") ;
            }
        } catch (Exception e) {
            System.out.println("KO : Mauvais type d'exception : MathException attendue mais "+e.getClass()+" lancée :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println() ;

        System.out.print("Test 2 : ");

        double[][] thisTest2 = {{0, 1}, {-1, 1}} ;
        double[][] thisTestI2 = {{0, 1}, {-1, 1}} ;
        m = new Matrice(2,2);
        double[][] dTest2 = {{0}, {1}, {0}} ;
        double[][] dTestI2 = {{0}, {1}, {0}} ;
        d = new Matrice(3,1);

        try {
            data.set(m,thisTest2) ;
            data.set(d,dTest2) ;
            boolean estStable = m.aPourEtatStable(d) ;
            System.out.println("KO : Il fallait une IllegalArgumentException car la matrice d n'a pas les bonnes dimensions") ;
            testOK=false ;
        } catch(IllegalArgumentException e) {
            double[][] dataR = (double[][]) data.get(m);
            double[][] dataD = (double[][]) data.get(d);
            if (!Arrays.deepEquals(thisTestI2,dataR)) {
                System.out.println("KO : vous avez modifié la matrice courante mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=2) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=2) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } if (!Arrays.deepEquals(dTestI2,dataD)) {
                System.out.println("KO : vous avez modifié la matrice d passée en paramètre mais il ne fallait pas.") ;
                testOK = false ;
            }else {
                System.out.println("OK") ;
            }
        } catch (Exception e) {
            System.out.println("KO : Mauvais type d'exception : IllegalArgumentException attendue mais "+e.getClass()+" lancée :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println() ;

        System.out.print("Test 3 : ");

        double[][] thisTest3 = {{1, 0, 0}, {0, 1, 1}, {-1, 1, 0}} ;
        double[][] thisTestI3 = {{1, 0, 0}, {0, 1, 1}, {-1, 1, 0}} ;
        m = new Matrice(3,3);
        double[][] dTest3 = {{0.5, 1}, {0.5, 1}, {0, 0}} ;
        double[][] dTestI3 = {{0.5, 1}, {0.5, 1}, {0, 0}} ;
        d = new Matrice(3,2);

        try {
            data.set(m,thisTest3) ;
            data.set(d,dTest3) ;
            boolean estStable = m.aPourEtatStable(d) ;
            System.out.println("KO : Il fallait une IllegalArgumentException car la matrice d n'a pas les bonnes dimensions") ;
            testOK=false ;
        } catch(IllegalArgumentException e) {
            double[][] dataR = (double[][]) data.get(m);
            double[][] dataD = (double[][]) data.get(d);
            if (!Arrays.deepEquals(thisTestI3,dataR)) {
                System.out.println("KO : vous avez modifié la matrice courante mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } if (!Arrays.deepEquals(dTestI3,dataD)) {
                System.out.println("KO : vous avez modifié la matrice d passée en paramètre mais il ne fallait pas.") ;
                testOK = false ;
            }else {
                System.out.println("OK") ;
            }
        } catch (Exception e) {
            System.out.println("KO : Mauvais type d'exception : IllegalArgumentException attendue mais "+e.getClass()+" lancée :");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println() ;

        System.out.print("Test 4 : ");

        double[][] thisTest4 = {{1, 0, 0}, {0, 1, 1}, {-1, 1, 0}} ;
        double[][] thisTestI4 = {{1, 0, 0}, {0, 1, 1}, {-1, 1, 0}} ;
        m = new Matrice(3,3);
        double[][] dTest4 = {{0.5}, {0.5}, {0}} ;
        double[][] dTestI4 = {{0.5}, {0.5}, {0}} ;
        d = new Matrice(3,1);

        try {
            data.set(m,thisTest4) ;
            data.set(d,dTest4) ;
            boolean estStable = m.aPourEtatStable(d) ;
            double[][] dataR = (double[][]) data.get(m);
            double[][] dataD = (double[][]) data.get(d);
            if (!estStable) {
                System.out.println("KO :") ;
                System.out.println("Votre méthode dit que la matrice d :") ;
                System.out.println(d);
                System.out.println("n'est pas un état stable de la matrice m : ") ;
                System.out.println(m) ;
                System.out.println("Or la matrice d est bien un état stable de la matrice m") ;
                testOK = false ;
            } else if (!Arrays.deepEquals(thisTestI4,dataR)) {
                System.out.println("KO : vous avez modifié la matrice courante mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } if (!Arrays.deepEquals(dTestI4,dataD)) {
                System.out.println("KO : vous avez modifié la matrice d passée en paramètre mais il ne fallait pas.") ;
                testOK = false ;
            } else {
                System.out.println("OK") ;
            }
        } catch (Exception e) {
            System.out.println("KO : Il ne fallait pas d'exception : ");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println() ;

        System.out.print("Test 5 : ");

        double[][] thisTest5 = {{1, 0, 0}, {0, 1, 1}, {-1, 1, 0}} ;
        double[][] thisTestI5 = {{1, 0, 0}, {0, 1, 1}, {-1, 1, 0}} ;
        m = new Matrice(3,3);
        double[][] dTest5 = {{0}, {1}, {0}} ;
        double[][] dTestI5 = {{0}, {1}, {0}} ;
        d = new Matrice(3,1);

        try {
            data.set(m,thisTest5) ;
            data.set(d,dTest5) ;
            boolean estStable = m.aPourEtatStable(d) ;
            double[][] dataR = (double[][]) data.get(m);
            double[][] dataD = (double[][]) data.get(d);
            if (estStable) {
                System.out.println("KO :") ;
                System.out.println("Votre méthode dit que la matrice d :") ;
                System.out.println(d);
                System.out.println("est un état stable de la matrice m : ") ;
                System.out.println(m) ;
                System.out.println("Or la matrice d n'est pas un état stable de la matrice m") ;
                testOK = false ;
            } else if (!Arrays.deepEquals(thisTestI5,dataR)) {
                System.out.println("KO : vous avez modifié la matrice courante mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } if (!Arrays.deepEquals(dTestI5,dataD)) {
                System.out.println("KO : vous avez modifié la matrice d passée en paramètre mais il ne fallait pas.") ;
                testOK = false ;
            } else {
                System.out.println("OK") ;
            }
        } catch (Exception e) {
            System.out.println("KO : Il ne fallait pas d'exception : ");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println() ;

        System.out.print("Test 6 : ");

        double[][] thisTest6 = {{0.75, 0.25, 0.25}, {0.25, 0, 0.25}, {0, 0.75, 0.5}} ;
        double[][] thisTestI6 = {{0.75, 0.25, 0.25}, {0.25, 0, 0.25}, {0, 0.75, 0.5}} ;
        m = new Matrice(3,3);
        double[][] dTest6 = {{50}, {20}, {30}} ;
        double[][] dTestI6 = {{50}, {20}, {30}} ;
        d = new Matrice(3,1);

        try {
            data.set(m,thisTest6) ;
            data.set(d,dTest6) ;
            boolean estStable = m.aPourEtatStable(d) ;
            double[][] dataR = (double[][]) data.get(m);
            double[][] dataD = (double[][]) data.get(d);
            if (!estStable) {
                System.out.println("KO :") ;
                System.out.println("Votre méthode dit que la matrice d :") ;
                System.out.println(d);
                System.out.println("n'est pas un état stable de la matrice m : ") ;
                System.out.println(m) ;
                System.out.println("Or la matrice d est bien un état stable de la matrice m") ;
                testOK = false ;
            } else if (!Arrays.deepEquals(thisTestI6,dataR)) {
                System.out.println("KO : vous avez modifié la matrice courante mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } if (!Arrays.deepEquals(dTestI6,dataD)) {
                System.out.println("KO : vous avez modifié la matrice d passée en paramètre mais il ne fallait pas.") ;
                testOK = false ;
            } else {
                System.out.println("OK") ;
            }
        } catch (Exception e) {
            System.out.println("KO : Il ne fallait pas d'exception : ");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println();

        System.out.print("Test 7 : ");

        double[][] thisTest7 = {{0.75, 0.25, 0.25}, {0.25, 0, 0.25}, {0, 0.75, 0.5}} ;
        double[][] thisTestI7 = {{0.75, 0.25, 0.25}, {0.25, 0, 0.25}, {0, 0.75, 0.5}} ;
        m = new Matrice(3,3);
        double[][] dTest7 = {{5}, {2}, {3}} ;
        double[][] dTestI7 = {{5}, {2}, {3}} ;
        d = new Matrice(3,1);

        try {
            data.set(m,thisTest7) ;
            data.set(d,dTest7) ;
            boolean estStable = m.aPourEtatStable(d) ;
            double[][] dataR = (double[][]) data.get(m);
            double[][] dataD = (double[][]) data.get(d);
            if (!estStable) {
                System.out.println("KO :") ;
                System.out.println("Votre méthode dit que la matrice d :") ;
                System.out.println(d);
                System.out.println("n'est pas un état stable de la matrice m : ") ;
                System.out.println(m) ;
                System.out.println("Or la matrice d est bien un état stable de la matrice m") ;
                testOK = false ;
            } else if (!Arrays.deepEquals(thisTestI7,dataR)) {
                System.out.println("KO : vous avez modifié la matrice courante mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } if (!Arrays.deepEquals(dTestI7,dataD)) {
                System.out.println("KO : vous avez modifié la matrice d passée en paramètre mais il ne fallait pas.") ;
                testOK = false ;
            } else {
                System.out.println("OK") ;
            }
        } catch (Exception e) {
            System.out.println("KO : Il ne fallait pas d'exception : ");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println();

        System.out.print("Test 8 : ");

        double[][] thisTest8 = {{0.75, 0.25, 0.25}, {0.25, 0, 0.25}, {0, 0.75, 0.5}} ;
        double[][] thisTestI8 = {{0.75, 0.25, 0.25}, {0.25, 0, 0.25}, {0, 0.75, 0.5}} ;
        m = new Matrice(3,3);
        double[][] dTest8 = {{50}, {30}, {20}} ;
        double[][] dTestI8 = {{50}, {30}, {20}} ;
        d = new Matrice(3,1);

        try {
            data.set(m,thisTest8) ;
            data.set(d,dTest8) ;
            boolean estStable = m.aPourEtatStable(d) ;
            double[][] dataR = (double[][]) data.get(m);
            double[][] dataD = (double[][]) data.get(d);
            if (estStable) {
                System.out.println("KO :") ;
                System.out.println("Votre méthode dit que la matrice d :") ;
                System.out.println(d);
                System.out.println("est un état stable de la matrice m : ") ;
                System.out.println(m) ;
                System.out.println("Or la matrice d n'est pas un état stable de la matrice m") ;
                testOK = false ;
            } else if (!Arrays.deepEquals(thisTestI8,dataR)) {
                System.out.println("KO : vous avez modifié la matrice courante mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=3) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } if (!Arrays.deepEquals(dTestI8,dataD)) {
                System.out.println("KO : vous avez modifié la matrice d passée en paramètre mais il ne fallait pas.") ;
                testOK = false ;
            } else {
                System.out.println("OK") ;
            }
        } catch (Exception e) {
            System.out.println("KO : Il ne fallait pas d'exception : ");
            e.printStackTrace();
            try {
                Thread.sleep(temps);
            } catch (InterruptedException ex) {

            }
            testOK = false;
        }

        System.out.println();

        System.out.print("Test 9 : ");

        double[][] thisTest9 = {{0, 1}, {-1, 1}} ;
        double[][] thisTestI9 = {{0, 1}, {-1, 1}} ;
        m = new Matrice(2,2);

        try {
            data.set(m,thisTest2) ;
            boolean estStable = m.aPourEtatStable(null) ;
            System.out.println("KO : Il fallait une IllegalArgumentException car la matrice d est null") ;
            testOK=false ;
        } catch(IllegalArgumentException e) {
            double[][] dataR = (double[][]) data.get(m);
            if (!Arrays.deepEquals(thisTestI2,dataR)) {
                System.out.println("KO : vous avez modifié la matrice courante mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbL.getInt(m)!=2) {
                System.out.println("KO : vous avez modifié le nombre de lignes mais il ne fallait pas.") ;
                testOK = false ;
            } else if (nbC.getInt(m)!=2) {
                System.out.println("KO : vous avez modifié le nombre de colonnes mais il ne fallait pas.");
                testOK = false;
            } else {
                System.out.println("OK") ;
            }
        } catch (Exception e) {
            System.out.println("KO : Mauvais type d'exception : IllegalArgumentException attendue mais "+e.getClass()+" lancée :");
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
