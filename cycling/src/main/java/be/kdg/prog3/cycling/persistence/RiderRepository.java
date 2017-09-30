package be.kdg.prog3.cycling.persistence;

import be.kdg.prog3.cycling.model.Rider;
import be.kdg.prog3.cycling.model.Stage;
import be.kdg.prog3.cycling.model.StageResult;
import be.kdg.prog3.cycling.model.Team;
import be.kdg.prog3.cycling.model.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Component
public class RiderRepository {
    private final List<Rider> riders;

    @Autowired
    public RiderRepository(TeamRepository teamRepository) {
        this.riders = new ArrayList<>();

        Team lts = teamRepository.findByUciCode("LTS");
        Team eqs = teamRepository.findByUciCode("EQS");

        Rider thomas = new Rider(1, "Thomas De Gendt", LocalDate.of(1986, Month.NOVEMBER, 6), lts);
        this.riders.add(thomas);
        Rider jelle = new Rider(2, "Jelle Vanendert", LocalDate.of(1985, Month.FEBRUARY, 19), lts);
        this.riders.add(jelle);
        Rider tim = new Rider(3, "Tim Wellens", LocalDate.of(1991, Month.MAY, 10), lts);
        this.riders.add(tim);
        Rider marcel = new Rider(4, "Marcel Kittel", LocalDate.of(1988, Month.MAY, 11), eqs);
        this.riders.add(marcel);
        Rider tony = new Rider(5, "Tony Martin", LocalDate.of(1985, Month.APRIL, 23), eqs);
        this.riders.add(tony);
        Rider zdenek = new Rider(6, "Zdeněk Štybar", LocalDate.of(1985, Month.DECEMBER, 11), eqs);
        this.riders.add(zdenek);

        Tour tour2015 = new Tour(1, 2015);
        Tour tour2016 = new Tour(2, 2016);

        Stage stage1 = new Stage(1, 1, tour2015);
        Stage stage2 = new Stage(2, 2, tour2015);
        Stage stage3 = new Stage(3, 3, tour2015);
        Stage stage4 = new Stage(4, 1, tour2016);
        Stage stage5 = new Stage(5, 2, tour2016);
        Stage stage6 = new Stage(6, 3, tour2016);

        /* Only the victories are inserted for now... */
        StageResult stageResult1 = new StageResult(1, 1, stage2, marcel);
        StageResult stageResult2 = new StageResult(2, 1, stage3, tony);
        StageResult stageResult3 = new StageResult(3, 1, stage4, zdenek);
        StageResult stageResult4 = new StageResult(4, 1, stage6, thomas);

        stage2.getStageResults().add(stageResult1);
        stage3.getStageResults().add(stageResult2);
        stage4.getStageResults().add(stageResult3);
        stage6.getStageResults().add(stageResult4);

        tour2015.getStages().add(stage1);
        tour2015.getStages().add(stage2);
        tour2015.getStages().add(stage3);
        tour2016.getStages().add(stage4);
        tour2016.getStages().add(stage5);
        tour2016.getStages().add(stage6);

        lts.getRiders().add(thomas);
        lts.getRiders().add(jelle);
        lts.getRiders().add(tim);
        eqs.getRiders().add(marcel);
        eqs.getRiders().add(tony);
        eqs.getRiders().add(zdenek);

        marcel.getStageResults().add(stageResult1);
        tony.getStageResults().add(stageResult2);
        zdenek.getStageResults().add(stageResult3);
        thomas.getStageResults().add(stageResult4);
    }

    public Rider findOne(long id) {
        for (Rider rider : riders) {
            if (rider.getId() == id) {
                return rider;
            }
        }
        return null;
    }

    public List<Rider> findAll() {
        return riders;
    }
}
