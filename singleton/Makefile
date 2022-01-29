log: logger.cpp
	g++ -c logger.cpp

comp: singleton.cpp
	g++ --std=c++11 -o single.out singleton.cpp logger.o -pthread

run:
	./single.out
