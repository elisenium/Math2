public class TestRelationPersonneLangue {

    public static java.util.Scanner scanner = new java.util.Scanner(System.in);

    /**
     * Cette methode verifie qu'un resultat attendu est bien un resultat obtenu.
     *
     * @param messageErreur message a afficher en cas de probleme
     * @param attendu la valeur qu'on s'attendait a recevoir
     * @param recu la valeur qu'on a recu en realite
     */

    private static void assertEquals(String messageErreur, Object attendu, Object recu) {
        if (attendu==null) {
            if (recu!=null) {
                System.out.println(messageErreur+". Attendu="+attendu+" recu="+recu);
                System.exit(0);
            }
        } else if (!attendu.equals(recu)) {
            System.out.println(messageErreur+". Attendu="+attendu+" recu="+recu);
            System.exit(0);
        }
    }

    public static void main(String[] args) {

        int choix;

        System.out.println("*******************************************");
        System.out.println("Tests pour la classe RelationPersonneLangue");
        System.out.println("*******************************************");
        do{
            System.out.println("Menu");
            System.out.println("****");
            System.out.println("1 -> bilingues()");
            System.out.println("2 -> peuventCommuniquer()");
            System.out.print("\nEntrez votre choix : ");

            choix=scanner.nextInt();

            switch(choix){
                case 1 : testBilingues();
                    break;
                case 2 : testCommunication();
                    break;

            }
        }while(choix>=1 && choix<=2);

        System.out.println("\nFin des tests");
    }

    private static void testBilingues() {
        System.out.println();
        System.out.println("bilingues()");
        System.out.println("-----------");
        boolean tousReussi = true;
        boolean testReussi = true;
        //test1
        int numeroTest = 1;
        System.out.println("test "+numeroTest+" : exemple de l'enonce");
        RelationPersonneLangue relation = relationEnonce();
        try{
            EnsemblePersonnes recu = relation.bilingues();
            EnsemblePersonnes attendu = new EnsemblePersonnes();
            Personne sam = new Personne("sam");
            Personne mia = new Personne("mia");
            attendu.ajouter(sam);
            attendu.ajouter(mia);
            if(!recu.equals(attendu)){
                System.out.println("test "+numeroTest+"ko");
                System.out.println("attendu : "+attendu.toString());
                System.out.println("recu : "+recu.toString());
                tousReussi = false;
                testReussi = false;
            }
        } catch(Exception e){
            System.out.println("test "+numeroTest+" ko, il y a eu une exception inattendue");
            e.printStackTrace();
            tousReussi = false;
            testReussi = false;
            System.exit(0);
        }
        if (testReussi)
            System.out.println("test "+numeroTest+" ok");
        System.out.println();

        //test2
        numeroTest ++;
        testReussi = true;
        System.out.println("test "+numeroTest+" : une seule relation : mia-italien");
        relation = relationMiaItalien();
        try{
            EnsemblePersonnes recu = relation.bilingues();
            EnsemblePersonnes attendu = new EnsemblePersonnes();
            if(!recu.equals(attendu)){
                System.out.println("test "+numeroTest+"ko");
                System.out.println("attendu : "+attendu.toString());
                System.out.println("recu : "+recu.toString());
                tousReussi = false;
                testReussi = false;
            }
        } catch(Exception e){
            System.out.println("test "+numeroTest+" ko, il y a eu une exception inattendue");
            e.printStackTrace();
            tousReussi = false;
            testReussi = false;
            System.exit(0);
        }
        if (testReussi)
            System.out.println("test "+numeroTest+" ok");
        System.out.println();

        //test3
        numeroTest ++;
        testReussi = true;
        System.out.println("test "+numeroTest+" : relation vide");
        relation = relationVide();
        try{
            EnsemblePersonnes recu = relation.bilingues();
            EnsemblePersonnes attendu = new EnsemblePersonnes();
            if(!recu.equals(attendu)){
                System.out.println("test "+numeroTest+"ko");
                System.out.println("attendu : "+attendu.toString());
                System.out.println("recu : "+recu.toString());
                tousReussi = false;
                testReussi = false;
            }
        } catch(Exception e){
            System.out.println("test "+numeroTest+" ko, il y a eu une exception inattendue");
            e.printStackTrace();
            tousReussi = false;
            testReussi = false;
            System.exit(0);
        }
        if (testReussi)
            System.out.println("test "+numeroTest+" ok");
        System.out.println();

        if(tousReussi){
            System.out.println("Tous les tests proposes ont reussi");
        }else{
            System.out.println("methode a revoir !");
        }
        System.out.println();

    }

    private static void testCommunication() {
        System.out.println();
        System.out.println("peuventCommuniquer()");
        System.out.println("--------------------");
        boolean tousReussi = true;
        boolean testReussi = true;
        //test1
        int numeroTest = 1;
        System.out.println("test "+numeroTest+" : exemple de l'enonce");
        System.out.println("on verifie si mia et sam peuvent communiquer");
        try{
            RelationPersonneLangue relation = relationEnonce();
            Personne sam = new Personne("sam");
            Personne mia = new Personne("mia");
            if(!relation.peuventCommuniquer(sam,mia) ){
                System.out.println("test "+numeroTest+"ko");
                System.out.println("votre methode annonce que sam et mia ne peuvent pas communiquer");
                System.out.println("Pourtant, ils connaissent tous les 2 l'italien");
                tousReussi = false;
                testReussi = false;
            }
            if(!relation.peuventCommuniquer(mia,sam) ){
                System.out.println("test "+numeroTest+"ko");
                System.out.println("votre methode annonce que mia et sam ne peuvent pas communiquer");
                System.out.println("Pourtant, ils connaissent tous les 2 l'italien");
                tousReussi = false;
                testReussi = false;
            }

        } catch(Exception e){
            System.out.println("test "+numeroTest+" ko, il y a eu une exception inattendue");
            e.printStackTrace();
            tousReussi = false;
            testReussi = false;
            System.exit(0);
        }
        if (testReussi)
            System.out.println("test "+numeroTest+" ok");
        System.out.println();

        //test2
        numeroTest ++;
        testReussi = true;
        System.out.println("test "+numeroTest+" : exemple de l'enonce");
        System.out.println("on verifie si mia et tim peuvent communiquer");
        try{
            RelationPersonneLangue relation = relationEnonce();
            Personne tim = new Personne("tim");
            Personne mia = new Personne("mia");
            if(!relation.peuventCommuniquer(tim,mia) ){
                System.out.println("test "+numeroTest+"ko");
                System.out.println("votre methode annonce que tim et mia ne peuvent pas communiquer");
                System.out.println("Pourtant, ils connaissent tous les 2 l'anglais");
                tousReussi = false;
                testReussi = false;
            }
            if(!relation.peuventCommuniquer(mia,tim) ){
                System.out.println("test "+numeroTest+"ko");
                System.out.println("votre methode annonce que mia et tim ne peuvent pas communiquer");
                System.out.println("Pourtant, ils connaissent tous les 2 l'anglais");
                tousReussi = false;
                testReussi = false;
            }

        } catch(Exception e){
            System.out.println("test "+numeroTest+" ko, il y a eu une exception inattendue");
            e.printStackTrace();
            tousReussi = false;
            testReussi = false;
            System.exit(0);
        }
        if (testReussi)
            System.out.println("test "+numeroTest+" ok");
        System.out.println();

        //test3
        numeroTest ++;
        testReussi = true;
        System.out.println("test "+numeroTest+" : exemple de l'enonce");
        System.out.println("on verifie si marie et tim peuvent communiquer");
        try{
            RelationPersonneLangue relation = relationEnonce();
            Personne tim = new Personne("tim");
            Personne marie = new Personne("marie");
            if(!relation.peuventCommuniquer(tim,marie) ){
                System.out.println("test "+numeroTest+"ko");
                System.out.println("votre methode annonce que tim et marie ne peuvent pas communiquer");
                System.out.println("Pourtant, ils connaissent tous les 2 le francais");
                tousReussi = false;
                testReussi = false;
            }
            if(!relation.peuventCommuniquer(marie,tim) ){
                System.out.println("test "+numeroTest+"ko");
                System.out.println("votre methode annonce que marie et tim ne peuvent pas communiquer");
                System.out.println("Pourtant, ils connaissent tous les 2 le francais");
                tousReussi = false;
                testReussi = false;
            }
        } catch(Exception e){
            System.out.println("test "+numeroTest+" ko, il y a eu une exception inattendue");
            e.printStackTrace();
            tousReussi = false;
            testReussi = false;
            System.exit(0);
        }
        if (testReussi)
            System.out.println("test "+numeroTest+" ok");
        System.out.println();

        //test4
        numeroTest ++;
        testReussi = true;
        System.out.println("test "+numeroTest+" : exemple de l'enonce");
        System.out.println("on verifie si marie et sam peuvent communiquer");
        try{
            RelationPersonneLangue relation = relationEnonce();
            Personne sam = new Personne("sam");
            Personne marie = new Personne("marie");
            if(relation.peuventCommuniquer(sam,marie) ){
                System.out.println("test "+numeroTest+"ko");
                System.out.println("votre methode annonce que sam et marie peuvent communiquer");
                System.out.println("Pourtant, ils n'ont aucune langue en commun");
                tousReussi = false;
                testReussi = false;
            }
            if(relation.peuventCommuniquer(marie,sam) ){
                System.out.println("test "+numeroTest+"ko");
                System.out.println("votre methode annonce que marie et sam peuvent communiquer");
                System.out.println("Pourtant, ils n'ont aucune langue en commun");
                tousReussi = false;
                testReussi = false;
            }
        } catch(Exception e){
            System.out.println("test "+numeroTest+" ko, il y a eu une exception inattendue");
            e.printStackTrace();
            tousReussi = false;
            testReussi = false;
            System.exit(0);
        }
        if(testReussi)
            System.out.println("test "+numeroTest+" ok");
        System.out.println();


        if(tousReussi){
            System.out.println("Tous les tests proposes ont reussi");
        }else{
            System.out.println("methode a revoir !");
        }
        System.out.println();
    }

    private static RelationPersonneLangue relationEnonce(){
        RelationPersonneLangue relation = new RelationPersonneLangue();
        Personne mia = new Personne("mia");
        Personne marie = new Personne("marie");
        Personne john = new Personne("john");
        Personne sam = new Personne("sam");
        Personne tim = new Personne("tim");
        Langue francais = new Langue("francais");
        Langue italien = new Langue("italien");
        Langue anglais = new Langue("anglais");
        Langue allemand = new Langue("allemand");
        relation.ajouter(mia,italien);
        relation.ajouter(mia,anglais);
        relation.ajouter(john,anglais);
        relation.ajouter(tim,francais);
        relation.ajouter(tim,anglais);
        relation.ajouter(tim,allemand);
        relation.ajouter(marie,francais);
        relation.ajouter(sam,italien);
        relation.ajouter(sam,allemand);
        return relation;
    }

    private static RelationPersonneLangue relationMiaItalien(){
        RelationPersonneLangue relation = new RelationPersonneLangue();
        Personne mia = new Personne("mia");
        Langue italien = new Langue("italien");
        relation.ajouter(mia,italien);
        return relation;
    }

    private static RelationPersonneLangue relationVide(){
        RelationPersonneLangue relation = new RelationPersonneLangue();
        return relation;
    }






}
