package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

public class TruckPositionRepository {
    private static List<TruckPosition> positions = new ArrayList<TruckPosition>();

    static void addPosition(TruckPosition position){
        positions.add(position);
    }

    static TruckPosition getBuId(Long id){
        for (TruckPosition position: positions
        ) {
            if (position.getTruckId()==id){
                return position;
            }
        }
        throw new IllegalArgumentException("Truck not exists");
    }
}
