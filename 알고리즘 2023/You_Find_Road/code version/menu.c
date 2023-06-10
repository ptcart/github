#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <windows.h>
#include "menu1.h"
#include "menu2.h"
#include "menu3.h"
#include "menu.h"

char save = 72;
int selectedOption = 1;  // ���õ� �ɼ��� ������ ����, �⺻������ 1 ����

void gotoxy(int x, int y) {
    COORD pos;
    pos.X = x - 1;
    pos.Y = y - 1;
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), pos);
}

void point(int x, int y) {
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 72 || key == 80) {
            gotoxy(x, y + (selectedOption - 1) * 2);
            printf(" ");  // ���� ���õ� �ɼ��� ȭ��ǥ ����

            if (key == 72) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 5 : selectedOption - 1;
            }
            else if (key == 80) {  // �Ʒ��� ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 5) ? 1 : selectedOption + 1;
            }

            gotoxy(x, y + (selectedOption - 1) * 2);
            printf("��");  // ���� ���õ� �ɼǿ� ȭ��ǥ ǥ��
        }
    }
}

void s(void) //�׸� �ڽ�
{
    printf("                               ��");
    for (int i = 0; i < 58; i++) {  // 1 ������ �ʺ� ����� ����
        printf("��");
    }
    printf("��");
    for (int i = 2; i <= 28; i++) {
        gotoxy(91, i);// 2 ������ �ʺ� ����� ����
        printf("��");
    }
    gotoxy(91, 29);// 3 ������ �ʺ� ����� ����
    printf("��");
    for (int i = 60; i > 2; i--) {// 4, 60���� ������ �ʺ� ����� ����
        gotoxy(i + 30, 29);
        printf("��");
    }
    gotoxy(32, 29);
    printf("��");
    for (int i = 28; i > 1; i--) {
        gotoxy(32, i);
        printf("��");
    }
}

void printMenu(int x, int y) {
    gotoxy(x, y);
    printf("    1. �� ã��");
    gotoxy(x, y + 2);
    printf("    2. ���ã�� �߰�");
    gotoxy(x, y + 4);
    printf("    3. ���ã�� ����");
    gotoxy(x, y + 6);
    printf("    4. �ֱ� ���");
    gotoxy(x, y + 8);
    printf("    5. ����");
    gotoxy(x, y + 10);
    printf("========================================");
}

void sub1page()
{
    s();
    gotoxy(42, 5);
    printf("========================================");
    //gotoxy(42, 6);
    //printf("         ����ٰ� ����� �ְ�           ");
   // gotoxy(42, 7);
   // printf("         ����ٰ� ������ �ְ�           ");
   /*
    gotoxy(42, 8);
    printf("========================================");
    gotoxy(42, 9);
    printf("              �̿� ����                 ");
    gotoxy(42, 10);
    printf("               1. ����                  ");
    gotoxy(42, 11);
    printf("               2. �ڵ���                ");
    gotoxy(42, 12);
    printf("               3. ���߱���              ");
    gotoxy(42, 13);
    printf("               4. ������                ");
    */


    gotoxy(42, 24);
    printf("========================================");
}


void sub2page()
{
    s();
    gotoxy(42, 5);
    printf("========================================");
    gotoxy(42, 6);
    printf("       1. ����    |   2. ����           ");
    gotoxy(42, 7);
    printf("========================================");
    gotoxy(42, 8);
    printf("       3. õ��    |   4. û��           ");
    gotoxy(42, 9);
    printf("========================================");
    gotoxy(42, 10);
    printf("       5. ����    |   6. ���           ");
    gotoxy(42, 11);
    printf("========================================");
    gotoxy(42, 12);
    printf("       7, ����    |   8. �뱸          ");
    gotoxy(42, 13);
    printf("========================================");
    gotoxy(42, 14);
    printf("       9. ����    |   10. �λ�          ");
    gotoxy(42, 15);
    printf("========================================");
}

void sub3page()
{
    s();
    gotoxy(42, 5);
    printf("========================================");
    gotoxy(42, 6);
    printf("            ���ã�� ���               ");

    gotoxy(42, 18);
    printf("========================================");
}
void RP()
{
    FILE* fp = fopen("Recent Paths.txt", "r");
    if (fp != NULL)
    {
        char line[100];
        int lineCount = 0;
        while (fgets(line, sizeof(line), fp) != NULL && lineCount < 5)
        {
            gotoxy(42, 8 + lineCount);
            printf("%s", line);
            lineCount++;
        }
        fclose(fp);
    }
}
void sub4page()
{
    s();
    gotoxy(42, 5);
    printf("========================================");
    gotoxy(42, 6);
    printf("              �ֱ� ���               ");
    RP();
    gotoxy(42, 14);
    printf("========================================");
    gotoxy(42, 18);
    printf("please press the enter...");
}


void mainpage()
{
    s();

    gotoxy(42, 7);
    printf("========================================");
    gotoxy(42, 8);
    printf("       �ȳ��ϼ��� U_R ���Դϴ�.");
    gotoxy(42, 9);
    printf("         ������ ���͵帱���?");
    gotoxy(42, 10);
    printf("========================================");


    printMenu(42, 12);  // �޴� ���

    point(40, 12);  // �޴� ����

    if (selectedOption == 1) {
        // �� ã�� �ɼ� ���� �� ó���� �۾� �߰�
        system("cls");
        sub1page();
        menu1();
    }
    else if (selectedOption == 2) {
        // ���ã�� �߰� �ɼ� ���� �� ó���� �۾� �߰�
        system("cls");
        sub2page();
        menu2();
    }
    else if (selectedOption == 3) {
        // ���ã�� ���� �ɼ� ���� �� ó���� �۾� �߰�
        system("cls");
        sub3page();
        menu3();
    }
    else if (selectedOption == 4) {
        // �ֱ� ��� �ɼ� ���� �� ó���� �۾� �߰�
        system("cls");
        sub4page();
    }
    else if (selectedOption == 5) {
        system("cls");  // ȭ�� ����� ���ο� UI �������� �Ѿ
        s();
        gotoxy(43, 12);
        printf("========================================");
        gotoxy(44, 13);
        printf("       �̿����ּż� �����մϴ�.");
        gotoxy(43, 14);
        printf("            �Լ��� �����մϴ�.");
        gotoxy(43, 15);
        printf("========================================");
        return;  // �Լ� ����
    }
}

int main(void) {
    int people = 0;
    while (1) {
        system("cls");  // ȭ���� ����� ���ο� UI �������� �Ѿ
        mainpage();
        _getch();
        if (selectedOption == 5) {
            break;  // �ݺ��� ����
        }
    }
    return 0;
}