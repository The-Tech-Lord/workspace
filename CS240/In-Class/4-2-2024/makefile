CC = gcc -std=c11
FLAGS = -Wall -Wextra -Werror
RM = rm -f

default: all
all: ReverseString DynamicStrings

ReverseString: ReverseString.c
	$(CC) $(FLAGS) -o ReverseString ReverseString.c

DynamicStrings: DynamicStrings.c
	$(CC) $(FLAGS) -o DynamicStrings DynamicStrings.c

clean:
	$(RM) ReverseString.o DynamicStrings.o
