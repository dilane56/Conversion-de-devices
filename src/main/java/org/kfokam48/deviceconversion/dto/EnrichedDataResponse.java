package org.kfokam48.deviceconversion.dto;

import java.math.BigInteger;
import java.util.Map;

public class EnrichedDataResponse {

    private String base_code;
    private String target_code;
    private double conversion_rate;
    private Map<String, Double> target_data;

    @Override
    public String toString() {
        return "EnrichedDataResponse{" +
                ", base_code='" + base_code + '\'' +
                ", target_code='" + target_code + '\'' +
                ", conversion_rate=" + conversion_rate +
                ", target_data=" + target_data +
                '}';
    }
}
