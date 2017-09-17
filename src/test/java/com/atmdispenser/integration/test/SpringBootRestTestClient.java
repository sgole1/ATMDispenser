package com.atmdispenser.integration.test;
 
import org.springframework.web.client.RestTemplate;
 

public class SpringBootRestTestClient {
 
    public static final String REST_SERVICE_URI = "http://localhost:8080/ATMDispenserAPI/api";
     

    private static void testATMMoneyWithdrawSuccess(){
        System.out.println("Testing withdraw API Success----------");
        RestTemplate restTemplate = new RestTemplate();
        String dispensedAmountDetails = restTemplate.getForObject(REST_SERVICE_URI+"/atm/250", String.class);
        System.out.println(dispensedAmountDetails);
    }
    
    private static void testATMMoneyWithdrawFailure(){
        System.out.println("Testing withdraw API Failure----------");
        RestTemplate restTemplate = new RestTemplate();
        String dispensedAmountDetails = restTemplate.getForObject(REST_SERVICE_URI+"/atm/80", String.class);
        System.out.println(dispensedAmountDetails);
    }

    public static void main(String args[]){
    	testATMMoneyWithdrawSuccess();
    	testATMMoneyWithdrawFailure();
    }
}