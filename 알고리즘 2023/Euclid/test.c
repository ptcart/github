#include <stdio.h>
#include "euclid.h"

int test_euclid()
{
	int gcd = 0;

	printf("안녕하세요. 테스트합시다. \n");


	//gcd = euclid_Recalsive(24, 12);
	gcd = euclid_Repetitive(24, 12);

	printf("%d와%d 의 최대공약수는 %d 입니다.", 24, 12, gcd);

	return 0;
}