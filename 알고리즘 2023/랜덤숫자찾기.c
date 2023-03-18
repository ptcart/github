// 찾고싶은 수를 입력하여 10개의 수 중에 원하는 값이 있는지 찾아주는 프로그램
// 단 수는 항상 랜덤이어야함
// 찾으면 그 값을 출력, 못찾으면 -1을 출력

// 구조체 복습하고 랜덤함수 포함하여 다시 코딩해보기


#include<stdio.h>
#include <stdlib.h> // 해더파일 rand()함수 포함 라이브러리
#include <time.h> // time()함수 포함 라이브러리

//funtion : find k from array A
//return :
//         -1 : if K is not found
//          K : if K is found

//int random_num(); // 랜덤함수
int searchk_rannum(int K); // 특정숫자를 찾아주는 함수
int testsearchk(); //값을 출력하는 함수


int main(void)
{
	//함수호출 이후 그 숫자가 있으면 해당숫자 출력 없으면 -1 출력
	testsearchk();

	return 0;
}



void random_num(int A[]) // 랜덤 함수
{
	int i ;
	srand(time(NULL));

	for (i = 0; i < 10; i++)
	{
		A[i] = rand() % 50 + 1; // 각 배열에 10개의 수를 랜덤으로 넣어줌 (50이하의 수)
		printf("%d ", A[i]);
	}

	return ;
} 



int searchk_rannum(int K) // 랜덤숫자 중 특정숫자를 찾아주는 함수
{
	//1. 배열을 임의의 숫자로 초기화한다.
	int i;
	int num[10] = { 0 }; // 배열은 0이라도 넣어서 초기화 시켜주는게 좋다 (문제엔 이렇게 되어있어서 씀)
	int ret = -1; // result의 약자
	
	// 1-2 num이라는 변수에 랜덤함수를 집어
	random_num(num);

	//2. K값을 순차적으로 찾는다.
	for (int i = 0; i < 10; i++)
	{
		if (num[i] == K)
		{
			ret = K;
			break;
		}
	}
	return ret;
}

// 준비물
// 랜덤함수
// 특정숫자를 찾아주는 함수
// 값을 출력해주는 함수

int testsearchk() //값을 출력하는 함수
{
	int fval = 0; // 변수 초기화 찾고싶은 값 입력하는 변수
	int xret = -1; // 못찾았을 경우 -1 출력하는 변수

	printf("찾고싶은 수를 입력하세요 >> ");
	scanf("%d", &fval);

	xret = searchk_rannum(fval);

	if (xret == fval)
	{
		printf("찾았습니다. %d", xret);
	}
	else
	{
		printf("값을 찾지 못하였습니다. %d", xret);
	}

}
