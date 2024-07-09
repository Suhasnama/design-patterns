#include<bits/stdc++.h>

using namespace std;

class Shape{
    public:
        virtual void draw() = 0;
};

class Rectangle : public Shape {
    public:
        void draw(){
            cout << "Rectangle"<<endl;
        }
};

class Circle : public Shape {
    public :
        void draw(){
            cout << "Circle" << endl;
        }
};


class ShapeFactory {
    public:
        enum SHAPES {
            CIRCLE,
            RECTANGLE
        };

        Shape* get(SHAPES type){
            switch (type)
            {
            case SHAPES::CIRCLE:
                return new Circle();
                break;
            case SHAPES::RECTANGLE:
                return new Rectangle();
            default:
                break;
            }
            return new Rectangle();
        }

};
int main(){
    Shape* circle = (new ShapeFactory())->get(ShapeFactory::SHAPES::RECTANGLE);
    circle->draw();
    return 0;
}