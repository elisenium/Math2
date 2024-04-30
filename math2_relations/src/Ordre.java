import java.util.Iterator;

public class Ordre extends RelationAbstraite {

	private Relation couples;

	//construit l'identité sur e
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Ordre(EnsembleAbstrait e) {
		if (e == null || e.equals("")) //on vérifie si le paramètre est valide
			throw new IllegalArgumentException();

		couples = Relation.identite(e); //construction de l'identité sur e
	}

	//construit le plus petit ordre contenant r
	//lance une IllegalArgumentException si cette construction n'est pas possible
	public Ordre(Relation r) {
		if (r == null || r.equals("")) //on vérifie si le paramètre est valide
			throw new IllegalArgumentException();

		if (!r.arrivee().equals(r.depart())) //si l'arrivée de r n'est pas égale au départ de r => IllegalArgumentException
			throw new IllegalArgumentException();

		couples = new Relation(r); //on rajoute r dans les couples de this
		//couples = r.clone(); //pareil qu'au-dessus mais en utilisant clone
		couples.cloReflex(); //on rend la clôture reflexive
		couples.cloTrans(); //on rend la clôture transitive

		if (!couples.antisymetrique()) //erreur si ce n'est pas antisymétrique = aucune double flèche
			throw new IllegalArgumentException();
	}
	
	//constructeur pas recopie
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Ordre(Ordre or) {
		if (or == null || or.equals("")) //on vérifie si le paramètre est valide
			throw new IllegalArgumentException();

		couples = new Relation(or.depart(), or.arrivee()); //on ajoute les ensembles de départ et d'arrivée de l'ordre reçu en paramètre
		for (Couple couple : or.couples) {
			couples.ajouter(couple); //on ajoute tous ses couples dans le couple courant
		}
	}

	//ajoute x à l'ensemble sous-jacent de la relation d'ordre
	//ne fait rien si x est déjà dans l'ensemble sous-jacent
	//lance une IllegalArgumentException en cas de paramètre invalide
	public void ajouterAuSousJacent(Elt x) {
		if (x == null || x.equals("")) throw new IllegalArgumentException();
		if (!this.depart().contient(x) && !this.arrivee().contient(x)) {
			// si l'ensemble de depart et d'arrivée ne contiennent pas l'élément alors ajout
			// dans les ensembles et la relation
			this.couples.ajouterDepart(x);
			this.couples.ajouterArrivee(x);
			couples.ajouter(new Couple(x, x));
		}
	}

	//enlève x de l'ensemble sous-jacent de la relation d'ordre
	//ainsi que toutes les flêches liées à x
	//ne fait rien si x n'est pas dans l'ensemble sous-jacent 
	//lance une IllegalArgumentException en cas de paramètre invalide
	public void enleverDuSousJacent(Elt x) {
		if (x == null || x.equals("")) throw new IllegalArgumentException();
		if (couples.depart().contient(x) && couples.arrivee().contient(x)) {
			// si element est dans l'ensemble de depart et d'arriv�e
			Relation rel = new Relation(couples); // copie temporaire de la relation
			for (Couple c : rel) { // pour chaque couples de la relation
				if (c.getX().equals(x) || c.getY().equals(x))
					couples.enlever(c); // enlever les relations avec l'element � enlever
			}
			// ensuite on enleve l'element de l'ensemble de depart et d'arrivee
			couples.supprimerArrivee(x);
			couples.supprimerDepart(x);
		}
	}
	
	@Override
	public Iterator<Couple> iterator() {
		return couples.iterator();
	}

	@Override
	public boolean estVide() {
		return couples.estVide();
	}

	@Override
	public boolean contient(Couple c) {
		if (c == null) throw new IllegalArgumentException();
		if (!couples.depart().contient(c.getX()) || !couples.arrivee().contient(c.getY()))
			throw new IllegalArgumentException();

		return couples.contient(c.getX(), c.getY());
	}

	@Override
	//crée (si possible) le plus petit ordre contenant this et c
	//lance une IllegalArgumentException en cas de paramètre invalide
	public void ajouter(Couple c) {
		if (c == null || c.equals(""))
			throw new IllegalArgumentException();

		if (!couples.depart().contient(c.getX()) || !couples.arrivee().contient(c.getY()) || couples.contient(c.reciproque()))
			throw new IllegalArgumentException();

		couples.ajouter(c);
		couples.cloReflex();
		couples.cloTrans();

		if (!couples.antisymetrique())
			throw new IllegalArgumentException();
	}


	@Override
	//Enlève (si possible) l'arête de Hasse c du la relation d'ordre
	//lance une IllegalArgumentException en cas de si le paramètre est invalide ou si c n'est pas une arête de Hasse
	//ne fait rien sinon
	public void enlever(Couple c) {
		if (c == null || c.equals("")) throw new IllegalArgumentException();
		if (!this.depart().contient(c.getX())) throw new IllegalArgumentException();
		if (!this.depart().contient(c.getY())) throw new IllegalArgumentException();
		if (!this.contient(new Couple(c.getX(), c.getY()))) return;
		if (!estUneAreteDeHasse(c.getX(), c.getY()))
			throw new IllegalArgumentException();

		Ensemble plusPttX = this.plusPetitQue(c.getX());
		Ensemble plusGrdY = this.plusGrandQue(c.getY());
		for (Elt eX : plusPttX) {
			for (Elt eY : plusGrdY) {
				this.couples.enlever(eX, eY);
			}
		}
		this.couples.cloTrans();
	}
	
	private Ensemble plusPetitQue(Elt e){
		Ensemble min = new Ensemble();
		for (Elt eC : couples.depart()){
			if (couples.contient(eC, e))
				min.ajouter(eC);
		}
		return min;
	}
	
	private Ensemble plusGrandQue(Elt e){
		Ensemble maj = new Ensemble();
		for (Elt eC : couples.depart()){
			if (couples.contient(e,eC))
				maj.ajouter(eC);
		}
		return maj;
	}

	private boolean estUneAreteDeHasse(Elt x, Elt y) {
		if (!this.contient(new Couple(x, y)))
			return false;
		if (x.equals(y))
			return false;
		EnsembleAbstrait aParcourir = this.depart();
		aParcourir.enlever(x);
		aParcourir.enlever(y);
		for (Elt e : aParcourir) {
			if (this.contient(new Couple(x, e)) && this.contient(new Couple(e, y)))
				return false;
		}
		return true;
	}

	@Override
	public EnsembleAbstrait depart() {
		return couples.depart();
	}

	@Override
	public EnsembleAbstrait arrivee() {
		return couples.arrivee();
	}

	//renvoie true ssi x et y sont comparables pour l'ordre courant
	//lance une IllegalArgumentException en cas de paramètre invalide
	public boolean comparables(Elt x, Elt y) {
		if (x == null || x.equals("") || y == null || y.equals(""))
			throw new IllegalArgumentException();

		if (!couples.depart().contient(x) || !couples.arrivee().contient(y))
			throw new IllegalArgumentException();

		return couples.contient(x,y) || couples.contient(y,x);
	}

	// Renvoie l'ensemble des éléments minimaux de b
	//lance une IllegalArgumentException en cas de paramètre invalide
	public EnsembleAbstrait minimaux(EnsembleAbstrait b) {
		if(b == null || !b.inclusDans(this.couples.depart()))
			throw new IllegalArgumentException();

		EnsembleAbstrait ens = new Ensemble();
		for (Elt e : b) {
			boolean minimal = true;
			for (Elt f: b) {
				if (this.couples.contient(new Couple(f,e)) && !e.equals(f)) {
					minimal = false;
					break;
				}
			}
			if (minimal)
				ens.ajouter(e);
		}
		return ens;
	}
	
	// Renvoie l'ensemble des éléments maximaux de b
	//lance une IllegalArgumentException en cas de paramètre invalide
	public EnsembleAbstrait maximaux(EnsembleAbstrait b) {
		if (b == null || !b.inclusDans(this.couples.depart()))
			throw new IllegalArgumentException();

		EnsembleAbstrait ens = new Ensemble();
		for (Elt e : b) {
			boolean maximal = true;
			for (Elt f: b) {
				if (this.couples.contient(new Couple(e,f)) && !e.equals(f)) {
					maximal = false;
					break;
				}
			}
			if (maximal)
				ens.ajouter(e);
		}
		return ens;
	}

	// Renvoie le minimum de b s'il existe; renvoie null sinon
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Elt minimum(EnsembleAbstrait b) {
		if (b == null || b.equals(""))
			throw new IllegalArgumentException();
		if (minimaux(b).cardinal() > 1 || minimaux(b).estVide()) // si plusieurs minimaux ou aucun minimaux ==> null
			return null;
		return minimaux(b).unElement(); // return l'element
	}
	
	// Renvoie le maximum de b s'il existe; renvoie null sinon
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Elt maximum(EnsembleAbstrait b) {
		if (b == null || b.equals(""))
			throw new IllegalArgumentException();
		if (maximaux(b).cardinal() > 1 || maximaux(b).estVide()) // si plusieurs maximaux ou aucun maximaux ==> null
			return null;
		return maximaux(b).unElement(); // return l'element
	}

	// Renvoie l'ensemble des minorants de b
	//lance une IllegalArgumentException en cas de paramètre invalide
	public EnsembleAbstrait minor(EnsembleAbstrait b) {
		if (b == null || b.equals("")) throw new IllegalArgumentException();
		if (!b.inclusDans(couples.depart()))
			throw new IllegalArgumentException(); // si l'ensemble n'est pas dans la relation alors throw IAe
		Ensemble aRenvoyer = new Ensemble();
		for (Elt elt : couples.depart()) { // pour chaque element de la relation de depart
			if (b.inclusDans(plusGrandQue(elt))) // si le plus grand element est inclus dans l'ensemble b
				aRenvoyer.ajouter(elt); // on ajoute l'element dans l'ensemble
		}
		if (minimum(b) != null) // si il y a un minimum alors on renvoie celui-ci pk le minimum est le plus
			// petit element comparable de l'ensemble
			aRenvoyer.ajouter(minimum(b));
		return aRenvoyer;
	}
	
	// Renvoie l'ensemble des majorants de b
	//lance une IllegalArgumentException en cas de paramètre invalide
	public EnsembleAbstrait major(EnsembleAbstrait b) {
		if (b == null || b.equals("")) throw new IllegalArgumentException();
		if (!b.inclusDans(couples.depart()))
			throw new IllegalArgumentException(); // si l'ensemble n'est pas dans la relation alors throw IAe
		Ensemble aRenvoyer = new Ensemble();
		for (Elt elt : couples.depart()) { // pour chaque element de la relation de depart
			if (b.inclusDans(plusPetitQue(elt))) // si le plus petit element est inclus dans l'ensemble b
				aRenvoyer.ajouter(elt); // on ajoute l'element dans l'ensemble
		}
		if (maximum(b) != null)// si il y a un maximum alors on renvoie celui-ci pk le maximum est le plus
			// grand element comparable de l'ensemble
			aRenvoyer.ajouter(maximum(b));
		return aRenvoyer;
	}

	// Renvoie l'infimum de b s'il existe; renvoie null sinon
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Elt infimum(EnsembleAbstrait b) {
		if (b == null || b.equals("")) throw new IllegalArgumentException();
		return maximum(minor(b)); // return le plus grand des minorants
	}
	
	// Renvoie le supremum de b s'il existe; renvoie null sinon
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Elt supremum(EnsembleAbstrait b) {
		if (b == null || b.equals(""))
			throw new IllegalArgumentException();

		return minimum(major(b)); // return le plus petit des majorants
	}

	//Renvoie true ssi this est un treillis
	//lance une IllegalArgumentException en cas de paramètre invalide
	public boolean treillis(){
		for (Elt elt : couples.depart()) { // pour chaquel element de depart
			for (Elt elt2 : couples.arrivee()) { // et d'arrivee
				if (!elt.equals(elt2)) { // si les elements sont diff�rents
					Ensemble tmp = new Ensemble();
					tmp.ajouter(elt); // ajouter les 2 elements � l'ensemble
					tmp.ajouter(elt2);
					if (supremum(tmp) == null || infimum(tmp) == null) // si il n'y a pas de supremum et de infimum
						return false;
				}
			}
		}
		return true;
	}

	public String toString() {
		return couples.toString();
	}
	
}
