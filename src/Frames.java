package src;

import src.View.ChatView;

import javax.swing.*;

public class Frames {
    private static ChatView View;
    private static ChatControl Control;
    public Frames(){

        JFrame Frame = new JFrame();
        View = new ChatView();
        Frame.add(View);
        Control = new ChatControl(View);
        Frame.setTitle("ChatView");
        Frame.setSize(400, 400);
        Frame.setLocation(200, 200);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setVisible(true);

    }
    public static void main(String[] args){

        new Frames();
    }
}
