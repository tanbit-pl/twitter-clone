package pl.tanbit.hsbctwitterclone.repository;

import org.springframework.stereotype.Repository;
import pl.tanbit.hsbctwitterclone.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by piotrn on 23.10.17.
 */
@Repository
public class UserRepository {
  private Map<String, User> repository = new ConcurrentHashMap<>();

  public User find(String userId) {
    return repository.computeIfAbsent(userId, id -> new User(id));
  }

  public User follow(String userId, String followUserId) {
    User user = find(userId);
    User followUser = find(followUserId);
    user.getFollowed().add(followUser.getId());
    return user;
  }

  public List<User> all() {
    return new ArrayList<>(repository.values());
  }
}
