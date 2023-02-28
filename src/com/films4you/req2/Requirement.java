package com.films4you.req2;

import com.films4you.main.RequirementInterface;
import com.films4you.main.TaskNotAttemptedException;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Your class description here.

 * @author Your name
 *
 */
public class Requirement implements RequirementInterface {
  
  @Override
  public @Nullable String getValueAsString() {
    throw new TaskNotAttemptedException();
  }

  @Override
  public @NonNull String getHumanReadable() {
    throw new TaskNotAttemptedException();
  }

}
