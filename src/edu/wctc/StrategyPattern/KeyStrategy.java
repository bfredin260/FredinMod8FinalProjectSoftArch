package edu.wctc.StrategyPattern;

// Strategy pattern
public class KeyStrategy implements OpenStrategy {
    @Override
    public double successRate() {
        return 1;
    }

    @Override
    public int successPointReward() {
        return 300;
    }

    @Override
    public int failurePointDeduction() {
        return 0;
    }

    @Override
    public String strategyName() {
        return "key";
    }

    @Override
    public boolean successConsumesItem() {
        return true;
    }

    @Override
    public boolean failureConsumesItem() {
        return false;
    }

    @Override
    public String itemToConsume() {
        return "key";
    }
}
