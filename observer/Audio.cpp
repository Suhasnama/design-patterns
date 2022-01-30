#include "Audio.hpp"

#include <bits/stdc++.h>

using namespace std;

AudioControl::AudioControl()
{
    this->__class__ = __func__;
    cout << __class__ << " instance created" << endl;
}

std::string AudioControl::getClass()
{
    return __class__;
}

void AudioControl::message(const int &batteryLevel)
{
    cout << "[AudioContorl Alert] Battery level @ " << batteryLevel << "%" << endl;
}