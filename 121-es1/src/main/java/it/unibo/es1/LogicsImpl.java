package it.unibo.es1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LogicsImpl implements Logics {

	private final List<Integer> buttonList;

	public LogicsImpl(int size) {
		this.buttonList = new ArrayList<>(Collections.nCopies(size,0));
	}

	@Override
	public int size() {
		return buttonList.size();
	}

	@Override
	public List<Integer> values() {
		return Collections.unmodifiableList(this.buttonList);
	}

	@Override
	public List<Boolean> enablings() {
		return buttonList.stream().map(it -> it < size()).toList();
	}

	@Override
	public int hit(int elem) {
		final int newValue = this.buttonList.get(elem) + 1;
		this.buttonList.set(elem, newValue);
		return newValue;
	}

	@Override
	public String result() {
		return buttonList.stream()
			.map(it -> Integer.toString(it))
			.collect(Collectors.joining("|", "<<", ">>"));
	}

	@Override
	public boolean toQuit() {
		final var first = this.buttonList.getFirst();
		return first != 0 && this.buttonList.stream().allMatch(i -> i == first);
	}
}
