// 가짜 동전 찾기
// 갯수 입력 후 몇번 째에 가짜가 있었는지 출력하는 프로그램 대신 이진탐색으로 해야할것

// 이 프로그램의 취약점: 2의 거듭제곱만 계산할 수 있다 = 한번이라도 동전의 갯수가 홀수개라도 있으면 안된다.

#include <stdio.h>
#include<time.h>

void findfakemoney(int coin[], int count);

int main() {
	int n = 1;
	int left, right, sum_left = 0, sum_right = 0;
	int fake_coin; // 가짜동전 
	
	srand((unsigned int)time(NULL));

	printf("동전의 개수를 입력하세요: ");
	scanf("%d", &n); 
	int coins[1000] = { 0 }; // 
	
	for (int i = 0; i < n; i++)
	{
		coins[i] = 1;  // 정상적인 동전의 무게를 1이라고 가정한다.
	}
	fake_coin = rand() % n;  // 가짜동전의 배열을 무작위로 돌린다
	coins[fake_coin] = 0; // 가짜동전자리에 무게를 0로 변경한다.
	for (int i = 0; i < n; i++)
	{
		printf("%d ", coins[i]);  // 정상적인 동전의 무게를 1이라고 가정한다.
	}

	findfakemoney(coins, n);
	return 0;
}


void findfakemoney(int coin[], int count) // 가짜동전찾기 (동전배열, 동전갯수)
{
	int left, right, sum_left = 0, sum_right = 0;
	left = 0;  // 왼쪽 = 배열의 처음
	right = count - 1;  // 오른쪽 = 배열의 끝
	while (left < right) {       // (반나눴을 기준) 오른쪽이 더 클경우
		sum_left = 0;
		sum_right = 0;
		
		
		for (int i = left; i <= right; i++) // 동전더미 처음부터 끝까지
		{
			if (i <= (left + right) / 2)  // 왼쪽부터 더해야하는 조건문
			{
				sum_left += coin[i];
			}
			else {                       // 나머지는 오른쪽 합하기
				sum_right += coin[i];
			}
		}
		if (sum_left == sum_right) { // 오른쪽 왼쪽 합이 같으면 가짜동전이 없다는 뜻
			printf("가짜 동전이 없습니다.\n");
			return 0;
		}
		else if (sum_left < sum_right) {  // 동전의 합이 오른쪽이 더 크다면
			right = (left + right) / 2; // 오른쪽기준으로 다시 배열을 정리해라
		}
		else { // 왼쪽의 합이 더 크다면
			left = (left + right) / 2 + 1;   // 왼쪽 수를 올려서 배열을 정리하라
		}
	}
	printf("가짜 동전은 %d번째 동전입니다.\n", left + 1); 
}

