package src;
import src.Server.ChatServer;
import src.View.ChatView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ChatControl {
    private static ChatView chatView;
    private ServerThread serverThread;
    private ChatServer server;
    private Sound sound;
    public ChatControl(ChatView v){
        sound = new Sound();
        chatView = v;

        chatView.addChatFieldListener(new chatListener());
        chatView.addIpFieldListener(new ipListener());
        chatView.addTopButtonsListener(new topButtonsListener());

    }
    public ChatControl(boolean fake){
        //Fake initializer for static values
    }

    public void printText(String text){
        chatView.printText(text);
    }

    private class chatListener implements KeyListener {
        public void keyPressed(KeyEvent ke){
            if(ke.getKeyCode()==KeyEvent.VK_ENTER){

                if (chatView.getFieldText().compareTo("") != 0) {
                    if(serverThread.Alive()){
                        serverThread.addNextMessage(chatView.getFieldText());
                        chatView.setFieldText("");
                    }
                    else{
                        chatView.printText("You are not connected to a server!");
                        sound.playErrorSound();
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
                if (chatView.getIpText().compareTo("") != 0) {
                    if(!serverThread.Alive()){
                        serverThread = new ServerThread("AddUserClass", chatView.getIpText(), 23476);
                        chatView.switchMiddlePanel("ChatArea");
                    }
                    else{
                        chatView.switchMiddlePanel("ChatArea");
                        chatView.printText("Already connected");
                        sound.playErrorSound();
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
                    chatView.switchMiddlePanel("ChatArea");
                    server = new ChatServer(23476);
                    Thread serverT = new Thread(server);
                    serverT.start();
                    break;
                case "Connect to server":
                    chatView.switchMiddlePanel("IpArea");
                    break;
                case "Chat":
                    chatView.switchMiddlePanel("ChatArea");
                    break;
            }

        }
    }
}
