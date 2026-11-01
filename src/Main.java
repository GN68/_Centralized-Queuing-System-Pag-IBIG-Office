/**
 * Simple demo for the Pag-IBIG Centralized Queuing System.
 * Put this in the same folder as your other classes.
 */
public class Main {

    public static void main(String[] args) {
        QueueManager manager = QueueManager.getInstance();

        HelpDeskStation desk1 = new HelpDeskStation(1, "Help Desk 1");
        HelpDeskStation desk2 = new HelpDeskStation(2, "Help Desk 2");
        HelpDeskStation desk3 = new HelpDeskStation(3, "Help Desk 3");
        QueueKiosk kiosk = new QueueKiosk();
        OnlineMonitor monitor = new OnlineMonitor();
        manager.addObserver(monitor);

        // Visitors get queue numbers
        System.out.println("Visitors get numbers: "
                + kiosk.requestQueueNumber() + ", "
                + kiosk.requestQueueNumber() + ", "
                + kiosk.requestQueueNumber());

        // All 3 desks use the same queue
        System.out.println("Desk 1 calls #" + desk1.callNextCustomer());
        System.out.println("Desk 2 calls #" + desk2.callNextCustomer());
        System.out.println("Desk 3 calls #" + desk3.callNextCustomer());
        monitor.display();

        // Reset the queue from a desk
        System.out.println("Desk 2 resets queue to 50");
        desk2.resetQueue(50);
        monitor.display();
        System.out.println("Next visitor gets #" + kiosk.requestQueueNumber());
    }
}