package io.github.some_example_name;

/**
 * Interfaccia generica per gli oggetti collezionabili nel gioco.
 * Attualmente si tratta solamente delle monete sottoforma di cranio scheletrico sterling ruby.
 */

public interface Collect {
    float getX();
    float getY();
    // Check se è stato raccolto
    boolean isCollected();
    // Quando raccolto e isCollected è true
    void collect();
}
