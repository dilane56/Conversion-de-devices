package org.kfokam48.deviceconversion.service;

import org.kfokam48.deviceconversion.dto.ConvertRateResponse;
import org.kfokam48.deviceconversion.dto.EnrichedDataResponse;
import org.kfokam48.deviceconversion.dto.ExchangeRateResponse;
import org.kfokam48.deviceconversion.dto.StandardResponse;
import org.kfokam48.deviceconversion.exception.CurrencyNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class CurrencyService {

    private final WebClient webClient;
    private final String apiUrl = "https://v6.exchangerate-api.com/v6/88d448e28f18c0f5aa6d4f01/latest/";
    private final  String exchangeRateUrl = "https://v6.exchangerate-api.com/v6/88d448e28f18c0f5aa6d4f01/pair/";
    private final String enrichedDataUrl="https://v6.exchangerate-api.com/v6/88d448e28f18c0f5aa6d4f01/https://v6.exchangerate-api.com/v6/88d448e28f18c0f5aa6d4f01/enriched/";
    public CurrencyService(WebClient webClient) {
        this.webClient = webClient;
    }


    public ConvertRateResponse getExchangeRate(String baseCurrency, String targetCurrency) {
        // Appel à l'API externe

        String baseUrl = exchangeRateUrl + baseCurrency+"/"+targetCurrency;
        System.out.println(baseUrl);
        ConvertRateResponse response = webClient.get()
                .uri(baseUrl)
                .retrieve()
                .bodyToMono(ConvertRateResponse.class)
                .block();


        if (response == null ) {
            throw new IllegalArgumentException("Devise cible invalide : " + targetCurrency);
        }

      return response;

    }

    public StandardResponse getAllExchangeRatesByBaseCurrency(String baseCurrency) {
        String baseUrl = apiUrl + baseCurrency;
        System.out.println(baseUrl);

        StandardResponse response= webClient.get()
                .uri(baseUrl)
                .retrieve()
                .bodyToMono(StandardResponse.class)
                .block();
        System.out.println(response);

        if (response == null ) {
            System.out.println(response);
            throw new IllegalArgumentException("Devise cible invalide : " + baseCurrency);
        }else{
            return response;
        }

    }

    public ExchangeRateResponse getConvertRateAndResultByCurrency(String baseCurrency, String targetCurrency, double amount) {
        String baseUrl = exchangeRateUrl + baseCurrency+"/"+targetCurrency+"/"+amount;
        ExchangeRateResponse  response =webClient.get()
                .uri(baseUrl)
                .retrieve()
                .bodyToMono(ExchangeRateResponse.class)
                .block();
        if(response == null ) {
            throw new CurrencyNotFoundException(" the BaseCurrency  : " + baseCurrency +" or the TargetCurrency : " + targetCurrency+" Not Found");
        }
        return response;
    }

    public EnrichedDataResponse getExtraDataByCurrency(String baseCurrency, String targetCurrency) {
        String baseUrl = enrichedDataUrl + baseCurrency+"/"+targetCurrency;
        System.out.println(baseUrl);
        EnrichedDataResponse enrichedDataResponse = webClient.get()
                .uri(baseUrl)
                .retrieve()
                .bodyToMono(EnrichedDataResponse.class)
                .block();
        if(enrichedDataResponse == null ) {
            throw new CurrencyNotFoundException(" the BaseCurrency  : " + baseCurrency +" or the TargetCurrency : " + targetCurrency+" Not Found");
        }
        return enrichedDataResponse;
    }
}