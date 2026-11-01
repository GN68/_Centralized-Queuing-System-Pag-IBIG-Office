public class OnlineMonitor implements QueueObserver{
    private int displayedNumber = -1;

    public void update(int currentNumber) {
        displayedNumber = currentNumber;
    }

    public void display() {
        System.out.println(displayedNumber);
    }
}
