package Lagersystem;

class ExpiredItemAddedException extends Exception { 
    public ExpiredItemAddedException () {
        super("Attempted to add expired product to database");
    }
}
        