package org.svvarm.symbolic.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.function.UnaryOperator;
import org.svvarm.symbolic.Expression;
import org.svvarm.symbolic.Function;
import org.svvarm.symbolic.Variable;

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
    final StringJoiner joiner = new StringJoiner(delimiter);

    for (final Expression argument : getArguments()) {
      joiner.add(argument.asMathString());
    }

    return joiner.toString();
  }

  @Override
  public String asMathString() {
    return getClass().getSimpleName() + "(" + joinArgumentMathStrings(", ") + ")";
  }

  /**
   * Map the arguments using the given mapper.
   *
   * @param mapper the argument mapper
   * @return a new list with the mapped arguments
   */
  protected List<Expression> mapArguments(final UnaryOperator<Expression> mapper) {
    final ArrayList<Expression> arguments = new ArrayList<>(getArguments());
    arguments.replaceAll(mapper);
    return arguments;
  }

  @Override
  public Expression evaluate(final Map<Variable, Expression> values) {
    return values.isEmpty() ? this : makeNewIfNeeded(mapArguments(e -> e.evaluate(values)));
  }

  /**
   * Returns the simplified arguments.
   *
   * @return simplified arguments
   */
  protected List<Expression> simplifyArguments() {
    return mapArguments(Expression::simplify);
  }

  /**
   * Creates a new instance if the given argument list is not equal to the current argument list.
   *
   * @param arguments the argument list
   * @return current or new instance if argument lists are not equal
   * @see #makeNew(List)
   */
  protected Expression makeNewIfNeeded(final List<Expression> arguments) {
    return getArguments().equals(arguments) ? this : makeNew(arguments);
  }

  /**
   * Creates a new instance of the class with the given arguments.
   *
   * @param arguments the arguments
   * @return new instance
   */
  protected abstract Expression makeNew(final List<Expression> arguments);
}
