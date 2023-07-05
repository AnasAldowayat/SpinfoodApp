package com.main.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class CourseTest {
    /**
     * Tests the values() method of the Course enum.
     * It retrieves all the Course enum constants using the values() method
     * and verifies the expected length and values of the array.
     */
    @Test
    public void values() {
        Course[] courses = Course.values();
        assertEquals(3, courses.length);
        // Verify the values of each enum constant
        System.out.println("The Courses are: ");
        for (Course course : courses) {
            System.out.println(course);
            assertEquals(Course.first, courses[0]);
            assertEquals(Course.main, courses[1]);
            assertEquals(Course.dessert, courses[2]);

        }

    } @Test
    public void valueOf(){
        System.out.println("Retrieved Course enum constants:");

        Course first = Course.valueOf("first");
        System.out.println("first - Retrieved: " + first);

        Course main = Course.valueOf("main");
        System.out.println("main - Retrieved: " + main);

        Course dessert = Course.valueOf("dessert");
        System.out.println("dessert - Retrieved: " + dessert);

        assertEquals(Course.first, first);
        assertEquals(Course.main, main);
        assertEquals(Course.dessert, dessert);
        System.out.println("---------------------------------------------------------------------");
    }
}