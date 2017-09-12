package com.samples.rest.springboot.controllers;

import com.samples.rest.springboot.models.ConversionRequest;
import com.samples.rest.springboot.utilities.CurrencyConverter;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {"http://localhost:8000", "http://localhost:9000"})
public class DemoUseAPIController {

    @RequestMapping(value = "/getCurrencyRate", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@RequestParam(value = "fromCurrency") String fromCurrency,
                                     @RequestParam(value = "toCurrency") String toCurrency) {

        ConversionRequest request = new ConversionRequest(fromCurrency, toCurrency);
        Double rate = CurrencyConverter.getConversionRate(fromCurrency, toCurrency);

        return new ResponseEntity<>(rate, HttpStatus.OK);
    }

    @RequestMapping(value = "/convertCurrency", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody ConversionRequest request) throws NotFound {
        Double result = new Double(0);
        if(request.isValidRequest())
            result = CurrencyConverter.convertCurrency(request.getFromCurrency(), request.getToCurrency(), request.getSourceAmount());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}