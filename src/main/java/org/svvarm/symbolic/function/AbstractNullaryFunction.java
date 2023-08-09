package org.svvarm.symbolic.function;

import java.util.List;
import org.svvarm.symbolic.Expression;

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
}
