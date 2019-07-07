package yggdrasil.qa.product.game.utils;

public enum StatusCode {
    STATUS_CODE_200(200), STATUS_CODE_404(404), STATUS_CODE_500(500);

    int statusCode;

    StatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getCode() {
        return statusCode;
    }
}
