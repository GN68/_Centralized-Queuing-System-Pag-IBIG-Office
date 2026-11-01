# Centralized Queuing System - Pag-IBIG Office

The Pag-ibig office implements a centralized queuing system for three help desk stations. Individuals visiting the office obtain a queue number from this centralized system. Additionally, there's an online monitoring system that displays the current queued number in real-time for individuals monitoring the queue remotely.

Each help desk station has an option to reset the queuing number based on an inputted number, allowing flexibility in managing the queue during specific situations, such as reorganization or technical issues. This queuing system ensures a single centralized queue number for all help desk stations, while the reset option offers control and adjustment in exceptional circumstances.

This approach aims to maintain an organized queuing process for individuals visiting the office and provides the flexibility to handle queue adjustments as needed.

## UML Class Diagram

```mermaid
classDiagram
    direction LR

    class QueueObserver {
        <<interface>>
        +update(currentNumber int) void
    }

    class QueueManager {
        -instance QueueManager$
        -lastIssuedNumber int
        -currentServingNumber int
        -observers List~QueueObserver~
        -QueueManager()
        +getInstance() QueueManager$
        +issueNumber() int
        +callNext(stationId int) int
        +resetQueue(newNumber int) void
        +getCurrentServingNumber() int
        +getLastIssuedNumber() int
        +addObserver(o QueueObserver) void
        +removeObserver(o QueueObserver) void
        -notifyObservers() void
    }

    class HelpDeskStation {
        -stationId int
        -stationName String
        -queueManager QueueManager
        +HelpDeskStation(stationId int, stationName String)
        +callNextCustomer() int
        +resetQueue(newNumber int) void
        +getStationId() int
        +getStationName() String
    }

    class QueueKiosk {
        -queueManager QueueManager
        +requestQueueNumber() int
    }

    class OnlineMonitor {
        -displayedNumber int
        +update(currentNumber int) void
        +display() void
    }

    class Main {
        +main(args String[]) void$
    }

    QueueObserver <|.. OnlineMonitor : implements
    QueueManager "1" o-- "0..*" QueueObserver : notifies
    HelpDeskStation "3" --> "1" QueueManager : uses
    QueueKiosk "1" --> "1" QueueManager : uses
    Main ..> QueueManager : creates
    Main ..> HelpDeskStation : creates
    Main ..> QueueKiosk : creates
    Main ..> OnlineMonitor : creates
```