package creational.abstractfactory.factory;

import creational.abstractfactory.animal.Bison;
import creational.abstractfactory.animal.Carnivore;
import creational.abstractfactory.animal.Herbivore;
import creational.abstractfactory.animal.Wolf;

public class AmericaFactory extends ContinentFactory {

	@Override
	public Herbivore createHervivore() {
		return new Bison();
	}

	@Override
	public Carnivore createCarnivore() {
		return new Wolf();
	}

}
