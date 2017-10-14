package be.kdg.prog3.upvote.dto;

public class VoteDto {
    private boolean isUp;

    public VoteDto() {
    }

    public VoteDto(boolean isUp) {
        this.isUp = isUp;
    }

    public boolean isUp() {
        return isUp;
    }

    public void setUp(boolean up) {
        isUp = up;
    }
}
