package no.uib.inf101.sem2.game.pokemon;

import javax.swing.*;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class PokemonGUI extends JFrame { // Nytt navn

    // Img links
    private static final String BACKGROUND_IMAGE_URL = "https://storage.ko-fi.com/cdn/useruploads/display/088aab6e-5910-4edd-82c5-9a1bca2b5e13_mdpokemonbattle_notextbox.png";
    private static final String PLAYER_GIF_URL = "https://raw.githubusercontent.com/Leonardo-Gomiero/PokeDuel/refs/heads/main/src/main/resources/Gifs/backElectivire.gif";
    private static final String OPPONENT_GIF_URL = "https://raw.githubusercontent.com/Leonardo-Gomiero/PokeDuel/refs/heads/main/src/main/resources/Gifs/Giratina.gif";

    // I do not own any of the images used. Some images are taken from
    // https://github.com/Leonardo-Gomiero/PokeDuel, as the images used fit my
    // project very well, and similiar photos/gifs are not easily available on other
    // sites.

    // Window size
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 800;

    // Pokemon position, only x/y pos

    private static final int PLAYER_X = 50;
    private static final int PLAYER_Y = 280;

    private static final int OPPONENT_X = 400;
    private static final int OPPONENT_Y = 100;

    public PokemonGUI() {
        setTitle("PokemonBattle SEM2 INF101 V25");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // background
        JLabel backgroundLabel = null;
        MediaTracker tracker = new MediaTracker(this);
        int backgroundId = 0; // ID for background img in tracker

        try {
            URL backgroundUrl = URI.create(BACKGROUND_IMAGE_URL).toURL(); // creating url for background img.
            ImageIcon bgIcon = new ImageIcon(backgroundUrl);

            tracker.addImage(bgIcon.getImage(), backgroundId);
            tracker.waitForID(backgroundId); // waiting for background image to load (if not, it will be blank)

            if (tracker.isErrorID(backgroundId)) {
                System.err.println("error during background loading");
                throw new Exception("MediaTracker error");
            }

            Image scaledBgImage = bgIcon.getImage().getScaledInstance(WINDOW_WIDTH, WINDOW_HEIGHT, Image.SCALE_SMOOTH);
            backgroundLabel = new JLabel(new ImageIcon(scaledBgImage));
            contentPane.add(backgroundLabel, BorderLayout.CENTER);

        } catch (URISyntaxException | MalformedURLException | IllegalArgumentException e) {
            System.err.println("could not create URL background: ");
            contentPane.setBackground(Color.GREEN);
        } catch (InterruptedException e) {
            System.err.println("Waiting for background aborted: ");
            Thread.currentThread().interrupt(); // Gjenopprett avbruddsstatus
            contentPane.setBackground(Color.GREEN);
        } catch (Exception e) {
            System.err.println("Unexpected error when waiting: ");
            contentPane.setBackground(Color.GREEN);
        }

        contentPane.setLayout(null);
        if (backgroundLabel != null) {
            backgroundLabel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        }

        // Electrivire /Pokemon1
        try {
            URL playerUrl = URI.create(PLAYER_GIF_URL).toURL();
            ImageIcon playerIcon = new ImageIcon(playerUrl);
            // wait for GIF to load
            int playerId = 1;
            tracker.addImage(playerIcon.getImage(), playerId);
            tracker.waitForID(playerId);
            if (tracker.isErrorID(playerId))
                throw new Exception("MediaTracker error");

            JLabel playerLabel = new JLabel(playerIcon);
            int actualW = playerIcon.getIconWidth();
            int actualH = playerIcon.getIconHeight();

            if (actualW <= 0 || actualH <= 0)
                throw new Exception("player gif invalid size");

            playerLabel.setBounds(PLAYER_X, PLAYER_Y, actualW, actualH);
            contentPane.add(playerLabel);

        } catch (Exception e) {
            System.err.println("couldnt load player-gif: " + e.getMessage());

        }

        // Opponent pokemon
        try {
            URL opponentUrl = URI.create(OPPONENT_GIF_URL).toURL();
            ImageIcon opponentIcon = new ImageIcon(opponentUrl);
            // Vent på motstander-GIF
            int opponentId = 2;
            tracker.addImage(opponentIcon.getImage(), opponentId);

            tracker.waitForID(opponentId);

            if (tracker.isErrorID(opponentId))
                throw new Exception("MediaTracker error");

            JLabel opponentLabel = new JLabel(opponentIcon);
            int actualW = opponentIcon.getIconWidth();
            int actualH = opponentIcon.getIconHeight();

            if (actualW <= 0 || actualH <= 0)
                throw new Exception("opponent gif error ");

            opponentLabel.setBounds(OPPONENT_X, OPPONENT_Y, actualW, actualH);
            contentPane.add(opponentLabel);

        } catch (Exception e) {
            System.err.println("Couldn't load opponent-GIF: ");
        }

        if (backgroundLabel != null) {
            contentPane.setComponentZOrder(backgroundLabel, contentPane.getComponentCount() - 1);

        }
        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PokemonGUI();
        });
    }
}