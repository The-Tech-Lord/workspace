CC = gcc -std=c11
FLAGS = -Wall -Wextra -Werror
RM = rm -f

default: all
all: LinkedList

LinkedList: LinkedList.c
	$(CC) $(FLAGS) -o linkedlist LinkedList.c

clean:
	$(RM) linkedlist.o
