
package pingpong.panels;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pingpong.AbleToGetOptions;
import static pingpong.MainForm.MENU_PANEL;
import static pingpong.MainForm.OPTIONS_PANEL;

/**
 *
 * @author Radek Bartyzal
 */
public class OptionsPanel extends JPanel {
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    
    private boolean matrixOn = true;
    private int type = 0;
    private int winningScore = 10;
    private boolean fixedSpeed = true;
    private boolean endlessFixedSpeed = true;
    private int lives = 1;
    
    public OptionsPanel(){
        initComponents();
        setName(OPTIONS_PANEL);
    }
    
    //Greying out other labels in settings
    private void matrixTypeLabels(JLabel label) {
        jLabel5.setForeground(new Color(153, 153, 153));
        jLabel6.setForeground(new Color(153, 153, 153));
        jLabel7.setForeground(new Color(153, 153, 153));
        jLabel8.setForeground(new Color(153, 153, 153));
        label.setForeground(Color.WHITE);
    }
    
    private void winningScoreLabels(JLabel label) {
        jLabel12.setForeground(new Color(153, 153, 153));
        jLabel13.setForeground(new Color(153, 153, 153));
        jLabel14.setForeground(new Color(153, 153, 153));
        jLabel15.setForeground(new Color(153, 153, 153));
        jLabel16.setForeground(new Color(153, 153, 153));
        jLabel17.setForeground(new Color(153, 153, 153));
        label.setForeground(Color.WHITE);
    }
    
    private void numberOfLivesLabels(JLabel label) {
        jLabel23.setForeground(new Color(153, 153, 153));
        jLabel24.setForeground(new Color(153, 153, 153));
        jLabel25.setForeground(new Color(153, 153, 153));
        jLabel26.setForeground(new Color(153, 153, 153));
        jLabel27.setForeground(new Color(153, 153, 153));
        jLabel28.setForeground(new Color(153, 153, 153));
        label.setForeground(Color.WHITE);
    }
    
    private void initComponents(){
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(240, 240, 240));
        jLabel3.setText("Back");
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

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Options");

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)), "Matrix Effect", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 15), new java.awt.Color(240, 240, 240))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Turned:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(240, 240, 240));
        jLabel4.setText("Type:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(240, 240, 240));
        jLabel5.setText("Chinese");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Latin");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Numbers");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("Signs");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(240, 240, 240));
        jLabel9.setText("On");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setText("Off");
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)), "Classic Mode", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 15), new java.awt.Color(240, 240, 240))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(240, 240, 240));
        jLabel11.setText("Winning score:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(240, 240, 240));
        jLabel12.setText("10");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setText("20");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 153, 153));
        jLabel14.setText("30");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(153, 153, 153));
        jLabel15.setText("40");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 153));
        jLabel16.setText("50");
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 153, 153));
        jLabel17.setText("Over 9000");
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(240, 240, 240));
        jLabel18.setText("Speed of ball:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(240, 240, 240));
        jLabel19.setText("Fixed");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(153, 153, 153));
        jLabel20.setText("Increasing");
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)), "Endless Mode", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 15), new java.awt.Color(240, 240, 240))); // NOI18N

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(240, 240, 240));
        jLabel21.setText("Number of lives:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(240, 240, 240));
        jLabel22.setText("Speed of ball:");

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(240, 240, 240));
        jLabel23.setText("1");
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(153, 153, 153));
        jLabel24.setText("10");
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(153, 153, 153));
        jLabel25.setText("20");
        jLabel25.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel25MouseClicked(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(153, 153, 153));
        jLabel26.setText("30");
        jLabel26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel26MouseClicked(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(153, 153, 153));
        jLabel27.setText("40");
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(153, 153, 153));
        jLabel28.setText("50");
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(240, 240, 240));
        jLabel29.setText("Fixed");
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(153, 153, 153));
        jLabel30.setText("Increasing");
        jLabel30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel30MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel30))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel26)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28)))
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27)
                    .addComponent(jLabel28))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(325, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        
    }
    
    
    //---------Back----Setting options of all panels------------
    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {  
        //Set options of all panels
        for (Component p : getParent().getComponents()) {          
            if(p instanceof AbleToGetOptions) {
                ((AbleToGetOptions) p).getOptions();
            }
        }
        CardLayout cl = (CardLayout) (getParent().getLayout());
        cl.show(getParent(), MENU_PANEL);
    }       
    
    //Back
    private void jLabel3MouseEntered(java.awt.event.MouseEvent evt) {                                     
        jLabel3.setFont(new Font("Tahoma", Font.BOLD, 22));
    }                                    
    //Back
    private void jLabel3MouseExited(java.awt.event.MouseEvent evt) {                                    
        jLabel3.setFont(new Font("Tahoma", Font.PLAIN, 20));
    }    
    
    //Off
    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {                                      

        jLabel10.setForeground(Color.WHITE);
        jLabel9.setForeground(new Color(153, 153, 153));
        matrixOn = false;
    }                                     
    //On
    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {                                     

        jLabel9.setForeground(Color.WHITE);
        jLabel10.setForeground(new Color(153, 153, 153));
        matrixOn = true;
    }                                    

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {                                     
        matrixTypeLabels(jLabel5);
        type = 0;
    }                                    

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {                                     
        matrixTypeLabels(jLabel6);
        type = 1;
    }                                    

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {                                     
        matrixTypeLabels(jLabel7);
        type = 2;
    }                                    

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {                                     
        matrixTypeLabels(jLabel8);
        type = 3;
    }                                    

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {                                      
        winningScoreLabels(jLabel12);
        winningScore = 10;
    }                                     

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {                                      
        winningScoreLabels(jLabel13);
        winningScore = 20;
    }                                     

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {                                      
        winningScoreLabels(jLabel14);
        winningScore = 30;
    }                                     

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {                                      
        winningScoreLabels(jLabel15);
        winningScore = 40;
    }                                     

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {                                      
        winningScoreLabels(jLabel16);
        winningScore = 50;
    }                                     

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {                                      
        winningScoreLabels(jLabel17);
        winningScore = 10000; /*ITS OVER 9000!!!!!*/
    }                                     
    /*Fixed speed*/
    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {                                      
        jLabel19.setForeground(Color.WHITE);
        jLabel20.setForeground(new Color(153, 153, 153));
        fixedSpeed = true;
    }                                     
    /*Increasing speed*/
    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {                                      
        jLabel20.setForeground(Color.WHITE);
        jLabel19.setForeground(new Color(153, 153, 153));
        fixedSpeed = false;
    }                                     

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {                                      
        numberOfLivesLabels(jLabel23);
        lives = 1;
    }                                     

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {                                      
        numberOfLivesLabels(jLabel24);
        lives = 10;
    }                                     

    private void jLabel25MouseClicked(java.awt.event.MouseEvent evt) {                                      
        numberOfLivesLabels(jLabel25);
        lives = 20;
    }                                     

    private void jLabel26MouseClicked(java.awt.event.MouseEvent evt) {                                      
        numberOfLivesLabels(jLabel26);
        lives = 30;
    }                                     

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {                                      
        numberOfLivesLabels(jLabel27);
        lives = 40;
    }                                     

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {                                      
        numberOfLivesLabels(jLabel28);
        lives = 50;
    }                                     

    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {                                      
        jLabel29.setForeground(Color.WHITE);
        jLabel30.setForeground(new Color(153, 153, 153));
        endlessFixedSpeed = true;
    }                                     

    private void jLabel30MouseClicked(java.awt.event.MouseEvent evt) {                                      
        jLabel30.setForeground(Color.WHITE);
        jLabel29.setForeground(new Color(153, 153, 153));
        endlessFixedSpeed = false;
    }

    public boolean isMatrixOn() {
        return matrixOn;
    }

    public int getType() {
        return type;
    }

    public int getWinningScore() {
        return winningScore;
    }

    public int getLives() {
        return lives;
    }

    public boolean isFixedSpeed() {
        return fixedSpeed;
    }

    public boolean isEndlessFixedSpeed() {
        return endlessFixedSpeed;
    }

    
}
