package com.lld.project.parkinglot;

public class ParkingSpot {
    private String spotId;
    private ParkingSpotType spotType;
    private String vehicleId;
    private String ticketId;

    public ParkingSpot(String spotId, ParkingSpotType spotType) {
        this.spotId = spotId;
        this.spotType = spotType;
    }

    public ParkingSpot(ParkingSpot copyParkingSpot) {
        this.spotId = copyParkingSpot.spotId;
        this.spotType = copyParkingSpot.spotType;
        this.vehicleId = copyParkingSpot.vehicleId;
        this.ticketId = copyParkingSpot.ticketId;
    }

    public String getSpotId() {
        return spotId;
    }

    public ParkingSpotType getSpotType() {
        return spotType;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getTicketId() {
        return ticketId;
    }

    public boolean isOccupied() {
        return vehicleId != null && ticketId != null;
    }

    public boolean occupy(String vehicleId, String ticketId) {
        if (this.isOccupied())
            return false;
        this.vehicleId = vehicleId;
        this.ticketId = ticketId;
        return true;
    }

    public boolean free() {
        if (!isOccupied()) {
            return false;
        }

        this.vehicleId = null;
        this.ticketId = null;
        return true;
    }

    @Override
    public String toString() {
        return this.spotId + "-" + this.spotType;
    }
};
