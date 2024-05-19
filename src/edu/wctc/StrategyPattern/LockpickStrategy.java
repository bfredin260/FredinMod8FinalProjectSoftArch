package edu.wctc.StrategyPattern;

// Strategy pattern
public class LockpickStrategy implements OpenStrategy {
    @Override
    public double successRate() {
        return .5;
    }

    @Override
    public int successRewardAmount() {
        return 150;
    }

    @Override
    public int failPunishmentAmount() {
        return -50;
    }

    @Override
    public String getStrategyName() {
        return "lockpick";
    }

    @Override
    public boolean successUsesItem() {
        return true;
    }

    @Override
    public boolean failUsesItem() {
        return true;
    }

    @Override
    public String itemToUse() {
        return "lockpick";
    }
}
