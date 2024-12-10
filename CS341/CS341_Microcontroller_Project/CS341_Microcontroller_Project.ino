void setup() {
    pinMode(0, OUTPUT);  // Incorrect
    pinMode(1, OUTPUT);  // Correct

    // 8-bit Binary Input  and Carry
    for (int i = 2; i <= 10; i++)
        pinMode(i, INPUT);

    // Work Lock Comms
    pinMode(A0, INPUT);
    pinMode(A1, OUTPUT);

    // Gameover Check
    pinMode(A2, INPUT);

    // Waiting LED
    pinMode(11, OUTPUT);

    // Next action
    pinMode(13, INPUT);
}

void unlock(int ms) {
    digitalWrite(A1, HIGH);
    delay(ms);
    digitalWrite(A1, LOW);
}

void loop() {
    int hardcoded_secret = 0b101010111;
    int secret = 0b000000000;

    digitalWrite(11, HIGH);

    // Secret Binary Key Stage
    while (true) {
        // Made it all into a one-liner, fight me coward
        //            0000 0001        0000 0010             0000 0100             0000 1000             0001 0000             0010 0000             0100 0000             1000 0000            1 0000 0000
        secret = digitalRead(2) | digitalRead(3) << 1 | digitalRead(4) << 2 | digitalRead(5) << 3 | digitalRead(6) << 4 | digitalRead(7) << 5 | digitalRead(8) << 6 | digitalRead(9) << 7 | digitalRead(10) << 8;

        if (digitalRead(13) == HIGH) {
            if (secret == 0b000000000)
                secret = hardcoded_secret;
            break;
        }
    }

    delay(1000);
    digitalWrite(11, LOW);
    while (true) {
        // Unlock attempt choosing stage
        if (digitalRead(13) == HIGH)
            unlock(500);

        if (digitalRead(A0) == HIGH) {
            digitalWrite(11, LOW);
            break;
        }

        delay(500);
    }

    // Guessing Stage
    while (true) {
        int answer = (digitalRead(2) | digitalRead(3) << 1 | digitalRead(4) << 2 | digitalRead(5) << 3 | digitalRead(6) << 4 | digitalRead(7) << 5 | digitalRead(8) << 6 | digitalRead(9) << 7 | digitalRead(10) << 8) == secret ? HIGH : LOW;

        if (digitalRead(13) == HIGH) {
            if (answer == HIGH) {
                digitalWrite(1, HIGH);
                unlock(500);
                digitalWrite(1, LOW);

                while (true) {
                    if (digitalRead(A0) == HIGH)
                        break;
                }

                break;
            } else {
                bool gameover = false;

                digitalWrite(0, HIGH);
                delay(500);
                digitalWrite(0, LOW);

                if (digitalRead(A3) == HIGH) {
                    gameover = true;
                    break;
                }

                if (gameover == true)
                    break;
            }
        }
    }
}
