package org.svvarm.symbolic.function;

import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.svvarm.symbolic.Expression;
import org.svvarm.symbolic.Variable;

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
    return getArgument().asMathString();
  }

  @Override
  public Expression evaluate(final Map<Variable, Expression> values) {
    return values.isEmpty() ? this : makeNewIfNeeded(getArgument().evaluate(values));
  }

  /**
   * Returns the simplified argument.
   *
   * @return simplified argument
   */
  protected Expression simplifyArgument() {
    return getArgument().simplify();
  }

  /**
   * Creates a new instance if the given argument is not equal to the current argument.
   *
   * @param argument the argument
   * @return current or new instance if arguments are not equal
   * @see #makeNew(Expression)
   */
  protected Expression makeNewIfNeeded(final Expression argument) {
    return getArgument().equals(argument) ? this : makeNew(argument);
  }

  @Override
  protected Expression makeNew(final List<Expression> arguments) {
    if (arguments.size() != 1) {
      throw new IllegalArgumentException("expected a singleton list");
    }

    return makeNew(arguments.get(0));
  }

  /**
   * Creates a new instance of the class with the given argument.
   *
   * @param argument the argument
   * @return new instance
   */
  protected abstract Expression makeNew(final Expression argument);
}
