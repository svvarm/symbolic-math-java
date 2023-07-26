package org.svvarm.symbolic;

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
  String mathString();

  /**
   * Simplifies the expression.
   *
   * @return simplified expression
   */
  Expression simplify();
}
