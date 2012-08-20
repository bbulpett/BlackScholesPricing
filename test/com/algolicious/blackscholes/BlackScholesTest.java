package com.algolicious.blackscholes;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: pez
 * Date: 20/08/12
 * Time: 11:40
 * To change this template use File | Settings | File Templates.
 */
public class BlackScholesTest {
    final double error = 0.809;
    BlackScholes.Builder builder;
    BlackScholes model;

    @Before
    public void setUp() throws Exception {
        builder = new BlackScholes.Builder();
        model = null;
    }

    @Test
    public void testFieldsPopulated() throws Exception {
        final String fb = "FB";
        final double sharePrice = 19.27;
        final double strikePrice = 23.0;
        final double riskFreeRate = 0.002;
        final double vol = 0.48;
        final double time = 0.5;
        model = builder
                .asset(fb)
                .sharePrice(sharePrice)
                .strikePrice(strikePrice)
                .riskFreeRate(riskFreeRate)
                .volatility(vol)
                .expiration(time)
                .build();

        assertEquals(fb, model.asset());
        assertEquals(sharePrice, model.sharePrice());
        assertEquals(strikePrice, model.strikePrice());
        assertEquals(riskFreeRate, model.riskFreeRate());
        assertEquals(vol, model.volatility());
        assertEquals(time, model.expiration());
    }

    @Test
    public void testMSFT() throws Exception {
        model = builder
                .asset("MSFT")
                .sharePrice(23.75)
                .strikePrice(15.00)
                .riskFreeRate(0.01)
                .volatility(0.35)
                .expiration(0.5)
                .build();

        assertEquals(9.10, model.optionPrice(), error);
    }

    @Test
    public void testGOOG() throws Exception {
        model = builder
                .asset("GOOG")
                .sharePrice(30.14)
                .strikePrice(15.0)
                .riskFreeRate(0.01)
                .volatility(0.332)
                .expiration(0.25)
                .build();

        assertEquals(14.5, model.optionPrice(), error);
    }
}
