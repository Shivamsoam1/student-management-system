package student;

public class Student extends Person {
    public Student(String name, int id) {
        //super ka kya use h
        super(name, id);
    }

    @Override
    public String toString() {
        return id + "," + name;
    }
}
