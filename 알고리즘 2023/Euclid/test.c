#include <stdio.h>
#include "euclid.h"

int test_euclid()
{
	int gcd = 0;

	printf("�ȳ��ϼ���. �׽�Ʈ�սô�. \n");


	//gcd = euclid_Recalsive(24, 12);
	gcd = euclid_Repetitive(24, 12);

	printf("%d��%d �� �ִ������� %d �Դϴ�.", 24, 12, gcd);

	return 0;
}