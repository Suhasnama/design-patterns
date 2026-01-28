package com.lld.project;

import java.util.List;

import com.lld.project.parkinglot.ParkingLot;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        var x = List.of(4, 2, 4, 2, 4);
        var y = List.of(4, 4, 4, 4, 4);
        var first = List.of(x, y);
        var second = List.of(List.of(4, 4, 4, 4, 4), List.of(4, 4, 4, 4, 4));

        List<List<List<Integer>>> physicalLot = List.of(first, second);

        ParkingLot parkingLot = new ParkingLot(physicalLot.size(), physicalLot.get(0).size(),
                physicalLot.get(0).get(0).size(), physicalLot);

        var query = "TS15 ZZ DDDD";
        parkingLot.park(4, query, "T-" + query, 1);

        System.out.printf("Search Vechicle %s%n", parkingLot.searchVehicle(query));
        var queryTwo = "TS15 ZZ DDDD";
        parkingLot.park(4, queryTwo, "T-" + queryTwo, 1);
        System.out.printf("Search Vechicle %s%n", parkingLot.searchVehicle(queryTwo));

    }
}
