package algorithm.atguigu.factory.trational;

import algorithm.atguigu.factory.trational.entity.Car;
import algorithm.atguigu.factory.trational.entity.Dongfeng1;
import algorithm.atguigu.factory.trational.entity.Dongfeng2;

public class DongFnegCarFactory extends CarFactory
{
    @Override
    Car createCar(String carName)
    {
        if (carName.equalsIgnoreCase("D1"))
        {
            return new Dongfeng1();
        }
        else
        {
            return new Dongfeng2();
        }
    }
}
