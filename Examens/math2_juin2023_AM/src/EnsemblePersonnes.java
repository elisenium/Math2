import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class EnsemblePersonnes implements Iterable<Personne>{

    private HashSet<Personne> hashSet;

    public EnsemblePersonnes() {
        hashSet = new HashSet<Personne>();
    }

    /** renvoie true si l'ensemble courant est vide */
    public boolean estVide() {
        return hashSet.isEmpty();
    }

    /** renvoie le cardinal de l'ensemble courant */
    public int cardinal() {
        return hashSet.size();
    }

    /** renvoie true ssi la personne p appartient a l'ensemble courant */
    public boolean contient(Personne p) {
        if (p == null)
            throw new IllegalArgumentException();
        return hashSet.contains(p);
    }

    /**
     * ajoute la personne p a l'ensemble courant.
     * sans effet si la personne p appartient deja a l'ensemble courant.
     */
    public void ajouter(Personne p) {
        if (p == null)
            throw new IllegalArgumentException();
        hashSet.add(p);
    }

    /**
     * enleve la personne p de l'ensemble courant.
     * sans effet si l'ensemble courant ne contient pas la personne p.
     */
    public void enlever(Personne p) {
        if (p == null)
            throw new IllegalArgumentException();
        hashSet.remove(p);
    }

    public EnsemblePersonnes clone(){
        EnsemblePersonnes clone = new EnsemblePersonnes();
        clone.hashSet = (HashSet<Personne>) hashSet.clone();
        return clone;
    }

    @Override
    public Iterator<Personne> iterator() {
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
        EnsemblePersonnes personnes = (EnsemblePersonnes) o;
        return Objects.equals(hashSet, personnes.hashSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashSet);
    }
}
