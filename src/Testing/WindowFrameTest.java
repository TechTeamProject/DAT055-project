package src.Testing;

import org.junit.jupiter.api.Test;
import src.View.WindowFrame;

class WindowFrameTest {
    WindowFrame a;
    @Test
    void changePanel() {
        a = new WindowFrame();
        a.changePanel("chat");
    }
}