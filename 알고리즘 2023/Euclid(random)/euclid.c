//����� �ִ����� �˰���
int euclid_Recalsive(int a, int b)
{
	if (b == 0)
	{
		return a;
	} // b�� 0�̸� a�� ����

	return euclid_Recalsive(b, a % b); // b�� a�� b�� ���� �������� ����
}


//�ݺ��� �ִ����� �˰���
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