//�������� 1000�� �������ִ� �Լ�
void make_random_number(int a[], int b[])
{
	for (int i = 0; i < 1000; i++)  // num1�� ������ num2���� Ŀ���Ѵ�.
	{
		int change_num = 0;
		a[i] = (rand() % 1000)+1;
		b[i] = (rand() % 1000)+1;
		if (a[i] < b[i])  // num2�� ���� ũ�ٸ� ���� �ٲ㼭 �����Ѵ�.
		{
			change_num = b[i];
			b[i] = a[i];
			a[i] = change_num;
		}
	}
}