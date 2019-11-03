package com.golo.golomonitorapi.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ServiceStatusDTO implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 6045544810124278595L;
	private List<ServiceStatus> serviceStatusList;


}
