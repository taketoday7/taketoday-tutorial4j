package cn.tuyucheng.taketoday.creational.abstractfactory2;

public class AbstractFactoryRunner {

    public static void main(String[] args) {
        new AnimalAbstractFactory().createAnimal(AnimalType.LAND);
    }
}