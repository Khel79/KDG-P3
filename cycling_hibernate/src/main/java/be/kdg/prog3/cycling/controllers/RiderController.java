package be.kdg.prog3.cycling.controllers;

import be.kdg.prog3.cycling.dto.DtoAssembler;
import be.kdg.prog3.cycling.dto.RiderDto;
import be.kdg.prog3.cycling.model.Rider;
import be.kdg.prog3.cycling.services.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class RiderController {
    private final RiderService riderService;
    private final DtoAssembler dtoAssembler;

    @Autowired
    public RiderController(RiderService riderService, DtoAssembler dtoAssembler) {
        this.riderService = riderService;
        this.dtoAssembler = dtoAssembler;
    }

    @GetMapping("/rider/{id}")
    public ModelAndView showRider(@PathVariable long id) {
        final Rider rider = riderService.findById(id);
        final RiderDto riderDto = this.dtoAssembler.toResource(rider);

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("rider");
        modelAndView.getModel().put("rider", riderDto);
        return modelAndView;
    }

    @GetMapping("/riders")
    public ModelAndView showAllRiders() {
        final List<Rider> riders = riderService.findAll();
        final List<RiderDto> riderDtos = this.dtoAssembler.toRiderResources(riders);

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("all_riders");
        modelAndView.getModel().put("riders", riderDtos);
        return modelAndView;
    }
}
