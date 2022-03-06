package src.Testing;

import org.junit.jupiter.api.Test;
import src.Model.Sound;

class SoundTest {

    @Test
    void playStartHost() {
        Sound.playStartHost();
    }

    @Test
    void playConnected() {
        Sound.playConnected();
    }

    @Test
    void playError() {
        Sound.playError();
    }
}