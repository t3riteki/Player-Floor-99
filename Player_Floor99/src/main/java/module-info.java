module player.game.player_floor99 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens player.game.player_floor99 to javafx.fxml;
    exports player.game.player_floor99;
}