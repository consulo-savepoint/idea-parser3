package ru.artlebedev.idea.plugins.parser.lang.lexer;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;

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

public class MergeTuple {
  private final TokenSet tokensToMerge;
  private final IElementType targetType;

  public static MergeTuple create(TokenSet tokensToMerge, IElementType targetType) {
    return new MergeTuple(tokensToMerge, targetType);
  }

  private MergeTuple(TokenSet tokensToMerge, IElementType targetType) {
    this.tokensToMerge = tokensToMerge;
    this.targetType = targetType;
  }

  public TokenSet getTokensToMerge() {
    return tokensToMerge;
  }

  public IElementType getTargetType() {
    return targetType;
  }
}