package edu.wctc.StrategyPattern;

// Strategy pattern
public class KickDownStrategy implements OpenStrategy{
    @Override
    public double successRate() {
        return .8;
    }

    @Override
    public int successRewardAmount() {
        return 50;
    }

    @Override
    public int failPunishmentAmount() {
        return -100;
    }

    @Override
    public String getStrategyName() {
        return "kickdown";
    }

    @Override
    public boolean successUsesItem() {
        return false;
    }

    @Override
    public boolean failUsesItem() {
        return false;
    }

    @Override
    public String itemToUse() {
        return null;
    }
}
