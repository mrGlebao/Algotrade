package ru.sbt.exchange.client;

import ru.sbt.exchange.client.impl.BuyOrderHandler;
import ru.sbt.exchange.client.impl.SellOrderHandler;
import ru.sbt.exchange.domain.*;
import ru.sbt.exchange.domain.instrument.Instruments;

import java.util.Random;

import static java.lang.Math.pow;
import static ru.sbt.exchange.domain.Direction.SELL;
import static ru.sbt.exchange.domain.ExchangeEventType.NEW_PERIOD_START;
import static ru.sbt.exchange.domain.ExchangeEventType.ORDER_NEW;
import static ru.sbt.exchange.domain.Order.sell;
import static ru.sbt.exchange.domain.instrument.Instruments.zeroCouponBond;

public class ArimaStrategy implements AlgoStrategy {
    int counter = 0;

    /**
     * if delta < that this value
     * no operation is performed
     */
    public static final double IDLE_THRESHOLD = pow(10, -4);

    private static ExchangeEventHandler buyHandler =
            new BuyOrderHandler();

    private static ExchangeEventHandler sellHandler =
            new SellOrderHandler();

    @Override
    public void onEvent(ExchangeEvent event, Broker broker) {
        Direction direction = event.getOrder().getDirection();
        ExchangeEventType type = event.getExchangeEventType();

        if (type == ExchangeEventType.ORDER_NEW){
            switch (direction){
                case SELL:
                    sellHandler.handle(event,broker);
                    break;
                case BUY:
                    buyHandler.handle(event, broker);
                    break;
                default:

            }
        }
    }
}