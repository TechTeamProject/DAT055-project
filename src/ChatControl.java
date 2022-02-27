package src;
import src.Server.ChatServer;
import src.View.ChatView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatControl {
    private static ChatView c;
    private ServerThread t;
    private ChatServer server;
    public ChatControl(ChatView v){
        c = v;

        c.addChatFieldListener(new chatListener());
        c.addIpFieldListener(new ipListener());
        c.addTopButtonsListener(new topButtonsListener());
    }
    public ChatControl(boolean fake){
        //Fake initializer for static values
    }

    public void printText(String text){
        c.printText(text);
    }

    private class chatListener implements KeyListener {
        public void keyPressed(KeyEvent ke){
            if(ke.getKeyCode()==KeyEvent.VK_ENTER){

                if (c.getFieldText().compareTo("") != 0) {
                    if(t.Alive()){
                        t.addNextMessage(c.getFieldText());
                        c.setFieldText("");
                    }
                    else{
                        c.printText("You are not connected to a server!");
                    }

                }
            }
        }
        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
    }
    private class ipListener implements KeyListener{
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                if (c.getIpText().compareTo("") != 0) {
                    if(!t.Alive()){
                        t = new ServerThread("AddUserClass", c.getIpText(), 23476);
                        c.switchMiddlePanel("ChatArea");
                    }
                    else{
                        c.switchMiddlePanel("ChatArea");
                        c.printText("Already connected");
                    }

                }
            }
        }
        @Override public void keyReleased (KeyEvent ke){}
        @Override public void keyTyped (KeyEvent ke){}
    }

    private class topButtonsListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String str = e.getActionCommand();
            switch (str) {
                case "Host server":
                    c.switchMiddlePanel("ChatArea");
                    server = new ChatServer(23476);
                    Thread serverT = new Thread(server);
                    serverT.start();
                    break;
                case "Connect to server":
                    c.switchMiddlePanel("IpArea");
                    break;
                case "Chat":
                    c.switchMiddlePanel("ChatArea");
                    break;
            }

        }
    }
}
