#ifndef AUDIO_H
#define AUDIO_H

#include "IObserver.hpp"

class AudioControl : public IObserver
{
public:
    AudioControl();
    void message(const int &) override;
    std::string getClass() override;

private:
    std::string __class__;
};
#endif