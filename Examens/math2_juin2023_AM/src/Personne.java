import java.util.Objects;

public class Personne {

    private String nom;

    public Personne(String nom) {
        if (nom == null || nom.length()==0)
            throw new IllegalArgumentException();
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return Objects.equals(nom, personne.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }
}
