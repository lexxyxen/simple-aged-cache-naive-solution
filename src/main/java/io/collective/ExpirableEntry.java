package io.collective;
import java.time.Clock;

public class ExpirableEntry<T> {
    private ExpirableEntry<T> nextNode;
    private T key;
    private T val;
    private Clock clock;
    private long expiryTimeInMills;

    public ExpirableEntry(T key, T val, Clock clock, int rentionTimeInMills) {
        this.nextNode = null;
        this.key = key;
        this.val = val;
        this.clock = clock;
        this.expiryTimeInMills=clock.millis() + rentionTimeInMills;
    }
    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }

    public long getExpiryTimeInMills() {
        return expiryTimeInMills;
    }

    public void setExpiryTimeInMills(long expiryTimeInMills) {
        this.expiryTimeInMills = expiryTimeInMills;
    }

    public ExpirableEntry<T> getNextNode() {
        return nextNode;
    }

    public void setNextNode(ExpirableEntry<T> nextNode) {
        this.nextNode = nextNode;
    }

    public boolean isExpired() {
        return clock.millis() > expiryTimeInMills;

    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (this.nextNode.getNextNode() != null){
            str.append("\nNext Node Key").append(this.getNextNode().getKey()).append("\nNext Node Value").append(this.getNextNode().getVal());
        }
        str.append("\nClock:").append(getClock()).append("\nExpiry time in milliseconds:").append(getExpiryTimeInMills());
        return str.toString();
    }
}
