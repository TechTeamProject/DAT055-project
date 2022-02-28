package src;

import javax.sound.sampled.*;

import static java.lang.Thread.sleep;

public class Sound {

    public float Sample = 8000f;

    public void playTone(int hz, int msecs, double vol)
            throws LineUnavailableException
    {
        byte[] buf = new byte[1];
        AudioFormat af =
                new AudioFormat(
                        Sample, // sampleRate
                        8,           // sampleSizeInBits
                        1,           // channels
                        true,        // signed
                        false);      // bigEndian
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();
        for (int i=0; i < msecs*8; i++) {
            double angle = i / (Sample / hz) * 2.0 * Math.PI;
            buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
            sdl.write(buf,0,1);
        }
        sdl.drain();
        sdl.stop();
        sdl.close();
    }

    public void playStartHost(){
        try{
            playTone(13000,100, 1);
            playTone(1000,100, 1);
            sleep(100);
        }catch(LineUnavailableException ex){

        }catch(InterruptedException ex){

        }

    }

    public void playConnected(){
        try{
            playTone(200,100, 1);
            playTone(400,100, 1);
            playTone(200,100, 1);
            sleep(100);
        }catch(LineUnavailableException ex){

        }catch(InterruptedException ex){

        }
    }

    public void playError(){
        try{
            playTone(300,100, 1);
            playTone(300,100, 1);
            sleep(100);
        }catch(LineUnavailableException ex){

        }catch(InterruptedException ex){

        }
    }
}