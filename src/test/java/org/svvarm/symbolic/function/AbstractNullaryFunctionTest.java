package org.svvarm.symbolic.function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.svvarm.symbolic.Expression;

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
  void testEvaluate() {
    assertThat(subject.evaluate(null), sameInstance(subject));
  }

  @Test
  void testSimplify() {
    assertThat(subject.simplify(), sameInstance(subject));
  }

  @ParameterizedTest
  @ValueSource(ints = {1, 2})
  void testMakeNew_whenNotEmptyList(final int size) {
    final List<Expression> args = Stream.generate(() -> mock(Expression.class))
        .limit(size)
        .toList();

    assertThrows(IllegalArgumentException.class, () -> subject.makeNew(args));
  }

  @Test
  void testMakeNew_whenSingletonList() {
    assertThat(subject.makeNew(List.of()), sameInstance(subject));
  }
}
