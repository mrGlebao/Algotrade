package ru.sbt.exchange.client;

import ru.sbt.exchange.domain.Direction;
import ru.sbt.exchange.domain.Order;
import ru.sbt.exchange.domain.instrument.Instrument;

/**
 * Created by lyan on 12.12.16.
 */
public class NewOrderSender {
    public void send(Instrument instrument,
                     int quantity,
                     Direction direction,
                     double price,
                     Broker broker){
        if (direction == Direction.SELL) {
            broker.addOrder(Order.sell(instrument).
                    withPrice(price).withQuantity(quantity).order());
        }else{
            broker.addOrder(Order.buy(instrument).
                    withPrice(price).withQuantity(quantity).order());
        }
    }
}
