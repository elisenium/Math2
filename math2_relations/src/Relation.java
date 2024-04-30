/** Classe Relation héritant de RelationDeBase
	 Fournit des outils de manipulation des relations entre sous-ensembles de l'Univers
 */

import java.util.*;

public class Relation extends RelationDeBase {

	/** Valeur numérique de MAXELT */
	private static final int MAX = Elt.MAXELT.val();

	/** Construit la Relation vide sur l'ensemble vide */
	public Relation() {
		super();
	}

	/** Construit la Relation vide de d vers a */
	public Relation(EnsembleAbstrait d, EnsembleAbstrait a) {
		super(d, a);
	}

	/** Clone */
	public Relation clone() {
		return (Relation) super.clone();
	}
	
	//Ex1
	//renvoie le domaine de la relation courante
	public EnsembleAbstrait domaine() {
		Ensemble domaine = new Ensemble();
		for (Couple c : this) {
			domaine.ajouter(c.getX());
		}
		return domaine;
	}
	
	//renvoie l'image de la relation courante
	public EnsembleAbstrait image() {
		Ensemble image = new Ensemble();
		for (Couple c : this) {
			image.ajouter(c.getY());
		}
		return image;
	}
	
	// EX 2
	// renvoie la complémentaire de la relation courante
	public Relation complementaire() {
		Relation complementaire = new Relation(depart(), arrivee());

		for (Elt depart : this.depart()) {
			for (Elt arrivee : this.arrivee()) {
				if (!this.contient(depart, arrivee)) {
					complementaire.ajouter(depart, arrivee);
				}
			}
		}

		return complementaire;
	}

	// renvoie la réciproque de la relation courante
	public Relation reciproque() {
		Relation reciproque = new Relation(arrivee(), depart());

		for (Elt depart : this.depart()) {
			for (Elt arrivee : this.arrivee()) {
				if (this.contient(depart, arrivee)) {
					reciproque.ajouter(arrivee, depart);
				}
			}
		}

		return reciproque;
	}

	// si possible, remplace la relation courante par son union avec r
	//sinon, lance une IllegalArgumentException
	public void ajouter(RelationInterface r) {
		if (r == null || r.equals("") || !r.depart().equals(this.depart()) || !r.arrivee().equals(this.arrivee()))
			throw new IllegalArgumentException();

		for (Couple couple : r) {
			this.ajouter(couple);
		}
	}

	// si possible, remplace this par sa différence avec r
	//sinon, lance une IllegalArgumentException
	public void enlever(RelationInterface r) {
		if (r == null || r.equals("") || !r.depart().equals(this.depart()) || !r.arrivee().equals(this.arrivee()))
			throw new IllegalArgumentException();

		Relation relationClone = (Relation) r;
		relationClone = relationClone.clone();

		for (Couple couple : relationClone) {
			this.enlever(couple);
		}
	}

	// si possible, remplace this par son intersection avec r
	//sinon, lance une IllegalArgumentException
	public void intersecter(RelationInterface r) {
		if (r == null || r.equals("") || !r.depart().equals(this.depart()) || !r.arrivee().equals(this.arrivee()))
			throw new IllegalArgumentException();

		Relation relation = clone(); //on copie la relation courante

		for (Couple couple : relation) { //on parcourt les couples
			if (!r.contient(couple))
				this.enlever(couple);
		}
	}

	// si possible, renvoie la composée : this après r
	//sinon, lance une IllegalArgumentException
	public Relation apres(RelationInterface r) {
		if (r == null || !r.arrivee().equals(this.depart()))
			throw new IllegalArgumentException();

		Relation apres = new Relation(r.depart(), this.arrivee());

		for (Couple couple1 : r) {
			for (Couple couple2 : this) {
				if (couple1.getY().equals(couple2.getX()))
					apres.ajouter(couple1.getX(), couple2.getY());
			}
		}
		return apres;
	}


	
	/*Les exercices 4 et 5 ne concernent que les relations sur un ensemble.
	 * Les méthodes demandées génèreront donc une MathException lorsque l'ensemble de départ
	 * ne coïncide pas avec l'ensemble d'arrivée.
	 */
	
	/* Ex 4 */
		
	// Clôture la Relation courante pour la réflexivité
	public void cloReflex() {
		if (!this.depart().equals(this.arrivee()))
			throw new MathException();
		for (Elt elt : depart()) {
			Couple couple = new Couple(elt, elt);
			this.ajouter(couple);
		}
	}

	// Clôture la Relation courante pour la symétrie
	public void cloSym() {
		if (!this.depart().equals(this.arrivee()))
			throw new MathException();

		for (Elt elt1 : depart()) {
			for (Elt elt2 : arrivee()) {
				if (this.contient(elt1, elt2)) {
					Couple couple = new Couple(elt2, elt1);
					this.ajouter(couple);
				}
			}
		}
	}

	// Clôture la Relation courante pour la transitivité (Warshall)
	public void cloTrans() {
		if (!this.depart().equals(this.arrivee()))
			throw new MathException();

		Relation rClone = this.clone();

		for (Elt milieu : rClone.depart()) {
			for (Elt debut : rClone.depart()) {
				if (contient(debut, milieu)) {
					for (Elt fin : rClone.arrivee()) {
						if (contient(milieu, fin))
							this.ajouter(debut, fin);
					}
				}
			}
		}
	}
	
	
	//Ex 5
	/*Les questions qui suivent ne concernent que les relations sur un ensemble.
	 * Les méthodes demandées génèreront donc une MathException lorsque l'ensemble de départ
	 * ne coïncide pas avec l'ensemble d'arrivée.
	 */
	// renvoie true ssi this est réflexive
	public boolean reflexive(){
		if (!this.depart().equals(this.arrivee()))
			throw new MathException();

		for (Elt elt : this.depart()) {
			if (!this.contient(elt, elt))
				return false;
		}
		return true;
	}

	// renvoie true ssi this est antiréflexive
	public boolean antireflexive(){
		if (!this.depart().equals(this.arrivee()))
			throw new MathException();

		for (Elt elt : this.depart()) {
			if (this.contient(elt, elt))
				return false;
		}
		return true;
	}

	// renvoie true ssi this est symétrique
	public boolean symetrique(){
		if (!this.depart().equals(this.arrivee()))
			throw new MathException();

		for (Elt elt1 : this.depart()) {
			for (Elt elt2 : this.arrivee()) {
				if (this.contient(elt1,elt2) && !this.contient(elt2,elt1)
						|| !this.contient(elt1,elt2) && this.contient(elt2,elt1))  {
					return false;
				}
			}
		}
		return true;
	}

	// renvoie true ssi this est antisymétrique
	public boolean antisymetrique(){
		if (!this.depart().equals(this.arrivee()))
			throw new MathException();

		for (Elt elt1 : this.depart()) {
			for (Elt elt2 : this.arrivee()) {
				if (this.contient(elt1,elt2) && this.contient(elt2,elt1) && !elt1.equals(elt2)){
					return false;
				}
			}
		}
		return true;
	}

	// renvoie true ssi this est transitive
	public boolean transitive(){
		if (!this.depart().equals(this.arrivee()))
			throw new MathException();

		for (Elt elt : this.arrivee()) {
			for (Elt elt1 : this.depart()) {
				if (this.contient(elt,elt1)){
					for (Elt elt2 : this.arrivee()) {
						if (this.contient(elt1,elt2) && !this.contient(elt,elt2)){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	
	// Ex 6
	//Construit une copie de la relation en paramètre
	//lance une IllegalArgumentException en cas de paramètre invalide
	public Relation(RelationInterface r) {
		this();
		if (r == null)
			throw new IllegalArgumentException();

		for (Elt elementDepart : r.depart()) {
			this.ajouterDepart(elementDepart);
		}
		for (Elt elementArrivee : r.arrivee()) {
			this.ajouterArrivee(elementArrivee);
		}
		for (Couple couple : r) {
			this.ajouter(couple);
		}

	}

	//renvoie l'identité sur e
	//lance une IllegalArgumentException en cas de paramètre invalide
	public static Relation identite(EnsembleAbstrait e) {
		if (e == null)
			throw new IllegalArgumentException();

		Relation identite = new Relation(e,e);
		for (Elt elt : e) {
			identite.ajouter(elt,elt);
		}

		return identite;
	}

	//renvoie le produit cartésien de a et b
	//lance une IllegalArgumentException en cas de paramètre invalide
	public static Relation produitCartesien(EnsembleAbstrait a, EnsembleAbstrait b) {
		if (a == null || b == null)
			throw new IllegalArgumentException();

		Relation relationProduitCartesien = new Relation(a,b);

		for (Elt elt : a) {
			for (Elt elt1 : b) {
				Couple couple = new Couple(elt,elt1);
				relationProduitCartesien.ajouter(couple);
			}
		}
		return relationProduitCartesien;
	}

} // class Relation
