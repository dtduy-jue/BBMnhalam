package sound;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Sound {

    private Media media;
    private MediaPlayer mediaPlayer;

    public Sound(String name, boolean loop) {
        media = new Media(new File(name).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        if (loop) {
            mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    mediaPlayer.seek(Duration.ZERO);
                }
            });
        }
    }

    public void playSound() {
        mediaPlayer.play();
    }

    public void playSound(double volume) {
        playSound();
        setVolume(volume);
    }

    public void setVolume(double volume) {
        mediaPlayer.setVolume(volume);
    }

    public void stopSound() {
        mediaPlayer.stop();
    }

    public void pause() {
        mediaPlayer.pause();
    }

    public void resume() {
        mediaPlayer.play();
    }

    public boolean isPlaying() {
        if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) return true;
        return false;
    }
}
