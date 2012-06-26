package eu.isas.peptideshaker.gui.preferencesdialogs;

import com.compomics.util.Util;
import com.compomics.util.examples.BareBonesBrowserLaunch;
import eu.isas.peptideshaker.gui.PeptideShakerGUI;
import java.io.*;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * A simple bug report dialog.
 *
 * @author Marc Vaudel
 * @author Harald Barsnes
 */
public class BugReport extends javax.swing.JDialog {

    /**
     * The PeptideShakerGUI main frame.
     */
    private PeptideShakerGUI peptideShakerGUI;

    /**
     * Creates a new BugReport dialog.
     *
     * @param peptideShakerGUI
     */
    public BugReport(PeptideShakerGUI peptideShakerGUI) {
        super(peptideShakerGUI, true);
        this.peptideShakerGUI = peptideShakerGUI;
        initComponents();
        insertLogFileContent();
        setLocationRelativeTo(peptideShakerGUI);
        setVisible(true);
    }

    /**
     * Displays the content of the log file.
     */
    private void insertLogFileContent() {
        String log = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(new File("resources/PeptideShaker.log")));
            String line;
            while ((line = br.readLine()) != null) {
                log += line + "\n";
            }
        } catch (FileNotFoundException e) {
            log = "resources/conf/peptideshaker.log not found.";
        } catch (IOException e) {
            log = "An error occured while reafing resources/peptideshaker.log.";
        }

        logTxt.setText(log);
        logTxt.setCaretPosition(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        logJPanel = new javax.swing.JPanel();
        logJScrollPane = new javax.swing.JScrollPane();
        logTxt = new javax.swing.JTextArea();
        clearJButton = new javax.swing.JButton();
        saveJButton = new javax.swing.JButton();
        infoJPanel = new javax.swing.JPanel();
        infoJScrollPane = new javax.swing.JScrollPane();
        infoJEditorPane = new javax.swing.JEditorPane();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("PeptideShaker Bug Report");

        backgroundPanel.setBackground(new java.awt.Color(230, 230, 230));

        logJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("PeptideShaker Log"));
        logJPanel.setOpaque(false);

        logTxt.setColumns(20);
        logTxt.setEditable(false);
        logTxt.setRows(3);
        logTxt.setTabSize(4);
        logJScrollPane.setViewportView(logTxt);

        clearJButton.setText("Clear");
        clearJButton.setToolTipText("Clear the log");
        clearJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearJButtonActionPerformed(evt);
            }
        });

        saveJButton.setText("Save");
        saveJButton.setToolTipText("Save the log to a text file");
        saveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveJButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout logJPanelLayout = new javax.swing.GroupLayout(logJPanel);
        logJPanel.setLayout(logJPanelLayout);
        logJPanelLayout.setHorizontalGroup(
            logJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(logJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logJPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(saveJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearJButton)))
                .addContainerGap())
        );

        logJPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clearJButton, saveJButton});

        logJPanelLayout.setVerticalGroup(
            logJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(logJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveJButton)
                    .addComponent(clearJButton))
                .addGap(6, 6, 6))
        );

        infoJPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Info"));
        infoJPanel.setOpaque(false);

        infoJEditorPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        infoJEditorPane.setContentType("text/html");
        infoJEditorPane.setEditable(false);
        infoJEditorPane.setText("<html>\r\n  <head>\r\n\r\n  </head>\r\n  <body>\r\nIf you encounter any bugs while processing your data, please do one of the following:\n<br>\n<ul>\n  <li>Create an issue on the <a href=\"http://code.google.com/p/peptide-shaker/issues/list\">PeptideShaker web page</a>.</li>\n  <li>or Send an e-mail to the <a href=\"http://groups.google.com/group/peptide-shaker\">PeptideShaker mailing list</a>.</li>\n</ul>\nPlease include any relevant information as well as the log message displayed below.\n  </body>\r\n</html>\r\n");
        infoJEditorPane.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                infoJEditorPaneHyperlinkUpdate(evt);
            }
        });
        infoJScrollPane.setViewportView(infoJEditorPane);

        javax.swing.GroupLayout infoJPanelLayout = new javax.swing.GroupLayout(infoJPanel);
        infoJPanel.setLayout(infoJPanelLayout);
        infoJPanelLayout.setHorizontalGroup(
            infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoJPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(infoJScrollPane)
                .addContainerGap())
        );
        infoJPanelLayout.setVerticalGroup(
            infoJPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoJPanelLayout.createSequentialGroup()
                .addComponent(infoJScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setText("Exit");
        jButton1.setMaximumSize(new java.awt.Dimension(57, 23));
        jButton1.setMinimumSize(new java.awt.Dimension(57, 23));
        jButton1.setPreferredSize(new java.awt.Dimension(57, 23));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(infoJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(infoJPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logJPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Makes the links active.
     *
     * @param evt
     */
    private void infoJEditorPaneHyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_infoJEditorPaneHyperlinkUpdate
        if (evt.getEventType().toString().equalsIgnoreCase(
                javax.swing.event.HyperlinkEvent.EventType.ENTERED.toString())) {
            setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        } else if (evt.getEventType().toString().equalsIgnoreCase(
                javax.swing.event.HyperlinkEvent.EventType.EXITED.toString())) {
            setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        } else if (evt.getEventType().toString().equalsIgnoreCase(
                javax.swing.event.HyperlinkEvent.EventType.ACTIVATED.toString())) {
            if (evt.getDescription().startsWith("#")) {
                infoJEditorPane.scrollToReference(evt.getDescription());
            } else {
                this.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
                BareBonesBrowserLaunch.openURL(evt.getDescription());
                this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
            }
        }
    }//GEN-LAST:event_infoJEditorPaneHyperlinkUpdate

    /**
     * Clears the log file.
     *
     * @param evt
     */
    private void clearJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearJButtonActionPerformed

        try {
            FileWriter w = new FileWriter(new File("resources/PeptideShaker.log"));
            BufferedWriter bw = new BufferedWriter(w);
            bw.write("\n" + new Date() + ": PeptideShaker version " + peptideShakerGUI.getVersion() + ".\n");
            bw.close();
            w.close();
        } catch (FileNotFoundException e) {
            // ignore error
        } catch (IOException e) {
            // ignore error
        }

        insertLogFileContent();
    }//GEN-LAST:event_clearJButtonActionPerformed

    /**
     * Save the log file to a user specified file.
     * 
     * @param evt 
     */
    private void saveJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveJButtonActionPerformed

        File finalOutputFile = peptideShakerGUI.getUserSelectedFile(".txt", "(Text File) *.txt", "Select Destination File", false);

        if (finalOutputFile != null) {

            try {

                File logFile = new File("resources/conf/PeptideShaker.log");

                if (logFile.exists()) {
                    Util.copyFile(logFile, finalOutputFile);
                }
                
                if (!finalOutputFile.exists()) {
                    JOptionPane.showMessageDialog(this, "An error occured when saving the log.", "Save Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "The log has been saved to \'" + finalOutputFile.getPath() + "\'.", "Log Saved", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "An error occured when saving the log file.", "Save Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_saveJButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton clearJButton;
    private javax.swing.JEditorPane infoJEditorPane;
    private javax.swing.JPanel infoJPanel;
    private javax.swing.JScrollPane infoJScrollPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel logJPanel;
    private javax.swing.JScrollPane logJScrollPane;
    private javax.swing.JTextArea logTxt;
    private javax.swing.JButton saveJButton;
    // End of variables declaration//GEN-END:variables
}
