package com.golo.golomonitorapi.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.golo.golomonitorapi.dto.GoloApiResponseDto;
import com.golo.golomonitorapi.dto.MonitoringServicePropertiesDTO;
import com.golo.golomonitorapi.dto.ServiceStatus;

@Service
public class MonitoringService {

	private boolean started;

	private static String uri;

	ObjectMapper mapper = new ObjectMapper();



	private static List<String> list;

	public void start(MonitoringServicePropertiesDTO monitoringServicePropertiesDTO)
			throws JsonParseException, JsonMappingException, IOException {
		started = true;

		uri = monitoringServicePropertiesDTO.getHostName();

		list = new ArrayList<String>();
	}

	public void stop() {
		started = false;
	}


	public List<String> getstatistics() throws JsonProcessingException {
		//ServiceStatusDTO serviceStatusDTO = new ServiceStatusDTO();
		//serviceStatusDTO.setServiceStatusList(list);
		//String json = mapper.writeValueAsString(serviceStatusDTO);
		return list;
	}

	public void apiScheduler() throws JsonParseException, JsonMappingException, IOException {
		if (started) {
			GoloApiResponseDto apiResponseDto = calltoGoloApi(uri);
			ServiceStatus serviceStatus = new ServiceStatus(apiResponseDto.getStatus(), new Date());
			
			list.add("Status => "+ apiResponseDto.getStatus()+" : "+"Date => "+new Date());

		}

	}

	public GoloApiResponseDto calltoGoloApi(String hostName)
			throws JsonParseException, JsonMappingException, IOException {

		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(hostName, String.class);

		GoloApiResponseDto apiResponseDto = mapper.readValue(result, GoloApiResponseDto.class);

		System.out.println("apiResponseDto ========> " + apiResponseDto);
		return apiResponseDto;
	}

}
