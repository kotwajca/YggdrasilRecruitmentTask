package yggdrasil.qa.product.game.utils;

public enum GameStatus {
    PENDING("Pending"), FINISHED("Finished");

    String status;

    GameStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
