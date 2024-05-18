package edu.wctc.openStrategies;

import edu.wctc.Player;

public class KickDownStrategy implements OpenStrategy{
    @Override
    public double successRate() {
        return .8;
    }

    @Override
    public int success() {
        return 50;
    }

    @Override
    public int fail() {
        return -100;
    }
}
