package com.teamtreehouse.core;

import com.teamtreehouse.course.Course;
import com.teamtreehouse.course.CourseRepository;
import com.teamtreehouse.review.Review;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements ApplicationRunner {

  private final CourseRepository courseRepository;

  @Autowired
  public DatabaseLoader(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
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

    List<Course> bunchOfCourses = new ArrayList<>();
    IntStream.range(0, 100)
        .forEach(i -> {
          String template = templates[i % templates.length];
          String buzzword = buzzwords[i % buzzwords.length];
          String title = String.format(template, buzzword);
          Course c = new Course(title, "http://www.example.com");
          c.addReview(new Review(i % 5, String.format("Moar %s please!!!", buzzword)));
          bunchOfCourses.add(c);
        });
    courseRepository.save(bunchOfCourses);
  }
}
