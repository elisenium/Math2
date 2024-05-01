import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class EnsembleCouplesPC implements Iterable<CouplePC>{
    private HashSet<CouplePC> hashSet;

    public EnsembleCouplesPC() {
        hashSet = new HashSet<CouplePC>();
    }

    /** renvoie true si l'ensemble courant est vide */
    public boolean estVide() {
        return hashSet.isEmpty();
    }

    /** renvoie le cardinal de l'ensemble courant */
    public int cardinal() {
        return hashSet.size();
    }

    /** renvoie true ssi le couple cpc appartient a l'ensemble courant */
    public boolean contient(CouplePC cpc) {
        if (cpc == null)
            throw new IllegalArgumentException();
        return hashSet.contains(cpc);
    }

    /**
     * ajoute le couple cpc a l'ensemble courant.
     * sans effet si le couple cpc appartient deja a l'ensemble courant.
     */
    public void ajouter(CouplePC cpc) {
        if (cpc == null)
            throw new IllegalArgumentException();
        hashSet.add(cpc);
    }

    /**
     * enleve le couple cpc de l'ensemble courant.
     * sans effet si l'ensemble courant ne contient pas le couple cpc.
     */
    public void enlever(CouplePC cpc) {
        if (cpc == null)
            throw new IllegalArgumentException();
        hashSet.remove(cpc);
    }

    public EnsembleCouplesPC clone(){
        EnsembleCouplesPC clone = new EnsembleCouplesPC();
        clone.hashSet = (HashSet<CouplePC>) hashSet.clone();
        return clone;
    }

    @Override
    public Iterator<CouplePC> iterator() {
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
        EnsembleCouplesPC couplePLS = (EnsembleCouplesPC) o;
        return Objects.equals(hashSet, couplePLS.hashSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hashSet);
    }
}
