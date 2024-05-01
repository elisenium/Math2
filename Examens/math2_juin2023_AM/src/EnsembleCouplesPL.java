import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class EnsembleCouplesPL implements Iterable<CouplePL>{
    private HashSet<CouplePL> hashSet;

    public EnsembleCouplesPL() {
        hashSet = new HashSet<CouplePL>();
    }

    /** renvoie true si l'ensemble courant est vide */
    public boolean estVide() {
        return hashSet.isEmpty();
    }

    /** renvoie le cardinal de l'ensemble courant */
    public int cardinal() {
        return hashSet.size();
    }

    /** renvoie true ssi le couple cpl appartient a l'ensemble courant */
    public boolean contient(CouplePL cpl) {
        if (cpl == null)
            throw new IllegalArgumentException();
        return hashSet.contains(cpl);
    }

    /**
     * ajoute le couple cpl a l'ensemble courant.
     * sans effet si le couple cpl appartient deja a l'ensemble courant.
     */
    public void ajouter(CouplePL cpl) {
        if (cpl == null)
            throw new IllegalArgumentException();
        hashSet.add(cpl);
    }

    /**
     * enleve le couple cpl de l'ensemble courant.
     * sans effet si l'ensemble courant ne contient pas le couple cpl.
     */
    public void enlever(CouplePL cpl) {
        if (cpl == null)
            throw new IllegalArgumentException();
        hashSet.remove(cpl);
    }

    public EnsembleCouplesPL clone(){
        EnsembleCouplesPL clone = new EnsembleCouplesPL();
        clone.hashSet = (HashSet<CouplePL>) hashSet.clone();
        return clone;
    }

    @Override
    public Iterator<CouplePL> iterator() {
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
        EnsembleCouplesPL couplePLS = (EnsembleCouplesPL) o;
        return Objects.equals(hashSet, couplePLS.hashSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashSet);
    }
}
