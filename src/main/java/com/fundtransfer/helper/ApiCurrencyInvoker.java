package com.fundtransfer.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiCurrencyInvoker {

  Logger logger = Logger.getLogger(ApiCurrencyInvoker.class.getName());

  @Autowired
  private RestTemplate restTemplate;

  @Value("${api.currencies}")
  private String urlCurrencies;

  public Map getAvailableCurrencies(String currency){

    ResponseEntity<String> currencies = restTemplate.getForEntity(urlCurrencies + currency, String.class);
    ObjectMapper mapper = new ObjectMapper();
    Map<String,Object> map = null;
    try {
      map = mapper.readValue(currencies.getBody(), Map.class);
    } catch (JsonProcessingException e) {
      logger.log(Level.SEVERE, "Error retrieving values from external currency API");
    }
    return map.containsKey("rates") ? map : new HashMap();

  }

}
