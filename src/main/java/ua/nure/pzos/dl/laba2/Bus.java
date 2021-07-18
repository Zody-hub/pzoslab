package ua.nure.pzos.dl.laba2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Bus extends Thread {
    private final String busName;
    private Station currentStation;
    private final Lock lock;
    private final Condition condition;
    private final Station destinationStation;
    private boolean isDestinationReached = false;
    private Station nextStation = null;

    public Bus(String busName, Station currentStation, Station destinationStation) {
        this.busName = busName;
        if (currentStation.getCurrentBusesCount() < currentStation.getMaxBusOnStation()) {
            this.currentStation = currentStation;
        } else {
            throw new IllegalArgumentException("Number of max buses in station is exceeded, please refactor input");
        }
        currentStation.addBus(this);
        this.destinationStation = destinationStation;
        setName("Thread" + busName);
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    public synchronized void moveToNextStation(Station nextStation) {
        if (nextStation.addBus(this)) {
            currentStation.removeBus(this);
            currentStation = nextStation;
            log.info("{} moved to station {}", busName, currentStation.getStationName());
            if (currentStation.getStationName().equals(destinationStation.getStationName())) {
                log.info("{} reached it's destination", busName);
                isDestinationReached = true;
            }
            notifyAll();
        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                log.warn(e.getMessage());
            }
        }
    }

    public void startMovement() {
        start();
    }

    @Override
    public synchronized void run() {

        log.info("{} started moving", busName);
        while (!isDestinationReached) {
            if (nextStation == null && !isDestinationReached) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    log.warn(e.getMessage());
                }
            }
            moveToNextStation(nextStation);
            nextStation = null;
        }
    }

    public void leave() throws InterruptedException {
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
        join();
    }

    public Station getCurrentStation() {
        return currentStation;
    }

    public boolean isDestinationReached() {
        return isDestinationReached;
    }

    public synchronized void setNextStation(Station nextStation) {
        this.nextStation = nextStation;
        notifyAll();
    }
}
