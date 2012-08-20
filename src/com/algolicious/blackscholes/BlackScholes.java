package com.algolicious.blackscholes;

import java.util.Random;

/**
 *
 */
public final class BlackScholes {
    private final String asset;
    private final double sharePrice;
    private final double strikePrice;
    private final double riskFreeRate;
    private final double volatility;
    private final double expiration;
    private final double optionPrice;

    BlackScholes(final String asset,
                 final double sharePrice,
                 final double strikePrice,
                 final double riskFreeRate,
                 final double volatility,
                 final double expiration,
                 final double optionPrice) {
        this.asset = asset;
        this.sharePrice = sharePrice;
        this.strikePrice = strikePrice;
        this.riskFreeRate = riskFreeRate;
        this.volatility = volatility;
        this.expiration = expiration;
        this.optionPrice = optionPrice;
    }

    public String asset() {
        return asset;
    }

    public double sharePrice() {
        return sharePrice;
    }

    public double strikePrice() {
        return strikePrice;
    }

    public double riskFreeRate() {
        return riskFreeRate;
    }

    public double volatility() {
        return volatility;
    }

    public double expiration() {
        return expiration;
    }

    public double optionPrice() {
        return optionPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BlackScholes that = (BlackScholes) o;

        if (Double.compare(that.expiration, expiration) != 0) return false;
        if (Double.compare(that.riskFreeRate, riskFreeRate) != 0) return false;
        if (Double.compare(that.sharePrice, sharePrice) != 0) return false;
        if (Double.compare(that.strikePrice, strikePrice) != 0) return false;
        if (Double.compare(that.volatility, volatility) != 0) return false;
        if (!asset.equals(that.asset)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = asset.hashCode();
        temp = sharePrice != +0.0d ? Double.doubleToLongBits(sharePrice) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = strikePrice != +0.0d ? Double.doubleToLongBits(strikePrice) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = riskFreeRate != +0.0d ? Double.doubleToLongBits(riskFreeRate) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = volatility != +0.0d ? Double.doubleToLongBits(volatility) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = expiration != +0.0d ? Double.doubleToLongBits(expiration) : 0L;
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public static class Builder {
        private static final Random r = new Random();
        private String asset;
        private double sharePrice;
        private double strikePrice;
        private double riskFreeRate;
        private double vol;
        private double expiration;
        private double optionPrice;

        public Builder asset(final String asset) {
            this.asset = asset;
            return this;
        }

        public String getAsset() {
            return asset;
        }

        public double getSharePrice() {
            return sharePrice;
        }

        public double getStrikePrice() {
            return strikePrice;
        }

        public double getRiskFreeRate() {
            return riskFreeRate;
        }

        public double getVolatility() {
            return vol;
        }

        public double getExpiration() {
            return expiration;
        }

        public double getOptionPrice() {
            return optionPrice;
        }

        public Builder sharePrice(final double sharePrice) {
            this.sharePrice = sharePrice;
            return this;
        }

        public Builder strikePrice(final double strikePrice) {
            this.strikePrice = strikePrice;
            return this;
        }

        public Builder riskFreeRate(final double riskFreeRate) {
            this.riskFreeRate = riskFreeRate;
            return this;
        }

        public Builder volatility(final double volatility) {
            this.vol = volatility;
            return this;
        }

        public Builder expiration(final double expiration) {
            this.expiration = expiration;
            return this;
        }

        public BlackScholes build() {
            if (asset == null)
                throw new NullPointerException("asset is null");
            if (sharePrice <= 0.0)
                throw new IllegalArgumentException("sharePrice < 0.0");
            if (strikePrice <= 0.0)
                throw new IllegalArgumentException("strikePrice < 0.0");
            if (riskFreeRate <= 0.0)
                throw new IllegalArgumentException("riskFreeRate < 0.0");
            if (vol <= 0.0)
                throw new IllegalArgumentException("vol < 0.0");
            if (expiration <= 0.0)
                throw new IllegalArgumentException("expiration < 0.0");

            return new BlackScholes(
                    asset,
                    sharePrice,
                    strikePrice,
                    riskFreeRate,
                    vol,
                    expiration,
                    price());
        }

        private double price() {
            final int n = 10000;
            double sum = 0.0;
            double eps = 0.0;
            double price = 0.0;
            final double strikeVol = vol * vol * expiration;
            final double riskFreeYield = riskFreeRate * expiration;
            final double rootExpiration = Math.sqrt(expiration);
            for (int i = 0; i < n; i++) {
                eps = gaussian();
                price = sharePrice * Math.exp(riskFreeYield - 0.5 * strikeVol + vol * eps * rootExpiration);
                sum += Math.max(price - strikePrice, 0);
            }
            return Math.exp(-riskFreeRate * expiration) * (sum / n);
        }

        /**
         * Return a independent random gaussian number with a standard Gaussian distribution.
         */
        public static double gaussian() {
            // Uses Box-Muller transform algorithm
            double r, x, y;
            do {
                x = uniform(-1.0, 1.0);
                y = uniform(-1.0, 1.0);
                r = x*x + y*y;
            } while (r >= 1 || r == 0);
            return x * Math.sqrt(-2 * Math.log(r) / r);
        }

        /**
         * Return real number uniformly in [a, b).
         */
        public static double uniform(double a, double b) {
            return a + r.nextDouble() * (b-a);
        }

    }

}