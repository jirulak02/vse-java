package cz.vse.adventura.logika;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 *  Třída PrikazZazpivat implementuje pro hru příkaz zazpívat.
 *
 *  Příkaz umožňující si společně zazpívat krásnou písničku.
 *
 *  Tato třída je součástí jednoduché textové hry.
 *
 *@author     Jiří Šimeček
 *@version    Duben 2023
 */
public class PrikazZazpivat implements IPrikaz {
    private static final String NAZEV = "zazpívat";
    private Batoh batoh;

    public PrikazZazpivat(Batoh batoh) {
        this.batoh = batoh;
    }

    public void zazpivej() {
        try {
            System.out.println("Zapněte si zvuk.\n");

            // Open an audio input stream.
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    PrikazZazpivat.class.getResourceAsStream("../audio/Pisnicka.wav"));

            // Get a clip resource.
            Clip clip = AudioSystem.getClip();

            // Open audio clip and load samples from the audio input stream.
            clip.open(audioInputStream);

            // Start playing the sound clip.
            clip.start();

            // Wait for the sound clip to finish playing.
            Thread.sleep(19000);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        }
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (!batoh.hasVec("mikrofon")) {
            return "Ke zpěvu potřebujete mikrofon.";
        }

        String[] rickRoll = {
                "We’re no strangers to love",
                "You know the rules and so do I",
                "A full commitment’s what I’m thinking of",
                "You wouldn’t get this from any other guy",
                "I just wanna tell you how I’m feeling",
                "Gotta make you understand",
                "Never gonna give you up",
                "Never gonna let you down",
                "Never gonna run around and desert you",
                "Never gonna make you cry",
                "Never gonna say goodbye",
                "Never gonna tell a lie and hurt you",
        };

        zazpivej();

        for (String line : rickRoll) {
            System.out.println(line);

            try {
                Thread.sleep(3400);
            } catch (Exception e) {
                System.out.println("Error message: " + e.getMessage());
            }
        }

        return "\nDík, že jste si se mnou zazpívali.";
    }

    @Override
    public String getNazev() {
        return NAZEV;
    }
}