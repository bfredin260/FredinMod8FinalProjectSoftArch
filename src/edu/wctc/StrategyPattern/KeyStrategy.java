package edu.wctc.StrategyPattern;

// Strategy pattern
public class KeyStrategy implements OpenStrategy {
    @Override
    public double successRate() {
        return 1;
    }

    @Override
    public int successRewardAmount() {
        return 300;
    }

    @Override
    public int failPunishmentAmount() {
        return 0;
    }

    @Override
    public String getStrategyName() {
        return "key";
    }

    @Override
    public boolean successUsesItem() {
        return true;
    }

    @Override
    public boolean failUsesItem() {
        return false;
    }

    @Override
    public String itemToUse() {
        return "key";
    }
}
