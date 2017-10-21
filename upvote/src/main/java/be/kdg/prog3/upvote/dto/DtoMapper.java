package be.kdg.prog3.upvote.dto;

import be.kdg.prog3.upvote.model.QuestionAnswer;
import be.kdg.prog3.upvote.model.Vote;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class DtoMapper {
    public DtoMapper() {
    }

    public List<QuestionAnswerDto> toDto(Map<QuestionAnswer, Vote> qasWithVotes) {
        final List<QuestionAnswerDto> result = new ArrayList<>();
        for (Map.Entry<QuestionAnswer, Vote> entry : qasWithVotes.entrySet()) {
            result.add(toDto(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    public QuestionAnswerDto toDto(QuestionAnswer qa, Vote vote) {
        final QuestionAnswerDto result = new QuestionAnswerDto();
        result.setId(qa.getId());
        result.setSubject(qa.getSubject());
        result.setBody(qa.getBody());
        result.setScore(qa.getScore());
        result.setTimestamp(qa.getTimestamp());
        result.setUserName(qa.getUser().getName());
        result.setCurrentUserVote(vote == null ? null : new VoteDto(vote.isUp()));
        return result;
    }

    public VoteDto toDto(Vote vote) {
        final VoteDto result = new VoteDto();
        result.setUp(vote.isUp());
        return result;
    }
}
