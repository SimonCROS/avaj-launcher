package fr.simoncros.avaj.simulator;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
	private List<Flyable> observers = new ArrayList<>();

	public void register(Flyable flyable) {
		this.observers.add(flyable);
	}

	public void unregister(Flyable flyable) {
		this.observers.remove(flyable);
	}

	protected void conditionsChanged() {
		this.observers.forEach(Flyable::updateConditions);
	}
}
