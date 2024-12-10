package it.unibo.es2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class LogicsImpl implements Logics{

    private List<List<Boolean>> table = new ArrayList<>();

    LogicsImpl(final int size){
        new ArrayList<>(Collections.nCopies(size,false));
        for (int i = 0; i < size; i++) {
        	this.table.add(new ArrayList<>(Collections.nCopies(size,false)));
        }
    }

    @Override
    public Boolean hit(int row, int column) {
        var newValue = !table.get(column).get(row);
        table.get(column).set(row, newValue);
        return newValue;
    }

    @Override
    public Boolean toQuit() {
        return anyRowFull() || anyColumnFull();
    }

    private Boolean anyColumnFull(){
        return this.table.stream().anyMatch(i -> i.stream().allMatch(v -> v));
    }

    private Boolean anyRowFull(){
        return IntStream.range(0, this.table.size()).anyMatch(i -> this.table.stream().allMatch(v -> v.get(i)));
    }
    
}
