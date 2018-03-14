package Students;

public class Student {
    private static int count;
    private String name;

    public Student(String name) {
        this.name = name;
        this.count++;
    }

    public static int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }
}
