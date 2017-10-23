package pl.tanbit.hsbctwitterclone.model;

import java.time.LocalDateTime;

/**
 * Created by piotrn on 23.10.17.
 */
public class Tweet implements Comparable<Tweet>{
  private String message;
  private String user;
  private LocalDateTime created;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public LocalDateTime getCreated() {
    return created;
  }

  public void setCreated(LocalDateTime created) {
    this.created = created;
  }

  @Override
  public int compareTo(Tweet o) {
    return Math.negateExact(created.compareTo(o.created));
  }
}
