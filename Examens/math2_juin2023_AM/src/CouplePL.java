import java.util.Objects;

public class CouplePL {

    private Personne personne;
    private Langue langue;

    public CouplePL(Personne p, Langue l) {
        if (p == null)
            throw new IllegalArgumentException();
        if (l == null)
            throw new IllegalArgumentException();
        this.personne = p;
        this.langue = l;
    }

    public Personne getPersonne() {
        return personne;
    }

    public Langue getLangue() {
        return langue;
    }

    @Override
    public String toString() {
        return "(" + personne + "," + langue + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouplePL that = (CouplePL) o;
        return Objects.equals(personne, that.personne) && Objects.equals(langue, that.langue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personne, langue);
    }
}
