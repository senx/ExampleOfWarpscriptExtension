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
    // Checks that calls of the functions run without error
    //

    stack.exec("23 RANDOMASCII");
    stack.exec("34 false false RANDOMSTRING");
    stack.exec("{ 'count' 34 'letters' false 'numbers' false 'chars' 'warp' }  RANDOMSTRING");

    System.out.println(stack.dump(stack.depth()));
  }

}
