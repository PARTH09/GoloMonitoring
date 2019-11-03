package com.golo.golomonitorapi.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceStatus implements Serializable {

    
    /**
	 * 
	 */
	private static final long serialVersionUID = -8125452315238914081L;
	private String apiStatus;
    private Date date;

}
