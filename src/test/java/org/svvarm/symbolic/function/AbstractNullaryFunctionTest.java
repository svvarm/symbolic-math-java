package org.svvarm.symbolic.function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AbstractNullaryFunctionTest {

  @Spy
  private AbstractNullaryFunction subject;

  @Test
  void testGetArguments() {
    assertThat(subject.getArguments(), empty());
  }

  @Test
  void testJoinArgumentMathStrings() {
    assertThat(subject.joinArgumentMathStrings(","), is(""));
  }

  @Test
  void testSimplify() {
    assertThat(subject.simplify(), sameInstance(subject));
  }

  @Test
  void testEvaluate() {
    assertThat(subject.evaluate(null), sameInstance(subject));
  }
}
