package src.Model;

import javax.sound.sampled.*;

import static java.lang.Thread.sleep;
/**
 * This class is used to create sound for the chat.
 *
 * @author  Oliver Brottare
 * @version 1.0
 * @since   2022-03-06
 */
public class Sound {

    /**
     * Used to play a tone. The parameters are used to create different tones.
     * @param hz The hz of the tone.
     * @param vol The volume of the tone played.
     * @throws LineUnavailableException This error can be thrown if the line is already in used.
     */
    private static void playTone(int hz, double vol)
            throws LineUnavailableException
    {
        byte[] buf = new byte[1];
        float sample = 8000f;
        AudioFormat af =
                new AudioFormat(
                        sample, // sampleRate
                        8,           // sampleSizeInBits
                        1,           // channels
                        true,        // signed
                        false);      // bigEndian
        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();
        for (int i = 0; i < 100 *8; i++) {
            double angle = i / (sample / hz) * 2.0 * Math.PI;
            buf[0] = (byte)(Math.sin(angle) * 127.0 * vol);
            sdl.write(buf,0,1);
        }
        sdl.drain();
        sdl.stop();
        sdl.close();
    }

    /**
     * A method with a pre-made set of tones. Used to simulate a hosting server sound.
     */
    public static void playStartHost(){
        try{
            playTone(13000, 1);
            playTone(1000, 1);
            sleep(100);
        }catch(LineUnavailableException | InterruptedException ex){
            ex.printStackTrace();
        }

    }

    /**
     * A method with a pre-made set of tones. Used to simulate a successful connection sound.
     */
    public static void playConnected(){
        try{
            playTone(200, 1);
            playTone(400, 1);
            playTone(200, 1);
            sleep(100);
        }catch(LineUnavailableException | InterruptedException ex){
            ex.printStackTrace();
        }
    }

    /**
     * A method with a pre-made set of tones. Used to simulate an error occurring.
     */
    public static void playError(){
        try{
            playTone(300, 1);
            playTone(300, 1);
            sleep(100);
        }catch(LineUnavailableException | InterruptedException ex){
            ex.printStackTrace();

        }
    }
}