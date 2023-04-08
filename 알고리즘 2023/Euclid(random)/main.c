#include <stdio.h>
#include <time.h>

int test_euclid();

int main()
{
	int ret = 0;
	clock_t start, finish; // 클락 함수 사용
	double duration; // 걸리는 시간을 변수로 둠

	start = clock(); // 시작시간

	printf("Hello world\n");
	ret = test_euclid();

	finish = clock(); // 코드 실행 후 끝난시간
	duration = (double)(finish - start) / CLOCKS_PER_SEC;    //시작과 끝난시간의 차를 구함
	printf("%f 초입니다.\n", duration);
	return 0;
}