package com.lousydev.simplecanisters.util;

/**
 * @author UpcraftLP
 */
public enum HeartType {
    RED(10),
    ORANGE(20),
    GREEN(30),
    BLUE(40);

    public final int healAmount;

    HeartType(int healAmount) {
        this.healAmount = healAmount;
    }
}
