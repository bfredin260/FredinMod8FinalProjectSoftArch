package edu.wctc.openStrategies;

import edu.wctc.Player;

// Strategy pattern
public interface OpenStrategy {
    double successRate();

    int success();
    int fail();
}
