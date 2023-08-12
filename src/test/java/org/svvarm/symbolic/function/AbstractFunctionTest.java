package org.svvarm.symbolic.function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.svvarm.symbolic.Variable.variable;

import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.svvarm.symbolic.Expression;
import org.svvarm.symbolic.Variable;

@ExtendWith(MockitoExtension.class)
class AbstractFunctionTest {

  @Spy
  private AbstractFunction subject;

  @Mock
  private Expression argument;

  @Test
  void testJoinArgumentMathStrings() {
    when(subject.getArguments()).thenReturn(List.of(variable("x"), variable("y")));

    assertThat(subject.joinArgumentMathStrings(", "), is("x, y"));
    assertThat(subject.joinArgumentMathStrings("+"), is("x+y"));
  }

  @Test
  void testAsMathString() {
    when(subject.getArguments()).thenReturn(List.of(variable("x"), variable("y")));

    final String mathString = subject.asMathString();
    assertThat(mathString, startsWith("AbstractFunction"));
    assertThat(mathString, endsWith("(x, y)"));
  }

  @Test
  void testMapArguments() {
    when(subject.getArguments()).thenReturn(List.of(argument));

    final Expression mapped = mock(Expression.class);
    final UnaryOperator<Expression> op = mock(UnaryOperator.class);
    when(op.apply(argument)).thenReturn(mapped);

    assertThat(subject.mapArguments(op), contains(mapped));
  }

  @Test
  void testEvaluate_whenEmptyMap() {
    assertThat(subject.evaluate(Map.of()), sameInstance(subject));
  }

  @Test
  void testEvaluate_whenNonEmptyMap() {
    when(subject.getArguments()).thenReturn(List.of(argument));

    final Expression evaluated = mock(Expression.class);
    final Map<Variable, Expression> values = Map.of(variable("x"), variable("y"));
    when(argument.evaluate(values)).thenReturn(evaluated);

    final Expression result = mock(Expression.class);
    when(subject.makeNew(List.of(evaluated))).thenReturn(result);

    assertThat(subject.evaluate(values), is(result));
  }

  @Test
  void testSimplifyArguments() {
    when(subject.getArguments()).thenReturn(List.of(argument));

    final Expression simplified = mock(Expression.class);
    when(argument.simplify()).thenReturn(simplified);

    assertThat(subject.simplifyArguments(), contains(simplified));
  }

  @Test
  void testMakeNewIfNeeded_whenNeeded() {
    when(subject.getArguments()).thenReturn(List.of(argument));

    final List<Expression> differentArgs = List.of();
    final Expression result = mock(Expression.class);
    when(subject.makeNew(differentArgs)).thenReturn(result);

    assertThat(subject.makeNewIfNeeded(differentArgs), is(result));
  }

  @Test
  void testMakeNewIfNeeded_whenNotNeeded() {
    final List<Expression> args = List.of(argument);
    when(subject.getArguments()).thenReturn(args);

    assertThat(subject.makeNewIfNeeded(args), sameInstance(subject));
  }
}
