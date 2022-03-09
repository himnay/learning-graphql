package com.org.graphql.service;

import com.org.graphql.model.HelloWorldDto;

import java.util.List;

public interface HelloWorldService {

    String helloWorld();

    List<String> names();

    String fullName(String firstName, String lastName);

    String fullNameRequestObject(HelloWorldDto request);
}
