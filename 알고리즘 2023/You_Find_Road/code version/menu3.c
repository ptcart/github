#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>
#include "dijkstra.h"
#include "menu1.h"
#include "menu3.h"

#define _CRT_SECURE_NO_WARNINGS

int nickname_num[10] = { 0 };
int count_num = 1;

void del_BM()
{
    int delete_num = 0;
    delete_num = List_del_BM("Region.txt");

    Delete("Region.txt", R[nickname_num[delete_num-1]].region, delete_num); 

    return 0;
}

int List_del_BM(const char* filename)
{
    int delete_pos = 0;
    load_road_Region(filename);
    count_num = 1;

    for (int i = 0; i < 10; i++)
    {
        if (strcmp(R[i].nickname, " ") == 0)
        {
            // �׳� �ѱ�
        }
        else
        {
            gotoxy(42, 6 + (2 * count_num));
            printf("%d. %s(%s)\n", count_num, R[i].region, R[i].nickname);
            nickname_num[count_num - 1] = i;
            count_num++;
        }
    }
    gotoxy(42, 19);
    printf("������ ���ã�⸦ �������ּ��� >> ");
    scanf("%d", &delete_pos);

    while (delete_pos == 0 || delete_pos + 1 > count_num)
    {
        gotoxy(42, 19);
        printf("�߸��� �Է��Դϴ�. �ٽ� �Է����ּ��� >> ");

        gotoxy(82, 19);
        printf("       ");
        gotoxy(82, 19);
        scanf("%d", &delete_pos);
    }
    return delete_pos;
}

void Delete(const char* filename, const char* targetArea, int delete_num)
{
    FILE* file = fopen(filename, "r");  // �Է� ���� ����
    FILE* tempFile = fopen("temp.txt", "w");  // �ӽ� ���� ����

    if (file == NULL || tempFile == NULL) {
        printf("������ �� �� �����ϴ�.\n");
        return;
    }

    char line[100];

    while (fgets(line, sizeof(line), file)) {
        line[strcspn(line, "\n")] = '\0';  // ���� ���� ����

        char* start = strchr(line, '(');
        if (start != NULL) {
            *start = '\0';  // ���� ���ڿ� ����

            // ������ ������ ��ġ�ϴ� ���
            if (strcmp(line, targetArea) == 0) {
                fprintf(tempFile, "%s( )\n", line);  // ���� ����
                continue;
            }
        }
        fprintf(tempFile, "%s(%s\n", line, start + 1); // �ӽ� ���Ͽ� ���� ���
    }
    fclose(file);
    fclose(tempFile);

    remove(filename);  // ���� ���� ����
    rename("temp.txt", filename);  // �ӽ� ������ ���� ���� �̸����� ����

    gotoxy(42, 21);
    printf("���ã�� %d���� �����Ͽ����ϴ�.\n", delete_num);
    count_num = 1; // ���ã�� ��ȣ �ʱ�ȭ
    gotoxy(42, 23);
    printf("please press the enter...");
}

void menu3() 
{
    del_BM();
}
