#include <stdio.h>
#include <time.h>

int test_euclid();

int main()
{
	int ret = 0;
	clock_t start, finish; // Ŭ�� �Լ� ���
	double duration; // �ɸ��� �ð��� ������ ��

	start = clock(); // ���۽ð�

	printf("Hello world\n");
	ret = test_euclid();

	finish = clock(); // �ڵ� ���� �� �����ð�
	duration = (double)(finish - start) / CLOCKS_PER_SEC;    //���۰� �����ð��� ���� ����
	printf("%f ���Դϴ�.\n", duration);
	return 0;
}