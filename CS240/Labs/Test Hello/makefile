CC = gcc
CFLAGS = -Wall -Wextra -Werror
RM = rm -f

default: all
all: hello

hello: hello.c
	$(CC) $(CFLAGS) -o hello hello.c

clean:
	$(RM) hello hello2
