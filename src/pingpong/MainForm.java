/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Menu.java
 *
 * Created on 8.8.2013, 19:10:20
 */
package pingpong;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Darmy
 */
public class MainForm extends JFrame {

    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private static javax.swing.JLabel jLabel7;
    private static javax.swing.JLabel jLabel8;
    private static MatrixPanel matrixPanel = new MatrixPanel();

    /** Creates new form Menu */
    public MainForm() {
        initComponents();

    }
    public static boolean visible = true;
    public static GameFrame sp = new GameFrame("sp");
    public static GameFrame mp = new GameFrame("mp");
    public static GameFrame endless = new GameFrame("endless");
    public static Options options = new Options();
    public static Leaderboards leaderboards = new Leaderboards();
    private static About about = new About();
    private static boolean oTempVisible = false;   //options temporary visible settings
    private static boolean lTempVisible = false;   //leaderboards temporary visible settings
    private static boolean aTempVisible = false;   //about temporary visible settings
    private static boolean spTempVisible = false;    //singleplayer gameFrame
    private static boolean mpTempVisible = false;   //multiplayer gameFrame
    private static boolean endTempVisible = false;  //endless gameFrame

    private static void backO() {
        if (options != null) {
            if (options.visible == true) {
                oTempVisible = true;
            }
            if (options.visible == false && oTempVisible == true) {
                oTempVisible = false;
                visible = true;
            }
        }
    }

    private static void backL() {
        if (leaderboards != null) {
            if (leaderboards.visible == true) {
                lTempVisible = true;
            }
            if (leaderboards.visible == false && lTempVisible == true) {
                lTempVisible = false;
                visible = true;
            }
        }
    }

    private static void backA() {
        if (about != null) {
            if (about.visible == true) {
                aTempVisible = true;
            }
            if (about.visible == false && aTempVisible == true) {
                aTempVisible = false;
                visible = true;
            }
        }
    }

    private static void backG() {

        if (sp.visible == true) {
            spTempVisible = true;
        }
        if (sp.visible == false && spTempVisible == true) {
            spTempVisible = false;
            visible = true;
            jLabel7.setVisible(false);
            jLabel8.setVisible(false);
        }
        if (mp.visible == true) {
            mpTempVisible = true;
        }
        if (mp.visible == false && mpTempVisible == true) {
            mpTempVisible = false;
            jLabel7.setVisible(false);
            jLabel8.setVisible(false);
            visible = true;
        }
        if (endless.visible == true) {
            endTempVisible = true;
        }
        if (endless.visible == false && endTempVisible == true) {
            endTempVisible = false;
            jLabel7.setVisible(false);
            jLabel8.setVisible(false);
            visible = true;
        }

    }

    private static void backToMenu() {
        backO();
        backL();
        backA();
        backG();
        
        optionSettings();

    }
    
    private static void optionSettings() {
        matrixPanel.setMatrixOn(options.matrixOn);
        matrixPanel.setType(options.type);
        sp.spgp.player1.winningScore = options.winningScore;
        sp.spgp.player2.winningScore = options.winningScore;
        mp.mpgp.player1.winningScore = options.winningScore;
        mp.mpgp.player2.winningScore = options.winningScore;
        
        sp.spgp.fixedSpeed = options.fixedSpeed;
        mp.mpgp.fixedSpeed = options.fixedSpeed;

        endless.endlessgp.player1.setLives(options.lives);
        /**WHAT????
        //WHAT???? endless.endlessgp.player2.lives = options.lives;*/
        endless.endlessgp.fixedSpeed = options.fixedSpeed;
        
        endless.endlessgp.leaderboardsArray = leaderboards.leaderboardsPanel.getLeaderboard();
        
        if (endless.endlessgp.endGame > 0) {
            
            leaderboards.leaderboardsPanel.setLeaderboard(endless.endlessgp.finalArray);
        }
    }

    private static void menuItems() {
        options.setVisible(options.visible);
        leaderboards.setVisible(leaderboards.visible);
        about.setVisible(about.visible);
        sp.setVisible(sp.visible);
        mp.setVisible(mp.visible);
        endless.setVisible(endless.visible);
    }

    private static void gameFrameUpdate() {
        mp.mpgp.repaint();
        sp.spgp.repaint();
        endless.endlessgp.repaint();

        sp.checkVisible();
        mp.checkVisible();
        endless.checkVisible();

        sp.setVisible(sp.visible);
        mp.setVisible(mp.visible);
        endless.setVisible(endless.visible);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {


        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Portal Pong");
        setBackground(new java.awt.Color(0, 0, 0));
        setResizable(false);

        matrixPanel.setBackground(new java.awt.Color(0, 0, 0));
        matrixPanel.setPreferredSize(new java.awt.Dimension(1000, 500));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Multiplayer");
        jLabel1.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel1.setMinimumSize(new java.awt.Dimension(110, 25));
        jLabel1.setPreferredSize(new java.awt.Dimension(40, 20));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20));
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Singleplayer");
        jLabel2.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel2.setMinimumSize(new java.awt.Dimension(110, 25));
        jLabel2.setPreferredSize(new java.awt.Dimension(40, 20));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel2MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel2MouseExited(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20));
        jLabel3.setForeground(new java.awt.Color(240, 240, 240));
        jLabel3.setText("Options");
        jLabel3.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel3.setMinimumSize(new java.awt.Dimension(110, 25));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel3MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel3MouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20));
        jLabel4.setForeground(new java.awt.Color(240, 240, 240));
        jLabel4.setText("Leaderboards");
        jLabel4.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel4.setMinimumSize(new java.awt.Dimension(110, 25));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel4MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel4MouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20));
        jLabel5.setForeground(new java.awt.Color(240, 240, 240));
        jLabel5.setText("About");
        jLabel5.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel5.setMinimumSize(new java.awt.Dimension(110, 25));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20));
        jLabel6.setForeground(new java.awt.Color(240, 240, 240));
        jLabel6.setText("Exit");
        jLabel6.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel6.setMinimumSize(new java.awt.Dimension(110, 25));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20));
        jLabel7.setForeground(new java.awt.Color(240, 240, 240));
        jLabel7.setText("Classic");
        jLabel7.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel7.setMinimumSize(new java.awt.Dimension(110, 25));
        jLabel7.setVisible(false);
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel7MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel7MouseExited(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20));
        jLabel8.setForeground(new java.awt.Color(240, 240, 240));
        jLabel8.setText("Endless");
        jLabel8.setMaximumSize(new java.awt.Dimension(110, 25));
        jLabel8.setMinimumSize(new java.awt.Dimension(110, 25));
        jLabel8.setVisible(false);
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel8MouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel8MouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(matrixPanel);
        matrixPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(65, 65, 65).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jLabel8).addGap(18, 18, 18).addComponent(jLabel7).addContainerGap()).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE).addContainerGap()).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap()).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel5).addContainerGap()).addGroup(jPanel1Layout.createSequentialGroup().addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGap(870, 870, 870)))))));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(jPanel1Layout.createSequentialGroup().addGap(104, 104, 104).addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE).addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18).addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(90, Short.MAX_VALUE)));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(matrixPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(matrixPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited

        jLabel6.setFont(new Font("Tahoma", Font.PLAIN, 20));
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered

        jLabel6.setFont(new Font("Tahoma", Font.BOLD, 22));
    }//GEN-LAST:event_jLabel6MouseEntered

    //Exit
    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked

        System.exit(0);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseExited

        jLabel5.setFont(new Font("Tahoma", Font.PLAIN, 20));
    }//GEN-LAST:event_jLabel5MouseExited

    private void jLabel5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseEntered

        jLabel5.setFont(new Font("Tahoma", Font.BOLD, 22));
    }//GEN-LAST:event_jLabel5MouseEntered

    //About
    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked

        visible = false;
        about.visible = true;
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseExited

        jLabel4.setFont(new Font("Tahoma", Font.PLAIN, 20));
    }//GEN-LAST:event_jLabel4MouseExited

    private void jLabel4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseEntered

        jLabel4.setFont(new Font("Tahoma", Font.BOLD, 22));
    }//GEN-LAST:event_jLabel4MouseEntered

    //Leaderboards
    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked

        visible = false;
        leaderboards.visible = true;
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseExited

        jLabel3.setFont(new Font("Tahoma", Font.PLAIN, 20));
    }//GEN-LAST:event_jLabel3MouseExited

    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseEntered

        jLabel3.setFont(new Font("Tahoma", Font.BOLD, 22));
    }//GEN-LAST:event_jLabel3MouseEntered

    //Options
    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        visible = false;
        options.visible = true;
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseEntered

        jLabel2.setFont(new Font("Tahoma", Font.BOLD, 22));
    }//GEN-LAST:event_jLabel2MouseEntered

    private void jLabel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseExited
        jLabel2.setFont(new Font("Tahoma", Font.PLAIN, 20));

    }//GEN-LAST:event_jLabel2MouseExited

    //Singleplayer
    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked

        if (jLabel7.isVisible()) {
            jLabel7.setVisible(false);
            jLabel8.setVisible(false);
        } else {
            jLabel7.setVisible(true);
            jLabel8.setVisible(true);
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited

        jLabel1.setFont(new Font("Tahoma", Font.PLAIN, 20));
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered

        jLabel1.setFont(new Font("Tahoma", Font.BOLD, 22));
    }//GEN-LAST:event_jLabel1MouseEntered

    //Multiplayer
    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        visible = false;
        mp.visible = true;
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseExited
        jLabel7.setFont(new Font("Tahoma", Font.PLAIN, 20));
    }//GEN-LAST:event_jLabel7MouseExited

    private void jLabel7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseEntered
        jLabel7.setFont(new Font("Tahoma", Font.BOLD, 22));
    }//GEN-LAST:event_jLabel7MouseEntered

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        visible = false;
        sp.visible = true;
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseExited
        jLabel8.setFont(new Font("Tahoma", Font.PLAIN, 20));
    }//GEN-LAST:event_jLabel8MouseExited

    private void jLabel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseEntered
        jLabel8.setFont(new Font("Tahoma", Font.BOLD, 22));
    }//GEN-LAST:event_jLabel8MouseEntered

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        visible = false;
        endless.visible = true;
    }//GEN-LAST:event_jLabel8MouseClicked

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

        final MainForm mainForm = new MainForm();
        Timer timer = new Timer(10, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                gameFrameUpdate();
                menuItems();
                backToMenu();

                mainForm.setVisible(visible);
            }
        });

        timer.start();


    }
}
