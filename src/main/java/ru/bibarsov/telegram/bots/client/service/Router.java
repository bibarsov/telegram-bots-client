package ru.bibarsov.telegram.bots.client.service;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ParametersAreNonnullByDefault
public class Router {

    private final List<ExecutorService> services;
    private final int size;

    public Router(int size) {
        this.services = new ArrayList<>(size);
        this.size = size;
        for (int i = 0; i < size; i++) {
            this.services.add(Executors.newSingleThreadExecutor());
        }
    }

    public void route(long id, Runnable runnable) {
        services.get((int) (id % size)).execute(runnable);
    }
}
