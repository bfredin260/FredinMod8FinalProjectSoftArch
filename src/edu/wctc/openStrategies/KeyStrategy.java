package edu.wctc.openStrategies;

import edu.wctc.Player;

public class KeyStrategy implements OpenStrategy {
    @Override
    public double successRate() {
        return 1;
    }

    @Override
    public int success() {
        return 300;
    }

    @Override
    public int fail() {
        return 0;
    }
}
