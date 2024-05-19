package edu.wctc.StrategyPattern;

// Strategy pattern
public interface OpenStrategy {
    double successRate();

    int successPointReward();
    int failurePointDeduction();

    String strategyName();

    boolean successConsumesItem();
    boolean failureConsumesItem();
    String itemToConsume();
}
