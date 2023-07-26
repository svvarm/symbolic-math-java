package org.svvarm.symbolic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
  void validNames(final String name) {
    final Variable variable = new Variable(name);
    assertThat(variable.name(), is(name));
    assertThat(variable.simplify(), is(variable));
  }

  @ParameterizedTest
  @ValueSource(strings = {
      "s p a c e",
      "c,o,m,m,a",
      "h-y-p-h-e-n",
      "1startsWithNumber",
      "StartsWithCapital"
  })
  void invalidNames(final String name) {
    assertThrows(IllegalArgumentException.class, () -> new Variable(name));
  }

  @Test
  void testEvaluate_whenMappingExists_thenReturnMappedValue() {
    final Variable x = new Variable("x");
    final Variable y = new Variable("y");

    final Expression evaluated = x.evaluate(Map.of(x, y));

    assertThat(evaluated, is(y));
  }

  @Test
  void testEvaluate_whenNoMappingExists_thenReturnVariable() {
    final Variable x = new Variable("x");

    final Expression evaluated = x.evaluate(Map.of());

    assertThat(evaluated, is(x));
  }
}
