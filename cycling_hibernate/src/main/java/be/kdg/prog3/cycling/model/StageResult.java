package be.kdg.prog3.cycling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class StageResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private short finishPlace;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Stage stage;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Rider rider;

    protected StageResult() {
    }

    public short getFinishPlace() {
        return finishPlace;
    }

    public Stage getStage() {
        return stage;
    }

    public Rider getRider() {
        return rider;
    }
}
