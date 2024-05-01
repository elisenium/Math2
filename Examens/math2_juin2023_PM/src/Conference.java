import java.util.Objects;

public class Conference {
    private String conference;

    public Conference(String sujet) {
        if (sujet == null || sujet.length()==0)
            throw new IllegalArgumentException();
        this.conference = sujet;
    }

    public String getConference() {
        return conference;
    }

    @Override
    public String toString() {
        return conference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conference conference = (Conference) o;
        return Objects.equals(this.conference, conference.conference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conference);
    }
}
