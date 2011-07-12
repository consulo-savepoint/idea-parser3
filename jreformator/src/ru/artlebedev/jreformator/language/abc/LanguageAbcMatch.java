package ru.artlebedev.jreformator.language.abc;

/**
 * jreformator
 * <p/>
 * Based on code originally written by <a href="mailto:vlalek@design.ru">Vladimir Tokmakov</a>
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

public class LanguageAbcMatch implements Comparable {
  private int i;
  private String name;
  private int matchesCount;

  public LanguageAbcMatch(int i, String name, int matchesCount) {
    this.i = i;
    this.name = name;
    this.matchesCount = matchesCount;
  }

  public int getI() {
    return i;
  }

  public String getName() {
    return name;
  }

  public int getMatchesCount() {
    return matchesCount;
  }

  @Override
  public int compareTo(Object o) {
    if(getMatchesCount() > ((LanguageAbcMatch) o).getMatchesCount()) {
      return -1;
    } else {
      return 1;
    }
  }
}
