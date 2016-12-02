package ru.sbt.exchange.client;

import ru.sbt.exchange.domain.ExchangeEvent;

/**
 * Created by lyan on 02.12.16.
 */
public interface ExchangeEventHandler {
    void handle(ExchangeEvent event,
                Broker broker);
}
