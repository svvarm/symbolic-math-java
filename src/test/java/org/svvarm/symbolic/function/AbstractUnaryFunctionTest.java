package org.svvarm.symbolic.function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.svvarm.symbolic.Variable.variable;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.svvarm.symbolic.Expression;
import org.svvarm.symbolic.Variable;

@ExtendWith(MockitoExtension.class)
class AbstractUnaryFunctionTest {

  @Spy
  private AbstractUnaryFunction subject;

  @Mock
  private Expression argument;

  @Test
  void testGetArguments() {
    when(subject.getArgument()).thenReturn(argument);

    assertThat(subject.getArguments(), contains(argument));
    assertThat(subject.getArguments(), sameInstance(subject.getArguments())); // lazy
  }

  @Test
  void testJoinArgumentMathStrings() {
    when(subject.getArgument()).thenReturn(argument);
    when(argument.asMathString()).thenReturn("x");

    assertThat(subject.joinArgumentMathStrings(""), is(argument.asMathString()));
  }

  @Test
  void testEvaluate_whenEmptyMap() {
    assertThat(subject.evaluate(Map.of()), sameInstance(subject));
  }

  @Test
  void testEvaluate_whenNonEmptyMap() {
    when(subject.getArgument()).thenReturn(argument);

    final Expression evaluated = mock(Expression.class);
    final Map<Variable, Expression> values = Map.of(variable("x"), variable("y"));
    when(argument.evaluate(values)).thenReturn(evaluated);

    final Expression result = mock(Expression.class);
    when(subject.makeNew(evaluated)).thenReturn(result);

    assertThat(subject.evaluate(values), is(result));
  }

  @Test
  void testSimplifyArgument() {
    when(subject.getArgument()).thenReturn(argument);

    final Expression simplified = mock(Expression.class);
    when(argument.simplify()).thenReturn(simplified);

    assertThat(subject.simplifyArgument(), is(simplified));
  }

  @Test
  void testMakeNewIfNeeded_whenNeeded() {
    when(subject.getArgument()).thenReturn(argument);

    final Expression differentArg = mock(Expression.class);
    final Expression result = mock(Expression.class);
    when(subject.makeNew(differentArg)).thenReturn(result);

    assertThat(subject.makeNewIfNeeded(differentArg), is(result));
  }

  @Test
  void testMakeNewIfNeeded_whenNotNeeded() {
    when(subject.getArgument()).thenReturn(argument);

    assertThat(subject.makeNewIfNeeded(argument), sameInstance(subject));
  }

  @ParameterizedTest
  @ValueSource(ints = {0, 2})
  void testMakeNew_whenNotSingletonList(final int size) {
    final List<Expression> args = Stream.generate(() -> mock(Expression.class))
        .limit(size)
        .toList();

    assertThrows(IllegalArgumentException.class, () -> subject.makeNew(args));
  }

  @Test
  void testMakeNew_whenSingletonList() {
    final Expression result = mock(Expression.class);
    when(subject.makeNew(argument)).thenReturn(result);

    assertThat(subject.makeNew(List.of(argument)), is(result));
  }
}
