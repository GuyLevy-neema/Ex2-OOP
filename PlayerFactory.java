package player;
public class PlayerFactory {
    public Player build(String playerType) {
        return switch (playerType) {
            case "clever" -> new CleverPlayer();
            case "human" -> new HumanPlayer();
            case "whatever" -> new WhateverPlayer();
            default -> null;
        };
    }
}
