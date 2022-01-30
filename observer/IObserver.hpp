#ifndef IOBSERVER_H
#define IOBSERVER_H
#include <string>

class IObserver
{
public:
    virtual void message(const int &) = 0;
    // Need this getter so that we can know the name of derived class, with base pointer, as used in PowerManager::registerListener()
    // This needs to be virtual , so appropriate derived class method is executed, when called with base pointer.
    virtual std::string getClass() = 0;

private:
    std::string __class__;
};

#endif