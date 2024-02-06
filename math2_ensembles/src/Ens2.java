public class Ens2 extends EnsembleAbstrait {

	private Elt[] elements; // contient les elements de l'ensemble. Il ne peut pas y avoir de doublon.
	private int cardinal;

	public Ens2() {
		elements = new Elt[MAX];
		cardinal = 0;
	}

	public Ens2(EnsembleInterface a) {
		this();
		if (a == null) throw new IllegalArgumentException();
		for (int i = 1; i <= MAX; i++) {
			Elt elt = new Elt(i);
			if (a.contient(elt)) {
				elements[cardinal] = elt;
				cardinal++;
			}
		}
	}

	public Ens2(Elt e) {
		this();
		if (e == null) throw new IllegalArgumentException();
		elements[cardinal] = e;
		cardinal++;
	}

	public boolean estVide() {
		return cardinal == 0;
	}
	
	public Elt unElement() {
		if (estVide()) throw new MathException();
		return elements[0];
	}

	public boolean contient(Elt e) {
		if (e == null || e.equals(""))
			throw new IllegalArgumentException();
		for (int i = 0; i < cardinal; i++) {
			if (e.equals(elements[i])) return true;
		}
		return false;
	}

	public void ajouter(Elt e) {
		if (e == null || e.equals(""))
			throw new IllegalArgumentException();
		if (!contient(e)) {
			elements[cardinal] = e;
			cardinal++;
		}
	}

	public void enlever(Elt e) {
		if (e == null || estVide())
			throw new IllegalArgumentException();
		for (int i = 0; i < cardinal; i++) {
			if (e.equals(elements[i])) {
				elements[i] = elements[cardinal-1];
				cardinal--;
				break;
			}
		}
	}

	public int cardinal() {
		return cardinal;
	}

	public void complementer() {
		//TODO;
		if (this.estVide()) {
			cardinal = Elt.MAXELT.val();
			for (int i = 0; i < Elt.MAXELT.val(); i++) {
				this.elements[i] = new Elt(i+1);
			}
		} else {
			boolean[] tab = new boolean[Elt.MAXELT.val()+1];
			int i = 0;
			while (i < cardinal) {
				tab[elements[i].val()] = true;
				i++;
			}
			cardinal = 0;
			for (int j = 1; j < tab.length; j++) {
				if (tab[j] == false) {
					elements[cardinal] = new Elt(j);
					cardinal++;
				}
			}
		}
	}

	public String toString() {
		if (cardinal == 0)
			return "{}";
		String extension = "{";
		for (int i = 0; i < cardinal; i++) {
			extension += elements[i].val() + ",";
		}
		return extension.substring(0, extension.length() - 1) + "}";
	}

}
