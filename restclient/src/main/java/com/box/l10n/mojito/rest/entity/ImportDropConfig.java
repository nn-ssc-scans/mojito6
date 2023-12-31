package com.box.l10n.mojito.rest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Configuration to trigger the import drop process
 *
 * @author aloison
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImportDropConfig {

  /**
   * Status of a text unit variant.
   *
   * <p>The usual workflow is the following (action in parenthesis):
   *
   * <p>NEW / TRANSLATION_NEEDED --> (translate) --> REVIEW_NEEDED --> (review) --> APPROVED
   *
   * <p>NEW is text unit that don't translation yet in the TM.
   */
  public enum Status {

    /**
     * Indicates that translation is needed for that text unit and locale.
     *
     * <p>Usually the case when string has been leveraged and the translation must be re-done or if
     * the translation is improper and was flagged.
     */
    TRANSLATION_NEEDED,
    /**
     * Indicates that the text unit needs to be reviewed.
     *
     * <p>Review comes after the translation. It doesn't tell you anything about the quality of the
     * translation, it can be good or bad, it just means that someone must review it.
     *
     * <p>When a string is identified as improper it should be marked for re-translation using the
     * TRANSLATION_NEEDED status along with a comment.
     */
    REVIEW_NEEDED,
    /** A string that doesn't need any work to be performed on it. */
    APPROVED;
  };

  @JsonProperty(required = true)
  Long repositoryId;

  @JsonProperty(required = true)
  Long dropId;

  Status status;

  PollableTask pollableTask;

  public Long getRepositoryId() {
    return repositoryId;
  }

  public void setRepositoryId(Long repositoryId) {
    this.repositoryId = repositoryId;
  }

  public Long getDropId() {
    return dropId;
  }

  public void setDropId(Long dropId) {
    this.dropId = dropId;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @JsonProperty
  public PollableTask getPollableTask() {
    return pollableTask;
  }

  /**
   * @JsonIgnore because this pollableTask is read only data generated by the server side, it is not
   * aimed to by external process via WS
   *
   * @param pollableTask
   */
  @JsonIgnore
  public void setPollableTask(PollableTask pollableTask) {
    this.pollableTask = pollableTask;
  }
}
