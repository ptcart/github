#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "tree.h"


Node* root;

Node* createNode(int val)
{
	// 1. sizeof(Node)로 메모리 할당
	Node* pNode = (Node*)malloc(sizeof(Node));

	// 2. 초기화 n->value = val, left, right (NULL)
	pNode->value = val;
	pNode->left = NULL;
	pNode->right = NULL;

	// 3. 만든 Node 객체(변수) 포인터 반환
	return pNode;
}



Node* connectChild(Node* parent, Node* left, Node* right)
{
	if (parent == NULL)
	{
		return NULL;
	}

	parent->left = left;
	parent->right = right;
}

/*
2. 트리 생성 함수 작성 : 다음과 같은 노드들을 생성, 포인터로
연결하여 Root 노드 포인터 반환

(100, 200, 300), (200, NULL, NULL), (300, 400, 500), (400, NULL, NULL), (500, NULL, NULL)

Node* makeTree()

- 노드 생성함수 : Node * createNode(int value)
*/

Node* makeTree()
{
	Node* pRoot = NULL;
	Node* tleft = NULL;
	Node* tright = NULL;

	pRoot = createNode(100); // value가 100인 노드 생성
	tleft = createNode(200); // value가 200인 노드 생성
	tright = createNode(300); // value가 300인 노드 생성


	connectChild(pRoot, tleft, tright); 
	//if문도 써야함 NULL 생각해서 써야함
	connectChild(tright, createNode(400), createNode(500)); 
	// 300기준으로 왼쪽 400, 오른쪽 500인 트리를 만든다.

	return pRoot;
}

void printTree(Node* root)
{
	if (root == NULL)
	{
		return;
	}

	printf("Node: %d, ", root->value);
	printTree(root->left);
	printTree(root->right);

}

//3. 전체 노드 값 출력 함수 작성 : pre order, in order, post order(재귀함수)
void pre_order(Node* root)
{ // root->value = 부모, root->left = 아들, root->right = 딸로 예시를 들어보자, 이게 더욱 더 이해하기 쉬움
	if (root != NULL) 
	{ // 노드값이 무조껀 있어야만 가능
		printf("%d ", root->value); // 부모(중간)노드부터 먼저 확인
		pre_order(root->left); // 다음 아들(왼쪽) 노드 확인
		pre_order(root->right); // 마지막으로 딸(오른쪽)노드 확인
	}
}

void in_order(Node* root)
{
	if (root != NULL)
	{ // 노드값이 무조껀 있어야만 가능
		in_order(root->left); // 우선 아들(왼쪽)노드부터 먼저 확인
		printf("%d ", root->value); // 다음 부모(중간)노드 확인
		in_order(root->right); // 마지막으로 딸(오른쪽)노드 확인
	}
}

void post_order(Node* root)
{
	if (root != NULL)
	{ // 노드값이 무조껀 있어야만 가능
		post_order(root->left); // 우선 아들(왼쪽)노드부터 먼저 확인
		post_order(root->right); // 다음 딸(오른쪽)노드 확인
		printf("%d ", root->value); // 마지막으로 부모(중간)노드 확인
	}
}


void printNode(Node* root) // 전위, 중위, 후위순회 모두 출력하는 프로그램
{ 
	printf("\n");

	// pre_order(전위)순회
	printf("pre_order : ");
	pre_order(root);

	printf("\n");

	// in_order(중위)순회
	printf("in_order : ");
	in_order(root);

	printf("\n");

	// post_order(후위)순회
	printf("post_order : ");
	post_order(root);
}

// 4. 추가 함수1 작성 : 특정 값을 찾는 노드의 포인터 반환하는 함수(선택적)

Node* findNode(Node* root, int value) 
{
	if (root == NULL) 
	{ // 트리가 없을 경우
		return NULL; // NULL을 반환한다.
	}
	if (root->value == value) 
	{ // 해당 트리의 값이 value일 경우
		return root; // 해당 트리를 반환한다.
	}
	Node* leftResult = findNode(root->left, value); // 왼쪽가지로 이어진 해당 value를 먼저 찾아본다.(재귀)
	if (leftResult != NULL) 
	{ // 왼쪽부분에서 찾고싶은 결과값이 있을 경우
		return leftResult; // 왼쪽 결과값을 반환한다.
	}
	return findNode(root->right, value); // 그마저도 없다면 오른쪽가지로 이어진 해당 value를 찾아본다(재귀)
}

// 5. 추가 함수2 작성 : 노드를 트리에 삽입하는 코드 작성(선택적)

Node* insertLeftNode(Node* where, Node* new_node) 
{
	// 넣고싶은 노드(where)이 없다면 
	if (where == NULL) {
		return NULL; //  NULL을 반환한다.
	}

	// 원래 노드값을 다른 값에다가 저장한 뒤, 새로운 노드를 삽입 후, 
	// 원래 노드 값을 새로운 노드 값에다가 대입을 함. (왼쪽 버전)
	Node* original_left = where->left;
	where->left = new_node;
	new_node->left = original_left;

	// 넣고 싶은 노드(where)를 반환
	return where;
}

Node* insertRightNode(Node* where, Node* new_node)
{
	// 넣고싶은 노드(where)이 없다면 
	if (where == NULL) 
	{
		return NULL; //  NULL을 반환한다.
	}

	// 원래 노드값을 다른 값에다가 저장한 뒤, 새로운 노드를 삽입 후, 
    // 원래 노드 값을 새로운 노드 값에다가 대입을 함. (오른쪽 버전), 방법은 동일함
	Node* original_right = where->right;
	where->right = new_node;
	new_node->right = original_right;

	// 넣고 싶은 노드(where)를 반환
	return where; 
}
