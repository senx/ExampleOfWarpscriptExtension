//
//   Copyright 2019  SenX S.A.S.
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
//

package io.exampleExtension;

import io.warp10.script.WarpScriptException;
import io.warp10.script.WarpScriptStack;
import io.warp10.script.formatted.FormattedWarpScriptFunction;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Map;

public class RANDOMSTRING extends FormattedWarpScriptFunction {

  private Arguments args;
  private static final String COUNT = "count";
  private static final String LETTERS = "letters";
  private static final String NUMBERS = "numbers";
  private static final String START = "start";
  private static final String END = "end";
  private static final String CHARS = "chars";

  @Override
  protected Arguments getArguments() {
    return args;
  }

  public RANDOMSTRING (String name) {
    super(name);

    getDocstring().append("Creates a random string based on a variety of options.");

    args = new ArgumentsBuilder()
      .addArgument(Long.class, COUNT,  "The length of random string to create")
      .addArgument(Boolean.class, LETTERS,  "Only allow letters?")
      .addArgument(Boolean.class, NUMBERS,  "Only allow numbers?")
      .addOptionalArgument(Long.class, START, "The position in set of chars to start at", 0)
      .addOptionalArgument(Long.class, END, "The position in set of chars to end before", -1)
      .addOptionalArgument(String.class, CHARS, "The set of chars to choose randoms from. If null, then it will use the set of all chars.", null)
      .build();
  }

  @Override
  protected WarpScriptStack apply(Map<String, Object> params, WarpScriptStack stack) throws WarpScriptException {

    // retrieve the arguments
    int count = ((Long) params.get(COUNT)).intValue();
    int start = ((Long) params.get(START)).intValue();
    int end = ((Long) params.get(END)).intValue();
    boolean letters = Boolean.TRUE.equals(params.get(LETTERS));
    boolean numbers = Boolean.TRUE.equals(params.get(NUMBERS));
    char[] chars = ((String) params.get(CHARS)).toCharArray();

    // call functions and return result
    String result = RandomStringUtils.random(count, start, end, letters, numbers, chars);
    stack.push(result);
    return stack;
  }
}
