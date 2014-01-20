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

/**
 * Class for line representation - it should help with lines with PHP methods
 *
 * @author tomor
 */
public class MLine {
    private final int lineNumber;
    private final int linePositionInDocument;
    private final String text;
    private final String methodName;
    private final String methodDefinition;

    public MLine(int lineNumber, int linePositionInDocument, String lineText) {
        this.lineNumber             = lineNumber;
        this.linePositionInDocument = linePositionInDocument;
        this.text                   = lineText;
        this.methodName             = parseMethodName(lineText);
        this.methodDefinition       = parseMethodDefinition(lineText);
    }

    /**
     * Parse method name from the line text
     *
     * @param text
     * @return
     */
    private String parseMethodName(String text) {
        // TODO remove everything bug the function name
        return text;
    }

    /**
     * Parse method definition from the line text
     *
     * @param text
     * @return
     */
    private String parseMethodDefinition(String text) {
        // todo remove ending { if exists on the line
        // if the method definition is written on many lines, there will be just the part of the
        // definition, but for this plugin it's ok
        return text.trim();
    }

    public int getLineNumber() {
        return this.lineNumber;
    }

    public String getText() {
        return this.text;
    }

    public String getMethodName() {
        return this.methodName;
    }

    public String getMethodDefinition() {
        return this.methodDefinition;
    }


    public int getlinePositionInDocument() {
        return this.linePositionInDocument;
    }

    @Override
    public String toString() {
        return this.getMethodDefinition();
    }
}
