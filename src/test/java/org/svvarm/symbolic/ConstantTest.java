package org.svvarm.symbolic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class ConstantTest {

  @ParameterizedTest
  @EnumSource(Constant.class)
  void testSimplify(final Constant c) {
    assertThat(c.simplify(), sameInstance(c));
  }

  @ParameterizedTest
  @EnumSource(Constant.class)
  void testEvaluate(final Constant c) {
    assertThat(c.evaluate(Map.of()), sameInstance(c));
  }

  @Test
  void testAsMathString() {
    assertThat(Constant.ZERO.asMathString(), is("0"));
    assertThat(Constant.ONE.asMathString(), is("1"));
    assertThat(Constant.PI.asMathString(), is("π"));
  }
}
