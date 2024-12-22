package com.paresh.practice.java8.streams;

import java.util.*;
import java.util.stream.Collectors;

public class StreamsPractice {


    List<Student> list = Arrays.asList(
            new Student(1, "Rohit", "Mall", 30, "Male", "Mechanical Engineering", 2015, "Mumbai", 122),
            new Student(2, "Pulkit", "Singh", 56, "Male", "Computer Engineering", 2018, "Delhi", 67),
            new Student(3, "Ankit", "Patil", 25, "Female", "Mechanical Engineering", 2019, "Kerala", 164),
            new Student(4, "Satish Ray", "Malaghan", 30, "Male", "Mechanical Engineering", 2014, "Kerala", 26),
            new Student(5, "Roshan", "Mukd", 23, "Male", "Biotech Engineering", 2022, "Mumbai", 12),
            new Student(6, "Chetan", "Star", 24, "Male", "Mechanical Engineering", 2023, "Karnataka", 90),
            new Student(7, "Arun", "Vittal", 26, "Male", "Electronics Engineering", 2014, "Karnataka", 324),
            new Student(8, "Nam", "Dev", 31, "Male", "Computer Engineering", 2014, "Karnataka", 433),
            new Student(9, "Sonu", "Shankar", 27, "Female", "Computer Engineering", 2018, "Karnataka", 7),
            new Student(10, "Shubham", "Pandey", 26, "Male", "Instrumentation Engineering", 2017, "Mumbai", 98));

    public static void main(String ... args) throws Exception {

        StreamsPractice practice = new StreamsPractice();

        //1- Find list of students whose first name starts with alphabet A
        List<Student> listA = practice.list.stream().filter(a -> a.getFirstName().startsWith("A")).collect(Collectors.toList());
        System.out.println(("List of students whose name starts with letter A : " + listA));

        //2- Group The Student By Department Names
        Map<String, List<Student>> groupByDept = practice.list.stream().collect(Collectors.groupingBy(Student::getDepartmantName));
        System.out.println("Students grouped by the department names : "+groupByDept);

        //3- Find the total count of student using stream
        long noOfStuds = practice.list.stream().count();
        System.out.println("Total count of students : "+noOfStuds);

        //4. find count of students in each department
        Map<String,Long> studCountByDept = practice.list.stream().collect(Collectors.groupingBy
                (Student::getDepartmantName,Collectors.counting()));
        studCountByDept.forEach((department, count) ->
                System.out.println("Department: " + department + ", Count: " + count));

        //5. find names of all student griup by department
        Map<String, Set<String>> departmentToStudentNames = practice.list.stream()
                .collect(Collectors.groupingBy(Student::getDepartmantName,                   // Group by department
                        Collectors.mapping(Student::getFirstName, Collectors.toSet())  // Collect employee names in a Set
                ));

        departmentToStudentNames.forEach((dept,studName) -> System.out.println("Name: " + studName +" Dept: "+dept));

        /**
         * groupingBy(Function),
         * groupingByConcurrent(Function),
         * groupingBy(Function, Collector),
         * groupingByConcurrent(Function, Collector),
         * groupingBy(Function, Supplier, Collector)
         * groupingByConcurrent(Function, Supplier, Collector)
         */

        //6. Find the max age of student
         Student maxAgeStud = practice.list.stream().max(Comparator.comparing(Student::getAge)).orElseThrow(Exception::new);
        maxAgeStud.getAge();

         OptionalInt maxAge = practice.list.stream().mapToInt(stud -> stud.getAge()).max();
         //practice.list.stream().

        //7.  Find all departments names

        List<String> departmentsNames = practice.list.stream().collect(Collectors.mapping(Student::getDepartmantName,Collectors.toSet())).stream().distinct().collect(Collectors.toList());

        Set<String> departments = practice.list.stream().map(Student::getDepartmantName).collect(Collectors.toSet());

        System.out.println("All distinct department names : "+departments);

        System.out.println("All department names : "+departmentsNames);

        // Find the list of students whose age is less than 30

        List<Student> ageLessThan30 = practice.list.stream().filter(student -> student.getAge() < 30).collect(Collectors.toList());
        System.out.println("Students with Age less than 30 "+ageLessThan30);


        //8- Find the list of students whose rank is in between 50 and 100
        List<Student> rankWise = practice.list.stream().filter(student -> student.getRank() >=50 && student.getRank() <= 100)
                .collect(Collectors.toList());
        System.out.println("List of students whose rank is between 50 and 100 : "+rankWise);

        //9- Find the average age of male and female students
        Map<String,Double> avgAgeByGender = practice.list.stream().collect(Collectors.groupingBy(Student::getGender,
                Collectors.averagingInt(Student::getAge)));
        avgAgeByGender.forEach((gender,avgAge) -> System.out.println("Gender: "+gender+" Avg Age: "+avgAge));


        //10- Find the department who is having maximum number of students

        String deptWithMaxEmp = practice.list.stream().collect(Collectors.groupingBy(Student::getDepartmantName,Collectors.counting()))
                .entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).get().getKey();
//        Entry<String, Long> entry = list.stream()
//                .collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.counting())).entrySet().stream()
//                .max(Map.Entry.comparingByValue()).get();
        System.out.println("Department having maximum number of students : "+entry);

        System.out.println("deptWithMaxEmp : "+deptWithMaxEmp);

        //TODO Practice below
        /**
         * 11- Find the Students who stays in Delhi and sort them by their names
         * List<Student> lstDelhistudent = list.stream().filter(dt -> dt.getCity().equals("Delhi"))
         *     .sorted(Comparator.comparing(Student::getFirstName)).collect(Collectors.toList());
         * System.out.println("List of students who stays in Delhi and sort them by their names : "+lstDelhistudent);
         * 12- Find the average rank in all departments
         * Map<String, Double> collect = list.stream()
         *     .collect(Collectors.groupingBy(Student::getDepartmantName, Collectors.averagingInt(Student::getRank)));
         * System.out.println("Average rank in all departments  : "+collect);
         * 13- Find the highest rank in each department
         * Map<String, Optional<Student>> studentData = list.stream().collect(Collectors.groupingBy(Student::getDepartmantName,
         *     Collectors.minBy(Comparator.comparing(Student::getRank))));
         * System.out.println("Highest rank in each department  : "+studentData);
         * 14- Find the list of students and sort them by their rank
         * List<Student> stuRankSorted = list.stream().sorted(Comparator.comparing(Student::getRank))
         *     .collect(Collectors.toList());
         * System.out.println("List of students sorted by their rank  : "+stuRankSorted);
         * 15- Find the student who has second rank
         * Student student = list.stream().sorted(Comparator.comparing(Student::getRank)).skip(1).findFirst().get();
         * System.out.println("Second highest rank student  : "+student);
         *
         *
         *What is the difference between map and flatMap?

         flatMap: Used for transforming each element into a Stream and then flattening the result into a single Stream.

         map: Transforms each element of the stream into another object. The output is a Stream of the same size

         map: Transforms each element of the stream into another object. The output is a Stream of the same size
map: Transforms each element of the stream into another object. The output is a Stream of the same size
         
         */



    }

}


/**
 * The Class Student.
 */
class Student {

    /** The id. */
    private int id;

    /** The first name. */
    private String firstName;

    /** The last name. */
    private String lastName;

    /** The age. */
    private int age;

    /** The gender. */
    private String gender;

    /** The departmant name. */
    private String departmantName;

    /** The joined year. */
    private int joinedYear;

    /** The city. */
    private String city;

    /** The rank. */
    private int rank;

    /**
     * Instantiates a new employee.
     *
     * @param id the id
     * @param firstName the first name
     * @param lastName the last name
     * @param age the age
     * @param gender the gender
     * @param departmantName the departmant name
     * @param joinedYear the joined year
     * @param city the city
     * @param rank the rank
     */
    public Student(int id, String firstName, String lastName, int age, String gender, String departmantName,
                   int joinedYear, String city, int rank) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.departmantName = departmantName;
        this.joinedYear = joinedYear;
        this.city = city;
        this.rank = rank;
    }

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets the age.
     *
     * @param age the new age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the gender.
     *
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender.
     *
     * @param gender the new gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Gets the departmant name.
     *
     * @return the departmant name
     */
    public String getDepartmantName() {
        return departmantName;
    }

    /**
     * Sets the departmant name.
     *
     * @param departmantName the new departmant name
     */
    public void setDepartmantName(String departmantName) {
        this.departmantName = departmantName;
    }

    /**
     * Gets the joined year.
     *
     * @return the joined year
     */
    public int getJoinedYear() {
        return joinedYear;
    }

    /**
     * Sets the joined year.
     *
     * @param joinedYear the new joined year
     */
    public void setJoinedYear(int joinedYear) {
        this.joinedYear = joinedYear;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city the new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the rank.
     *
     * @return the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * Sets the rank.
     *
     * @param rank the new rank
     */
    public void setRank(int rank) {
        this.rank = rank;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", age=" + age
                + ", gender=" + gender + ", departmantName=" + departmantName + ", joinedYear=" + joinedYear + ", city="
                + city + ", rank=" + rank + "]";
    }

}
