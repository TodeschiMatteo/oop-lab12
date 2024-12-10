package it.unibo.es1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LogicsImpl implements Logics {
	private final static int MAX_BUTTON_VALUE = 4;

	private final int size;
	private final List<Integer> buttonList;
	private final List<Boolean> buttonEnabled;

	public LogicsImpl(int size) {
		this.size = size;
		this.buttonList = IntStream
			.range(0, this.size)
			.mapToObj(i -> 0)
			.collect(Collectors.toCollection(ArrayList::new));
		this.buttonEnabled = IntStream
			.range(0, this.size)
			.mapToObj(i -> true)
			.collect(Collectors.toCollection(ArrayList::new));
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public List<Integer> values() {
		return List.copyOf(this.buttonList);
	}

	@Override
	public List<Boolean> enablings() {
		return List.copyOf(this.buttonEnabled);
	}

	@Override
	public int hit(int elem) {
		this.buttonList.set(elem, this.buttonList.get(elem) + 1);
		if(this.buttonList.get(elem) == MAX_BUTTON_VALUE){
			this.buttonEnabled.set(elem,false);
		}
		return this.buttonList.get(elem);
	}

	@Override
	public String result() {
		final StringBuilder result = new StringBuilder();
		result.append("<<");
		for (int i = 0; i < this.size; i++) {
			result.append(this.buttonList.get(i) + (i < this.size-1?"|":""));
		}
		result.append(">>");
		return result.toString();
	}

	@Override
	public boolean toQuit() {
		return this.buttonList.stream().allMatch(i -> i.equals(this.buttonList.get(0)));
	}
}
