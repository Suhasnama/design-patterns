#ifndef DISPLAY_H
#define DISPLAY_H

#include "IObserver.hpp"

class DisplayControl : public IObserver
{
public:
    DisplayControl();
    void message(const int &) override;
    std::string getClass() override;

private:
    std::string __class__;
};
#endif