import java.util.Objects;

public class CouplePC {

    private Personne personne;
    private Conference conference;

    public CouplePC(Personne p, Conference c) {
        if (p == null)
            throw new IllegalArgumentException();
        if (c == null)
            throw new IllegalArgumentException();
        this.personne = p;
        this.conference = c;
    }

    public Personne getPersonne() {
        return personne;
    }

    public Conference getConference() {
        return conference;
    }

    @Override
    public String toString() {
        return "(" + personne + "," + conference + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CouplePC that = (CouplePC) o;
        return Objects.equals(personne, that.personne) && Objects.equals(conference, that.conference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personne, conference);
    }
}
