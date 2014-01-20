/**
 * The MIT License.
 *
 * Copyright (c) 2014 Tomas Ptacnik (tomor)
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */

package org.netbeans.modules.php.quickmethodjump;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import org.openide.cookies.EditorCookie;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "PHP",
        id = "org.netbeans.modules.php.quickmethodjump.QuickMethodJumpAction"
)
@ActionRegistration(
        displayName = "#CTL_QuickMethodJumpAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/Source", position = 9100, separatorBefore = 9050),
    @ActionReference(path = "Editors/text/x-php5/Popup", position = 4300, separatorBefore = 4250)
})
@Messages("CTL_QuickMethodJumpAction=Quick Method Jump")
public final class OpenDialogAction implements ActionListener {

    private final EditorCookie context;

    private DocumentParser dp;

    public OpenDialogAction(EditorCookie context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        try {
            // parse currently opened php file
            this.dp = new DocumentParser(this.context);

            // create component helper
            ComponentHelper cpHelper = new ComponentHelper(context);

            // fill dialog with method names and show it
            // pass also helper to the dialog - dialog will be able to move with caret
            createDialog(this.dp.getMethodsLines(), cpHelper);

        } catch (BadLocationException ex) {
            Exceptions.printStackTrace(ex);

            // TODO show err that method search can't be done
        }
    }

    /**
     * Show search dialog
     */
    public void createDialog(List<MLine> lines, final ComponentHelper cpHelper) {
        final List<MLine> linesCopy = new ArrayList<MLine>(lines);
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        /*try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SearchDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SearchDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SearchDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SearchDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }*/
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                dialog = new SearchDialog(new javax.swing.JFrame(), true, linesCopy, cpHelper);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setLocationRelativeTo(null); // center the dialog on the screen
                dialog.setVisible(true);
            }
        });
    }

    private SearchDialog dialog;
}
