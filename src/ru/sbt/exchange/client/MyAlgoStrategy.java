package ru.sbt.exchange.client;

import ru.sbt.exchange.domain.ExchangeEvent;
import ru.sbt.exchange.domain.Order;
import ru.sbt.exchange.domain.Portfolio;
import ru.sbt.exchange.domain.TopOrders;
import ru.sbt.exchange.domain.instrument.Instruments;

import java.util.Random;

import static ru.sbt.exchange.domain.Direction.SELL;
import static ru.sbt.exchange.domain.ExchangeEventType.NEW_PERIOD_START;
import static ru.sbt.exchange.domain.ExchangeEventType.ORDER_NEW;
import static ru.sbt.exchange.domain.Order.sell;
import static ru.sbt.exchange.domain.instrument.Instruments.zeroCouponBond;

public class MyAlgoStrategy implements AlgoStrategy {
    int counter = 0;

    @Override
    public void onEvent(ExchangeEvent event, Broker broker) {

        if (event.getExchangeEventType() == ORDER_NEW) {
            Order order = event.getOrder();
            if (order.getInstrument().equals(Instruments.fixedCouponBond())
                    && order.getDirection() == SELL
                    && order.getPrice() < 50) {
                Order buyOrder = order.opposite().withQuantity(order.getQuantity() / 2);
                broker.addOrder(buyOrder);
            }
        }

        if (event.getExchangeEventType() == NEW_PERIOD_START) {
            Portfolio myPortfolio = broker.getMyPortfolio();
            int count = myPortfolio.getCountByInstrument().get(zeroCouponBond());
            if (count > 0) {
                TopOrders topOrders = broker.getTopOrders(zeroCouponBond());
                double price = topOrders.getSellOrders().isEmpty() ? 50 : topOrders.getSellOrders().get(0).getPrice() - 0.1;
                broker.addOrder(sell(zeroCouponBond()).withPrice(price).withQuantity(count).order());
            }
        }

    }
}