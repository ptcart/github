#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "tree.h"

int testcode()
{
	printf("Hello World\n");

	int value = 200; // 찾고 싶은 값
	Node* find_value;
	Node* insert_value;

	Node* root = makeTree();
	Node* root1 = createNode(600); // root1에 600이란 값이 있는 노드 생성
	Node* root2 = createNode(700); // root2에 700이란 값이 있는 노드 생성
	printTree(root); // root 트리 먼저 출력
	//printTree(root1);  테스트 확인

	_getch();

	printNode(root); // 순회 3개 모두 출력
	find_value = findNode(root, value); // 찾고싶은 노드 반환값을 find_value에 대입

	printf("\n찾고싶은 value :%d, 해당하는 주소 :%p \n", find_value->value, find_value->value); // 찾고싶은 값과, 그의 주소를 출력

	root = insertLeftNode(root, root1); // root에 root1에 있는 600값을 삽입
	root = insertRightNode(root, root2); // root에 root2에 있는 700값을 삽입
	printTree(root); // root 트리 출력

	return 0;
}
