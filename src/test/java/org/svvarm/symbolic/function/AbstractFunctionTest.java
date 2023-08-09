package org.svvarm.symbolic.function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.svvarm.symbolic.Expression;
import org.svvarm.symbolic.Variable;

@ExtendWith(MockitoExtension.class)
class AbstractFunctionTest {

  @Spy
  private AbstractFunction subject;

  @Test
  void testJoinArgumentMathStrings() {
    when(subject.getArguments()).thenReturn(List.of(Variable.of("x"), Variable.of("y")));

    assertThat(subject.joinArgumentMathStrings(", "), is("x, y"));
    assertThat(subject.joinArgumentMathStrings("+"), is("x+y"));
  }

  @Test
  void testAsMathString() {
    when(subject.getArguments()).thenReturn(List.of(Variable.of("x"), Variable.of("y")));

    final String mathString = subject.asMathString();
    assertThat(mathString, startsWith("AbstractFunction"));
    assertThat(mathString, endsWith("(x, y)"));
  }

  @Test
  void testSimplifyArguments_whenSimplification_returnSimplifiedList() {
    final Expression argument = mock(Expression.class);
    final Expression simplified = mock(Expression.class);
    when(argument.simplify()).thenReturn(simplified);
    when(subject.getArguments()).thenReturn(List.of(argument));

    assertThat(subject.simplifyArguments(), contains(simplified));
  }

  @Test
  void testSimplifyArguments_whenNoSimplifications_returnOriginalList() {
    final Expression argument = mock(Expression.class);
    when(argument.simplify()).thenReturn(argument);
    final List<Expression> arguments = List.of(argument);
    when(subject.getArguments()).thenReturn(arguments);

    assertThat(subject.simplifyArguments(), sameInstance(arguments));
  }
}
