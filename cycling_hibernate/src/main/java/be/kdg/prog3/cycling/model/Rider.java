package be.kdg.prog3.cycling.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

@Entity
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(length=50, nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dateOfBirth;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Team team;

    @OneToMany(mappedBy = "rider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StageResult> stageResults;

    protected Rider() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Team getTeam() {
        return team;
    }

    public List<StageResult> getStageResults() {
        return stageResults;
    }
}
