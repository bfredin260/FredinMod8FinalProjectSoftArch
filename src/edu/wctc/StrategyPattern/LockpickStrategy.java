package edu.wctc.StrategyPattern;

// Strategy pattern
public class LockpickStrategy implements OpenStrategy {
    @Override
    public double successRate() {
        return .5;
    }

    @Override
    public int successPointReward() {
        return 150;
    }

    @Override
    public int failurePointDeduction() {
        return -50;
    }

    @Override
    public String strategyName() {
        return "lockpick";
    }

    @Override
    public boolean successConsumesItem() {
        return true;
    }

    @Override
    public boolean failureConsumesItem() {
        return true;
    }

    @Override
    public String itemToConsume() {
        return "lockpick";
    }
}
