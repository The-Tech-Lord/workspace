void setup() {
    // Show if you're a winner or loser
    for (int i = 2; i <= 9; i++)
        pinMode(i, OUTPUT);

    // Attempt Number Choosing Stage
    for (int i = 10; i <= 13; i++)
        pinMode(i, INPUT);

    // Gameover Send
    pinMode(A2, OUTPUT);

    Serial.begin(9600);
}

// Break the other Arduino out of an infinite while-loop
void unlock(int ms) {
    digitalWrite(A0, HIGH);
    delay(ms);
    digitalWrite(A0, LOW);
}

void correct(void) {
    /* Flash Pattern
     * 11111111 00000000 11111111 00000000
     */
    for (int i = 0; i < 5; i++) {
        for (int j = 2; j <= 9; j++)
            digitalWrite(j, HIGH);
        delay(500);

        for (int j = 2; j <= 9; j++)
            digitalWrite(j, LOW);
        delay(500);
    }

    // Ensure LEDs are off for next game
    for (int i = 2; i <= 9; i++)
        digitalWrite(i, LOW);
    
    unlock(300);
}

void incorrect(void) {
    /* Flash Pattern
     * 00001111 11110000 00001111 11110000
     */
    for (int i = 0; i < 5; i++) {
        for (int j = 2; j <= 5; j++)
            digitalWrite(j, HIGH);
        delay(500);

        for (int j = 2; j <= 5; j++)
            digitalWrite(j, LOW);
				
        for (int j = 6; j <= 9; j++)
            digitalWrite(j, HIGH);
        delay(500);

        for (int j = 6; j <= 9; j++)
            digitalWrite(j, LOW);
    }

    // Ensure LEDs are off for next attempt
    for (int i = 2; i <= 9; i++)
        digitalWrite(i, LOW);
}

void game_over(void) {
    /* Flash Pattern
     * 01010101 10101010 01010101 10101010
     */
    for (int i = 0; i < 5; i++) {
        for (int j = 2; j <= 9; j++)
            if (j % 2 == 0)
                digitalWrite(j, HIGH);
        delay(500);

        for (int j = 2; j <= 9; j++)
            if (j % 2 == 0)
                digitalWrite(j, LOW);

        for (int j = 2; j <= 9; j++)
            if (j % 2 != 0)
                digitalWrite(j, HIGH);
        delay(500);

        for (int j = 2; j <= 9; j++)
            if (j % 2 != 0) 
                digitalWrite(j, LOW);
    }

    // Ensure LEDs are off for next attempt
    for (int i = 2; i <= 9; i++)
        digitalWrite(i, LOW);
}

void attempts_display(int attempt) {
    for (int i = 2; i <= attempt + 1; i++)
        digitalWrite(i, HIGH);
}

void attempts_undisplay() {
    for (int i = 2; i <= 9; i++)
        digitalWrite(i, LOW);
}

void loop() {
    int hardcoded_attempts = 3;
    int attempts = 0;

    // Attempt Number Choosing Stage
    while (true) {
        if (digitalRead(A1) == HIGH) {
            // Attempt limit
            attempts = (digitalRead(10) | digitalRead(11) << 1 | digitalRead(12) << 2 | digitalRead(13) << 3);

            // Cap amount attempts to 8
            if (attempts > 8)
                attempts = 8;
            if (attempts == 0)
                attempts = hardcoded_attempts;

            break;
        }
    }

    unlock(600);

    // Check guesses
    while (true) {
        attempts_display(attempts);
        if (digitalRead(1) == HIGH) {
            attempts_undisplay();
            correct();
            break;
        } else if (digitalRead(0) == HIGH) {
            attempts--;

            if (attempts <= 0) {
                game_over();

                digitalWrite(A2, HIGH);
                delay(500);
                digitalWrite(A2, LOW);

                break;
            }

            incorrect();
            unlock(500);
        }
    }

    digitalWrite(A3, LOW);  // Successful reset of program
}
