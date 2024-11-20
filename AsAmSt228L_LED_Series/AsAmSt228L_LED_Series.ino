void setup() {
    // Main connections to LEDs
    for (int i = 2; i <= 9; i++) {
        pinMode(i, OUTPUT);
    }

    // Shorts the circuit to turn all LEDs off
    pinMode(13, OUTPUT);
}

void loop() {
    int delay_ms = 2000;

    // Initialize pins 3-8 and 13 to LOW
    for (int i = 2; i <= 8; i++) {
        digitalWrite(i, LOW);
    }

    // It's show time BD
    delay(delay_ms);
    for (int i = 2; i <= 8; i++) {
        digitalWrite(i, HIGH);
        delay(delay_ms);
    }

    // Turn off all LEDs
    delay(3000);
}
