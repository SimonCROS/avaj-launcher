package fr.simoncros.avaj.simulator;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower {
	private List<Flyable> observers = new ArrayList<>();

	public void register(Flyable flyable) {
		Logs.print("Tower says: " + flyable + " registered to weather tower.");
		this.observers.add(flyable);
	}

	public void unregister(Flyable flyable) {
		Logs.print("Tower says: " + flyable + " unregistered from weather tower.");
		this.observers.remove(flyable);
	}

	protected void conditionsChanged() {
		List<Flyable> immutable = new ArrayList<>(this.observers);
		for (Flyable flyable : immutable)
			flyable.updateConditions();
	}
}
