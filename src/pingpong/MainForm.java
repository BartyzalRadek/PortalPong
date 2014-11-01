package pingpong;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Radek Bartyzal
 */
public class MainForm {

    private JPanel cards; ///< A panel that uses CardLayout
    public final static int FRAME_HEIGHT = 500;
    public final static int FRAME_WIDTH = 1000;
    public final static String MENU_PANEL = "Card with menu";
    public final static String OPTIONS_PANEL = "Card with options";
    public final static String ABOUT_PANEL = "Card with about";
    public final static String LEADERBOARDS_PANEL = "Card with leaderboards";
    public final static String SP_PANEL = "Card with singleplayer mode choice";
    public final static String CLASSIC_PANEL = "Card with classic singleplayer";
    public final static String ENDLESS_PANEL = "Card with endless singleplayer";
    public final static String MP_PANEL = "Card with multiplayer";
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private JPanel optionsCard;
    private JPanel aboutCard;
    private LeaderboardsPanel leaderCard;
    private SPChoicePanel spCard;
    private MPGraphicsPanel mpCard;
    private SPGraphicsPanel classicCard;
    private EndlessPanel endlessCard;

    private void labelActions() {
        //Singleplayer
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, SP_PANEL);
            }

        });

        //Multiplayer
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, MP_PANEL);
            }

        });

        //Options
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, OPTIONS_PANEL);
            }

        });

        //Leaderboards
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, LEADERBOARDS_PANEL);
            }

        });

        //About
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, ABOUT_PANEL);
            }

        });

        //Exit
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.exit(1);
            }

        });

    }

    private void setLabelParam(final JLabel label) {
        label.setFont(new java.awt.Font("Tahoma", 0, 20));
        label.setForeground(new java.awt.Color(240, 240, 240));
        label.setMaximumSize(new java.awt.Dimension(200, 25));
        label.setMinimumSize(new java.awt.Dimension(200, 25));
        label.setPreferredSize(new java.awt.Dimension(200, 25));
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                label.setFont(new Font("Tahoma", Font.BOLD, 22));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                label.setFont(new Font("Tahoma", Font.PLAIN, 20));
            }
        });
    }

    //private JLabel labels[];
    public void addComponentToPane(Container pane) {
        //Create the "cards".
        JPanel menuCard = new MatrixPanel();
        menuCard.setLayout(new BoxLayout(menuCard, BoxLayout.Y_AXIS));

        jLabel1 = new javax.swing.JLabel("Singleplayer");
        jLabel2 = new javax.swing.JLabel("Multiplayer");
        jLabel3 = new javax.swing.JLabel("Options");
        jLabel4 = new javax.swing.JLabel("Leaderboards");
        jLabel5 = new javax.swing.JLabel("About");
        jLabel6 = new javax.swing.JLabel("Exit");

        JLabel labels[] = {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6};
        for (final JLabel label : labels) {
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            setLabelParam(label);
        }

        labelActions();

        menuCard.add(Box.createRigidArea(new Dimension(20, 100)));
        for (JLabel label : labels) {
            menuCard.add(label);
            menuCard.add(Box.createRigidArea(new Dimension(20, 20)));
        }

        optionsCard = new OptionsPanel();
        aboutCard = new AboutPanel();
        leaderCard = new LeaderboardsPanel();
        spCard = new SPChoicePanel();
        mpCard = new MPGraphicsPanel();
        classicCard = new SPGraphicsPanel();
        endlessCard = new EndlessPanel();

        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(spCard, SP_PANEL);
        cards.add(mpCard, MP_PANEL);
        cards.add(classicCard, CLASSIC_PANEL);
        cards.add(endlessCard, ENDLESS_PANEL);
        cards.add(menuCard, MENU_PANEL);
        cards.add(optionsCard, OPTIONS_PANEL);
        cards.add(leaderCard, LEADERBOARDS_PANEL);
        cards.add(aboutCard, ABOUT_PANEL);

        pane.add(cards, BorderLayout.CENTER);

        //Show specific card
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, MENU_PANEL);
        menuCard.requestFocus();
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Portal Pong");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setResizable(false);

        //Create and set up the content pane.
        MainForm mainFrame = new MainForm();
        mainFrame.addComponentToPane(frame.getContentPane());

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
            public void run() {
                createAndShowGUI();
            }
        });

    }
}
