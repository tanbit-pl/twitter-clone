package pl.tanbit.hsbctwitterclone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tanbit.hsbctwitterclone.model.Tweet;
import pl.tanbit.hsbctwitterclone.model.User;
import pl.tanbit.hsbctwitterclone.repository.TweetRepository;
import pl.tanbit.hsbctwitterclone.repository.UserRepository;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

/**
 * Created by piotrn on 23.10.17.
 */
@RestController
@RequestMapping("api/v1/users")
@Validated
public class UserController {

  private static final int MAX_TWEET_SIZE = 140;

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private TweetRepository tweetRepository;

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  public List<User> getUsers() {
    return userRepository.all();
  }

  @PostMapping(path = "{userId}/tweet", consumes = TEXT_PLAIN_VALUE, produces = APPLICATION_JSON_VALUE)
  public ResponseEntity postTweet(@PathVariable String userId, @RequestBody String tweet) {
    if (tweet.length() > MAX_TWEET_SIZE) {
      return status(BAD_REQUEST).body("Tweet is to long " + tweet.length() + ", max " + MAX_TWEET_SIZE + " characters.");
    }
    User user = userRepository.find(userId);
    return ok(tweetRepository.create(user, tweet));
  }

  @GetMapping(path = "{userId}/wall", produces = APPLICATION_JSON_VALUE)
  public List<Tweet> getWall(@PathVariable String userId) {
    User user = userRepository.find(userId);
    return tweetRepository.getWall(user);
  }

  @GetMapping(path = "{userId}/timeline", produces = APPLICATION_JSON_VALUE)
  public List<Tweet> getTimeline(@PathVariable String userId) {
    User user = userRepository.find(userId);
    return tweetRepository.getTimeline(user);
  }

  @PutMapping(path = "{userId}/follow/{followUserId}", produces = APPLICATION_JSON_VALUE)
  public ResponseEntity followOtherUser(@PathVariable String userId, @PathVariable String followUserId) {
    if (userId.equals(followUserId)) {
      return status(BAD_REQUEST).body("User cannot follow himself");
    }
    return ok(userRepository.follow(userId, followUserId));
  }
}
