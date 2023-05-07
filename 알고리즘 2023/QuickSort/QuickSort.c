//알고리즘 코드케이스 2 : 퀵정렬 파일입출력을 받고 배열에 저장한 뒤
// 퀵솔트 한 값을 출력, result.txt를 만들어 결과 삽입

#include <stdio.h>
#include <stdlib.h>

void quickSort(int arr[], int L, int R);


int main()
{
	//int a[12] = { 6,3,11,9,12,2,8,15,18,10,7,14 };
	int a = 0;
	int res[12] = { 0 };
	int i = 0;
	int j = 0;
	int c;

	FILE* fp = NULL;
	FILE* fp1 = NULL;


	fp = fopen("data.txt", "r");
	fp1 = fopen("result.txt", "w");
	if (fp == NULL)
	{
		printf("파일 열기 실패\n");
		return 1;
	}
	else
	{
		printf("파일 열기 성공\n");
	}

	//printf("파일 입출력 후 : ");

	while (fscanf(fp, "%d ", &a) != EOF) { // 파일에서 정수 읽어오기
		//printf("%d ", a); // 읽어온 데이터 출력
	    res[i] = a; // 값을 arr 배열에 저장을 해줌
		i++; // 배열위치 증가
	}

	//printf("\narr 배열 값 :");
	/*
	for (j = 0; j < 12; j++)
	{
		printf("%d ", res[j]); // 배열 값 확인 차 출력
	}
	*/
	
	printf("\n정렬 전: ");
	for (i = 0; i < 12; i++)
	{
		printf("%d ", res[i]);
	}
	printf("\n");

	quickSort(res, 0, 11);
	printf("정렬 후: ");
	for (i = 0; i < 12; i++)
	{
		printf("%d ", res[i]);
	}

	for (i = 0; i < 12; i++)
	{
		fprintf(fp1, "%d ", res[i]); // 파일에 정수 쓰기
	}

	fclose(fp);
	fclose(fp1);
	printf("\n");
	return 0;
}

void quickSort(int arr[], int L, int R)
{
	int left = L;
	int right = R;
	int pivot = arr[(L + R) / 2];
	int temp;

	do
	{
		while (arr[left] < pivot)
		{
			left++;
		}
		while (arr[right] > pivot)
		{
			right--;
		}
		if (left <= right)
		{
			temp = arr[left];
			arr[left] = arr[right];
			arr[right] = temp;
			left++;
			right--;
		}
	} while (left <= right);

	if (L < right)
	{
		quickSort(arr, L, right);
	}
	if (left < R)
	{
		quickSort(arr, left, R);
	}

}
