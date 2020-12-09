package com.trendyolbootcamp.linkedinclonejob.domain;


import java.util.ArrayList;
import java.util.Arrays;

public class FakeDataJob {
    public static final String title = "Software Developer";
    public static final String sector = "Software Development";
    public static final String city = "İstanbul";
    public static final String company = "Trendyol Tech";
    public static final int numberOfHires = 8;
    public static final ArrayList<String> requirements = new ArrayList<>(Arrays.asList("Problem solving skills",
            "Writing and testing code","Collaborating with other developers"));
    public static final ArrayList<String> applicants= new ArrayList<>(Arrays.asList("Gökhan", "Ahmet", "Yonca"));
}
