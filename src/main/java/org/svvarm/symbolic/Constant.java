package org.svvarm.symbolic;

import java.util.Map;
import lombok.RequiredArgsConstructor;

/**
 *
 */
@RequiredArgsConstructor
public enum Constant implements Expression {
  ZERO("0"),

  ONE("1"),

  PI("π");


  private final String value;

  @Override
  public String asMathString() {
    return value;
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
