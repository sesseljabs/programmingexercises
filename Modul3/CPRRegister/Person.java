package Modul3.CPRRegister;

public class Person {
    String name;
    int age;
    String cpr;

    Person(String name, int age, String cpr){
        this.name=name;
        this.age = age;
        this.cpr = cpr;
    }
    Person(){
        name="";
        age=0;
        cpr="";
    }

    public String getName(){
        return this.name;
    }
    public int getAge(){
        return this.age;
    }
    public String getCPR(){
        return this.cpr;
    }
    public String toString(){
        return name+", "+age+", "+cpr;
    }
}
