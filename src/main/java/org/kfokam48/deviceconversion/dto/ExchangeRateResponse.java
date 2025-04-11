package org.kfokam48.deviceconversion.dto;



public class ExchangeRateResponse {
    private String base_code; // Correspond à "base_code" dans le JSON
    private String target_code;
    private String conversion_rate; // Correspond à "conversion_rates" dans le JSON
    private double conversion_result;

    public String getBase_code() {
        return base_code;
    }

    public void setBase_code(String base_code) {
        this.base_code = base_code;
    }

    public String getTarget_code() {
        return target_code;
    }

    public void setTarget_code(String target_code) {
        this.target_code = target_code;
    }

    public String getConversion_rate() {
        return conversion_rate;
    }

    public void setConversion_rate(String conversion_rate) {
        this.conversion_rate = conversion_rate;
    }

    public double getConversion_result() {
        return conversion_result;
    }

    public void setConversion_result(double conversion_result) {
        this.conversion_result = conversion_result;
    }

    @Override
    public String toString() {
        return "ExchangeRateResponse{" +
                "base_code='" + base_code + '\'' +
                ", target_code='" + target_code + '\'' +
                ", conversion_rate='" + conversion_rate + '\'' +
                ", conversion_result=" + conversion_result +
                '}';
    }
}