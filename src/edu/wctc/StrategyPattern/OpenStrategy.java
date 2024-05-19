package edu.wctc.StrategyPattern;

// Strategy pattern
public interface OpenStrategy {
    double successRate();

    int successRewardAmount();
    int failPunishmentAmount();

    String getStrategyName();

    boolean successUsesItem();
    boolean failUsesItem();
    String itemToUse();
}
