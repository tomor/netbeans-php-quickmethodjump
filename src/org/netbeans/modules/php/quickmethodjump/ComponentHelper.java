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

import javax.swing.JEditorPane;
import javax.swing.text.JTextComponent;
import org.openide.cookies.EditorCookie;

/**
 * Class primary designed to set caret to position of the opened editor window
 *
 * @author tomor
 */
public class ComponentHelper {

    private JTextComponent component;

    public ComponentHelper(EditorCookie context) {
        JEditorPane panes[] = context.getOpenedPanes();
        if (panes != null && panes.length > 0) {
            component = panes[0];
        }

    }

    public void setCarretPosition(int position) {
        component.setCaretPosition(position);
    }

}
