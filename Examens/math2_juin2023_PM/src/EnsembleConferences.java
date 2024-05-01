import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class EnsembleConferences implements Iterable<Conference>{
  
    private HashSet<Conference> hashSet;

    public EnsembleConferences() {
        hashSet = new HashSet<Conference>();
    }

    /** renvoie true si l'ensemble courant est vide */
    public boolean estVide() {
        return hashSet.isEmpty();
    }

    /** renvoie le cardinal de l'ensemble courant */
    public int cardinal() {
        return hashSet.size();
    }

    /** renvoie true ssi la conference c appartient a l'ensemble courant */
    public boolean contient(Conference c) {
        if (c == null)
            throw new IllegalArgumentException();
        return hashSet.contains(c);
    }

    /**
     * ajoute la conference c a l'ensemble courant.
     * Sans effet si la conference c appartient deja a l'ensemble courant.
     */
    public void ajouter(Conference c) {
        if (c == null)
            throw new IllegalArgumentException();
        hashSet.add(c);
    }

    /**
     * enleve la conference c de l'ensemble courant.
     * sans effet si l'ensemble courant ne contient pas la conference c.
     */
    public void enlever(Conference c) {
        if (c == null)
            throw new IllegalArgumentException();
        hashSet.remove(c);
    }

    public EnsembleConferences clone(){
        EnsembleConferences clone = new EnsembleConferences();
        clone.hashSet = (HashSet<Conference>) hashSet.clone();
        return clone;
    }

    @Override
    public Iterator<Conference> iterator() {
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
        EnsembleConferences conferences = (EnsembleConferences) o;
        return Objects.equals(hashSet, conferences.hashSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashSet);
    }
}
