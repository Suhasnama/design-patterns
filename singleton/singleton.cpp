#include "logger.hpp"
#include <bits/stdc++.h>
#include <thread>

using namespace std;

void runner()
{
    Logger *logger = Logger::getInstance();
    logger->info("Hello World");
}
int main()
{

    thread t1(runner);
    thread t2(runner);

    t1.join();
    t2.join();
    return 0;
}