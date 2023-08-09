package org.svvarm.symbolic.function;

import java.util.List;
import java.util.Map;
import org.svvarm.symbolic.Expression;
import org.svvarm.symbolic.Variable;

/**
 * An abstract implementation of a function with no arguments.
 *
 * @since 1.0
 */
public abstract class AbstractNullaryFunction extends AbstractFunction {

  @Override
  public List<Expression> getArguments() {
    return List.of();
  }

  @Override
  protected String joinArgumentMathStrings(final CharSequence delimiter) {
    // optimized for no argument
    return "";
  }

  @Override
  public Expression simplify() {
    return this;
  }

  @Override
  public Expression evaluate(final Map<Variable, Expression> values) {
    return this;
  }
}
