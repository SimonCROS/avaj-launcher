package fr.simoncros.avaj.simulator;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
	private List<Flyable> observers = new ArrayList<>();

	public void register(Flyable flyable) {
		// LOG
		this.observers.add(flyable);
	}

	public void unregister(Flyable flyable) {
		// LOG
		this.observers.remove(flyable);
	}

	protected void conditionsChanged() {
		for (Flyable flyable : this.observers)
			flyable.updateConditions();
	}
}
