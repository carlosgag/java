package creational.abstractfactory.animal;

public class Lion extends Carnivore {

	@Override
	public void eat(Herbivore h) {
		System.out.println(this.getClass().getName() + " eats" + h.getClass().getName());
	}

}
