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

import io.warp10.WarpConfig;
import io.warp10.script.MemoryWarpScriptStack;
import io.warp10.script.WarpScriptLib;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.StringReader;

public class MyAwesomeWarpScriptExtensionTest {

  @BeforeClass
  public static void beforeClass() throws Exception {

    StringBuilder properties = new StringBuilder();
    properties.append("warp.timeunits=us");
    WarpConfig.safeSetProperties(new StringReader(properties.toString()));

    WarpScriptLib.register(new MyAwesomeWarpScriptExtension());
  }

  @Test public void RANDOMSTRING_test() throws Exception {
    MemoryWarpScriptStack stack = new MemoryWarpScriptStack(null, null);
    stack.maxLimits();

    //
    // For this example, we only check that the functions run without error, and that the sizes are correct
    //

    stack.exec("23 RANDOMASCII");
    stack.exec("DUP SIZE 23 == ASSERT");

    stack.exec("34 false false RANDOMSTRING");
    stack.exec("DUP SIZE 34 == ASSERT");

    // for a FormattedWarpScriptFunction that sets at least one of its optional arguments, its argument are passed using a Map.
    stack.exec("{ 'count' 27 'letters' false 'numbers' false 'chars' 'warp' }  RANDOMSTRING");
    stack.exec("DUP SIZE 27 == ASSERT");

    // printing
    System.out.println(stack.dump(stack.depth()));
  }
}
