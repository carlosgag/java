package creational.abstractfactory;

import creational.abstractfactory.factory.AfricaFactory;
import creational.abstractfactory.factory.AmericaFactory;
import creational.abstractfactory.factory.ContinentFactory;

public class MainApp {
	public static void main(String [] args) {
		ContinentFactory africa = new AfricaFactory();
		AnimalWorld animalWorld = new AnimalWorld(africa);
		animalWorld.runFoodChain();
		
		ContinentFactory america = new AmericaFactory();
		animalWorld = new AnimalWorld(america);
		animalWorld.runFoodChain();
		
	}
	
}
