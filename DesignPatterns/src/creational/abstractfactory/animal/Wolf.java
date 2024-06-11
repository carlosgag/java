package creational.abstractfactory.animal;

public class Wolf extends Carnivore {

	@Override
	public void eat(Herbivore h) {
		System.out.println(this.getClass().getName() + " eats" + h.getClass().getName());
	}

}
