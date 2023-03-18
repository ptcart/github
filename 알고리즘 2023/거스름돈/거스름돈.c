// 거스름돈을 입력받아 500원, 100원, 10원 동전의 최소 개수를 말하는 프로그램을 작성하시오
// 거스름돈을 500원으로 나눈 몫과 나머지를 구하고, 나머지를 다시 100원으로 나눈 몫과 나머지를 구하고, 10원으로 몫을 구한 값을 출력함

// if or switch, 함수사용, 구조체사용

#include<stdio.h>

// 거스름돈을 만들어주는 함수 만들고 메인함수에 출력
// 입력 : 거스름돈,  출력 : 각 동전의 갯수
// 구조체 Ncoins, 거스름돈 함수 CalCoin

struct Ncoins
{
	int w500, w100, w50, w10; // 500원, 100원, 50원, 10원 동전의 갯수를 가진 구조체
};

void CalCoin(int w);

int main(void)
{
	int won; // 거스름돈 입력해주는 변수
	printf("거스름돈을 입력해주세요! >> ");
	scanf("%d", &won);

	CalCoin(won);


	return 0;
}

void CalCoin(int w) // 거스름돈을계산하고 출력해주는 함수
{
	struct Ncoins NC;
	NC.w500 = 0;
	NC.w100 = 0;
	NC.w50 = 0;
	NC.w10 = 0;

	NC.w500 = w / 500; // 1
	w = w % 500;       // w =  280

	NC.w100 = w / 100;   // 2
	w = w % 100;    // w = 80 

	NC.w50 = w / 50;  //1
	w = w % 50;      //30

	NC.w10 = w / 10;  // 3     

	printf("500원 : %d개 \n100원 : %d개 \n50원 : %d개 \n10원 : %d개", NC.w500, NC.w100, NC.w50, NC.w10);
	return 0;
}
