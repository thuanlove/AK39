package models;

public class Person {
    String name;
    String height;
    String mass;
    String hairColor;
    String eyeColor;
    String skinColor;
    String birthYear;
    String gender;

    public Person(String name, String height, String mass, String hairColor, String eyeColor, String skinColor, String birthYear, String gender) {
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hairColor = hairColor;
        this.eyeColor = eyeColor;
        this.skinColor = skinColor;
        this.birthYear = birthYear;
        this.gender = gender;
    }

    void info(){
        System.out.println(this.name);
        System.out.println(this.height);
        System.out.println(this.mass);
        System.out.println(this.hairColor);
        System.out.println(this.eyeColor);
        System.out.println(this.skinColor);
        System.out.println(this.birthYear);
        System.out.println(this.gender);
    }
}
