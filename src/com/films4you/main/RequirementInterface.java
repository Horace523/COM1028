package com.films4you.main;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * An interface for the requirements in the Films4You project.
 */
public interface RequirementInterface {

  /**
   * Get the value of the requirement as a string representation.
   *
   * @return The value of the requirement as a string, or null if not applicable.
   */
  public @Nullable String getValueAsString();

  /**
   * Get the human-readable representation of the requirement.
   *
   * @return The human-readable representation of the requirement as a string.
   */
  public @NonNull String getHumanReadable();
}
