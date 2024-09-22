/* Project: PositionInsertNode
 * Name   : MD Raiyan Hossain
 * Date   : 5/6/2024
 * File   : Q3.c
 * Notes  : Inserts a node at a specified position of a linked list
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
	int data;
	struct Node *next;
} Node;

// Inserts a node at the end of the linked list
void insert(Node **head, int data) {
	Node *node = (Node *)malloc(sizeof(Node));

	if (node == NULL) {
		exit(1);
	}

	node->data = data;

	// Allocate a node to `head` if null
	if ((*head) == NULL) {
		(*head) = node;
		return;
	}

	Node *ptr = (*head);
	while (ptr->next != NULL) {
		ptr = ptr->next;
	}
	ptr->next = node;
}

// Inserts a node at the specified position (starting at 1) of the linked list
void insert_at_pos(Node **head, int data, int pos) {
	Node *node = (Node *)malloc(sizeof(Node));

	if (node == NULL) {
		exit(1);
	}

	node->data = data;

	// Allocate a node to `head` if null
	if ((*head) == NULL) {
		(*head) = node;
	}

	Node *ptr = (*head);
	Node *prev_ptr = NULL;
	for (int i = 1; i < pos; i++) {
		if (i == pos - 1) {
			prev_ptr = ptr;
		}
		ptr = ptr->next;
	}

	if (prev_ptr != NULL) {
		prev_ptr->next = node;
	}
	node->next = ptr;
}

int main() {
	int node_num = 0;
	Node *head = NULL;

	printf("Linked List : Insert a new node at the middle of the Linked List :\n");
	printf("----------------------------------------------------------------------\n");
	printf("Input the number of nodes (3 or more) : ");

	while (1) {
		scanf("%d", &node_num);

		if (node_num < 3) {
			printf("Please choose a size greater than or equal to 3\n");
			printf("Input the number of nodes (3 or more) : ");
			continue;
		} else {
			break;
		}
	}

	for (int i = 0; i < node_num; i++) {
		int data = 0;
		printf("Input data for node %d : ", i + 1);
		scanf("%d", &data);
		insert(&head, data);
	}

	printf("\nData entered in the list are :\n");

	Node *ptr = head;
	while (ptr != NULL) {
		printf("Data = %d\n", ptr->data);
		ptr = ptr->next;
	}

	int data = 0;
	int pos = 0;
	printf("\nInput data to insert in the middle of the list : ");
	scanf("%d", &data);
	printf("Input the position to insert new node : ");
	scanf("%d", &pos);

	insert_at_pos(&head, data, pos);
	
	printf("\nInsertion completed successfully.\n");
	printf("\nThe new list is :\n");

	ptr = head;
	while (ptr != NULL) {
		printf("Data = %d\n", ptr->data);
		ptr = ptr->next;
	}
	
	return 0;
}
