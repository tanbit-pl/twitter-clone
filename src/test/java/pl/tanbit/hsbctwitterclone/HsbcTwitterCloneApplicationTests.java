package pl.tanbit.hsbctwitterclone;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.tanbit.hsbctwitterclone.controller.UserController;
import pl.tanbit.hsbctwitterclone.model.Tweet;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HsbcTwitterCloneApplicationTests {

  private static final String USER = "User";
  private static final String FOLLOWED_USER_1 = "FollowedUser1";
  private static final String FOLLOWED_USER_2 = "FollowedUser2";
  private static final String TWEET1 = "Test_tweet_1";
  private static final String TWEET2 = "Test_tweet_2";

  @Autowired
  private UserController userController;

  @Test
  public void shouldGetWallWhenUserTweets() throws InterruptedException {
    userController.postTweet(USER, TWEET1);
    sleep(1);
    userController.postTweet(USER, TWEET2);
    List<Tweet> tweets = userController.getWall(USER);

    assertEquals(tweets.size(), 2);
    assertEquals(tweets.get(0).getMessage(), TWEET2);
    assertEquals(tweets.get(1).getMessage(), TWEET1);
  }

  @Test
  public void shouldGetTimelineWhenOtherUsersTweets() throws InterruptedException {
    userController.postTweet(FOLLOWED_USER_1, TWEET1);
    sleep(1);
    userController.postTweet(FOLLOWED_USER_2, TWEET2);
    userController.followOtherUser(USER, FOLLOWED_USER_1);
    userController.followOtherUser(USER, FOLLOWED_USER_2);
    List<Tweet> tweets = userController.getTimeline(USER);

    assertEquals(tweets.size(), 2);
    assertEquals(tweets.get(0).getMessage(), TWEET2);
    assertEquals(tweets.get(1).getMessage(), TWEET1);
  }
}
