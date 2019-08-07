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

    // add a description
    getDocstring().append("Creates a random string based on a variety of options.");

    //
    // Build the arguments:
    //  - addArguments() adds a mandatory argument by its class, name and description,
    //  - addOptionalArgument() adds an optional argument (it also requires a default value),
    //  - build() creates and fixes the arguments of the function
    //

    args = new ArgumentsBuilder()
      .addArgument(Long.class, COUNT,  "The length of random string to create")
      .addArgument(Boolean.class, LETTERS,  "Only allow letters?")
      .addArgument(Boolean.class, NUMBERS,  "Only allow numbers?")
      .addOptionalArgument(Long.class, START, "The position in set of chars to start at", 0L)
      .addOptionalArgument(Long.class, END, "The position in set of chars to end before", 0L)
      .addOptionalArgument(String.class, CHARS, "The set of chars (a String) to choose randoms from. If empty, then it will use the set of all chars.", "")
      .build();

    //
    // Other methods for ArgumentBuilder() includes:
    //   - addListArgument / addOptionalListArgument: same as addArgument / addOptionalArgument but for List<class>
    //   - addMapArgument / addOptionalMapArgument: same as addArgument / addOptionalArgument but for Map<class1,class2>
    //   - firstArgIsListExpandable: first argument can be passed as a list or not, in which case the result is a list or not (on head 2.1.1 since commit 0e2db1d)
    //
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
    if (0 == chars.length) {
      chars = null;
    } else {
      end = Math.min(chars.length, end);
    }

    // call the function and return the result
    String result = RandomStringUtils.random(count, start, end, letters, numbers, chars);
    stack.push(result);
    return stack;
  }
}
