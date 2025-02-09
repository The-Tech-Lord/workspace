#include <Wire.h>
#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 16, 2);

void setup() {
    // 8-bit Binary Input  and Carry
    for (int i = 2; i <= 10; i++)
        pinMode(i, INPUT);

    // Next action
    pinMode(13, INPUT);

    lcd.init();
    lcd.backlight();
    Serial.begin(9600);
}

// Break the other Arduino out of an infinite while-loop
void unlock(int ms) {
    digitalWrite(A1, HIGH);
    delay(ms);
    digitalWrite(A1, LOW);
}

void loop() {
    int hardcoded_secret = 0b101010111;
    int secret = 0b000000000;

    lcd.setCursor(0, 0);
    lcd.print("Choose your");
    lcd.setCursor(0, 1);
    lcd.print("secret key");
    delay(1000);
    lcd.clear();

    // Secret Binary Key Stage
    while (true) {
        // Made it all into a one-liner, fight me coward
        //          0000 0001          0000 0010             0000 0100             0000 1000             0001 0000             0010 0000             0100 0000             1000 0000            1 0000 0000
        secret = digitalRead(2) | digitalRead(3) << 1 | digitalRead(4) << 2 | digitalRead(5) << 3 | digitalRead(6) << 4 | digitalRead(7) << 5 | digitalRead(8) << 6 | digitalRead(9) << 7 | digitalRead(10) << 8;

        lcd.print("Current Number:");
        lcd.setCursor(0, 1);
        lcd.print(secret);
        delay(200);
        lcd.clear();

        if (digitalRead(13) == HIGH) {
            if (secret == 0b000000000) {
                lcd.print("Set key to");
                lcd.setCursor(0, 1);
                lcd.print("default?");

                while (true) {
                    if (digitalRead(13) == HIGH) {
                        lcd.clear();
                        lcd.print("Setting key");
                        lcd.setCursor(12, 0);

                        for (int i = 0; i < 4; i++) {
                            lcd.print(".");
                            delay(600);
                        }

                        lcd.clear();
                        break;
                    }
                }

                delay(600);
                lcd.clear();
                lcd.setCursor(0, 0);
                lcd.print("Default Key");
                lcd.setCursor(0, 1);
                lcd.print("set");
                delay(600);
                lcd.clear();
                secret = hardcoded_secret;
            } else {
                lcd.print("Set key to");
                lcd.setCursor(0, 1);
                lcd.print(secret);
                lcd.print("?");
                lcd.setCursor(5, 1);

                while (true) {
                    if (digitalRead(13) == HIGH) {
                        lcd.clear();
                        lcd.print("Setting key");
                        lcd.setCursor(12, 0);

                        for (int i = 0; i < 4; i++) {
                            lcd.print(".");
                            delay(600);
                        }

                        lcd.clear();
                        break;
                    }
                }
            }
            break;
        }
    }
    Serial.println("Secret key chosen");

    lcd.setCursor(0, 0);
    lcd.print("Secret key chosen");
    delay(1000);
    lcd.clear();

    while (true) {
        // Unlock attempt choosing stage
        // NOTE: Prepare your attempt count before pressing the button
        if (digitalRead(13) == HIGH)
            unlock(500);

        if (digitalRead(A0) == HIGH) {
            digitalWrite(11, LOW);
            break;
        }
    }

    // Guessing Stage
    while (true) {
        int answer = (digitalRead(2) | digitalRead(3) << 1 | digitalRead(4) << 2 | digitalRead(5) << 3 | digitalRead(6) << 4 | digitalRead(7) << 5 | digitalRead(8) << 6 | digitalRead(9) << 7 | digitalRead(10) << 8) == secret ? HIGH : LOW;

        if (digitalRead(13) == HIGH) {
            // Correct answer
            if (answer == HIGH) {
                digitalWrite(1, HIGH);
                unlock(500);
                digitalWrite(1, LOW);

                while (true) {
                    if (digitalRead(A0) == HIGH)
                        break;
                }

                break;
            // Wrong answer / Game Over
            } else {
                digitalWrite(0, HIGH);
                delay(500);
                digitalWrite(0, LOW);

                int gameover = 0;
                while (true) {
                    if (digitalRead(A0) == HIGH)
                        break;

                    if (digitalRead(A3) == HIGH) {
                        gameover = 1;
                        break;
                    }
                }

                if (gameover == 1)
                    break;
            }
        }
    }

    digitalWrite(11, LOW);  // Successful reset of program
}
