public class Ens1 extends EnsembleAbstrait {

	private boolean[] tabB; // e appartient � l'ensemble courant ssi tabE[e.val()] est � true.
	private int cardinal;

	public Ens1() {
		tabB = new boolean[Elt.MAXELT.val()+1];
		cardinal = 0;
	}

	public Ens1(EnsembleInterface a) {
		this();
		if (a == null) throw new IllegalArgumentException();
		for (int i = 1; i <= MAX; i++) {
			Elt elt = new Elt(i);
			if (a.contient(elt)) {
				tabB[i] = true;
				cardinal++;
			}
		}
	}

	public Ens1(Elt e) {
		this();
		if (e == null) throw new IllegalArgumentException();
		tabB[e.val()] = true;
		cardinal++;
	}
	
	public boolean estVide() {
		return cardinal == 0 ;
	}
	
	public Elt unElement() {
		if (estVide()) throw new MathException();
		for (int i = 0; i <= MAX; i++) {
			if (tabB[i]) {
				Elt e = new Elt(i);
				return e;
			}
		}
		return null;
	}

	public boolean contient(Elt e) {
		if (e == null) throw new IllegalArgumentException();

		return tabB[e.val()];
	}

	public void ajouter(Elt e) {
		if (e == null) throw new IllegalArgumentException();
		if (!contient(e)) {
			tabB[e.val()] = true;
			cardinal++;
		}
	}

	public void enlever(Elt e) {
		if (e == null) throw new IllegalArgumentException();
		if (contient(e)) {
			tabB[e.val()] = false;
			cardinal--;
		}
	}

	public int cardinal() {
		return cardinal;
	}

	public void complementer() {
		for (int i = 1; i <= MAX; i++) {
			tabB[i] = !tabB[i];
		}
		cardinal = Elt.MAXELT.val() - cardinal;
	}

	public String toString() {
		if (cardinal == 0)
			return "{}";
		String toString = "{";
		for (int i = 1; i <= MAX; i++) {
			if (tabB[i] == true) {
				toString += String.valueOf(i) + ",";
			}
		}
		toString = toString.substring(0, toString.length()-1);
		toString += "}";
		return toString;
	}
	
}
