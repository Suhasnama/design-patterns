#include "Display.hpp"

#include <bits/stdc++.h>

using namespace std;

DisplayControl::DisplayControl()
{
    this->__class__ = __func__;
    cout << __class__ << " instance created" << endl;
}

std::string DisplayControl::getClass()
{
    return __class__;
}
void DisplayControl::message(const int &batteryLevel)
{
    cout << "[DisplayControl Alert] Battery level @ " << batteryLevel << "%" << endl;
}