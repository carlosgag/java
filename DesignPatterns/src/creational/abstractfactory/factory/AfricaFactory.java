package creational.abstractfactory.factory;

import creational.abstractfactory.animal.Carnivore;
import creational.abstractfactory.animal.Herbivore;
import creational.abstractfactory.animal.Lion;
import creational.abstractfactory.animal.Wildebeest;

public class AfricaFactory extends ContinentFactory {

	@Override
	public Herbivore createHervivore() {
		return new Wildebeest();
	}

	@Override
	public Carnivore createCarnivore() {
		return new Lion();
	}

}
