//랜덤변수 1000개 생성해주는 함수
void make_random_number(int a[], int b[])
{
	for (int i = 0; i < 1000; i++)  // num1이 무조껀 num2보다 커야한다.
	{
		int change_num = 0;
		a[i] = (rand() % 1000)+1;
		b[i] = (rand() % 1000)+1;
		if (a[i] < b[i])  // num2의 수가 크다면 서로 바꿔서 진행한다.
		{
			change_num = b[i];
			b[i] = a[i];
			a[i] = change_num;
		}
	}
}