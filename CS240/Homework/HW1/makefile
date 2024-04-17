CC = gcc
FLAGS = -std=c11 -Wall -Wextra -Werror
RM = rm -f

default: all
all: Q1 Q2 Q3

Q1: Q1.c
	$(CC) $(FLAGS) -o q1 Q1.c

Q2: Q2.c
	$(CC) $(FLAGS) -o q2 Q2.c

Q3: Q3.c
	$(CC) $(FLAGS) -o q3 Q3.c

clean:
	$(RM) Q1 Q2 Q3
