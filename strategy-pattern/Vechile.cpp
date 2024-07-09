// Below program is to display Strategy Pattern

#include <iostream>
#include <bits/stdc++.h>
using namespace std;

class DriveStrategy {
    public:
    // Pure virtual function
    virtual void drive() = 0;
    virtual ~DriveStrategy();
};

class NormalDrive: public DriveStrategy {
    public:
    void drive(){
        cout <<"Normal drive"<< endl;
    }
};

class SportyDrive: public DriveStrategy {
    public:
    void drive(){
        cout <<"Sporty drive"<< endl;
    }
};
class OffRoadDrive: public DriveStrategy {
    public:
    void drive(){
        cout << "OffRoad drive" << endl;
    }
};


class Vehicle {
    private:
    DriveStrategy* driveStrategy;
    public:
    Vehicle(DriveStrategy* ds = new NormalDrive() ){
        driveStrategy = ds;
    }

    virtual void drive(){
        this->driveStrategy->drive();
    }
    virtual ~Vehicle(){};
};

class NormalVechicle : public Vehicle {
    public:
    NormalVechicle(DriveStrategy* dst = new NormalDrive()):Vehicle(dst){
    }
};

class SportyVehicle : public Vehicle{
    public:
    SportyVehicle(DriveStrategy* dst):Vehicle(dst){
        
    }
   
};

class OffroadVehicle : public Vehicle{
    public:
    OffroadVehicle(DriveStrategy* dst):Vehicle(dst){
        
    }

};


int main(){
    vector<Vehicle*> cars;
    

    cars.push_back(new NormalVechicle());
    cars.push_back(new SportyVehicle(new SportyDrive()));
    cars.push_back(new OffroadVehicle(new OffRoadDrive()));

    for(auto c : cars){
        c->drive();
    }

    return 0;
}