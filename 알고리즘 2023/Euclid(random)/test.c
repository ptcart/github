#include <stdio.h>
#include <time.h>
#include "euclid.h"
#include "rand.h"

int test_euclid()
{
	/* 재귀, 반복호출이 제대로 되었는지 확인 차 코드

	int ran_num1[1000] = { 10, 9, 8, 7, 6 };
	int ran_num2[1000] = { 6, 4, 7, 8, 2 };

	*/
	int ran_num1[1000] = { 0 };  // 랜덤 배열 2개 생성
	int ran_num2[1000] = { 0 };
	int change_num = 0; // num1의 수가 작을 수도 있기에 대비해놓은 변수
	int gcd[1000] = { 0 };

	srand(time(NULL));

	make_random_number(ran_num1, ran_num2); // 두 수에 랜덤변수 1000개 제작

	for (int i = 0; i < 1000; i++)  // num1이 무조껀 num2보다 커야한다.
	{
		//gcd[i] = euclid_Repetitive(ran_num1[i], ran_num2[i]); // 반복
		gcd[i] = euclid_Recalsive(ran_num1[i], ran_num2[i]); // 재귀
	}

	/* rand.c 만들기 전 테스트
	
	for (int i = 0; i < 1000; i++)  // num1이 무조껀 num2보다 커야한다.
	{
		//ran_num1[i] = (rand() % 1000)+1;
		//ran_num2[i] = (rand() % 1000)+1;
		if (ran_num1[i] < ran_num2[i])  // num2의 수가 크다면 서로 바꿔서 진행한다.
		{
			change_num = ran_num2[i];
			ran_num2[i] = ran_num1[i];
			ran_num1[i] = change_num;
		}
		gcd[i] = euclid_Repetitive(ran_num1[i], ran_num2[i]);
		//gcd[i] = euclid_Recalsive(ran_num1[i], ran_num2[i]);
	}
	*/
	printf("안녕하세요. 테스트합시다. \n");

	for (int i = 0; i < 1000; i++)
	{
		printf("%d 와 %d 의 최대공약수는 %d 입니다.\n", ran_num1[i], ran_num2[i], gcd[i]);
	}
	return 0;
}