//선택정렬 알고리즘 파일입출력 추가 후 구현

#include <stdio.h>

int select_sort(int arr[], int left, int right, int n);

int main() {
    //int res[] = { 5, 3, 9, 7, 1, 8, 2, 10, 4, 6 };
    int res[12];
    int a = 0;
    int i = 0;
    int n = 5; // 5번째로 작은 수

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

    while (fscanf(fp, "%d ", &a) != EOF)  // 파일에서 정수 읽어오기
    {
        //printf("%d ", a); // 읽어온 데이터 출력
        res[i] = a; // 값을 arr 배열에 저장을 해줌
        i++; // 배열위치 증가
    }

    int nth_smallest = select_sort(res, 0, 11, n);

    printf("%d번째로 작은 수: %d\n", n, nth_smallest);
    fprintf(fp1, "%d번째로 작은 수: %d\n", n, nth_smallest); // 파일에 정수 쓰기


    fclose(fp);
    fclose(fp1);
    printf("\n");
    return 0;
}


int select_sort(int arr[], int left, int right, int n) {
    if (left == right) {
        return arr[left];
    }

    int i = left;
    int j = right;

    int pivot = arr[(left + right) / 2];

    while (i <= j) {
        while (arr[i] < pivot) {
            i++;
        }
        while (arr[j] > pivot) {
            j--;
        }
        if (i <= j) {  // 서로 교환
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }
    if (left + n - 1 <= j)
    {
        return select_sort(arr, left, j, n);
    }
    else if (left + n - 1 >= i)
    {
        return select_sort(arr, i, right, n - (i - left));
    }
    else
    {
        return arr[j + 1];
    }
}
