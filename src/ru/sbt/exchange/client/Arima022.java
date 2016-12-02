package ru.sbt.exchange.client;

import java.util.Random;

/**
 * Created by lyan on 02.12.16.
 */
public class Arima022 {

    Random random; // this is for
    // random gaussian noise

    private double t1, t2; // these are prices on t-1 and
    // t-2 time respectively

    private double randT1, randT2, randT;
    // these are random coefficients recieved
    // from random

    private double alpha, beta; // these are coefficients
    // alpha is smoothing factor
    // beta is trend smoothing factor

    public Arima022(double alpha, double beta){

        this.alpha = alpha;
        this.beta = beta;

        this.random = new Random(System.currentTimeMillis());
    }

    public void fit(double t1, double t2){
        this.t1 = t1;
        this.t2 = t2;
        this.randT2 = this.randT1;
        this.randT1 = randT;
        this.randT = random.nextGaussian();

    }


    public double predict(){
        return 2.0 * t1 - t2 +
                (alpha + beta - 2) * randT1 +
                (1-alpha) * randT2 +
                randT;
    }
}
