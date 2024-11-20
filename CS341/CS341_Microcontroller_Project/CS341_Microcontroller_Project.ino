void setup() {
    // 8-bit and Carry Inputs
    pinMode(A0, INPUT);
    pinMode(A1, INPUT);
    pinMode(A2, INPUT);
    pinMode(A3, INPUT);
    pinMode(A4, INPUT);
    pinMode(A5, INPUT);
    pinMode(11, INPUT);
    pinMode(12, INPUT);
    pinMode(13, INPUT);

    // Solution check pin
    pinMode(10, INPUT);

    for (int i = 2; i <= 9; i++)
        pinMode(i, OUTPUT);
}

void correct(void) {
    /* Flash Pattern
     * 11111111
     * 00000000
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
}

void incorrect(void) {
    /* Flash Pattern
     * 00001111
     * 11110000
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
     * 01010101
     * 10101010
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

void loop() {
    int secret = 0b00010111, attempts = 0;

    while (true) {
        // Made it all into a one-liner, fight me coward
        //            0000 0001         0000 0010              0000 0100              0000 1000              0001 0000              0010 0000              0100 0000              1000 0000              1 0000 0000
        int answer = (digitalRead(A0) | digitalRead(A1) << 1 | digitalRead(A2) << 2 | digitalRead(A3) << 3 | digitalRead(A4) << 4 | digitalRead(A5) << 5 | digitalRead(11) << 6 | digitalRead(12) << 7 | digitalRead(13) << 8) == secret ? HIGH : LOW;

        // To prevent immediate execution, use a push button to start solution check
        if (digitalRead(10) == HIGH) {
            if (answer == HIGH) {
                correct();
                delay(500);

                // Restart the game
                break;
            } else {
                attempts++;

                if (attempts >= 5) {
                    game_over();
                    break;
                }

                incorrect();
            }
        }
    }
}
