import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class EnsembleLangues implements Iterable<Langue>{
  
    private HashSet<Langue> hashSet;

    public EnsembleLangues() {
        hashSet = new HashSet<Langue>();
    }

    /** renvoie true si l'ensemble courant est vide */
    public boolean estVide() {
        return hashSet.isEmpty();
    }

    /** renvoie le cardinal de l'ensemble courant */
    public int cardinal() {
        return hashSet.size();
    }

    /** renvoie true ssi la langue l appartient a l'ensemble courant */
    public boolean contient(Langue l) {
        if (l == null)
            throw new IllegalArgumentException();
        return hashSet.contains(l);
    }

    /**
     * ajoute la langue l a l'ensemble courant.
     * Sans effet si la langue l appartient deja a l'ensemble courant.
     */
    public void ajouter(Langue l) {
        if (l == null)
            throw new IllegalArgumentException();
        hashSet.add(l);
    }

    /**
     * enleve la langue l de l'ensemble courant.
     * sans effet si l'ensemble courant ne contient pas la langue l.
     */
    public void enlever(Langue l) {
        if (l == null)
            throw new IllegalArgumentException();
        hashSet.remove(l);
    }

    public EnsembleLangues clone(){
        EnsembleLangues clone = new EnsembleLangues();
        clone.hashSet = (HashSet<Langue>) hashSet.clone();
        return clone;
    }

    @Override
    public Iterator<Langue> iterator() {
        return hashSet.iterator();
    }

    @Override
    public String toString() {
        return hashSet.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnsembleLangues langues = (EnsembleLangues) o;
        return Objects.equals(hashSet, langues.hashSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashSet);
    }
}
