package creational.abstractfactory;

import creational.abstractfactory.animal.Carnivore;
import creational.abstractfactory.animal.Herbivore;
import creational.abstractfactory.factory.ContinentFactory;

public class AnimalWorld {
	
	private Herbivore herbivore;
	private Carnivore carnivore;

	public AnimalWorld(ContinentFactory continentFactory) {
		herbivore = continentFactory.createHervivore();
		carnivore = continentFactory.createCarnivore();
	}
	
	public void runFoodChain() {
		carnivore.eat(herbivore);
	}
}
