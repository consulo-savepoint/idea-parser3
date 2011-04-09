package ru.artlebedev.idea.plugins.parser.editor.highlighting;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import ru.artlebedev.idea.plugins.parser.ParserIcons;
import ru.artlebedev.idea.plugins.parser.file.ParserFileType;

import javax.swing.*;
import java.util.Map;

/**
 * idea-parser3: slightly useful plugin.
 * <p/>
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

public class ParserColorsAndFontsPage implements ColorSettingsPage {
  @NotNull
  @Override
  public String getDisplayName() {
    return "Parser";
  }

  @Override
  public Icon getIcon() {
    return ParserIcons.PARSER_FILE_ICON;
  }

  private static final AttributesDescriptor[] ATTRS =
          new AttributesDescriptor[]{
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_KEYWORD_ID, ParserFileSyntaxHighlighter.PARSER_KEYWORD),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_RESULT_ID, ParserFileSyntaxHighlighter.PARSER_RESULT),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_KEY_AT_SIGN_ID, ParserFileSyntaxHighlighter.PARSER_KEY_AT_SIGN),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_STRING_ID, ParserFileSyntaxHighlighter.PARSER_STRING),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_LINE_COMMENT_ID, ParserFileSyntaxHighlighter.PARSER_LINE_COMMENT),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_PARSERDOC_COMMENT_ID, ParserFileSyntaxHighlighter.PARSER_PARSERDOC_COMMENT),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_OPERATION_SIGN_ID, ParserFileSyntaxHighlighter.PARSER_OPERATION_SIGN),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_PARENTHS_ID, ParserFileSyntaxHighlighter.PARSER_PARENTHS),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_BRACKETS_ID, ParserFileSyntaxHighlighter.PARSER_BRACKETS),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_BRACES_ID, ParserFileSyntaxHighlighter.PARSER_BRACES),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_NUMBER_ID, ParserFileSyntaxHighlighter.PARSER_NUMBER),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_SEMICOLON_ID, ParserFileSyntaxHighlighter.PARSER_SEMICOLON),
                  new AttributesDescriptor(ParserFileSyntaxHighlighter.PARSER_BAD_CHARACTER_ID, ParserFileSyntaxHighlighter.PARSER_BAD_CHARACTER),
          };

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return ATTRS;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    return SyntaxHighlighter.PROVIDER.create(ParserFileType.PARSER_FILE_TYPE, null, null);
  }

  @NotNull
  @Override
  public String getDemoText() {
    return "@CLASS\n" +
            "test\n\n" +
            "@BASE\n" +
            "test2\n\n" +
            "@OPTIONS\n" +
            "locals\n\n" +
            "@auto[]\n" +
            "$result[test]\n" +
            "###\n\n" +
            "#: constructor\n" +
            "#: param param1 type string\n" +
            "@create[param1]\n" +
            "###\n\n" +
            "@method1[]\n" +
            "###\n\n" +
            "@method2[]\n" +
            "^self.method1[]\n" +
            "###";

  }

  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return null;
  }
}