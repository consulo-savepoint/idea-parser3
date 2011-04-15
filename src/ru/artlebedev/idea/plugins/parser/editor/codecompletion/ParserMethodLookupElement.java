package ru.artlebedev.idea.plugins.parser.editor.codecompletion;

import com.intellij.codeInsight.lookup.LookupElementPresentation;
import com.intellij.util.Icons;

/**
 * idea-parser3: slightly useful plugin.
 * <p/>
 * Copyright 2011 Valeriy Yatsko <dwr@design.ru>
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

/*
 * Adds an icon in the form of < > (might be changed),
 * it means that this lookup belongs to live template
 * and could be expanded with tab.
 * -- dwr
 */
public class ParserMethodLookupElement extends ParserKeywordLookupElement {
  public ParserMethodLookupElement(String keyword) {
    super(keyword);
  }

  @Override
  public void renderElement(LookupElementPresentation presentation) {
      presentation.setIcon(Icons.ADVICE_ICON);
      super.renderElement(presentation);
  }
}
