package creational.abstractfactory.factory;

import creational.abstractfactory.animal.Carnivore;
import creational.abstractfactory.animal.Herbivore;

public abstract class ContinentFactory {
	public abstract Herbivore createHervivore();
	public abstract Carnivore createCarnivore();
}
