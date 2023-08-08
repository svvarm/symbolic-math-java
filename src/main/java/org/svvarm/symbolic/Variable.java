package org.svvarm.symbolic;

import java.util.Map;
import java.util.regex.Pattern;
import lombok.Data;

/**
 * A variable with name. The name must only have alphanumeric character.
 *
 * @since 1.0
 */
@Data(staticConstructor = "of")
public final class Variable implements Expression {

  // https://stackoverflow.com/questions/17564088/how-to-form-a-regex-to-recognize-correct-declaration-of-variable-names
  private static final Pattern NAME_PATTERN = Pattern.compile("^[_a-z]\\w*$");

  private final String name;

  private Variable(final String name) {
    if (!NAME_PATTERN.matcher(name).matches()) {
      throw new IllegalArgumentException("invalid variable name");
    }

    this.name = name;
  }

  @Override
  public String asMathString() {
    return name;
  }

  @Override
  public Expression simplify() {
    return this;
  }

  @Override
  public Expression evaluate(final Map<Variable, Expression> values) {
    return values.getOrDefault(this, this);
  }
}
