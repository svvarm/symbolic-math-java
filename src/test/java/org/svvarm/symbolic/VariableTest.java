package org.svvarm.symbolic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.svvarm.symbolic.Variable.variable;

import java.util.Map;
import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class VariableTest {

  @ParameterizedTest
  @ValueSource(strings = {
      "x",
      "x1",
      "abc",
      "variableName",
      "u_n_d_e_r_s_c_o_r_e",
  })
  void testValidNames(final String name) {
    assertThat(variable(name).asMathString(), is(name));
  }

  @ParameterizedTest
  @NullAndEmptySource
  @ValueSource(strings = {
      "s p a c e",
      "c,o,m,m,a",
      "h-y-p-h-e-n",
      "1startsWithNumber",
      "StartsWithCapital"
  })
  void testInvalidNames(final String name) {
    assertThrows(RuntimeException.class, () -> variable(name));
  }

  @Test
  void testEvaluate_whenMappingExists() {
    final Variable x = variable("x");
    final Variable y = variable("y");

    final Expression evaluated = x.evaluate(Map.of(x, y));

    assertThat(evaluated, is(y));
  }

  @Test
  void testEvaluate_whenNoMappingExists() {
    final Variable x = variable("x");

    final Expression evaluated = x.evaluate(Map.of());

    assertThat(evaluated, is(x));
  }

  @Test
  void testSimplify() {
    final Variable x = variable("x");

    assertThat(x.simplify(), sameInstance(x));
  }

  @Test
  void testEquality() {
    EqualsVerifier.forClass(Variable.class).verify();
  }
}
