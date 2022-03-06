package src.Testing;

import org.testng.annotations.Test;
import src.View.WindowFrame;

class WindowFrameTest {
    WindowFrame a;
    @Test
    @org.junit.jupiter.api.Test
    void changePanel() {
        a = new WindowFrame();
        a.changePanel("chat");
    }
}