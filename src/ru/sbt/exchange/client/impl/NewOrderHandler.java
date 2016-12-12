package ru.sbt.exchange.client.impl;

import ru.sbt.exchange.client.Arima022;
import ru.sbt.exchange.client.Broker;
import ru.sbt.exchange.client.ExchangeEventHandler;
import ru.sbt.exchange.client.NewOrderSender;
import ru.sbt.exchange.domain.Direction;
import ru.sbt.exchange.domain.ExchangeEvent;

/**
 * Created by lyan on 02.12.16.
 */
public abstract class NewOrderHandler implements ExchangeEventHandler{

    protected Arima022 arima = new Arima022(0.3,0.3);

    public static final int BASE_QUANTITY = 500;

    protected NewOrderSender sender = new NewOrderSender();

    @Override
    public void handle(ExchangeEvent event,
                  Broker broker) {
    }
}
