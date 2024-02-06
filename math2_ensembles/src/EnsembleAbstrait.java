public abstract class EnsembleAbstrait implements EnsembleInterface {

	// renvoie true ssi this est inclus dans a
	// lance une IllegalArgumentException en cas de param�tre invalide
	public boolean inclusDans(EnsembleAbstrait a) {
		if (a == null) throw new IllegalArgumentException();

		for (int i = 1; i <= MAX; i++) {
			Elt eltCourant = new Elt(i);
			if (this.contient(eltCourant) && !a.contient(eltCourant)) {
				return false;
			}
		}
		return true;
	}

	// renvoie true ssi this est �gal � a o
	public final boolean equals(Object o) {
		if (o == null)
			return false;
		if (o == this)
			return true;
		if (!(o instanceof EnsembleAbstrait))
			return false ;
		EnsembleAbstrait autreEnsemble = (EnsembleAbstrait) o;

		if (this.cardinal() != autreEnsemble.cardinal()) { //Check si les cardinaux sont égaux
			return false;
		}

		for (int i = 1; i <= MAX; i++) { //Check si les éléments sont égaux
			Elt eltCourant = new Elt(i);
			if (this.contient(eltCourant) != autreEnsemble.contient(eltCourant)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public final int hashCode() {
		int result = 1;
		int prime = 31;
		for (int i = 1; i <= MAX; i++) {
			Elt ei = new Elt(i);
			if (this.contient(ei))
				result = result * prime + ei.hashCode();
		}
		return result;
	}
	
}
