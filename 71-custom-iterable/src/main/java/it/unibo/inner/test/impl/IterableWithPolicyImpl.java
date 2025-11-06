package it.unibo.inner.test.impl;


import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T>{

    private T[] elements;
    private Predicate<T> predicate;

    public IterableWithPolicyImpl(T[] elements)
    {
        this(elements, x -> true);
    }

    public IterableWithPolicyImpl(T[] elements, Predicate<T> predicate)
    {
        this.elements = elements;
        this.predicate = predicate;
    }



    @Override
    public void setIterationPolicy(Predicate<T> filter) {
        this.predicate = filter;
    }


    private class MyIterator implements java.util.Iterator<T>
    {
        private int current;
        private final int stop;

        public MyIterator(int current, int stop) {
            this.current = current;
            this.stop = stop;
        }

        @Override
        public boolean hasNext() {
            var c = this.current;
            while (c <= stop) {
                if(predicate.test(elements[c++])) return true;
            }
            return false;
        }

        @Override
        public T next() {
            while (current <= stop && !predicate.test(elements[current++]));
            return elements[current - 1];
        }

    }


    @Override
    public MyIterator iterator() {
        return new MyIterator(0, elements.length - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        var it = this.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
}
