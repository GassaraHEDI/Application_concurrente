package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/truck/{id}")
public class TruckController {
    private final Logger log = LoggerFactory.getLogger(TruckController.class);


    @Autowired
    ServiceTruck serviceTruck;


    @GetMapping()
    public synchronized TruckPosition getById(@PathVariable Long id) {
        try {
            return TruckPositionRepository.getBuId(id);
        }
        catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "truck not found"
            );
        }
    }

    @GetMapping("/map")
    @ResponseBody
    public RedirectView getMap(@PathVariable Long id) {
        String url =      serviceTruck.post(TruckPositionRepository.getBuId(id).getPosition());
        return  new RedirectView(url);
    }

}
