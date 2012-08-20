package com.algolicious.blackscholes;

import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: pez
 * Date: 20/08/12
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */
public class BlackScholesBuilderTest {
    public static final String FB = "FB";
    public static final double SHARE_PRICE = 19.02;
    public static final double STRIKE_PRICE = 22.09;
    public static final double RISK_FREE = 0.1;
    public static final double VOL = 0.35;
    public static final double EXPIRE = 0.5;

    private BlackScholes.Builder builder;

    @Before
    public void setUp() throws Exception {
        builder = new BlackScholes.Builder();
    }

    @Test(expected = NullPointerException.class)
    public void testAssetNull() throws Exception {
        builder.asset(null).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalSharePrice_1() throws Exception {
        builder.asset(FB).sharePrice(0.0).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalSharePrice_2() throws Exception {
        builder.asset("FB").sharePrice(-1.0).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalStrikePrice_1() throws Exception {
        builder.asset(FB)
                .sharePrice(SHARE_PRICE)
                .strikePrice(0.0)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalStrikePrice_2() throws Exception {
        builder.asset(FB)
                .sharePrice(SHARE_PRICE)
                .strikePrice(-0.524)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalRiskFreeRate_1() throws Exception {
        builder.asset(FB)
                .sharePrice(SHARE_PRICE)
                .strikePrice(STRIKE_PRICE)
                .riskFreeRate(0.0)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalRiskFreeRate_2() throws Exception {
        builder.asset(FB)
                .sharePrice(SHARE_PRICE)
                .strikePrice(STRIKE_PRICE)
                .riskFreeRate(-1.0)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalVol_1() throws Exception {
        builder.asset(FB)
                .sharePrice(SHARE_PRICE)
                .strikePrice(STRIKE_PRICE)
                .riskFreeRate(RISK_FREE)
                .volatility(0.0)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalVol_2() throws Exception {
        builder.asset(FB)
                .sharePrice(SHARE_PRICE)
                .strikePrice(STRIKE_PRICE)
                .riskFreeRate(RISK_FREE)
                .volatility(-1.0)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalExp_1() throws Exception {
        builder.asset(FB)
                .sharePrice(SHARE_PRICE)
                .strikePrice(STRIKE_PRICE)
                .riskFreeRate(RISK_FREE)
                .volatility(VOL)
                .expiration(0.0)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalExp_2() throws Exception {
        builder.asset(FB)
                .sharePrice(SHARE_PRICE)
                .strikePrice(STRIKE_PRICE)
                .riskFreeRate(RISK_FREE)
                .volatility(VOL)
                .expiration(-1.0)
                .build();
    }
}
