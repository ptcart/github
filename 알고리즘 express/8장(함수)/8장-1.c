// 수학적인 조합 값을 구하는 예제
#include <stdio.h>
// 팩토리얼 값을 반환
int factorial(int n)
{
	int i, result = 1;

	for (i = 1; i <= n; i++)
		result *= i; // result = result * i , 팩토리얼의 개념

	return result;
}
// 팩토리얼 값을 이용하여서 조합값을 계산
int combination(int n, int r)
{
	return (factorial(n) / (factorial(r) * factorial(n - r))); // 함수를 이용하여 팩토리얼 공식을 활용
}
// 사용자로부터 값을 입력받아서 반환
int get_integer(void)
{
	int n;

	printf("정수를 입력하시오: ");
	scanf("%d", &n);

	return n;
}

// a, b 정수를 입력 받아서 조합값 
int main(void)
{
	int a, b;
	a = get_integer();  // a, b 정수를 입력 받아서 조합값 
	b = get_integer();

	printf("C(%d, %d) = %d \n", a, b, combination(a, b));

	return 0;
}
