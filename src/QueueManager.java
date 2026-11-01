import java.util.ArrayList;
import java.util.List;

public class QueueManager {
    private static QueueManager instance;

    private int lastIssuedNumber;
    private int currentServingNumber;
    private List<QueueObserver> observers = new ArrayList<>();


    private QueueManager() {
        lastIssuedNumber = 0;
        currentServingNumber = 0;
    }

    public static QueueManager getInstance() {
        if (instance == null) {
            instance = new QueueManager();
        }
        return instance;
    }


    public synchronized int issueNumber() {
        lastIssuedNumber++;
        return lastIssuedNumber;
    }

    public synchronized int callNext(int stationId) {
        if (currentServingNumber >= lastIssuedNumber) {
            return -1;
        }
        currentServingNumber++;
        notifyObservers();
        return currentServingNumber;
    }

    public synchronized void resetQueue(int newNumber) {
        if (newNumber < 0) {
            throw new IllegalArgumentException("Queue number cannot be negative.");
        }
        currentServingNumber = newNumber;
        lastIssuedNumber = newNumber;
        notifyObservers();
    }

    public int getCurrentServingNumber() {
        return currentServingNumber;
    }

    public int getLastIssuedNumber() {
        return lastIssuedNumber;
    }

    public void addObserver(QueueObserver o) {
        observers.add(o);
        o.update(currentServingNumber);
    }

    public void removeObserver(QueueObserver o) {
        observers.remove(o);
    }
    private void notifyObservers() {
        for (QueueObserver o : observers) {
            o.update(currentServingNumber);
        }
    }
}