import java.util.Objects;

public class Langue {
    private String langue;

    public Langue(String l) {
        if (l == null || l.length()==0)
            throw new IllegalArgumentException();
        this.langue = l;
    }

    public String getLangue() {
        return langue;
    }

    @Override
    public String toString() {
        return langue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Langue langues = (Langue) o;
        return Objects.equals(langue, langues.langue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(langue);
    }
}
