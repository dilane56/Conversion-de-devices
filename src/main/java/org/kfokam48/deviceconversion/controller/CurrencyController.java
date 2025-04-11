package org.kfokam48.deviceconversion.controller;

import org.kfokam48.deviceconversion.dto.ConvertRateResponse;
import org.kfokam48.deviceconversion.dto.EnrichedDataResponse;
import org.kfokam48.deviceconversion.dto.ExchangeRateResponse;
import org.kfokam48.deviceconversion.dto.StandardResponse;
import org.kfokam48.deviceconversion.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/v1/exchange")
@Tag(name = "Currency Exchange", description = "APIs pour gérer les conversions de devises et les taux de change")
public class CurrencyController {

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/rate")
    @Operation(
            summary = "Obtenir le taux de change entre deux devises",
            description = "Retourne le taux de change actuel entre une devise de base et une devise cible."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Taux de change retourné avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide, paramètres manquants ou incorrects")
    })
    public ResponseEntity<ConvertRateResponse> convertCurrency(
            @Parameter(description = "Devise de base", required = true) @RequestParam String baseCurrency,
            @Parameter(description = "Devise cible", required = true) @RequestParam String targetCurrency
    ) {
        ConvertRateResponse response = currencyService.getExchangeRate(baseCurrency, targetCurrency);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(
            summary = "Obtenir les taux de change de toutes les devises par rapport à une devise de base",
            description = "Retourne une liste des taux de change de toutes les devises par rapport à une devise donnée."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Liste des taux de change retournée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide, paramètre de devise de base manquant")
    })
    public ResponseEntity<StandardResponse> allCurrency(
            @Parameter(description = "Devise de base", required = true) @RequestParam String baseCurrency
    ) {
        StandardResponse response = currencyService.getAllExchangeRatesByBaseCurrency(baseCurrency);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rate/amount")
    @Operation(
            summary = "Convertir un montant entre deux devises",
            description = "Retourne le taux de change et le montant converti entre une devise de base et une devise cible."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Conversion effectuée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide, paramètres manquants ou incorrects")
    })
    public ResponseEntity<ExchangeRateResponse> getRateAndResult(
            @Parameter(description = "Devise de base", required = true) @RequestParam String baseCurrency,
            @Parameter(description = "Devise cible", required = true) @RequestParam String targetCurrency,
            @Parameter(description = "Montant à convertir", required = true) @RequestParam double amount
    ) {
        ExchangeRateResponse response = currencyService.getConvertRateAndResultByCurrency(baseCurrency, targetCurrency, amount);
        return ResponseEntity.ok(response);
    }
//
//    @GetMapping("/extradata")
//    @Operation(
//            summary = "Obtenir des données enrichies pour une conversion de devises",
//            description = "Retourne des informations supplémentaires comme les fluctuations historiques et les métadonnées des devises."
//    )
//    @ApiResponses({
//            @ApiResponse(responseCode = "200", description = "Données enrichies retournées avec succès"),
//            @ApiResponse(responseCode = "400", description = "Requête invalide, paramètres manquants ou incorrects")
//    })
//    public ResponseEntity<EnrichedDataResponse> getExtraDataByCurrency(
//            @Parameter(description = "Currency base Code   ex:GBP", required = true) @RequestParam String baseCurrency,
//            @Parameter(description = "Currency Target Code : JPY", required = true) @RequestParam String targetCurrency
//    ) {
//        EnrichedDataResponse response = currencyService.getExtraDataByCurrency(baseCurrency, targetCurrency);
//        return ResponseEntity.ok(response);
//    }
}