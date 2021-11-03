package Modul3.CPRRegister;
import java.util.ArrayList;

public class Main {
    public static void main(String[]args){
        ArrayList<Person> persons = new ArrayList<Person>();
        persons.add(new Person("Gummi", 69, "1910761234"));
        persons.add(new Person("Kalli", 54, "0101551010"));
        persons.add(new Person("Siggi", 12, "0101010101"));
        persons.add(new Person("Kormákur", 1, "0311211234"));
        persons.add(new Person("Grétar", 115, "1212017268"));
        
        for(int i = 0; i < persons.size(); i++){
            if(persons.get(i).getCPR() == "0101010101") {
                System.out.println(persons.get(i));
            }
        }
    }
}
