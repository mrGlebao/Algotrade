package ru.sbt.exchange.client;

import ru.sbt.exchange.domain.ExchangeEvent;
import ru.sbt.exchange.domain.Order;
import ru.sbt.exchange.domain.Portfolio;
import ru.sbt.exchange.domain.TopOrders;
import ru.sbt.exchange.domain.instrument.Instrument;
import ru.sbt.exchange.domain.instrument.Instruments;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static ru.sbt.exchange.domain.Direction.BUY;
import static ru.sbt.exchange.domain.Direction.SELL;
import static ru.sbt.exchange.domain.ExchangeEventType.NEW_PERIOD_START;
import static ru.sbt.exchange.domain.ExchangeEventType.ORDER_NEW;
import static ru.sbt.exchange.domain.Order.sell;
import static ru.sbt.exchange.domain.instrument.Instruments.supportedInstruments;
import static ru.sbt.exchange.domain.instrument.Instruments.zeroCouponBond;

public class MyAlgoStrategy implements AlgoStrategy {
    int counter = 0;

    @Override
    public void onEvent(ExchangeEvent event, Broker broker){




    }
}