package algorithm.atguigu.factory.trational;

import algorithm.atguigu.factory.trational.entity.Car;

public abstract class CarFactory
{
    abstract Car createCar(String carName);

    public CarFactory() {

    }

}
