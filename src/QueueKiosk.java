public class QueueKiosk {
    private final QueueManager queueManager;

    public QueueKiosk() {
        queueManager = QueueManager.getInstance();
    }

    public int requestQueueNumber() {
        return queueManager.issueNumber();
    }
}
