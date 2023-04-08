#include <stdio.h>
#include <time.h>
#include "euclid.h"
#include "rand.h"

int test_euclid()
{
	/* ���, �ݺ�ȣ���� ����� �Ǿ����� Ȯ�� �� �ڵ�

	int ran_num1[1000] = { 10, 9, 8, 7, 6 };
	int ran_num2[1000] = { 6, 4, 7, 8, 2 };

	*/
	int ran_num1[1000] = { 0 };  // ���� �迭 2�� ����
	int ran_num2[1000] = { 0 };
	int change_num = 0; // num1�� ���� ���� ���� �ֱ⿡ ����س��� ����
	int gcd[1000] = { 0 };

	srand(time(NULL));

	make_random_number(ran_num1, ran_num2); // �� ���� �������� 1000�� ����

	for (int i = 0; i < 1000; i++)  // num1�� ������ num2���� Ŀ���Ѵ�.
	{
		//gcd[i] = euclid_Repetitive(ran_num1[i], ran_num2[i]); // �ݺ�
		gcd[i] = euclid_Recalsive(ran_num1[i], ran_num2[i]); // ���
	}

	/* rand.c ����� �� �׽�Ʈ
	
	for (int i = 0; i < 1000; i++)  // num1�� ������ num2���� Ŀ���Ѵ�.
	{
		//ran_num1[i] = (rand() % 1000)+1;
		//ran_num2[i] = (rand() % 1000)+1;
		if (ran_num1[i] < ran_num2[i])  // num2�� ���� ũ�ٸ� ���� �ٲ㼭 �����Ѵ�.
		{
			change_num = ran_num2[i];
			ran_num2[i] = ran_num1[i];
			ran_num1[i] = change_num;
		}
		gcd[i] = euclid_Repetitive(ran_num1[i], ran_num2[i]);
		//gcd[i] = euclid_Recalsive(ran_num1[i], ran_num2[i]);
	}
	*/
	printf("�ȳ��ϼ���. �׽�Ʈ�սô�. \n");

	for (int i = 0; i < 1000; i++)
	{
		printf("%d �� %d �� �ִ������� %d �Դϴ�.\n", ran_num1[i], ran_num2[i], gcd[i]);
	}
	return 0;
}