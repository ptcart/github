#pragma once
//1. 노드 타입 선언 : 정수값, 왼쪽 노드, 오른쪽 노드 포인터로 구성 : 노트 타입 정의(Value, Left Node, Right Node)

//typedef struct _Node { int value; struct _Node left, struct _Node right; } Node;

typedef struct Node  // 노드 타입 선언
{ 
	int value; // 해당 노드의 정수 값
	struct Node* left; // 왼쪽 노드
	struct Node* right; // 오른쪽 노드
} Node; // 명칭

extern Node* makeTree();
extern void printTree(Node* root);
extern void pre_order(Node* root);
extern void in_order(Node* root);
extern void post_order(Node* root);
extern void printNode(Node* root);
extern Node* findNode(Node* root, int value);
extern Node* insertLeftNode(Node* where, Node* new_node);
extern Node* insertRightNode(Node* where, Node* new_node);
