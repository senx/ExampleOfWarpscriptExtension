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

import io.warp10.script.NamedWarpScriptFunction;
import io.warp10.script.WarpScriptException;
import io.warp10.script.WarpScriptStack;
import io.warp10.script.WarpScriptStackFunction;

import org.apache.commons.lang3.RandomStringUtils;

public class RANDOMASCII extends NamedWarpScriptFunction implements WarpScriptStackFunction {

  public RANDOMASCII(String name) {
    super(name);
  }

  public Object apply(WarpScriptStack stack) throws WarpScriptException {

    // get the argument and check its type
    Object o = stack.pop();
    if (!(o instanceof Long)) {
      throw new WarpScriptException(getName() + " expects a LONG on top of the stack.");
    }

    // convert the argument anc call function
    int count = ((Long) o).intValue();
    String result = RandomStringUtils.randomAscii(count);

    // return the result
    stack.push(result);
    return stack;
  }
}
