# target: dependency
#	action

output.out: main.o Audio.o Display.o PowerManager.o
	g++ --std=c++14 -o output.out *.o	

Audio.o: Audio.cpp
	g++ --std=c++14 -c Audio.cpp

Display.o: Display.cpp
	g++ --std=c++14 -c Display.cpp

PowerManager.o: PowerManager.cpp
	g++ --std=c++14 -c PowerManager.cpp

main.o:	main.cpp
	g++ --std=c++14 -c main.cpp

dep: 
	g++ --std=c++14 -c *.cpp

comp: 
	g++ --std=c++14 -o output.out *.o

run:
	./output.out

clean:
	rm *.o
	rm *.out