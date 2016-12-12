package ru.sbt.exchange.client.impl;

import ru.sbt.exchange.client.Arima022;
import ru.sbt.exchange.client.ArimaStrategy;
import ru.sbt.exchange.client.Broker;
import ru.sbt.exchange.domain.ExchangeEvent;
import ru.sbt.exchange.domain.Order;
import ru.sbt.exchange.domain.instrument.Instrument;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;
import static ru.sbt.exchange.domain.Direction.BUY;
import static ru.sbt.exchange.domain.Direction.SELL;
import static ru.sbt.exchange.domain.instrument.Instruments.supportedInstruments;

/**
 * if we recieved sell order we check
 *
 * Created by lyan on 02.12.16.
 */
public class SellOrderHandler extends NewOrderHandler {

    public SellOrderHandler(){
        this.arima = new Arima022(0.3, 0.3);
    }

    @Override
    public void handle(ExchangeEvent event,
                       Broker broker) {
        List<Double> predictions = new ArrayList<>(),
                currentPrice = new ArrayList<>();


        for(Instrument i:supportedInstruments()){
            Double[] lastPrices = (Double[]) broker.getTopOrders(i).getSellOrders()
                    .stream()
                    .limit(2).collect(Collectors.toList()).toArray();

            arima.fit(lastPrices[0], lastPrices[1]);
            predictions.add(arima.predict());

            currentPrice.add(event.getOrder().getPrice());
        }

        for(int i = 0; i<predictions.size(); i++){
            final double pred = predictions.get(i),
                    cur = currentPrice.get(i);

            if (cur > pred){
                sender.send(supportedInstruments().get(i),
                        BASE_QUANTITY,
                        SELL,
                        cur * 1.3,
                        broker);
            }
        }
    }
}
