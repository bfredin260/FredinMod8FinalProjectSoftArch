package edu.wctc.openStrategies;

import edu.wctc.Player;

public class LockpickStrategy implements OpenStrategy {
    @Override
    public double successRate() {
        return .5;
    }

    @Override
    public int success() {
        return 150;
    }

    @Override
    public int fail() {
        return -50;
    }
}
