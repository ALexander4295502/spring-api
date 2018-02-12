package com.teamtreehouse.core;

import com.teamtreehouse.course.Course;
import com.teamtreehouse.course.CourseRepository;
import com.teamtreehouse.review.Review;
import com.teamtreehouse.user.User;
import com.teamtreehouse.user.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements ApplicationRunner {

  private final CourseRepository courseRepository;
  private final UserRepository userRepository;

  @Autowired
  public DatabaseLoader(CourseRepository courseRepository, UserRepository userRepository) {
    this.courseRepository = courseRepository;
    this.userRepository = userRepository;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Course course = new Course("Java Basics", "https://teamtreehouse.com/library/java-basics");
    course.addReview(new Review(3, "You ARE a dork!!!"));
    courseRepository.save(course);

    String[] templates = {
        "Up and Running with %s",
        "%s Basics",
        "%s for Beginners",
        "%s for Neckbeards",
        "Under the hood: %s"
    };

    String[] buzzwords = {
        "Spring REST Data",
        "Java 9",
        "Scala",
        "Groovy",
        "Hibernate",
        "Spring"
    };

    List<User> students = Arrays.asList(
        new User("jacobproffer", "Jacob",  "Proffer", "password", new String[] {"ROLE_USER"}),
        new User("mlnorman", "Mike",  "Norman", "password", new String[] {"ROLE_USER"}),
        new User("k_freemansmith", "Karen",  "Freeman-Smith", "password", new String[] {"ROLE_USER"}),
        new User("seth_lk", "Seth",  "Kroger", "password", new String[] {"ROLE_USER"}),
        new User("mrstreetgrid", "Java",  "Vince", "password", new String[] {"ROLE_USER"}),
        new User("anthonymikhail", "Tony",  "Mikhail", "password", new String[] {"ROLE_USER"}),
        new User("boog690", "AJ",  "Teacher", "password", new String[] {"ROLE_USER"}),
        new User("faelor", "Erik",  "Faelor Shafer", "password", new String[] {"ROLE_USER"}),
        new User("christophernowack", "Christopher",  "Nowack", "password", new String[] {"ROLE_USER"}),
        new User("calebkleveter", "Caleb",  "Kleveter", "password", new String[] {"ROLE_USER"}),
        new User("richdonellan", "Rich",  "Donnellan", "password", new String[] {"ROLE_USER"}),
        new User("albertqerimi", "Albert",  "Qerimi", "password", new String[] {"ROLE_USER"})
    );
    userRepository.save(students);
    userRepository.save(new User("yuanzheng", "zheng", "yuan", "chengxi0314", new String[] {"ROLE_USER", "USER_ADMIN"}));

    List<Course> bunchOfCourses = new ArrayList<>();
    IntStream.range(0, 100)
        .forEach(i -> {
          String template = templates[i % templates.length];
          String buzzword = buzzwords[i % buzzwords.length];
          String title = String.format(template, buzzword);
          Course c = new Course(title, "http://www.example.com");
          Review review = new Review((i % 5) + 1, String.format("Moar %s please!!!", buzzword));
          review.setReviewer(students.get(i % students.size()));
          c.addReview(review);
          bunchOfCourses.add(c);
        });
    courseRepository.save(bunchOfCourses);
  }
}
