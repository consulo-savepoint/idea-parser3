package ru.artlebedev.idea.plugins.parser.editor.codecompletion;

import com.intellij.codeInsight.completion.CompletionContributor;
import com.intellij.codeInsight.completion.CompletionType;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.patterns.ElementPattern;
import com.intellij.patterns.StandardPatterns;
import com.intellij.psi.PsiElement;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers.ParserAfterDollarCompletionProvider;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers.ParserAfterHatCompletionProvider;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers.ParserAfterSignCompletionProvider;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers.ParserDefaultCompletionProvider;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers.ParserExceptionTypeCompletionProvider;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers.ParserLogicalStatementCompletionProvider;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers.ParserOptionCompletionProvider;
import ru.artlebedev.idea.plugins.parser.editor.codecompletion.providers.ParserTaintCompletionProvider;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.lang.parser.ParserElementTypes;

import static com.intellij.patterns.PlatformPatterns.psiElement;

/**
 * idea-parser3: the most advanced parser3 ide.
 * <p/>
 * Copyright 2011 <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * Copyright 2011 ArtLebedev Studio
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * This class handles additional variants for completion which are not match
 * any of getVariants of Psi-elements.
 *
 * @author <a href="mailto:dwr@design.ru">Valeriy Yatsko</a>
 * @version 1.0
 */
public class ParserCompletionContributor extends CompletionContributor {
  public static String newline = System.getProperty("line.separator");

  /**
   * Logger
   */
  private static final Logger log =
          Logger.getInstance("ParserCompletionContributor");


  /**
   * For all cases
   */
  private static final ElementPattern<PsiElement> DEFAULT =
          StandardPatterns.instanceOf(PsiElement.class)
                  .andNot(psiElement().afterLeaf("@"));

  /**
   * After @OPTIONS
   */
  private static final ElementPattern<PsiElement> OPTION =
          StandardPatterns.instanceOf(PsiElement.class)
                  .and(psiElement().afterLeaf(
                    psiElement().withElementType(
                            ParserTokenTypes.NEW_LINE)
                     .and(psiElement().afterLeaf(
                       psiElement().withElementType(
                               ParserTokenTypes.OPTIONS_KEYWORD)))));

  /**
   * ^taint params
   */
  private static final ElementPattern<PsiElement> TAINT =
          StandardPatterns.instanceOf(PsiElement.class)
                  .andNot(psiElement().afterLeaf("@"));

  /**
   * exception types
   */
  private static final ElementPattern<PsiElement> EXCEPTION_TYPE =
          StandardPatterns.instanceOf(PsiElement.class)
                  .andNot(psiElement().afterLeaf("@"));

  /**
   * Logical statements (inside braces)
   */
  private static final ElementPattern<PsiElement> LOGICAL_STATEMENT =
          StandardPatterns.instanceOf(PsiElement.class);

  /**
   * After ^ matches (including ZenParser)
   */
  private static final ElementPattern<PsiElement> AFTER_HAT =
          psiElement().afterLeaf("^");

  /**
   * After $ matches (including ZenParser)
   */
  private static final ElementPattern<PsiElement> AFTER_DOLLAR =
          psiElement().afterLeaf("$");

  /**
   * After @ matches (including ZenParser)
   */
  private static final ElementPattern<PsiElement> AFTER_SIGN =
          psiElement().afterLeaf("@");

  /**
   * Initializer
   */
  public ParserCompletionContributor() {
    log.info("Created parser completion contributor");

    extend(CompletionType.BASIC, DEFAULT,
            new ParserDefaultCompletionProvider());

    extend(CompletionType.BASIC, OPTION,
            new ParserOptionCompletionProvider());

    extend(CompletionType.BASIC, TAINT,
            new ParserTaintCompletionProvider());

    extend(CompletionType.BASIC, EXCEPTION_TYPE,
            new ParserExceptionTypeCompletionProvider());

    extend(CompletionType.BASIC, LOGICAL_STATEMENT,
            new ParserLogicalStatementCompletionProvider());

    extend(CompletionType.BASIC, AFTER_HAT,
            new ParserAfterHatCompletionProvider());

    extend(CompletionType.BASIC, AFTER_DOLLAR,
            new ParserAfterDollarCompletionProvider());

    extend(CompletionType.BASIC, AFTER_SIGN,
            new ParserAfterSignCompletionProvider());
  }
}
