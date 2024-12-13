package it.unibo.es3;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.IntStream;

public class LogicsImpl implements Logics{

    final static int INITIAL_BUTTONS_LIMIT = 3;

    private Set<Point> activated = new HashSet<>();
    private int size;

    LogicsImpl(final int size){
        this.size = size;
        final Random random = new Random();
        while(activated.size() < INITIAL_BUTTONS_LIMIT){
            this.activated.add(new Point(random.nextInt(size), random.nextInt(size)));
        }
    }

    @Override
    public Set<Point> getActivated() {
        return Set.copyOf(this.activated);
    }

    @Override
    public void hitKey() {
        Set<Point> elaborated = new HashSet<>();
        IntStream.range(0, this.size)
                .boxed()
                .flatMap(x -> IntStream.range(0, size)
                .mapToObj(y -> new Point(x, y)))
                .filter(pnt1 -> this.activated.stream().anyMatch(pnt2 -> this.areNear(pnt1, pnt2)))
                .forEach(p -> elaborated.add(p));
        this.activated.addAll(elaborated);
    }

    private boolean areNear(final Point p1, final Point p2){
        return Math.abs(p1.x() - p2.x()) <= 1 && Math.abs(p1.y() - p2.y()) <= 1;
    }

    @Override
    public Boolean toQuit() {
        return this.activated.size() == this.size * this.size;
    }
    
}
