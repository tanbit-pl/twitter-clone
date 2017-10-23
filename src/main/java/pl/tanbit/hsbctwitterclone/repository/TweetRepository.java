package pl.tanbit.hsbctwitterclone.repository;

import org.springframework.stereotype.Repository;
import pl.tanbit.hsbctwitterclone.model.Tweet;
import pl.tanbit.hsbctwitterclone.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by piotrn on 23.10.17.
 */
@Repository
public class TweetRepository {
  private List<Tweet> repository = new ArrayList<>(1000);

  public Tweet create(User user, String message) {
    Tweet tweet = new Tweet();
    tweet.setUser(user.getId());
    tweet.setMessage(message);
    tweet.setCreated(LocalDateTime.now());
    repository.add(tweet);
    return tweet;
  }

  public List<Tweet> getWall(User user) {
    return repository.stream()
            .filter(tweet -> user.getId().equals(tweet.getUser()))
            .sorted(Tweet::compareTo)
            .collect(toList());
  }

  public List<Tweet> getTimeline(User user) {
    return repository.stream()
            .filter(tweet -> user.getFollowed().contains(tweet.getUser()))
            .sorted(Tweet::compareTo)
            .collect(toList());
  }
}
