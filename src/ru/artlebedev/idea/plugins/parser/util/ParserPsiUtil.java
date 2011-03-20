package ru.artlebedev.idea.plugins.parser.util;

import com.intellij.lang.PsiBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiElementFilter;
import com.intellij.psi.util.PsiTreeUtil;
import ru.artlebedev.idea.plugins.parser.lang.lexer.ParserTokenTypes;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
 * Copyright 2006 Jay Bird <a4blank@yahoo.com>
 * Copyright 2006-2011 ArtLebedev Studio
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

public final class ParserPsiUtil {

  public static <T extends PsiElement> List<T> collectElementsAsList(PsiElement parent, final Class<T> typeToFind) {
    PsiElement[] psiElements = PsiTreeUtil.collectElements(parent, new PsiElementFilter() {
      public boolean isAccepted(PsiElement element) {
        return typeToFind.isInstance(element);
      }
    });
    List<T> result = new ArrayList<T>();
    for (PsiElement element : psiElements) {
      result.add((T) element);
    }
    return result;
  }

  public static IElementType getNextTokenCarefully(PsiBuilder builder) {
    PsiBuilder.Marker marker = builder.mark();
    IElementType iElementType = builder.getTokenType();
    marker.rollbackTo();
    return iElementType;
  }

  public static IElementType getNextTokenIgnoringNLandParserDoc(PsiBuilder builder) {
    PsiBuilder.Marker marker = builder.mark();
    IElementType iElementType = builder.getTokenType();
    while (iElementType == ParserTokenTypes.NEW_LINE || iElementType == ParserTokenTypes.PARSERDOC_START) {
      if (builder.getTokenType() == ParserTokenTypes.PARSERDOC_START) {
        while (builder.getTokenType() != ParserTokenTypes.NEW_LINE && !builder.eof()) {
          builder.advanceLexer();
        }
      }
      builder.advanceLexer();
      iElementType = builder.getTokenType();
    }
    if (builder.eof()) {
      marker.rollbackTo();
      return ParserTokenTypes.EOF;
    }
    marker.rollbackTo();
    return iElementType;
  }
}

