// Video : https://www.youtube.com/watch?v=w6a9MXUwcfY

#include <bits/stdc++.h>
using namespace std;

// Abstract Class
class BasePizza {
    public:
        virtual int cost() = 0;
};

// Concrete Class
class MargheritaPizza : public BasePizza {
    public:
        int cost(){
            return 100;
        }
};

class VegDelightPizza : public BasePizza {
    public:
        int cost() {
            return 200;
        }
};


class ToppingsDecorator : public BasePizza{
    
};

class CheezeTopping : public ToppingsDecorator{
    private:
        BasePizza* pizza;
    public:
        CheezeTopping(BasePizza* basePizza){
            pizza = basePizza;
        }
        int cost(){
            return pizza->cost() + 10;            
        }
};


class OnionTopping : public ToppingsDecorator{
    private:
        BasePizza* pizza;
    public:
        OnionTopping(BasePizza* basePizza){
            pizza = basePizza;
        }
        int cost(){
            return pizza->cost() + 15;            
        }
};

int main(){
    BasePizza* pizza = new MargheritaPizza();
    pizza = new CheezeTopping(pizza);
    pizza = new OnionTopping(pizza);
    cout << pizza->cost()<<endl;
}