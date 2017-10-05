package be.kdg.prog3.cycling.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(length=50, nullable = false)
    private String name;

    @Column(length=3, nullable = false, columnDefinition="char(3)")
    private String uciCode;

    @Column(nullable = false)
    private short founded;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Rider> riders;

    protected Team() {
    }

    public String getName() {
        return name;
    }

    public String getUciCode() {
        return uciCode;
    }

    public short getFounded() {
        return founded;
    }

    public List<Rider> getRiders() {
        return riders;
    }
}
