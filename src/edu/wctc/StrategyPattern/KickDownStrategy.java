package edu.wctc.StrategyPattern;

// Strategy pattern
public class KickDownStrategy implements OpenStrategy{
    @Override
    public double successRate() {
        return .8;
    }

    @Override
    public int successPointReward() {
        return 50;
    }

    @Override
    public int failurePointDeduction() {
        return -100;
    }

    @Override
    public String strategyName() {
        return "kickdown";
    }

    @Override
    public boolean successConsumesItem() {
        return false;
    }

    @Override
    public boolean failureConsumesItem() {
        return false;
    }

    @Override
    public String itemToConsume() {
        return null;
    }
}
