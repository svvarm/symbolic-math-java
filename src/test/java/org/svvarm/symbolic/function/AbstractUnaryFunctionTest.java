package org.svvarm.symbolic.function;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.svvarm.symbolic.Expression;
import org.svvarm.symbolic.Variable;

@ExtendWith(MockitoExtension.class)
class AbstractUnaryFunctionTest {

  @Spy
  private AbstractUnaryFunction subject;

  @Test
  void testGetArguments() {
    final Expression argument = Variable.of("x");
    when(subject.getArgument()).thenReturn(argument);

    assertThat(subject.getArguments(), contains(argument));
  }

  @Test
  void testJoinArgumentMathStrings() {
    final Expression argument = Variable.of("x");
    when(subject.getArgument()).thenReturn(argument);

    assertThat(subject.joinArgumentMathStrings(""), is(argument.asMathString()));
  }

  @Test
  void testSimplifyArgument_whenSimplification_returnSimplified() {
    final Expression argument = mock(Expression.class);
    final Expression simplified = mock(Expression.class);
    when(argument.simplify()).thenReturn(simplified);
    when(subject.getArgument()).thenReturn(argument);

    assertThat(subject.simplifyArgument(), is(simplified));
  }

  @Test
  void testSimplifyArgument_whenNoSimplifications_returnOriginal() {
    final Expression argument = mock(Expression.class);
    when(argument.simplify()).thenReturn(argument);
    when(subject.getArgument()).thenReturn(argument);

    assertThat(subject.simplifyArgument(), is(argument));
  }
}
