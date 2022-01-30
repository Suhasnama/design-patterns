#include <bits/stdc++.h>
#include "PowerManager.hpp"
#include "IObserver.hpp"
#include "Audio.hpp"
#include "Display.hpp"
#include <memory>
using namespace std;

int main()
{
    // Create powermanager
    unique_ptr<PowerManager> pManager = make_unique<PowerManager>();
    // Config powermanager
    pManager->setBatteryThreshold(20);
    pManager->setBatteryLevel(100);

    // Create Audio , Display controls
    shared_ptr<IObserver> audio = make_shared<AudioControl>();
    shared_ptr<IObserver> display = make_shared<DisplayControl>();
    // Register them with powermanager
    pManager->registerListener(audio);
    pManager->registerListener(display);
    
    
    pManager->setBatteryLevel(60);
    pManager->setBatteryLevel(1);

    return 0;
}