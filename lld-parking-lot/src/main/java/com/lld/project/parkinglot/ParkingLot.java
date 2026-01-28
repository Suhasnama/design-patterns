package com.lld.project.parkinglot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLot {
    private List<ParkingFloor> parkingFloors;
    // VechileID / TicketId , SpotId
    private Map<String, String> vechicleToSpotId;

    public ParkingLot(Integer floors, Integer rows, Integer cols, final List<List<List<Integer>>> physicalLot) {
        this.parkingFloors = new ArrayList<>();
        this.vechicleToSpotId = new HashMap<>();

        for (int f = 0; f < floors; f++)
            parkingFloors.add(new ParkingFloor(f, rows, cols, physicalLot));
    }

    public String park(Integer vechicleType, String vehicleNumber, String ticketId, int parkingStrategy) {
        String spotId = "";

        if (parkingStrategy == 1)
            spotId = this.parkingStratageyOne(vechicleType, vehicleNumber, ticketId);
        else
            spotId = this.parkingStratageyTwo(vechicleType, vehicleNumber, ticketId);

        if (spotId == "")
            return spotId;

        vechicleToSpotId.put(ticketId, spotId);
        vechicleToSpotId.put(vehicleNumber, spotId);
        return spotId;
    }

    public boolean removeVehicle(String spotId) {
        String[] splits = spotId.split("-");

        Integer floor = Integer.parseInt((splits[0])),
                row = Integer.parseInt(splits[1]),
                col = Integer.parseInt(splits[2]);

        var spot = this.parkingFloors.get(floor).getParkingSpots().get(row).get(col);
        vechicleToSpotId.remove(spot.getVehicleId());
        return spot.free();
    }

    /**
     * String searchVehicle(String query)
     * searches the latest parking details of a vehicle parked in previous park()
     * method calls.
     * returns spotId e.g. 2-0-15 or empty string ""
     * Query will be either vehicleNumber or ticketId.
     * 
     * @param query
     * @return
     */
    public String searchVehicle(String query) {
        return vechicleToSpotId.getOrDefault(query, "");
    }

    private String parkingStratageyOne(Integer vechicleType, String vehicleNumber, String ticketId) {
        // Get parking spot at the lowest floor with lowest row & col
        final ParkingSpotType vechParkingSpotType = vechicleType == 4 ? ParkingSpotType.FOUR_WHEELER
                : ParkingSpotType.TWO_WHEELER;

        for (ParkingFloor parkingFloor : this.parkingFloors) {
            if (parkingFloor.getFreeSpots() <= 0)
                continue;
            var spot = this.allocateParkingSpot(parkingFloor, vechParkingSpotType, vechicleType, vehicleNumber,
                    ticketId);
            if (spot != null)
                return spot;
        }
        throw new Error("NoFreeSparkingSpots | Unable to allocate parking spots.");
    }

    private String parkingStratageyTwo(Integer vechicleType, String vehicleNumber, String ticketId) {
        final ParkingSpotType vechParkingSpotType = vechicleType == 4 ? ParkingSpotType.FOUR_WHEELER
                : ParkingSpotType.TWO_WHEELER;

        // Get parking spot in floor with max # of free spots.

        // Create a copy of floors.
        // Re-arragne them by max # of free seats for a given vechile type.

        

        List<ParkingFloor> sortedParkingFloors = this.parkingFloors
                .stream()
                // .peek(f -> System.out.println("Checking floor: " + f)) // Debugging
                .sorted((a, b) -> Integer.compare(a.getFreeSpotsByParkingSpotType(vechParkingSpotType),
                        b.getFreeSpotsByParkingSpotType(vechParkingSpotType)))
                // .sorted(Comparator.comparingInt(floor ->
                // floor.getFreeSpotsByParkingSpotType(vechParkingSpotType)))
                .collect(Collectors.toList()).reversed();

        // System.out.println(this.parkingFloors);
        // sortedParkingFloors.forEach(f -> System.out.println("floor id is : " + f.getFloorId()));
        // System.out.println(sortedParkingFloors);

        // Iterate on re-arr floors to get a parking spot.
        for (ParkingFloor parkingFloor : sortedParkingFloors) {
            if (parkingFloor.getFreeSpots() <= 0)
                continue;
            var spot = this.allocateParkingSpot(parkingFloor, vechParkingSpotType, vechicleType, vehicleNumber,
                    ticketId);
            if (spot != null)
                return spot;
        }

        throw new Error("NoFreeSparkingSpots | Unable to allocate parking spots.");
    }

    private String allocateParkingSpot(ParkingFloor parkingFloor, ParkingSpotType vechParkingSpotType,
            Integer vechicleType, String vehicleNumber,
            String ticketId) {
        for (int r = 0; r < parkingFloor.getParkingSpots().size(); r++) {
            for (int c = 0; c < parkingFloor.getParkingSpots().get(r).size(); c++) {
                var spot = parkingFloor.getParkingSpots().get(r).get(c);
                if (!spot.isOccupied() && spot.getSpotType().equals(vechParkingSpotType)) {
                    spot.occupy(vehicleNumber, ticketId);
                    return spot.getSpotId();
                }
            }
        }
        return null;
    }

}
