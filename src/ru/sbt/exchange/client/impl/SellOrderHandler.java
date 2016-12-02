package ru.sbt.exchange.client.impl;

import ru.sbt.exchange.client.Arima022;
import ru.sbt.exchange.client.ArimaStrategy;
import ru.sbt.exchange.client.Broker;
import ru.sbt.exchange.client.ExchangeEventHandler;
import ru.sbt.exchange.domain.ExchangeEvent;
import ru.sbt.exchange.domain.Order;

import static java.lang.Math.abs;

/**
 * if we recieved sell order we check
 *
 * Created by lyan on 02.12.16.
 */
public class SellOrderHandler extends NewOrderHandler {

    public SellOrderHandler(){
        this.priceHistory = new double[]{0, 0};
        this.predictor = new Arima022(0.3, 0.3);
    }

    @Override
    public void handle(ExchangeEvent event,
                       Broker broker) {
        predictor.fit(priceHistory[0], priceHistory[1]);
        double newPrice = predictor.predict(),
                delta = (priceHistory[0] - newPrice);

        if (delta > ArimaStrategy.IDLE_THRESHOLD) {
            if (delta > 0 ){ // sell here
                broker.addOrder(
                        Order.sell(event.getOrder().getInstrument())
                                .withPrice(newPrice)
                                .withQuantity(5)
                                .order());
            }else{ // buy here
                broker.addOrder(
                        Order.buy(event.getOrder().getInstrument())
                                .withPrice(newPrice)
                                .withQuantity(5)
                                .order());
            }
        }
    }
}
