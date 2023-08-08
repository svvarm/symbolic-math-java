package org.svvarm.symbolic;

import java.util.List;

/**
 * A mathematical function.
 *
 * @since 1.0
 */
public interface Function extends Expression {

  /**
   * Returns the arguments.
   *
   * @return function arguments
   */
  List<Expression> getArguments();
}
