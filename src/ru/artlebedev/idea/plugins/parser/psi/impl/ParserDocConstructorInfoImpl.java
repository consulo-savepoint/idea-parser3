package ru.artlebedev.idea.plugins.parser.psi.impl;

import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.artlebedev.idea.plugins.parser.indexer.ParserFileIndex;
import ru.artlebedev.idea.plugins.parser.lexer.ParserTokenTypes;
import ru.artlebedev.idea.plugins.parser.psi.ParserFile;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserClass;
import ru.artlebedev.idea.plugins.parser.psi.api.ParserDocConstructorInfo;
import ru.artlebedev.idea.plugins.parser.psi.lookup.ParserLookupUtil;
import ru.artlebedev.idea.plugins.parser.psi.resolve.ParserResolveUtil;
import ru.artlebedev.idea.plugins.parser.utils.ParserChangeUtil;

import java.util.ArrayList;
import java.util.Collection;
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

public class ParserDocConstructorInfoImpl extends ParserElementImpl implements ParserDocConstructorInfo, PsiReference {
  public ParserDocConstructorInfoImpl(ASTNode astNode) {
    super(astNode);
  }

  public PsiReference getReference() {
    return this;
  }

  public PsiElement getElement() {
    return this;
  }

  public String getName() {
    ASTNode nameNode = findNameNode();
    if (nameNode != null)
      return nameNode.getText();
    return null;
  }

  public int getTextOffset() {
    ASTNode name = findNameNode();
    return name != null ? name.getStartOffset() : super.getTextOffset();
  }


  public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
    ASTNode identifier = ParserChangeUtil.createNameIdentifier(getProject(), name);
    ASTNode nameNode = findNameNode();
    if (nameNode != null)
      getNode().replaceChild(nameNode, identifier);
    return this;
  }

  public ASTNode findNameNode() {
    return getNode().findChildByType(ParserTokenTypes.IDENTIFIER);
  }

  public TextRange getRangeInElement() {
    final ASTNode firstChild = findNameNode();
    if (firstChild.getTextRange() != null) {
      return firstChild.getTextRange().shiftRight(-1 * getNode().getStartOffset());
    }
    return null;
  }

  @Nullable
  public PsiElement resolve() {
    Collection<ParserFile> parserFiles = getProject().getComponent(ParserFileIndex.class).getLoadedClasses().values();

    for (ParserFile parserFile : parserFiles) {
      ParserClass parserClass = PsiTreeUtil.getChildOfType(parserFile, ParserClass.class);
      if (parserClass != null && parserClass.getName().equals(getName())) {
        return parserClass;
      }
    }

    List<PsiElement> psiElements = ParserResolveUtil.collectClassIncludes(getContainingFile());
    for (PsiElement element : psiElements) {
      ParserClass parserClass = (ParserClass) element;
      getProject().getComponent(ParserFileIndex.class).contributeClass(parserClass);
      if (parserClass.getName().equals(getName())) {
        return parserClass;
      }
    }

    return null;
  }

  public String getCanonicalText() {
    return null;
  }

  public PsiElement handleElementRename(String newElementName) throws IncorrectOperationException {
    return setName(newElementName);
  }

  public PsiElement bindToElement(PsiElement element) throws IncorrectOperationException {
    return null;
  }

  public boolean isReferenceTo(PsiElement element) {
    return element instanceof ParserClassImpl && element == resolve();
  }

  public Object[] getVariants() {
    List<PsiElement> result = new ArrayList<PsiElement>();
    Collection<ParserFile> parserFiles = getProject().getComponent(ParserFileIndex.class).getLoadedClasses().values();
    result.addAll(ParserResolveUtil.getClassesFromFiles(parserFiles));
    return ParserLookupUtil.createSmartLookupItems(result);
  }

  public boolean isSoft() {
    return false;
  }

  public String toString() {
    return "ParserDocConstructorInfo";
  }
}