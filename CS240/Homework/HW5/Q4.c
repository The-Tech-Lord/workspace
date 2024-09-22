/* Project: BeginningDeleteNode
 * Name   : MD Raiyan Hossain
 * Date   : 5/6/2024
 * File   : Q4.c
 * Notes  : Deletes a node to the beginning of a linked list
 */

#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
	int data;
	struct Node *next;
} Node;

// Inserts a node at the end of a linked list
void insert(Node **head, int data) {
	Node *node = (Node *)malloc(sizeof(Node));

	if (node == NULL) {
		exit(1);
	}

	node->data = data;

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

// Deletes a node from the beginning of a linked list
int delete(Node **head) {
	Node *temp = (*head);
	int deleted_data = temp->data;
	(*head) = (*head)->next;
	free(temp);
	return deleted_data;
}

int main() {
	int node_num = 0;
	Node *head = NULL;

	printf("Linked List : Delete first node of Singly Linked List :\n");
	printf("-----------------------------------------------------------\n");
	printf("Input the number of nodes : ");
	scanf("%d", &node_num);

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

	printf("\nData of node 1 which is being : %d\n\n", delete(&head));
	printf("Data, after deletion of first node :\n");

	ptr = head;
	while (ptr != NULL) {
		printf("Data = %d\n", ptr->data);
		ptr = ptr->next;
	}
	
	return 0;
}
