package org.svvarm.symbolic;

import java.util.regex.Pattern;

/**
 * A variable with name. The name must only have alphanumeric character.
 *
 * @since 1.0
 */
public record Variable(String name) implements Expression {

  // https://stackoverflow.com/questions/17564088/how-to-form-a-regex-to-recognize-correct-declaration-of-variable-names
  private static final Pattern NAME_PATTERN = Pattern.compile("^[_a-z]\\w*$");

  public Variable {
    if (!NAME_PATTERN.matcher(name).matches()) {
      throw new IllegalArgumentException("invalid variable name");
    }
  }

  @Override
  public String mathString() {
    return name;
  }

  @Override
  public Expression simplify() {
    return this;
  }
}
