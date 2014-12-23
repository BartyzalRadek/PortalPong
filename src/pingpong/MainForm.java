package pingpong;

import pingpong.panels.MPGraphicsPanel;
import pingpong.panels.AboutPanel;
import pingpong.panels.SPChoicePanel;
import pingpong.panels.EndlessPanel;
import pingpong.panels.LeaderboardsPanel;
import pingpong.panels.SPGraphicsPanel;
import pingpong.panels.OptionsPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import pingpong.panels.CardsPanel;
import static pingpong.panels.CardsPanel.FRAME_HEIGHT;
import static pingpong.panels.CardsPanel.FRAME_WIDTH;
import pingpong.panels.MenuPanel;

/**
 *
 * @author Radek Bartyzal
 */
public class MainForm {
    
    public final static String MENU_PANEL = "Card with menu";
    public final static String OPTIONS_PANEL = "Card with options";
    public final static String ABOUT_PANEL = "Card with about";
    public final static String LEADERBOARDS_PANEL = "Card with leaderboards";
    public final static String SP_PANEL = "Card with singleplayer mode choice";
    public final static String CLASSIC_PANEL = "Card with classic singleplayer";
    public final static String ENDLESS_PANEL = "Card with endless singleplayer";
    public final static String MP_PANEL = "Card with multiplayer";

    private CardsPanel cards; //** A panel that uses CardLayout*/
    private MenuPanel menuCard;
    private OptionsPanel optionsCard;
    private AboutPanel aboutCard;
    private LeaderboardsPanel leaderCard;
    private SPChoicePanel spCard;
    private MPGraphicsPanel mpCard;
    private SPGraphicsPanel classicCard;
    private EndlessPanel endlessCard;

    //private JLabel labels[];
    private void addComponentsToPane(Container pane) {
        //Create the "cards".
        menuCard = new MenuPanel();
        optionsCard = new OptionsPanel();
        aboutCard = new AboutPanel();
        leaderCard = new LeaderboardsPanel();
        spCard = new SPChoicePanel();
        mpCard = new MPGraphicsPanel();
        classicCard = new SPGraphicsPanel();
        endlessCard = new EndlessPanel();

        //Create the panel that contains the "cards".
        cards = new CardsPanel(new CardLayout());
        cards.add(spCard, SP_PANEL);
        cards.add(mpCard, MP_PANEL);
        cards.add(classicCard, CLASSIC_PANEL);
        cards.add(endlessCard, ENDLESS_PANEL);
        cards.add(menuCard, MENU_PANEL);
        cards.add(optionsCard, OPTIONS_PANEL);
        cards.add(leaderCard, LEADERBOARDS_PANEL);
        cards.add(aboutCard, ABOUT_PANEL);

        //Add panel containing the cards to the container pane (frame)
        pane.setLayout(new BorderLayout());
        pane.add(cards, BorderLayout.CENTER);

        //Show specific card
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, MENU_PANEL);

    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Portal Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        frame.setResizable(true);

        //Create and set up the content pane.
        MainForm mainFrame = new MainForm();
        mainFrame.addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });

    }
}
