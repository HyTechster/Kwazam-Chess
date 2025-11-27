package services;

import java.io.*;
import javax.sound.sampled.*;

/**
 * Service class responsible for playing audio files
 */
public class AudioService {

    private static AudioService instance;

    // Private constructor for singleton pattern
    private AudioService() {
    }

    // Get singleton instance
    public static AudioService getInstance() {
        if (instance == null) {
            instance = new AudioService();
        }
        return instance;
    }

    // Method to play a sound file
    public void playSound(String soundFileName) {
        try {
            File soundFile = new File(soundFileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
