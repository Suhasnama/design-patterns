#include<bits/stdc++.h>

using namespace std;

class Observer{
    public:
    virtual void update(int event) = 0;
};

class Observable{
    public:
    virtual void subscribe(Observer* observer) = 0;
    virtual void set(int d) = 0;
    virtual void notify(int event) = 0;
    // virtual ~Observable() = 0;
};

class MacbookObservable: public Observable {
    private:
        unordered_set<Observer*> observers;
        int data;
        void notify(int data){
            for(Observer* o : observers){
                o->update(data);
            }
        }
    public:
        MacbookObservable(){
            data = 0;
        };
       
        void subscribe(Observer* obs){
            if(observers.find(obs) != observers.end()){
                return;
            }
            observers.insert(obs);
        }

        void unsbscribe(Observer* obs){
            if(observers.find(obs) != observers.end()){
                observers.erase(obs);
            }
        }

        void set(int d){
            if(data != d && d > 0)
                notify(d);
            data = d;
            
        }

        // ~MacbookObservable(){};
};

class User : public Observer{
    private:
        string name;
    public:
        User(string n){
            name = n;
        }   
    void update(int event){
        cout << name<<"\tStock updated to "<< event << endl;
    }    
};

int main(){
    User u1("user1"), u2("user2");
    MacbookObservable mbO;
    mbO.subscribe(&u1);
    mbO.subscribe(&u2);
    mbO.set(1);
    mbO.set(2);
    mbO.unsbscribe(&u2);
    mbO.set(10);

    return 0;
}