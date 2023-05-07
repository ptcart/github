// 합병정렬 알고리즘 코드로 구현화, 배열은 무작위로 나열
// 파일입출력 사용하여 text.txt, result.txt로 만들기

#include <stdio.h>
void mergeSort(int data[], int p, int r);

void merge(int data[], int p, int q, int r);

int main() {
    //int res[12] = { 6,3,11,9,12,2,8,15,18,10,7,14 };
    int arr[12] = { 0 };
    int a;
    int i = 0;
    int j = 0;

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
    while (fscanf(fp, "%d ", &a) != EOF) // 파일에서 정수 읽어오기
    {
        arr[i] = a; // 값을 arr 배열에 저장을 해줌
        i++; // 배열위치 증가
    }

    printf("정렬 전\n");

    for (i = 0; i < 12; i++) {
        printf("%d ", arr[i]);
    }

    mergeSort(arr, 0, 11);

    printf("\n정렬 후\n");

    for (i = 0; i < 12; i++)
    {
        printf("%d ", arr[i]);
    }

    for (i = 0; i < 12; i++)
    {
        fprintf(fp1, "%d ", arr[i]); // 파일에 정수 쓰기
    }

    fclose(fp);
    fclose(fp1);
    printf("\n");
    return 0;
}


void mergeSort(int data[], int p, int r) {
    int q;
    if (p < r) {
        q = (p + r) / 2;  // q = 중앙
        mergeSort(data, p, q); // 왼쪽 합병정렬
        mergeSort(data, q + 1, r); // 오른쪽 합병정렬
        merge(data, p, q, r); // 분할한 것을 크기비교 후 정렬
    }
}
void merge(int data[], int p, int q, int r) {
    int i = p, j = q + 1, k = p;
    int tmp[12];
    while (i <= q && j <= r) {
        if (data[i] <= data[j]) // 오른쪽구간 왼쪽구간 크기 비교
        { // 오른쪽구간이 더 크다면
            tmp[k++] = data[i++]; // tmp에 data 값을 저장
        }
        else // 왼쪽이 더 크다면
        {
            tmp[k++] = data[j++]; // tmp에 data 값을 저장
        }
    }
    while (i <= q)
    {
        tmp[k++] = data[i++];
    }
    while (j <= r)
    {
        tmp[k++] = data[j++];
    }
    for (int a = p; a <= r; a++)
    {
        data[a] = tmp[a];
    }
}
