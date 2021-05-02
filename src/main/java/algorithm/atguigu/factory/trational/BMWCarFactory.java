package algorithm.atguigu.factory.trational;

import algorithm.atguigu.factory.trational.entity.BMW1;
import algorithm.atguigu.factory.trational.entity.BMW2;
import algorithm.atguigu.factory.trational.entity.Car;

public class BMWCarFactory extends CarFactory
{
    @Override
    Car createCar(String carName)
    {
        if (carName.equalsIgnoreCase("BMW1")) {
            return new BMW1();
        } else {
            return new BMW2();
        }
    }
}
