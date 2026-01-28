package com.lld.project.parkinglot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingFloor {

    private Integer floorId;
    private List<List<ParkingSpot>> parkingSpots;
    private Integer freeSpots;
    // }

    public ParkingFloor(Integer floorId, List<List<ParkingSpot>> parkingSpots, Integer freeSpots) {
        this.floorId = floorId;
        this.parkingSpots = parkingSpots;
        this.freeSpots = freeSpots;
    }

    public ParkingFloor(ParkingFloor other) {
        this.floorId = other.floorId;
        this.freeSpots = other.freeSpots;

        // Deep copy
        // this.parkingSpots = other.parkingSpots.stream()
        // .map(parkingRow ->
        // parkingRow.stream().map(ParkingSpot::new).collect(Collectors.toList())).toList();

        // Shallow copy
        this.parkingSpots = other.parkingSpots.stream()
                .map(row -> new ArrayList<>(row)).collect(Collectors.toList());

        // Option 1 -> This creats mutable list
        // other.parkingSpots.stream()
        // .map(row -> new ArrayList<>(row)).collect(Collectors.toList());

        // Option 2 -> This creates immutable list
        other.parkingSpots.stream()
                .map(row -> new ArrayList<>(row)).toList();
    }

    public ParkingFloor(Integer floor, Integer rows, Integer cols,
            final List<List<List<Integer>>> physicalLot) {

        this.floorId = floor;

        this.freeSpots = rows * cols;

        this.parkingSpots = new ArrayList<>();

        for (Integer r = 0; r < rows; r++) {

            List<ParkingSpot> parkingRow = new ArrayList<>();

            for (Integer c = 0; c < cols; c++) {

                Integer physicalType = physicalLot.get(floor).get(r).get(c);
                ParkingSpotType spotType = ParkingSpotType.IN_ACTIVE;

                if (physicalType == 4) {
                    spotType = ParkingSpotType.FOUR_WHEELER;
                } else if (physicalType == 2) {
                    spotType = ParkingSpotType.TWO_WHEELER;
                }

                if (spotType == ParkingSpotType.IN_ACTIVE)
                    this.freeSpots--;

                parkingRow.add(new ParkingSpot(floor.toString() + "-" + r.toString() + "-" + c.toString(), spotType));
            }
            this.parkingSpots.add(parkingRow);
        }
    }

    public Integer getFloorId() {
        return floorId;
    }

    public List<List<ParkingSpot>> getParkingSpots() {
        return parkingSpots;
    }

    public Integer getFreeSpots() {
        return freeSpots;
    }

    public Integer getFreeSpotsByParkingSpotType(ParkingSpotType parkingSpotType) {
        // System.out.println("***************** Invocation *****************");
        Integer freeSpots = 0;

        for (int r = 0; r < this.parkingSpots.size(); r++) {
            for (int c = 0; c < this.parkingSpots.get(r).size(); c++) {
                var spot = this.parkingSpots.get(r).get(c);
                if (!spot.isOccupied() && spot.getSpotType().equals(parkingSpotType)) {
                    freeSpots++;
                }
            }
        }

        // System.out.printf("ParkingFloor.getFreeSpotsByParkingSpotType(%s)  %s %n", parkingSpotType, freeSpots);

        return freeSpots;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        this.parkingSpots.forEach(row -> {
            sb.append(row.toString());
            sb.append("\n");
        });
        return sb.toString();
    }
}
