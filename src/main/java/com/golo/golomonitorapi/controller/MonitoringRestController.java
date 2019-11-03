package com.golo.golomonitorapi.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.golo.golomonitorapi.dto.MonitoringServicePropertiesDTO;
import com.golo.golomonitorapi.services.MonitoringService;

@Controller
@RequestMapping("/golomonitor")
public class MonitoringRestController {

	@Autowired
	MonitoringService monitoringService;

    @RequestMapping(value = "/start", method = RequestMethod.POST)
    public ResponseEntity startMonitoring(@RequestBody @Valid MonitoringServicePropertiesDTO monitoringServicePropertiesDTO) throws JsonParseException, JsonMappingException, IOException{
        monitoringService.start(monitoringServicePropertiesDTO);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/stop", method = RequestMethod.GET)
    public ResponseEntity stopMonitoring(){
        monitoringService.stop();
        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/statistics", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getHealth() throws JsonProcessingException{
        List<String> serviceStatus = monitoringService.getstatistics();
        return ResponseEntity.ok(serviceStatus);
    }

}
