package be.kdg.prog3.cycling.model;

public class StageResult {
    private long id;
    private int finishPlace;
    private Stage stage;
    private Rider rider;

    protected StageResult() {
    }

    public StageResult(long id, int finishPlace, Stage stage, Rider rider) {
        this.id = id;
        this.finishPlace = finishPlace;
        this.stage = stage;
        this.rider = rider;
    }

    public int getFinishPlace() {
        return finishPlace;
    }

    public Stage getStage() {
        return stage;
    }

    public Rider getRider() {
        return rider;
    }
}
