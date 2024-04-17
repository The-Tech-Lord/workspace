CC = gcc -std=c11
FLAGS = -Wall -Wextra -Werror
RM = rm -f

default: all
all: Q1 Q2

Q1: Q1.c
	$(CC) $(FLAGS) -o q1 Q1.c

Q2: Q2.c
	$(CC) $(FLAGS) -o q2 Q2.c

clean:
	$(RM) q1.o q2.o
