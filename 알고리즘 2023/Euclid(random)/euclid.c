//재귀적 최대공약수 알고리즘
int euclid_Recalsive(int a, int b)
{
	if (b == 0)
	{
		return a;
	} // b가 0이면 a를 리턴

	return euclid_Recalsive(b, a % b); // b와 a를 b로 나눈 나머지를 리턴
}


//반복적 최대공약수 알고리즘
int euclid_Repetitive(int a, int b)
{
	int n = 0;
	while (b!=0)
	{
		n = a % b;
		a = b;
		b = n;
		
	}
	return a;
}