package pl.tanbit.hsbctwitterclone.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by piotrn on 23.10.17.
 */
public class User {
  private String id;
  private Set<String> followed;

  public User() {
  }

  public User(String id) {
    this.id = id;
    this.followed = new HashSet<>(1);
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Set<String> getFollowed() {
    return followed;
  }

  public void setFollowed(Set<String> followed) {
    this.followed = followed;
  }

  @Override
  public String toString() {
    return "User{id='" + id + "\'}";
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return id != null ? id.equals(user.id) : user.id == null;
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
