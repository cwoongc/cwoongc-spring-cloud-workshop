package com.wcchoi.workshop.springcloud.zone.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/zone")
public class ZoneController {

    @Value("${eureka.instance.metadata-map.zone}")
    private String zone;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getZone() {
        return String.format("zone=%s", zone);
    }

}
