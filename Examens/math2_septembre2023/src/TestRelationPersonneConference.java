public class TestRelationPersonneConference {

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

        System.out.println("***********************************************");
        System.out.println("Tests pour la classe RelationPersonneConference");
        System.out.println("***********************************************");
        do{
            System.out.println("Menu");
            System.out.println("****");
            System.out.println("1 -> desinscrire()");
            System.out.println("2 -> conferencesCommunes()");
            System.out.print("\nEntrez votre choix : ");

            choix=scanner.nextInt();

            switch(choix){
                case 1 : testDesinscrire();
                    break;
                case 2 : testConferencesCommunes();
                    break;

            }
        }while(choix>=1 && choix<=2);

        System.out.println("\nFin des tests");
    }

    private static void testDesinscrire() {
        System.out.println();
        System.out.println("desinscrire()");
        System.out.println("-------------");
        boolean tousReussi = true;

        //test1
        int numeroTest = 1;
        System.out.println("test "+numeroTest+" : exemple de l'enonce");
        System.out.println("on essaye de supprimer le couple (mia, abeilles) qui n'existe pas");
        try{
            RelationPersonneConference relation = relationEnonce();
            Personne mia = new Personne("mia");
            Conference abeilles = new Conference("abeilles");
            if(relation.desinscrire(mia,abeilles)){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("le couple (mia,abeilles) n'existe pas, la methode aurait du renvoyer false");
                tousReussi = false;
            }
            if(relation.cardinal()!= 10 ){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("la relation a ete modifiee");
                tousReussi = false;
            }
            if(!relation.contientPersonne(mia)){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("mia n'aurait pas du etre enlevee de l'ensemble des personnes");
                tousReussi = false;
            }
            if(!relation.contientConference(abeilles)){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("la conference sur les abeilles n'aurait pas du etre enlevee de l'ensemble des conferences");
                tousReussi = false;
            }
        } catch(Exception e){
            System.out.println("test "+numeroTest+" ko, il y a eu une exception inattendue");
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println();

        //test2
        numeroTest++;
        System.out.println("test "+numeroTest+" : exemple de l'enonce");
        System.out.println("on essaye de supprimer le couple (mia,papillons) qui existe");
        try{
            RelationPersonneConference relation = relationEnonce();
            Personne mia = new Personne("mia");
            Conference papillons = new Conference("papillons");
            if(!relation.desinscrire(mia,papillons)){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("le couple (mia,papillons) existe, la methode aurait du renvoyer true");
                tousReussi = false;
            }
            if(relation.contient(new CouplePC(mia,papillons)) ){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("la relation contient toujours le couple (mia,papillons)");
                tousReussi = false;
            }
            if(!relation.contientPersonne(mia)){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("mia n'aurait pas du etre enlevee de l'ensemble des personnes");
                System.out.println("mia est toujours inscrite a au moins 1 conference");
                tousReussi = false;
            }
            if(!relation.contientConference(papillons)){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("la conference sur les papillons n'aurait pas du etre enlevee de l'ensemble des conferences");
                tousReussi = false;
            }
        } catch(Exception e){
            System.out.println("test "+numeroTest+" ko, il y a eu une exception inattendue");
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println();

        //test3
        numeroTest++;
        System.out.println("test "+numeroTest+" : exemple de l'enonce");
        System.out.println("on essaye de supprimer le couple (mia,fourmis) qui existe");
        try{
            RelationPersonneConference relation = relationEnonce();
            Personne mia = new Personne("mia");
            Conference fourmis = new Conference("fourmis");
            if(!relation.desinscrire(mia,fourmis)){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("le couple (mia,fourmis) existe, la methode aurait du renvoyer true");
                tousReussi = false;
            }
            if(relation.contient(new CouplePC(mia,fourmis)) ){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("la relation contient toujours le couple (mia,fourmis)");
                tousReussi = false;
            }
            if(!relation.contientPersonne(mia)){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("mia n'aurait pas du etre enlevee de l'ensemble des personnes");
                System.out.println("mia est toujours inscrite a au moins 1 conference");
                tousReussi = false;
            }
            if(!relation.contientConference(fourmis)){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("la conference sur les fourmis n'aurait pas du etre enlevee de l'ensemble des conferences");
                tousReussi = false;
            }
        } catch(Exception e){
            System.out.println("test "+numeroTest+" ko, il y a eu une exception inattendue");
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println();

        //test4
        numeroTest++;
        System.out.println("test "+numeroTest+" : exemple de l'enonce");
        System.out.println("on essaye de supprimer le couple (john,papillons) qui existe");
        try{
            RelationPersonneConference relation = relationEnonce();
            Personne john = new Personne("john");
            Conference papillons = new Conference("papillons");
            relation.desinscrire(john,papillons);
            if(relation.contient(new CouplePC(john,papillons)) ){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("la relation contient toujours le couple (john,papillons)");
                tousReussi = false;
            }
            if(relation.contientPersonne(john)){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("john aurait du etre enleve de l'ensemble des personnes");
                System.out.println("john n'est inscrit a aucune conference apres suppression du couple (john, papillons");
                tousReussi = false;
            }
            if(!relation.contientConference(papillons)){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("la conference sur les papillons n'aurait pas du etre enlevee de l'ensemble des conferences");
                tousReussi = false;
            }

        } catch(Exception e){
            System.out.println("test "+numeroTest+" ko, il y a eu une exception inattendue");
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println();

        if(tousReussi){
            System.out.println("Tous les tests proposes ont reussi");
        }else{
            System.out.println("methode a revoir !");
        }
        System.out.println();

    }


    private static void testConferencesCommunes() {
        System.out.println();
        System.out.println("conferencesCommunes()");
        System.out.println("---------------------");
        boolean tousReussi = true;
        //test1
        int numeroTest = 1;
        System.out.println("test "+numeroTest+" : exemple de l'enonce avec Mia et Tim");
        RelationPersonneConference relation = relationEnonce();
        try{
            Personne mia = new Personne("mia");
            Personne tim = new Personne("tim");
            Conference coccinelles = new Conference("coccinelles");
            Conference papillons = new Conference("papillons");
            EnsembleConferences recu = relation.conferencesCommunes(mia,tim);
            EnsembleConferences attendu = new EnsembleConferences();
            attendu.ajouter(coccinelles);
            attendu.ajouter(papillons);
            if(!recu.equals(attendu)){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("attendu : "+attendu.toString());
                System.out.println("recu : "+recu.toString());
                tousReussi = false;
            }
        } catch(Exception e){
            System.out.println("test "+numeroTest+" ko, il y a eu une exception inattendue");
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println();

        //test2
        numeroTest++;
        System.out.println("test "+numeroTest+" : exemple de l'enonce avec Mia et Sam");
        relation = relationEnonce();
        try{
            Personne mia = new Personne("mia");
            Personne sam = new Personne("sam");
            EnsembleConferences recu = relation.conferencesCommunes(mia,sam);
            EnsembleConferences attendu = new EnsembleConferences();
            if(!recu.equals(attendu)){
                System.out.println("test "+numeroTest+" ko");
                System.out.println("attendu : "+attendu.toString());
                System.out.println("recu : "+recu.toString());
                tousReussi = false;
            }
        } catch(Exception e){
            System.out.println("test "+numeroTest+" ko, il y a eu une exception inattendue");
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println();

        if(tousReussi){
            System.out.println("Tous les tests proposes ont reussi");
        }else{
            System.out.println("methode a revoir !");
        }
        System.out.println();
    }

    private static RelationPersonneConference relationEnonce(){
        RelationPersonneConference relation = new RelationPersonneConference();
        Personne mia = new Personne("mia");
        Personne marie = new Personne("marie");
        Personne john = new Personne("john");
        Personne sam = new Personne("sam");
        Personne tim = new Personne("tim");
        Conference coccinelles = new Conference("coccinelles");
        Conference fourmis = new Conference("fourmis");
        Conference papillons = new Conference("papillons");
        Conference abeilles = new Conference("abeilles");
        Conference scarabees = new Conference("scarabees");
        relation.ajouterConference(scarabees);
        relation.ajouterConference(coccinelles);
        relation.ajouterConference(fourmis);
        relation.ajouterConference(papillons);
        relation.ajouterConference(abeilles);
        relation.ajouter(sam,abeilles);
        relation.ajouter(sam,scarabees);
        relation.ajouter (mia,fourmis);
        relation.ajouter(mia,papillons);
        relation.ajouter(mia,coccinelles);
        relation.ajouter(john,papillons);
        relation.ajouter(tim,coccinelles);
        relation.ajouter(tim,papillons);
        relation.ajouter(tim,abeilles);
        relation.ajouter(marie,coccinelles);
        return relation;
    }

}
