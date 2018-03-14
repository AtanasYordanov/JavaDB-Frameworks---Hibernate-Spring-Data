package Animals;

public abstract class Animal implements SoundProducible {
    private String name;
    private int age;
    private String gender;

    public Animal(String name, int age, String gender) {
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.equals("")){
            throw new IllegalArgumentException("Invalid input!");
        }
        for (int i = 0; i < name.length(); i++) {
            if (Character.isDigit(name.charAt(i))){
                throw new IllegalArgumentException("Invalid input!");
            }
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 1){
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender == null ||
                (!gender.equals("Male") && !gender.equals("Female"))){
            throw new IllegalArgumentException("Invalid input!");
        }
        for (int i = 0; i < gender.length(); i++) {
            if (Character.isDigit(gender.charAt(i))){
                throw new IllegalArgumentException("Invalid input!");
            }
        }
        this.gender = gender;
    }

    @Override
    public String produceSound(){
        return "Not implemented!";
    }

    @Override
    public String toString() {
        return String.format("%s%n%s %d %s", getClass().getSimpleName(),
                this.name, this.age, this.gender);
    }
}
