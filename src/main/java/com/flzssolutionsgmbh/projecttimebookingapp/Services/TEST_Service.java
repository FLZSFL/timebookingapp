package com.flzssolutionsgmbh.projecttimebookingapp.Services;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;


@Service
public class TEST_Service {

    private static String dataTest = "";


    /*This tells the Spring to start this method as soon the application will run*/
    @PostConstruct
    public void fetchData(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(dataTest))
                .build();
    }
}
