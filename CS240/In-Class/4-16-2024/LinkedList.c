#include <stdio.h>
#include <stdlib.h>


//Create a struct node for linked list
struct Node {
	int number;
	struct Node *next;
	struct Node *prev;
};

// Function prototypes
void createNodeList(int n, struct Node **head, struct Node **tail);
void reverseDispList(int n, struct Node **head, struct Node **tail);
void displayList(struct Node **head, struct Node **tail);   

// Main function
int main() {
    int n = 0;

	struct Node *head = (struct Node *)malloc(sizeof(struct Node));
	head->next = NULL;
	head->prev = NULL;

	struct Node *tail = (struct Node *)malloc(sizeof(struct Node));
	head->next = tail;
	tail->prev = head;
	tail->next = NULL;

    // Displaying the purpose of the program
    printf("\n\nLinked List : Create a singly linked list and print it in reverse order :\n");
    printf("------------------------------------------------------------------------------\n");

    // Inputting the number of nodes for the linked list
    printf("Input the number of nodes : ");
    scanf("%d", &n);

    // Creating the linked list with n nodes
    createNodeList(n, &head, &tail);
    printf("\nData entered in the list are : \n");		

    // Displaying the data entered in the linked list
    displayList(&head, &tail);

    // Reversing the linked list
    reverseDispList(n, &head, &tail);
    printf("\n The list in reverse are :  \n");

    // Displaying the reversed linked list
    displayList(&head, &tail);

	free(head);
	free(tail);

    return 0;
}

// Function to create a linked list with n nodes
void createNodeList(int n, struct Node **head, struct Node **tail) {
	struct Node *traverse_pointer = (*head);
	for (int i = 0; i < n; i++) {
		struct Node *node = (struct Node *)malloc(sizeof(struct Node));
		
		printf("Input data for node %d : ", i + 1);
		scanf("%d", &node->number);

		traverse_pointer->next = node;
		node->prev = traverse_pointer;
		node->next = (*tail);
		(*tail)->prev = node;
		traverse_pointer = node;
	}
}

// Function to reverse the linked list
void reverseDispList(int n, struct Node **head, struct Node **tail) {
	struct Node *traverse_pointer_head = (*head)->next;  // Starts from the beginning of the list
	struct Node *traverse_pointer_tail = (*tail)->prev;  // Starts from the end of the list

	for (int i = 0; i < n / 2; i++) {
		int temp = traverse_pointer_head->number;
		traverse_pointer_head->number = traverse_pointer_tail->number;
		traverse_pointer_tail->number = temp;

		traverse_pointer_head = traverse_pointer_head->next;
		traverse_pointer_tail = traverse_pointer_tail->prev;
	}
}

// Function to display the linked list
void displayList(struct Node **head, struct Node **tail) {
	struct Node *traverse_pointer = (*head)->next;

	while (traverse_pointer != (*tail)) {
		printf("Data = %d\n", traverse_pointer->number);
		traverse_pointer = traverse_pointer->next;
	}
	printf("\n");
}
