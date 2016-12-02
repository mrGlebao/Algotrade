package ru.sbt.exchange.client.impl;

import ru.sbt.exchange.client.Arima022;
import ru.sbt.exchange.client.Broker;
import ru.sbt.exchange.client.ExchangeEventHandler;
import ru.sbt.exchange.domain.Direction;
import ru.sbt.exchange.domain.ExchangeEvent;

/**
 * Created by lyan on 02.12.16.
 */
public abstract class NewOrderHandler implements ExchangeEventHandler{

    protected Arima022 predictor;
    protected double[] priceHistory;

    public static double SELL_THRESHOLD = 1; //
    // this is threshold for selling
    public static double BASE_QUANTITY = 5;

    @Override
    public void handle(ExchangeEvent event,
                  Broker broker) {
    }
}
