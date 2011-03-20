package ru.artlebedev.idea.plugins.parser.parser.parsers;

import com.intellij.lang.PsiBuilder;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.parser.ParserElementTypes;
import ru.artlebedev.idea.plugins.parser.ParserBundle;

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

public class ParserDocParser extends BaseTokenParser {
  public void parseToken(PsiBuilder builder) {
    PsiBuilder.Marker parserDoc = builder.mark();

    while (builder.getTokenType() != ParserTokenTypes.NEW_LINE) {

      if (builder.getTokenType() == ParserTokenTypes.PARSERDOC_PARAM_KEYWORD) {
        PsiBuilder.Marker parserDocParamInfo = builder.mark();
        builder.advanceLexer();
        if (builder.getTokenType() == ParserTokenTypes.IDENTIFIER) {
          builder.advanceLexer();
        } else {
          builder.error(ParserBundle.message("parser.parse.expected.parameter"));
        }
        if (builder.getTokenType() == ParserTokenTypes.PARSERDOC_TYPE_KEYWORD) {
          PsiBuilder.Marker parserDocTypeInfo = builder.mark();
          builder.advanceLexer();
          if (builder.getTokenType() == ParserTokenTypes.IDENTIFIER) {
            builder.advanceLexer();
          } else {
            builder.error(ParserBundle.message("parser.parse.expected.className"));
          }
          parserDocTypeInfo.done(ParserElementTypes.PARSERDOC_TYPE_INFO);
        }
        parserDocParamInfo.done(ParserElementTypes.PARSERDOC_PARAM_INFO);
      }
      if (builder.getTokenType() == ParserTokenTypes.NEW_LINE) {
        break;
      } else {
        builder.advanceLexer();
      }
    }

    parserDoc.done(ParserElementTypes.PARSERDOC);
  }
}
