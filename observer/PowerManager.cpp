#include "PowerManager.hpp"

#include <bits/stdc++.h>

using namespace std;

// Public Methods
PowerManager::PowerManager()
{
    __class__ = __func__;
    cout << __class__ << " instance created" << endl;
}

PowerManager::~PowerManager()
{
    cout << "PowerManager instance destructed" << endl;
}

void PowerManager::registerListener(shared_ptr<IObserver> observer)
{
    cout << observer->getClass() << " registered at PowerManager" << endl;
    this->_listeners.push_back(observer);
}

void PowerManager::unRegisterListener(shared_ptr<IObserver> observer)
{
    cout << observer->getClass() << " unregistered at PowerManager" << endl;
    this->_listeners.remove(observer);
}

/**
 * Setters
 */
void PowerManager::setBatteryLevel(int batteryLevel)
{
    cout << "[BATTERY LEVEL] @ " << batteryLevel << endl;
    this->_batteryLevel = batteryLevel;
    if (this->_batteryLevel <= this->_BATTERY_THRESHOLD)
    {
        this->_notifyListeners(this->_batteryLevel);
    }
};

void PowerManager::setBatteryThreshold(int batteryLevelThreshold)
{
    cout << "[BATTERY THRESHOLD] @ " << batteryLevelThreshold << endl;
    this->_BATTERY_THRESHOLD = batteryLevelThreshold;
}

// Private Methods

void PowerManager::_notifyListeners(const int &batteryLevel)
{
    for (auto it = this->_listeners.begin(); it != _listeners.end(); it++)
    {
        (*it)->message(batteryLevel);
    }
}