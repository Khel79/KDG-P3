package be.kdg.prog3.cycling.controllers;

import be.kdg.prog3.cycling.dto.DtoAssembler;
import be.kdg.prog3.cycling.dto.TeamDto;
import be.kdg.prog3.cycling.model.Team;
import be.kdg.prog3.cycling.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TeamController {
    private final TeamService teamService;
    private final DtoAssembler dtoAssembler;

    @Autowired
    public TeamController(TeamService teamService, DtoAssembler dtoAssembler) {
        this.teamService = teamService;
        this.dtoAssembler = dtoAssembler;
    }

    @GetMapping({"/", "/teams"})
    public ModelAndView showAllTeams() {
        final List<Team> teams = teamService.findAll();
        final List<TeamDto> teamDtos = this.dtoAssembler.toTeamResources(teams);

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("all_teams");
        modelAndView.getModel().put("teams", teamDtos);
        return modelAndView;
    }

    @GetMapping("/team/{uciCode}")
    public ModelAndView showTeam(@PathVariable String uciCode) {
        final Team team = teamService.findByUciCode(uciCode);
        final TeamDto teamDto = this.dtoAssembler.toResource(team);

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("team");
        modelAndView.getModel().put("team", teamDto);
        return modelAndView;
    }
}
