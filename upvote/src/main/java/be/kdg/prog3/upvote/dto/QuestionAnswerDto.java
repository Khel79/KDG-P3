package be.kdg.prog3.upvote.dto;

import java.util.Date;

public class QuestionAnswerDto {
    private long id;
    private String subject;
    private String body;
    private int score;
    private Date timestamp;
    private String userName;
    private VoteDto currentUserVote;

    public QuestionAnswerDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public VoteDto getCurrentUserVote() {
        return currentUserVote;
    }

    public void setCurrentUserVote(VoteDto currentUserVote) {
        this.currentUserVote = currentUserVote;
    }
}
