#include "logger.hpp"
#include <bits/stdc++.h>
// #include <mutex>
using namespace std;

/**
 * Logger members
*/
int Logger::counter = 0;
Logger *Logger::instance = nullptr;
mutex Logger::mtx;
/**
 * Logger default constructor
*/
Logger::Logger()
{
    cout << "Instance created : " << counter << endl;
    counter++;
}

/**
 * Logger methods definitions
*/

/**
 * Thread safe getInstance
 */
Logger *Logger::getInstance()
{
    // If instance is nullptr , only 1st thread takes lock and the checks
    // if instance is nullptr or  not, if then creates object.
    if (instance == nullptr)
    {
        mtx.lock();
        if (instance == nullptr)
        {
            instance = new Logger();
        }
        mtx.unlock();
    }
    return instance;
}

void Logger::info(const string &msg)
{
    cout << "[" << msg << "]" << endl;
}