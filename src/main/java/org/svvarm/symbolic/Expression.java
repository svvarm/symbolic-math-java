package org.svvarm.symbolic;

import java.util.Map;

/**
 * A mathematical expression. Expression is the basis for other symbolic objects.
 *
 * @since 1.0
 */
public interface Expression {

  /**
   * Returns a string representation of the expression.
   *
   * @return string representation of the expression
   */
  String asMathString();

  /**
   * Simplifies the expression.
   *
   * @return simplified expression
   */
  Expression simplify();

  /**
   * Evaluates the variables with the given expressions.
   *
   * @param values the map of variables
   * @return the evaluated expression
   */
  Expression evaluate(Map<Variable, Expression> values);
}
