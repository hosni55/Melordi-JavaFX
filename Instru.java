import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Instru {
    private int volume = 100; // Default MIDI volume (0-127)
    private Synthesizer synthetiseur;
    private MidiChannel canal;

    public Instru() {
        try {
            // Initialize synthesizer and open it
            synthetiseur = MidiSystem.getSynthesizer();
            synthetiseur.open();
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(Instru.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (synthetiseur != null) {
            canal = synthetiseur.getChannels()[0];
            // Initialize with Piano (program 0)
            canal.programChange(0);
        }
    }

    // Play note
    public void note_on(int note) {
        if (canal != null) {
            // Ensure volume is within MIDI range 0-127
            int vel = Math.min(127, Math.max(0, volume)); 
            canal.noteOn(note, vel); 
        }
    }

    // Stop note
    public void note_off(int note) {
        if (canal != null) {
            canal.noteOff(note);
        }
    }

    // Change instrument
    public void set_instrument(int instru) {
        if (canal != null) {
            canal.programChange(instru);
        }
    }
    
    public void setVolume(int vol) {
        this.volume = vol;
    }
    
    public int getVolume() {
        return this.volume;
    }
}
