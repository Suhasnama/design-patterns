log: logger.cpp
	g++ -c logger.cpp

comp: singleton.cpp
	g++ --std=c++11 -o single singleton.cpp logger.o -pthread

run:
	./single
