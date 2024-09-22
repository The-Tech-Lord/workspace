/* Project: BeginningInsertNode
 * Name   : MD Raiyan Hossain
 * Date   : 5/6/2024
 * File   : Q1.c
 * Notes  : Inserts a node to the beginning of a linked list
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

// Inserts a node at the beginning of the linked list
void beginning_insert(Node **head, int data) {
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

	node->next = (*head);
	(*head) = node;
}

int main() {
	int node_num = 0;
	Node *head = NULL; 
	
	printf("Linked List : Insert a new node at the beginning of the Singly Linked List:\n");
	printf("-------------------------------------------------------------------------------\n");
	printf("Input the number of nodes : ");
	scanf("%d", &node_num);

	for (int i = 0; i < node_num; i++) {
		int data = 0;
		printf("Data = ");
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
	printf("\nInput data to insert at the beginning of the list : ");
	scanf("%d", &data);
	beginning_insert(&head, data);

	printf("\nData after inserted in the list are :\n");

	ptr = head;
	while (ptr  != NULL) {
		printf("Data = %d\n", ptr->data);
		ptr = ptr->next;
	}

	return 0;
}
