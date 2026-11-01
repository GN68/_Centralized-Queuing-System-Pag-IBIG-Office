public class HelpDeskStation {

    private int stationId;
    private String stationName;
    private QueueManager queueManager;

    public HelpDeskStation(int stationId, String stationName) {
        this.stationId = stationId;
        this.stationName = stationName;
        this.queueManager = QueueManager.getInstance();
    }

    public int callNextCustomer() {
        return queueManager.callNext(stationId);
    }

    public void resetQueue(int newNumber) {
        queueManager.resetQueue(newNumber);
    }

    public int getStationId() {
        return stationId;
    }

    public String getStationName() {
        return stationName;
    }
}