//Base class for person
class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public void talk() {
        System.out.println(name + ": I'm ready to take on the day!");
    }

    //It will be overriden
    public void performDailyRoutine() {
        System.out.println(name + ": Performing daily routine.");
    }
}

//Subsclass #1
class Engineer extends Person {
    public Engineer(String name) {
        super(name); // Call the constructor of the base class and pass the name
    }

    @Override
    public void performDailyRoutine() {
        System.out.println(name + ": Fixing bugs and typos in code.");
    }
}

//Composition class
class Motorcycle {
    private String model;

    public Motorcycle(String model) {
        this.model = model;
    }

    public void ride() {
        System.out.println("Riding the " + model + " across Long Island.");
    }
}

//Subclass #2
class Biker extends Person {
    private Motorcycle motorcycle;

    public Biker(String name, Motorcycle motorcycle) {
        super(name); // Call the constructor of the base class and pass the name
        this.motorcycle = motorcycle;
    }

    @Override
    public void performDailyRoutine() {
        System.out.println(name + ": Going for a ride on my motorcycle.");
        motorcycle.ride();
    }
}

//Main class 
public class GabrielLife {
    public static void main(String[] args) {
        Person engineer = new Engineer("Gabriel");
        Person biker = new Biker("Gabriel", new Motorcycle("Harley Davidson Sporster 1200"));

        engineer.performDailyRoutine(); // Polymorphic call
        biker.performDailyRoutine(); // Polymorphic call

        engineer.talk(); // Base class method call
        biker.talk(); // Base class method call
    }
}