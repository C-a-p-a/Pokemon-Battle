package no.uib.inf101.sem2.game.pokemon;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

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
    private static final int WINDOW_HEIGHT = 600;

    // sprite sizes
    private final int Sprite_Target_Width = 200;
    private final int Sprite_Target_Height = 200;

    // pokemon font
    private Font pokemonFont = null;

    // information boxes locations
    private final int INFO_Width = 200;
    private final int INFO_Height = 40;
    private final int PLAYER_HP_X = PLAYER_X - 40;
    private final int PLAYER_XP_Y = PLAYER_Y + Sprite_Target_Height + 5;
    private final int OPPONENT_HP_X = OPPONENT_X - 40;
    private final int OPPONENT_HP_Y = OPPONENT_Y - INFO_Height - 5;

    private final int TEXTBOX_X = WINDOW_WIDTH - 330;
    private final int TEXTBOX_Y = 30;
    private final int TEXTBOX_W = 300;
    private final int TEXTBOX_H = 100;

    // gui components
    private JLabel playerSpriteLabel;
    private JLabel opponentSpriteLabel;
    private JLabel backgroundLabel;

    private JLabel playerNameLabel;
    private JProgressBar playerHpBar;
    private JLabel opponentNameLabel;
    private JProgressBar opponentHpBar;

    private JTextArea textArea;
    private JScrollPane messageScrollPane;

    // Pokemon position, only x/y pos

    private static final int PLAYER_X = 85;
    private static final int PLAYER_Y = 320;

    private static final int OPPONENT_X = 520;
    private static final int OPPONENT_Y = 150;

    public PokemonGUI() {
        setTitle("PokemonBattle SEM2 INF101 V25");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        pokemonFont = loadCustomFont("/pokemon_pixel_font.ttf", 14f);
        if (pokemonFont == null) {
            pokemonFont = new Font("Monospaced", Font.PLAIN, 12);
        }

        JLayeredPane layeredPane = new JLayeredPane();
        // setContentPane(layeredPane); // Sett som content pane til slutt

        // background
        backgroundLabel = createBackgroundLabel();
        if (backgroundLabel != null) {
            backgroundLabel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
            layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);
        } else {
            layeredPane.setOpaque(true);
            layeredPane.setBackground(Color.DARK_GRAY);
        }

        // player sprites
        playerSpriteLabel = createGifLabel(PLAYER_GIF_URL, "Player", Sprite_Target_Width, Sprite_Target_Height);
        opponentSpriteLabel = createGifLabel(OPPONENT_GIF_URL, "Opponent", Sprite_Target_Width, Sprite_Target_Height);

        if (playerSpriteLabel != null) {
            int width = playerSpriteLabel.getIcon().getIconWidth();
            int height = playerSpriteLabel.getIcon().getIconHeight();
            playerSpriteLabel.setBounds(PLAYER_X, PLAYER_Y, width, height);
            layeredPane.add(playerSpriteLabel, JLayeredPane.PALETTE_LAYER);
        }
        if (opponentSpriteLabel != null) {
            int width = opponentSpriteLabel.getIcon().getIconWidth();
            int height = opponentSpriteLabel.getIcon().getIconHeight();
            opponentSpriteLabel.setBounds(OPPONENT_X, OPPONENT_Y, width, height);
            layeredPane.add(opponentSpriteLabel, JLayeredPane.PALETTE_LAYER);
        }

        // player information textlabel
        playerNameLabel = new JLabel("Player");
        playerNameLabel.setBounds(PLAYER_HP_X + 75, PLAYER_XP_Y - 260, INFO_Width, 20);
        playerNameLabel.setForeground(Color.BLUE);
        layeredPane.add(playerNameLabel, JLayeredPane.MODAL_LAYER);

        // player hp bar
        playerHpBar = new JProgressBar(0, 100);
        playerHpBar.setValue(100);
        playerHpBar.setStringPainted(true);
        playerHpBar.setForeground(Color.GREEN);
        playerHpBar.setBackground(Color.GRAY);
        playerHpBar.setBounds(PLAYER_HP_X + 75, PLAYER_XP_Y - 230, INFO_Width, 25);
        layeredPane.add(playerHpBar, JLayeredPane.MODAL_LAYER);

        // opponent information textlabel
        opponentNameLabel = new JLabel("Opponent");
        opponentNameLabel.setBounds(OPPONENT_HP_X + 60, OPPONENT_HP_Y + 30, INFO_Width, 20);
        opponentNameLabel.setForeground(Color.RED);
        layeredPane.add(opponentNameLabel, JLayeredPane.MODAL_LAYER);

        // opponent hp bar
        opponentHpBar = new JProgressBar(0, 100);
        opponentHpBar.setValue(100);
        opponentHpBar.setStringPainted(true);
        opponentHpBar.setForeground(Color.GREEN);
        opponentHpBar.setBackground(Color.GRAY);
        opponentHpBar.setBounds(OPPONENT_HP_X + 20, OPPONENT_HP_Y + 45, INFO_Width, 20);
        layeredPane.add(opponentHpBar, JLayeredPane.MODAL_LAYER);

        // text area
        textArea = new JTextArea(5, 30);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(pokemonFont);

        messageScrollPane = new JScrollPane(textArea);
        messageScrollPane.setBounds(TEXTBOX_X, TEXTBOX_Y, TEXTBOX_W, TEXTBOX_H);
        layeredPane.add(messageScrollPane, JLayeredPane.MODAL_LAYER);

        setContentPane(layeredPane);
        setFocusable(true);
        requestFocusInWindow();
        setVisible(true);

    }

    /**
     * In this method, I got assistance from AI in order to load the original
     * Pokemon font correctly.
     * 
     * @param path
     * @param size
     * @return
     */
    private Font loadCustomFont(String path, float size) {
        try {
            InputStream fontStream = PokemonGUI.class.getResourceAsStream(path);

            if (fontStream == null) {
                System.out.println("error, couldnt find font file");
                return null;
            }

            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            fontStream.close();

            return customFont.deriveFont(size);
        } catch (IOException e) {
            System.out.println("error, couldnt read font file");
            return null;
        } catch (FontFormatException e) {
            System.out.println("error, illegal font format");
            return null;
        }
    }

    private JLabel createBackgroundLabel() {
        try {
            URL bgUrl = URI.create(BACKGROUND_IMAGE_URL).toURL();
            ImageIcon bgIcon = new ImageIcon(bgUrl);
            MediaTracker tracker = new MediaTracker(this);
            tracker.addImage(bgIcon.getImage(), 0);
            tracker.waitForID(0);
            Image scaledBgImage = bgIcon.getImage().getScaledInstance(WINDOW_WIDTH, WINDOW_HEIGHT, Image.SCALE_SMOOTH);
            return new JLabel(new ImageIcon(scaledBgImage));
        } catch (Exception e) {
            System.out.println("couldnt load background.");
            return null;
        }
    }

    private JLabel createGifLabel(String urlString, String description, int targetWidth, int targetHeight) {
        try {
            URL gifUrl = URI.create(urlString).toURL();
            ImageIcon icon = new ImageIcon(gifUrl);

            MediaTracker tracker = new MediaTracker(this);
            Image originalImage = icon.getImage();
            tracker.addImage(icon.getImage(), 1);
            tracker.waitForID(1);

            Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);

            return new JLabel(scaledIcon);
        } catch (Exception e) {
            System.out.println("error couldnt load gif");
            return null;
        }
    }

    public void updateStatus(String player1Name, int p1HP, int p1MaxHp, String p2Name, int p2Hp, int p2MaxHp) {
        SwingUtilities.invokeLater(() -> {
            playerNameLabel.setText(player1Name);
            playerHpBar.setMaximum(p1MaxHp);
            playerHpBar.setValue(p1HP);
            updateHpBarColor(playerHpBar);
            playerHpBar.setString(p1HP + "/" + p1MaxHp);

            opponentNameLabel.setText(p2Name);
            opponentHpBar.setMaximum(p2MaxHp);
            opponentHpBar.setValue(p2Hp);
            updateHpBarColor(opponentHpBar);
            opponentHpBar.setString(p2Hp + "/" + p2MaxHp);
        });
    }

    private void updateHpBarColor(JProgressBar hpBar) {
        double percentage = (double) hpBar.getValue() / hpBar.getMaximum();
        if (percentage > 0.5)
            hpBar.setForeground(Color.GREEN);
        else if (percentage > 0.2)
            hpBar.setForeground(Color.YELLOW);
        else
            hpBar.setForeground(Color.RED);
    }

    public void addMessage(String message) {
        SwingUtilities.invokeLater(() -> {
            textArea.append(message + "\n");
            textArea.setCaretPosition(textArea.getDocument().getLength());
        });
    }

    public void showWinner(String winnerMessage) {
        SwingUtilities.invokeLater(() -> {
            addMessage("======= BATTLE STARTED =======");
            addMessage(winnerMessage);
            JOptionPane.showMessageDialog(this, winnerMessage, "THE BATTLE IS OVER!", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    public void displayPlayerOptions(String playerName, List<Attack> moves) {
        SwingUtilities.invokeLater(() -> {
            addMessage("\n Your turn, " + playerName);
            if (!moves.isEmpty()) {
                addMessage("Choose an attack! Press 1-" + moves.size());
                for (int i = 0; i < moves.size(); i++) {
                    addMessage(" " + (i + 1) + ": " + moves.get(i).getName());
                }
            } else {
                addMessage("You have no moves to perform!");
            }
        });
    }

    // main-method for testing purposes only
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PokemonGUI();
        });
    }
}