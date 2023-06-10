#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>
#include "dijkstra.h"
#include "menu.h"
#include "menu1.h"
#include "menu2.h"

#define _CRT_SECURE_NO_WARNINGS

void add_BM()
{
    List_add_BM("Region.txt");
    Register("Region.txt");
}

void List_add_BM(const char* filename)
{
    load_road_Region(filename);
}


int check_region(const char* filename, const char* region) {
    FILE* file = fopen(filename, "r");  // �Է� ���� ����

    if (file == NULL) {
        printf("������ �� �� �����ϴ�.\n");
        return 0;
    }

    char line[100];

    while (fgets(line, sizeof(line), file)) {
        line[strcspn(line, "\n")] = '\0';  // ���� ���� ����

        char* start = strchr(line, '(');
        if (start != NULL) {
            *start = '\0';  // ���� ���ڿ� ����

            // ������ �ش� ������ �ִ� ���� ã��
            if (strcmp(line, region) == 0) {
                fclose(file);
                return 1;
            }
        }
    }

    fclose(file);
    return 0;
}

void Register(const char* filename)
{
    char region[50];
    char nickname[50];

    gotoxy(42, 17);
    printf("�߰��� ������ �Է��ϼ���: ");
    scanf("%s", region);

    

    while (1)
    {
        int found = check_region(filename, region);
        if (!found) {
            
            gotoxy(42, 17);
            printf("                               ");

            gotoxy(72, 18);
            printf("           ");

            gotoxy(47, 17);
            printf("�ش� ������ �������� �ʽ��ϴ�.");
            gotoxy(49, 18);
            printf("�ٽ� �Է����ֽʽÿ� >> ", region);
            scanf("%s", region);
        }
        else
        {
            gotoxy(42, 20);
            printf("�߰��� ������ �Է��ϼ���: ");
            scanf("%s", nickname);
            if (strcmp(nickname, "-1") == 0) {
                system("cls");
                mainpage(); // ���� �޴��� ���ư��� �Լ� ȣ��
                return;
            }

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

                    // ������ �ش� ������ �ִ� ���� ã�� ������ �����
                    if (strcmp(line, region) == 0) {
                        fprintf(tempFile, "%s(%s)\n", region, nickname); // ���ο� ������ �������� �����
                    }
                    else {
                        fprintf(tempFile, "%s(%s\n", line, start + 1); // �ӽ� ���Ͽ� ���� ���
                    }
                }
                else {
                    fprintf(tempFile, "%s\n", line); // �ӽ� ���Ͽ� ���� ���
                }
            }

            fclose(file);
            fclose(tempFile);

            remove(filename);  // ���� ���� ����
            rename("temp.txt", filename);  // �ӽ� ������ ���� ���� �̸����� ����

            gotoxy(42, 22);
            printf("������ �߰��Ǿ����ϴ�.\n");
            gotoxy(42, 24);
            printf("please press the enter...");
            break;
        }
    }
}

void menu2() 
{
    add_BM();
}
