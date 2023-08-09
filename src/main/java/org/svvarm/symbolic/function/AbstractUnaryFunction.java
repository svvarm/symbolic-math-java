package org.svvarm.symbolic.function;

import java.util.List;
import lombok.Getter;
import org.svvarm.symbolic.Expression;

/**
 * An abstract implementation of a function with a single argument.
 *
 * @since 1.0
 */
public abstract class AbstractUnaryFunction extends AbstractFunction {

  /**
   * Returns the singular argument of the function.
   *
   * @return function argument
   */
  public abstract Expression getArgument();

  @Getter(lazy = true)
  private final List<Expression> arguments = List.of(getArgument());

  @Override
  protected String joinArgumentMathStrings(final CharSequence delimiter) {
    // optimized for singular argument
    return getArgument().asMathString();
  }

  /**
   * Returns the simplified argument; to avoid copying, the original argument reference is returned
   * if the simplified argument is {@link Object#equals(Object) equals} to the current argument.
   *
   * @return simplified argument
   */
  protected Expression simplifyArgument() {
    final Expression argument = getArgument();
    final Expression simplified = argument.simplify();
    return argument.equals(simplified) ? argument : simplified;
  }
}
