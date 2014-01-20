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

import java.util.ArrayList;
import java.util.List;
import javax.swing.text.BadLocationException;
import org.openide.cookies.EditorCookie;
import org.openide.text.Line;

/**
 * Class for parsing currently opened file
 *
 * @author tomor
 */
public class DocumentParser {

    private final List<MLine> linesWithMethods;

    DocumentParser(EditorCookie context) throws BadLocationException {
        this.linesWithMethods = new ArrayList<MLine>();
        this.parse(context);
    }

    /**
     * Find lines with methods (functions)
     *
     * @param context
     * @throws BadLocationException
     */
    private void parse(EditorCookie context) throws BadLocationException {
        context.getLineSet().getLines();

        List<? extends Line> lines = context.getLineSet().getLines();

        int documentLengthCounter = 0;

        // find lines with methods
        for (Line line : lines) {
            String lineText = line.getText();


            if (lineText.contains("function")) {
                this.linesWithMethods.add(new MLine(line.getLineNumber(), documentLengthCounter, lineText));
            }

            // measure length of the document;
            documentLengthCounter += lineText.length();
        }
    }

    /**
     * Get lines with methods from parsed document
     */
    public List<MLine> getMethodsLines() {
        return this.linesWithMethods;
    }

}
