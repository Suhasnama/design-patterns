// Header guards
#ifndef POWER_MANAGER_H
#define POWER_MANAGER_H
#include "IObserver.hpp"

#include <bits/stdc++.h>
using namespace std;

class PowerManager
{
public:
    // Constructor
    PowerManager();
    // Destructor
    ~PowerManager();

    void registerListener(shared_ptr<IObserver> observer);
    void unRegisterListener(shared_ptr<IObserver> observer);

    void setBatteryLevel(int batteryLevel);
    void setBatteryThreshold(int batteryLevel);

private:
    string __class__;
    int _batteryLevel;
    int _BATTERY_THRESHOLD;
    void _notifyListeners(const int &batteryLevel);
    list<shared_ptr<IObserver>> _listeners;
};
#endif