//����� �ִ����� �˰���
int euclid_Recalsive(int a, int b)
{
	if (b == 0) return a;  // b�� 0�̸� a�� ����

	return euclid_Recalsive(b, a % b); // b�� a�� b�� ���� �������� ����
}


//�ݺ��� �ִ����� �˰���
int euclid_Repetitive(int a, int b)
{
	while (b != 0)
	{
		a = b;
		b = a % b;
	}
	return a;
}