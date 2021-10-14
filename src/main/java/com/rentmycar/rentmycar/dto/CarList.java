package com.rentmycar.rentmycar.dto;

import java.util.HashMap;
import java.util.Map;

public class CarList {

    private String Brand;
    private String Model;

    public Map<String, String> getBrand() {
        Map<String, String> carList = new HashMap<>();
        carList.put("brand", Brand);
        carList.put("model", Model);

        return carList;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }
}
