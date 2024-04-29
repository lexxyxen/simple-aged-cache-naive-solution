package io.collective;
import java.time.Clock;

public class SimpleAgedCache {
    private ExpirableEntry headNode;
    private ExpirableEntry tailNode;
    private Clock clock;
    private int counter = 0;

    public SimpleAgedCache(Clock clock) {
        this.headNode = null;
        this.tailNode = null;
        this.clock = clock;
    }

    public SimpleAgedCache() {
        this(Clock.systemUTC());
    }

    public void put(Object key, Object value, int retentionInMillis) {
        ExpirableEntry eeObj = new ExpirableEntry(key, value, this.clock, retentionInMillis);

        if (this.headNode == null) {
            this.headNode = eeObj;
        } else {
            this.tailNode.setNextNode(eeObj);
        }

        this.tailNode = eeObj;
    }

    public boolean isEmpty() {
        return this.headNode == null;
    }

    public int size() {
        for (ExpirableEntry currentNode = this.headNode; currentNode != null; currentNode = currentNode.getNextNode()) {
            if (!currentNode.isExpired()) {
                this.counter++;
            }
        }
        return this.counter;
    }

    public Object get(Object key) {
        for (ExpirableEntry currentNode = this.headNode; currentNode != null; currentNode = currentNode.getNextNode()) {
            if (key.equals(currentNode.getKey()) && !currentNode.isExpired()) {
                return currentNode.getVal();
            }
        }
        return null;
    }
}