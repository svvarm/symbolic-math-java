package org.svvarm.symbolic.function;

import static java.util.stream.Collectors.joining;

import java.util.List;
import org.svvarm.symbolic.Expression;
import org.svvarm.symbolic.Function;

/**
 * An abstract implementation of {@link Function} interface.
 *
 * @since 1.0
 */
public abstract class AbstractFunction implements Function {

  /**
   * Joins the arguments' mathematical representations with the given delimiter.
   *
   * @param delimiter the delimiter to join the strings
   * @return joined string
   */
  protected String joinArgumentMathStrings(final CharSequence delimiter) {
    return getArguments().stream()
        .map(Expression::asMathString)
        .collect(joining(delimiter));
  }

  @Override
  public String asMathString() {
    return getClass().getSimpleName() + "(" + joinArgumentMathStrings(", ") + ")";
  }

  /**
   * Returns the simplified arguments; to avoid copying, the original argument references are
   * returned if the simplified arguments are {@link Object#equals(Object) equal} to the current
   * arguments.
   *
   * @return simplified arguments
   */
  protected List<Expression> simplifyArguments() {
    final List<Expression> arguments = getArguments();
    final List<Expression> simplified = arguments.stream()
        .map(Expression::simplify)
        .toList();

    return arguments.equals(simplified) ? arguments : simplified;
  }
}
