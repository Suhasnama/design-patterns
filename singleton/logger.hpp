#ifndef LOGGER_H
#define LOGGER_H
#include <string>
#include <mutex>

using namespace std;
class Logger
{
public:
    static Logger *getInstance();
    void info(const string &msg);

private:
    static int counter;
    static Logger *instance;
    static mutex mtx;
    
    /**
     * Make all constructors private.
     */

    // Default constructor
    Logger();
    // Copy constructor
    Logger(const Logger &);
    // operator overloading
    Logger operator=(Logger &);
};

#endif