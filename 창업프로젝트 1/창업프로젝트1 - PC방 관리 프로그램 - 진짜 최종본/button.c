//����â���� ���̴� ��ư��

#define _CRT_SECURE_NO_WARNINGS

#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <windows.h>
#include"header.h"

#define seat_max 60
#define time_max 8

char m[3][20] = { "ȸ������", "���̵�ã��", "��й�ȣã��" };
char ok_cancle[2][20] = { "Ȯ��", "���" };
char quit_cancle[2][20] = { "����", "���" };
char delete_cancle[2][20] = { "����", "���" };
char order_cancle[2][20] = { "�ֹ�", "���" };
char order_check[3][20] = { "�ֹ��Ϸ�", "�ֹ����", "��������"};
char account_check[4][20] = { "ȸ�� ã��", "����", "����", "���ư���" };

char user_menu[3][20] = { "�ð� �߰�", "�԰Ÿ� �ֹ�", "��� ����" };

char pay_select[2][20] = { "���� ����", "ī�� ����" }; // ������� �迭
char sales_select[2][20] = { "������Ȳ", "ó������" }; // ������� �� ����

char order_select[4][20] = { "�� ����", "���� ȭ��", "����", "����"};

char cat_food_menu[7][20] = { "��  ��", "����", "���ڷ�", "�����", "��  Ÿ", "���", "��ٱ���" };

char save = 72;
int selectedOption = 1;  // ���õ� �ɼ��� ������ ����, �⺻������ 1 ����

int seat_selected[60] = { 0 };
int time_selected[8] = { 0 };

void mainpoint(int x, int y, char button[][20]) {
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77) { // ������Ű, ����Ű ������ ���
            if ((selectedOption) == 1)
            {
                square_color(15, 2, x + (selectedOption - 1) * 16, y, 7);
                goto_xy((x + 4) + (selectedOption - 1) * 16, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 2)
            {
                square_color(15, 2, x + (selectedOption - 1) * 16, y, 7);
                goto_xy((x + 3) + (selectedOption - 1) * 16, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 3)
            {
                square_color(15, 2, x + (selectedOption - 1) * 16, y, 7);
                goto_xy((x + 2) + (selectedOption - 1) * 16, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }


            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 3 : selectedOption - 1;
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 3) ? 1 : selectedOption + 1;
            }

            if ((selectedOption) == 1)
            {
                square_color(15, 2, x + (selectedOption - 1) * 16, y, 6); // 27 19 ��ǥ ���� ����
                goto_xy((x + 4) + (selectedOption - 1) * 16, y + 1);
                printf("%s", button[selectedOption - 1]); // Ȯ��ǥ��
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 2)
            {
                square_color(15, 2, x + (selectedOption - 1) * 16, y, 6); // 27 19 ��ǥ ���� ����
                goto_xy((x + 3) + (selectedOption - 1) * 16, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 3)
            {
                square_color(15, 2, x + (selectedOption - 1) * 16, y, 6); // 27 19 ��ǥ ���� ����
                goto_xy((x + 2) + (selectedOption - 1) * 16, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
        }
    }
}

void main_button()
{
    mainpoint(27, 19, m);  // �޴� ����

    if (selectedOption == 1) {
        // ȸ�� ����
        system("cls");
        join_membership();
    }
    else if (selectedOption == 2) {
        // ���̵� ã�� (ī����)
        system("cls");
        go_counter();
    }
    else if (selectedOption == 3) {
        // ��й�ȣ ã�� (ī����)
        system("cls");
        go_counter();
    }
}

void ok_point(int x, int y, char button[][20]) {
    selectedOption == 0;
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77) { // ������Ű, ����Ű ������ ���
            if ((selectedOption) == 1)
            {
                square_color(9, 2, x + (selectedOption - 1) * 10, y, 7);
                goto_xy((x + 3) + (selectedOption - 1) * 10, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 2)
            {
                square_color(9, 2, x + (selectedOption - 1) * 10, y, 7);
                goto_xy((x + 3) + (selectedOption - 1) * 10, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 2 : selectedOption - 1;
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 2) ? 1 : selectedOption + 1;
            }
            if ((selectedOption) == 1)
            {
                square_color(9, 2, x + (selectedOption - 1) * 10, y, 6); // 27 19 ��ǥ ���� ����
                goto_xy((x + 3) + (selectedOption - 1) * 10, y + 1);
                printf("%s", button[selectedOption - 1]); // Ȯ��ǥ��
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 2)
            {
                square_color(9, 2, x + (selectedOption - 1) * 10, y, 6); // 27 19 ��ǥ ���� ����
                goto_xy((x + 3) + (selectedOption - 1) * 10, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
        }
    }
}
void ok_button1()
{
    ok_point(54, 28, ok_cancle);  // �޴� ����
    if (selectedOption == 1) {
        // ȸ������

        if (join_check == 1)
        {
            join_check = 0;
            show_join();
        }
        else if (join_check == 0)
        {
            goto_xy(27, 32);
            printf("�������� �Է����� �ʾҽ��ϴ�. �ٽ� �Է����ּ���");
            goto_xy(0, 10); // Ŀ�� ����
            Sleep(2000);
            join_membership();
        }
    }
    else if (selectedOption == 2) {
        // ���
        system("cls");
        mainlog();
        //menu2();
    }
}



void modify_point(int x, int y,  User button[]) {
    selectedOption = 1;
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 72 || key == 80) { // ������Ű, ����Ű ������ ���           
            if ((selectedOption) == 2)
            {
                square_color(30, 2, x , y + (selectedOption - 1) * 3, 7);
                goto_xy((x + 2), (y + 1) + (selectedOption - 1) * 3);
                printf("%s", button[save_num].pw);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 3)
            {
                square_color(30, 2, x, y + (selectedOption - 1) * 3, 7);
                goto_xy((x + 2), (y + 1) + (selectedOption - 1) * 3);
                printf("%s", button[save_num].name);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 4)
            {
                square_color(30, 2, x, y + (selectedOption - 1) * 3, 7);
                goto_xy((x + 2), (y + 1) + (selectedOption - 1) * 3);
                printf("%s", button[save_num].gender);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 5)
            {
                square_color(30, 2, x, y + (selectedOption - 1) * 3, 7);
                goto_xy((x + 2), (y + 1) + (selectedOption - 1) * 3);
                printf("%s", button[save_num].birth);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 6)
            {
                square_color(14, 2, x + 22, (y + 2) + (selectedOption - 1) * 3, 7);
                goto_xy((x + 25), (y + 3) + (selectedOption - 1) * 3);
                printf("���� ����");
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 72) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 1) ? 6 : selectedOption - 1;
                //printf("%d", selectedOption);
            }
            else if (key == 80) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 6) ? 2 : selectedOption + 1;
            }

            if ((selectedOption) == 2)
            {
                square_color(30, 2, x, y + (selectedOption - 1) * 3, 6);
                goto_xy((x + 2), (y + 1) + (selectedOption - 1) * 3);
                printf("%s", button[save_num].pw);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 3)
            {
                square_color(30, 2, x, y + (selectedOption - 1) * 3, 6);
                goto_xy((x + 2), (y + 1) + (selectedOption - 1) * 3);
                printf("%s", button[save_num].name);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 4)
            {
                square_color(30, 2, x, y + (selectedOption - 1) * 3, 6);
                goto_xy((x + 2), (y + 1) + (selectedOption - 1) * 3);
                printf("%s", button[save_num].gender);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 5)
            {
                square_color(30, 2, x, y + (selectedOption - 1) * 3, 6);
                goto_xy((x + 2), (y + 1) + (selectedOption - 1) * 3);
                printf("%s", button[save_num].birth);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 6)
            {
                square_color(14, 2, x + 22, (y + 2) + (selectedOption - 1) * 3, 6);
                goto_xy((x + 25), (y + 3) + (selectedOption - 1) * 3);
                printf("���� ����");
                goto_xy(0, 10); // Ŀ�� ����
            }
        }
    }
}



void modify_button1() // ȸ���� ������ư
{
    modify_point(38, 11, admin_account);  // �޴� ����

    if (selectedOption == 2) {
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
        square(30, 2, 38, 14); // ��� �Է¹ڽ�
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
        goto_xy(40, 15);
        scanf("%s", admin_account[save_num].pw);

        modify_num_File1("login.txt", admin_account[save_num].id, admin_account);
        show_modify();
    }
    else if (selectedOption == 3) {

        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
        square(30, 2, 38, 17); // �̸� �Է¹ڽ�
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
        goto_xy(40, 18);
        scanf("%s", admin_account[save_num].name);

        modify_num_File1("login.txt", admin_account[save_num].id, admin_account);
        show_modify();
    }
    else if (selectedOption == 4) {
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
        square(30, 2, 38, 20); // ���� �Է¹ڽ�
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
        goto_xy(40, 21);
        scanf("%s", admin_account[save_num].gender);

        modify_num_File1("login.txt", admin_account[save_num].id, admin_account);
        show_modify();
    }
    else if (selectedOption == 5) {
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
        square(30, 2, 38, 23); // �������� �Է¹ڽ�
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
        goto_xy(40, 24);
        scanf("%s", admin_account[save_num].birth);
        sscanf(admin_account[save_num].birth, "%4d%2d%2d", &year[save_num], &month[save_num], &day[save_num]);
        modify_num_File1("login.txt", admin_account[save_num].id, admin_account);

        show_modify();
    }
    else if (selectedOption == 6) {
        system("cls");
        admin_main2();
    }
}

void modify_button2() // �˻��� ȸ���� ȸ������ ��ư
{


    modify_point(38, 11, admin_search_account);  // �޴� ����

    if (selectedOption == 2) {

        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
        square(30, 2, 38, 14); // ��� �Է¹ڽ�
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
        goto_xy(40, 15);
        scanf("%s", admin_search_account[save_num].pw);

        modify_num_File1("login.txt", admin_search_account[save_num].id, admin_search_account);
        show_modify2();
    }
    else if (selectedOption == 3) {

        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
        square(30, 2, 38, 17); // �̸� �Է¹ڽ�
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
        goto_xy(40, 18);
        scanf("%s", admin_search_account[save_num].name);

        modify_num_File1("login.txt", admin_search_account[save_num].id, admin_search_account);
        show_modify2();
    }
    else if (selectedOption == 4) {
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
        square(30, 2, 38, 20); // ���� �Է¹ڽ�
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
        goto_xy(40, 21);
        scanf("%s", admin_search_account[save_num].gender);

        modify_num_File1("login.txt", admin_search_account[save_num].id, admin_search_account);
        show_modify2();
    }
    else if (selectedOption == 5) {
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 1);
        square(30, 2, 38, 23); // �������� �Է¹ڽ�
        SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
        goto_xy(40, 24);
        scanf("%s", admin_search_account[save_num].birth);
        sscanf(admin_search_account[save_num].birth, "%4d%2d%2d", &year[save_num], &month[save_num], &day[save_num]);
        modify_num_File1("login.txt", admin_search_account[save_num].id, admin_search_account);

        show_modify2();
    }
    else if (selectedOption == 6) {
        system("cls");
        admin_main3();
    }
}
void ok_button3()
{

    ok_point(54, 28, ok_cancle);  // �޴� ����

    if (selectedOption == 1) {


        if (join_check == 1)
        {
            join_check = 0;
            show_regist(); 
        }
        else if (join_check == 0)
        {
            goto_xy(27, 32);
            printf("�������� �Է����� �ʾҽ��ϴ�. �ٽ� �Է����ּ���");
            goto_xy(0, 10); // Ŀ�� ����
            Sleep(2000);
            register_membership();
        }

        //menu1();
    }
    else if (selectedOption == 2) {
        system("cls");
        admin_main1();
    }
}

int seat_point(int x, int y, int button[])
{
    for (int i = 0; i < seat_max; i++)
    {
        seat_selected[i] = i + 1;
    }

    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77 || key == 72 || key == 80) { // ������Ű(75), ����Ű(77), ��Ű(72), �Ʒ�Ű(80) ������ ���
            //goto_xy(x + 13, y ); //+ (selectedOption - 1) * 2
            if ((selectedOption) >= 1 && (selectedOption) <= 10)
            {
                square_color(6, 2, x + (selectedOption - 1) * 6, y, 7);
                goto_xy((x + 3) + (selectedOption - 1) * 6, y + 1);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 10 && (selectedOption) <= 20)
            {
                square_color(6, 2, x + ((selectedOption - 1) - 10) * 6, y + 3, 7);
                goto_xy((x + 3) + ((selectedOption - 1) - 10) * 6, y + 4);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 20 && (selectedOption) <= 30)
            {
                square_color(6, 2, x + ((selectedOption - 1) - 20) * 6, y + 7, 7);
                goto_xy((x + 3) + ((selectedOption - 1) - 20) * 6, y + 8);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 30 && (selectedOption) <= 40)
            {
                square_color(6, 2, x + ((selectedOption - 1) - 30) * 6, y + 10, 7);
                goto_xy((x + 3) + ((selectedOption - 1) - 30) * 6, y + 11);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 40 && (selectedOption) <= 50)
            {
                square_color(6, 2, x + ((selectedOption - 1) - 40) * 6, y + 14, 7);
                goto_xy((x + 3) + ((selectedOption - 1) - 40) * 6, y + 15);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 50 && (selectedOption) <= 60)
            {
                square_color(6, 2, x + ((selectedOption - 1) - 50) * 6, y + 17, 7);
                goto_xy((x + 3) + ((selectedOption - 1) - 50) * 6, y + 18);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 60 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 60) ? 1 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 80) {  // �Ʒ� ȭ��ǥ Ű
                selectedOption = (selectedOption + 10 > 60) ? selectedOption % 50 : selectedOption + 10;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                selectedOption = (selectedOption - 10 <= 0) ? selectedOption + 50 : selectedOption - 10;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }

            if ((selectedOption) >= 1 && (selectedOption) <= 10)
            {
                square_color(6, 2, x + (selectedOption - 1) * 6, y, 6);
                goto_xy((x + 3) + (selectedOption - 1) * 6, y + 1);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 10 && (selectedOption) <= 20)
            {
                square_color(6, 2, x + ((selectedOption - 1) - 10) * 6, y + 3, 6);
                goto_xy((x + 3) + ((selectedOption - 1) - 10) * 6, y + 4);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 20 && (selectedOption) <= 30)
            {
                square_color(6, 2, x + ((selectedOption - 1) - 20) * 6, y + 7, 6);
                goto_xy((x + 3) + ((selectedOption - 1) - 20) * 6, y + 8);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 30 && (selectedOption) <= 40)
            {
                square_color(6, 2, x + ((selectedOption - 1) - 30) * 6, y + 10, 6);
                goto_xy((x + 3) + ((selectedOption - 1) - 30) * 6, y + 11);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 40 && (selectedOption) <= 50)
            {
                square_color(6, 2, x + ((selectedOption - 1) - 40) * 6, y + 14, 6);
                goto_xy((x + 3) + ((selectedOption - 1) - 40) * 6, y + 15);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 50 && (selectedOption) <= 60)
            {
                square_color(6, 2, x + ((selectedOption - 1) - 50) * 6, y + 17, 6);
                goto_xy((x + 3) + ((selectedOption - 1) - 50) * 6, y + 18);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }


            //printf("��");  // ���� ���õ� �ɼǿ� ȭ��ǥ ǥ��
        }
    }
    return selectedOption;
}
int seat_button()
{
    int number = 0;
    number = seat_point(24, 9, seat_selected);  // �޴� ����

    return number;


}


// ����ȭ�鿡�� ���̴� ��ư��

void user_point(int x, int y, char button[][20])
{
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 72 || key == 80) { // ��Ű, �Ʒ�Ű ������ ���

            if ((selectedOption) == 1)
            {
                square_color(18, 2, x, y + (selectedOption - 1) * 4, 7);
                goto_xy((x + 5), (y + 1) + (selectedOption - 1) * 4);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 2)
            {
                square_color(18, 2, x, y + (selectedOption - 1) * 4, 7);
                goto_xy((x + 4), (y + 1) + (selectedOption - 1) * 4);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 3)
            {
                square_color(18, 2, x, y + (selectedOption - 1) * 4, 7);
                goto_xy((x + 5), (y + 1) + (selectedOption - 1) * 4);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }


            if (key == 72) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 3 : selectedOption - 1;
                goto_xy(0, 5);
                printf("%d", selectedOption);
            }
            else if (key == 80) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 3) ? 1 : selectedOption + 1;
                goto_xy(0, 5);
                printf("%d", selectedOption);
            }

            if ((selectedOption) == 1)
            {
                square_color(18, 2, x, y + (selectedOption - 1) * 4, 6);
                goto_xy((x + 5), (y + 1) + (selectedOption - 1) * 4);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 2)
            {
                square_color(18, 2, x, y + (selectedOption - 1) * 4, 6);
                goto_xy((x + 4), (y + 1) + (selectedOption - 1) * 4);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 3)
            {
                square_color(18, 2, x, y + (selectedOption - 1) * 4, 6);
                goto_xy((x + 5), (y + 1) + (selectedOption - 1) * 4);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
        }
    }
}
void user_button()
{
    user_point(41, 21, user_menu);  // �޴� ����

    if (selectedOption == 1) {
        //�ð��߰� ��ư�� �Էµ� ��
        system("cls");
        add_time2();
    }
    else if (selectedOption == 2) {
        //�԰Ÿ� �ֹ� ��ư�� �Էµ� ��
        system("cls");
        food_menu1();
    }
    else if (selectedOption == 3) {
        //������� ��ư�� �Էµ� ��
        system("cls");
        quit_pc();
    }
}

int time_point(int x, int y, Menu button1[])
{
    for (int i = 0; i < time_max; i++)
    {
        time_selected[i] = i + 1;
    }

    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77 || key == 72 || key == 80) { // ������Ű(75), ����Ű(77), ��Ű(72), �Ʒ�Ű(80) ������ ���
            if ((selectedOption) >= 1 && (selectedOption) <= 2)
            {
                square_color(17, 5, x + (selectedOption - 1) * 19, y, 7);
                goto_xy((x + 6) + (selectedOption - 1) * 19, y + 2);
                printf("%d ��", button1[selectedOption - 1].price);
                goto_xy((x + 6) + (selectedOption - 1) * 19, y + 3);
                printf("(0%s:00)", button1[selectedOption - 1].Name);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 2 && (selectedOption) <= 4)
            {
                square_color(17, 5, x + ((selectedOption - 1) - 2) * 19, y + 6, 7);
                goto_xy((x + 6) + ((selectedOption - 1) - 2) * 19, y + 8);
                printf("%d ��", button1[selectedOption - 1].price);
                goto_xy((x + 6) + ((selectedOption - 1) - 2) * 19, y + 9);
                printf("(0%s:00)", button1[selectedOption - 1].Name);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 4 && (selectedOption) <= 6)
            {
                square_color(17, 5, x + ((selectedOption - 1) - 4) * 19, y + 12, 7);
                goto_xy((x + 6) + ((selectedOption - 1) - 4) * 19, y + 14);
                printf("%d ��", button1[selectedOption - 1].price);
                goto_xy((x + 6) + ((selectedOption - 1) - 4) * 19, y + 15);
                printf("(0%s:00)", button1[selectedOption - 1].Name);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 6 && (selectedOption) <= 8)
            {
                square_color(17, 5, x + ((selectedOption - 1) - 6) * 19, y + 18, 7);
                goto_xy((x + 6) + ((selectedOption - 1) - 6) * 19, y + 20);
                printf("%d ��", button1[selectedOption - 1].price);
                goto_xy((x + 6) + ((selectedOption - 1) - 6) * 19, y + 21);
                printf("(0%s:00)", button1[selectedOption - 1].Name);
                goto_xy(0, 10); // Ŀ�� ����
            }
            //printf(" ");  // ���� ���õ� �ɼ��� ȭ��ǥ ����

            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 8 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 8) ? 1 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 80) {  // �Ʒ� ȭ��ǥ Ű
                selectedOption = (selectedOption + 2 > 8) ? selectedOption % 6 : selectedOption + 2;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                selectedOption = (selectedOption - 2 <= 0) ? selectedOption + 6 : selectedOption - 2;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            if ((selectedOption) >= 1 && (selectedOption) <= 2)
            {
                square_color(17, 5, x + (selectedOption - 1) * 19, y, 6);
                goto_xy((x + 6) + (selectedOption - 1) * 19, y + 2);
                printf("%d ��", button1[selectedOption - 1].price);
                goto_xy((x + 6) + (selectedOption - 1) * 19, y + 3);
                printf("(0%s:00)", button1[selectedOption - 1].Name);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 2 && (selectedOption) <= 4)
            {
                square_color(17, 5, x + ((selectedOption - 1) - 2) * 19, y + 6, 6);
                goto_xy((x + 6) + ((selectedOption - 1) - 2) * 19, y + 8);
                printf("%d ��", button1[selectedOption - 1].price);
                goto_xy((x + 6) + ((selectedOption - 1) - 2) * 19, y + 9);
                printf("(0%s:00)", button1[selectedOption - 1].Name);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 4 && (selectedOption) <= 6)
            {
                square_color(17, 5, x + ((selectedOption - 1) - 4) * 19, y + 12, 6);
                goto_xy((x + 6) + ((selectedOption - 1) - 4) * 19, y + 14);
                printf("%d ��", button1[selectedOption - 1].price);
                goto_xy((x + 6) + ((selectedOption - 1) - 4) * 19, y + 15);
                printf("(0%s:00)", button1[selectedOption - 1].Name);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 6 && (selectedOption) <= 8)
            {
                square_color(17, 5, x + ((selectedOption - 1) - 6) * 19, y + 18, 6);
                goto_xy((x + 6) + ((selectedOption - 1) - 6) * 19, y + 20);
                printf("%d ��", button1[selectedOption - 1].price);
                goto_xy((x + 6) + ((selectedOption - 1) - 6) * 19, y + 21);
                printf("(0%s:00)", button1[selectedOption - 1].Name);
                goto_xy(0, 10); // Ŀ�� ����
            }
        }
    }
    return selectedOption;
}
int time_button() // ��ư�� ���� ���� �߰�
{
    int pc_price = 0;
    int pc_hour = 0;

    pc_hour = (time_point(31, 7, in_menu)); // ������ ��(1~8)�� �ð�(pc_hour)�� �ִ´�.
    pc_price = pc_hour * 1000;

    in_user.second += pc_hour * 3600; // �ð��� �ʷ� ȯ���� ������Ѵ�. 
                                      //�ŷ��� �Ϸ�Ǿ��� �� ���Ͽ� �߰��� �ð��� ���Ҿ� ������ �ð� �־��ֱ�

    return pc_price;
}

int pay_point(int x, int y, char button[][20]) {
    (selectedOption) = 0;
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 72 || key == 80) { // ��Ű, �Ʒ�Ű ������ ���
            //goto_xy(x + 13, y ); //+ (selectedOption - 1) * 2
            if ((selectedOption) >= 1 && (selectedOption) <= 2)
            {
                square_color(22, 5, x, y + (selectedOption - 1) * 7, 7);
                goto_xy((x + 7), (y + 2) + (selectedOption - 1) * 7);
                printf("%s", button[(selectedOption - 1)]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            if (key == 80) {  // �Ʒ� ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 2) ? selectedOption - 1 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? selectedOption + 1 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            if ((selectedOption) >= 1 && (selectedOption) <= 2)
            {
                square_color(22, 5, x, (y) + (selectedOption - 1) * 7, 6);
                goto_xy((x + 7), (y + 2) + (selectedOption - 1) * 7);
                printf("%s", button[(selectedOption - 1)]);
                goto_xy(0, 10); // Ŀ�� ����
            }

        }

    }
    return selectedOption;
}
int pay_button()
{
    int name_number = 0;
    name_number = pay_point(39, 13, pay_select);

    return name_number;
}


void quit_point(int x, int y, char button[][20])
{
    selectedOption = 0;
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77) { // ������Ű, ����Ű ������ ���
            if ((selectedOption) == 1)
            {
                square_color(15, 2, x + (selectedOption - 1) * 21, y, 7);
                goto_xy((x + 6) + (selectedOption - 1) * 21, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 2)
            {
                square_color(15, 2, x + (selectedOption - 1) * 21, y, 7);
                goto_xy((x + 6) + (selectedOption - 1) * 21, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }


            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 2 : selectedOption - 1;
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 2) ? 1 : selectedOption + 1;
            }


            if ((selectedOption) == 1)
            {
                square_color(15, 2, x + (selectedOption - 1) * 21, y, 6);
                goto_xy((x + 6) + (selectedOption - 1) * 21, y + 1);
                printf("%s", button[selectedOption - 1]); // Ȯ��ǥ��
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 2)
            {
                square_color(15, 2, x + (selectedOption - 1) * 21, y, 6);
                goto_xy((x + 6) + (selectedOption - 1) * 21, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
        }
    }
}

void quit_point2(int x, int y, char button[][20]) {
    selectedOption = 0;
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77) { // ������Ű, ����Ű ������ ���
            if ((selectedOption) == 1)
            {
                square_color(15, 2, x + (selectedOption - 1) * 16, y, 7);
                goto_xy((x + 4) + (selectedOption - 1) * 16, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 2)
            {
                square_color(15, 2, x + (selectedOption - 1) * 16, y, 7);
                goto_xy((x + 4) + (selectedOption - 1) * 16, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 3)
            {
                square_color(15, 2, x + (selectedOption - 1) * 16, y, 7);
                goto_xy((x + 4) + (selectedOption - 1) * 16, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 3 : selectedOption - 1;
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 3) ? 1 : selectedOption + 1;
            }

            if ((selectedOption) == 1)
            {
                square_color(15, 2, x + (selectedOption - 1) * 16, y, 6); 
                goto_xy((x + 4) + (selectedOption - 1) * 16, y + 1);
                printf("%s", button[selectedOption - 1]); // Ȯ��ǥ��
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 2)
            {
                square_color(15, 2, x + (selectedOption - 1) * 16, y, 6); 
                goto_xy((x + 4) + (selectedOption - 1) * 16, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 3)
            {
                square_color(15, 2, x + (selectedOption - 1) * 16, y, 6); 
                goto_xy((x + 4) + (selectedOption - 1) * 16, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
        }
    }
}


void quit_button1()
{
    quit_point(32, 27, quit_cancle);  // �޴� ����

    if (selectedOption == 1)
    {
        system("cls");
        change_second(); // �ش� ��,��,�ʸ� �ٽ� �ʷ� ��ȯ�Ͽ� in_user.second�� ����
        in_user.money = 0; // �ش� ������� ���ݾ� 0������ �ʱ�ȭ
        modify_num_File("login.txt", in_user.id);

        quit_pc2(number);
    }
    else if (selectedOption == 2)
    {
        system("cls");
        user_main();
    }
}
void quit_button2()
{
    quit_point(32, 27, quit_cancle);  // �޴� ����

    if (selectedOption == 1)
    {
        system("cls");
        quit_pc4(number);
    }

    else if (selectedOption == 2)
    {
        system("cls");
        admin_main1();
        //menu2();
    }
}
void quit_button3() // ������ �ֹ� �Ϸ�/���/��������
{
    quit_point2(27, 32, order_check);

    if (selectedOption == 1)
    { // �ֹ��Ϸ�
        system("cls");
        for (int i = 0; i < 1000; i++)
        {
            order_pc_num[i] = NULL;
            strcpy(order_pc_id[i], "");
        } // �ֹ� ���̵�, PC��ȣ �迭 �ʱ�ȭ
        if (flag == 2)
        {
            square(60, 35, 20, 1); // ū Ʋ

            square(50, 12, 25, 12); // ���� ȭ�� �ڽ�
            goto_xy(37, 17);
            SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 4);
            printf("ù �ֹ��� ���� �ȳ������ϴ�.");
            goto_xy(39, 18);
            printf("�׷��� �Ϸ��Ͻðڽ��ϱ� ? ");
            goto_xy(32, 19);
            printf("('��'�� 1, �ƴϿ�� '2'�� �����ּ���) ");
            SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);

            char r = "";
            while (r != '1' || r != '2')
            {
                r = getch();
                if (r == '1')
                {
                    order_page = 0;
                    modify_state_File("Order.txt", admin_basket[0].Order_id, 1); // 1: �ֹ��Ϸ�
                    order_status3();
                }
                else if (r == '2')
                {
                    order_page = 0;
                    order_status1();
                }
                else
                {

                }
            }
        }
        modify_state_File("Order.txt", admin_basket[0].Order_id, 1); // 1: �ֹ��Ϸ�
        order_status3();
        
        
    }

    else if (selectedOption == 2)
    { // �ֹ����
        system("cls");
        for (int i = 0; i < 1000; i++)
        {
            order_pc_num[i] = NULL;
            strcpy(order_pc_id[i], "");
        } // �ֹ� ���̵�, PC��ȣ �迭 �ʱ�ȭ
        if (flag == 2)
        {
            square(60, 35, 20, 1); // ū Ʋ

            square(50, 12, 25, 12); // ���� ȭ�� �ڽ�
            SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 4);
            goto_xy(37, 17);
            printf("ù �ֹ��� ���� �ȳ������ϴ�.");
            goto_xy(39, 18);
            printf("�׷��� ����Ͻðڽ��ϱ� ? ");
            goto_xy(32, 19);
            printf("('��'�� 1, �ƴϿ�� '2'�� �����ּ���) ");
            SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
            
            char r = "";
            while (r != '1' || r != '2')
            {
                r = getch();
                if (r == '1')
                {
                    order_page = 0;
                    modify_state_File("Order.txt", admin_basket[0].Order_id, 2); // 2: �ֹ����
                    order_status4();
                }
                else if (r == '2')
                {
                    order_page = 0;
                    order_status1();
                }
                else
                {

                }
            }
            
            

            //order_status1();
        }
        modify_state_File("Order.txt", admin_basket[0].Order_id, 2); // 2: �ֹ����
        order_status4();
        
    }

    else if (selectedOption == 3)
    { // ��������
        order_page = 0;
        system("cls");
        for (int i = 0; i < 5; i++)
        {
            admin_basket[i].count = 0;
        } // �ֹ� ���̵�, PC��ȣ �迭 �ʱ�ȭ
        order_status1();
    }
}

void quit_button4() // ��ٱ��� �ֹ�/���
{
    quit_point(32, 32, order_cancle);  // �޴� ����
    //test();

    if (selectedOption == 1)
    { // �ֹ�
        system("cls");
        food_menu4();
    }

    else if (selectedOption == 2)
    { // ���
        system("cls");
        food_menu1();
    }

}

void delete_button()
{


    //printMenu(42, 12);  // �޴� ���

    quit_point(32, 27, delete_cancle);  // �޴� ����
    //test();

    if (selectedOption == 1)
    {
        
        system("cls");
        if (cases == 0)
        {
            show_delete();
        }

        else if (cases == 1)
        {
            show_delete_1();
        }
        
    }

    else if (selectedOption == 2)
    {
        system("cls");
        admin_main1();
    }
}

int cat_menu_point(int x, int y, char button[][20]) {
    selectedOption = 0;
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 72 || key == 80) { // ��Ű, �Ʒ�Ű ������ ���
            //goto_xy(x + 13, y ); //+ (selectedOption - 1) * 2
            if ((selectedOption) >= 1 && (selectedOption) <= 5)
            {
                square_color(22, 4, x, y + (selectedOption - 1) * 5, 7);
                goto_xy((x + 9), (y + 2) + (selectedOption - 1) * 5);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 6)
            {
                square_color(15, 2, (x + 23), (y + 10) + (selectedOption - 1) * 3, 7);
                goto_xy((x + 29), (y + 11) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 7)
            {
                square_color(15, 2, (x - 16), (y + 7) + (selectedOption - 1) * 3, 7);
                goto_xy((x - 12), (y + 8) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }


            //printf(" ");  // ���� ���õ� �ɼ��� ȭ��ǥ ����

            if (key == 80) {  // �Ʒ� ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 7) ? selectedOption - 6 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? selectedOption + 6 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }

            //gotoxy(x, y + (selectedOption - 1) * 2);
            //goto_xy(x + (selectedOption - 1) * 13, y);
            if ((selectedOption) >= 1 && (selectedOption) <= 5)
            {
                square_color(22, 4, x, y + (selectedOption - 1) * 5, 6);
                goto_xy((x + 9), (y + 2) + (selectedOption - 1) * 5);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 6)
            {
                square_color(15, 2, (x + 23), (y + 10) + (selectedOption - 1) * 3, 6);
                goto_xy((x + 29), (y + 11) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 7)
            {
                square_color(15, 2, (x - 16), (y + 7) + (selectedOption - 1) * 3, 6);
                goto_xy((x - 12), (y + 8) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

        }

    }
    return selectedOption;
}
int cat_menu_button()
{
    int number = 0;
    //printMenu(42, 12);  // �޴� ���

    number = cat_menu_point(39, 7, cat_food_menu);  // �޴� ����
    //test();

    if (selectedOption == 6) {
        //��� ��ư�� �Էµ� ��
        system("cls");
        user_main();
    }

    else if (selectedOption == 7) {
        // ��ٱ��� ��ư�� �Էµ� ��
        system("cls");
        food_basket();
    }

    return number;
}

int name_menu_point(int x, int y, Menu button[], int number) {
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 72 || key == 80) { // ��Ű, �Ʒ�Ű ������ ���
            if ((selectedOption) >= 1 && (selectedOption) <= 8)
            {
                square_color(33, 2, x, y + (selectedOption - 1) * 3, 7);
                goto_xy((x + 2), (y + 1) + (selectedOption - 1) * 3);
                printf("%s", button[(selectedOption - 1) + (8 * (number - 1))].Name);
                goto_xy((x + 25), (y + 1) + (selectedOption - 1) * 3);
                printf("%d��", button[(selectedOption - 1) + (8 * (number - 1))].price);
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 80) {  // �Ʒ� ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 8) ? selectedOption - 7 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? selectedOption + 7 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }

            if ((selectedOption) >= 1 && (selectedOption) <= 8)
            {
                square_color(33, 2, x, y + (selectedOption - 1) * 3, 6);
                goto_xy((x + 2), (y + 1) + (selectedOption - 1) * 3);
                printf("%s", button[(selectedOption - 1) + (8 * (number - 1))].Name);
                goto_xy((x + 25), (y + 1) + (selectedOption - 1) * 3);
                printf("%d��", button[(selectedOption - 1) + (8 * (number - 1))].price);
                goto_xy(0, 10); // Ŀ�� ����
            }

        }

    }
    return selectedOption;
}
int name_menu_button(int number)
{
    int name_number = 0;

    name_number = name_menu_point(33, 5, in_menu, number);  // �޴� ����

    return name_number;
}

//�����ڿ��� ���̴� ��ư��
char admin_menu_[8][20] = { "�ð� �߰�", "ȸ�� ����", "ȸ�� ����", "ó�� ����", "ȸ�� ���", "�ֹ� ����", "ȸ�� ���(ã��)" , "���� ��Ȳ" };
char sales_menu[4][20] = { "�� ������Ȳ", "�� ������Ȳ", "�� ������Ȳ", "��������"};

int year_selected[10] = { 0 };
int month_selected[12] = { 0 };
int day_selected[31] = { 0 };

void admin_point1(int x, int y, char button[][20]) {

    selectedOption = 3;
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 72 || key == 80) { // ������Ű, ����Ű ������ ���

            if ((selectedOption) == 4)
            {
                square_color(18, 2, x, (y - 1) + (selectedOption - 1) * 4, 7);
                goto_xy((x + 5), (y)+(selectedOption - 1) * 4);
                printf("%s", button[selectedOption + 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 5)
            {
                square_color(18, 2, x, (y + 2) + (selectedOption - 1) * 3, 7);
                goto_xy((x + 2), (y + 3) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption + 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 6)
            {
                square_color(18, 2, x, (y + 2) + (selectedOption - 1) * 3, 7);
                goto_xy((x + 5), (y + 3) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption + 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 7)
            {
                square_color(18, 2, x, (y + 2) + (selectedOption - 5) * 3, 7);
                goto_xy((x + 5), (y + 3) + (selectedOption - 5) * 3);
                printf("%s", button[selectedOption - 3]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 72) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 3) ? 7 : selectedOption - 1;
            }
            else if (key == 80) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 7) ? 4 : selectedOption + 1;
            }

            if ((selectedOption) == 4)
            {
                square_color(18, 2, x, (y - 1) + (selectedOption - 1) * 4, 6);
                goto_xy((x + 5), (y)+(selectedOption - 1) * 4);
                printf("%s", button[selectedOption + 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 5)
            {
                square_color(18, 2, x, (y + 2) + (selectedOption - 1) * 3, 6);
                goto_xy((x + 2), (y + 3) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption + 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 6)
            {
                square_color(18, 2, x, (y + 2) + (selectedOption - 1) * 3, 6);
                goto_xy((x + 5), (y + 3) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption + 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 7)
            {
                square_color(18, 2, x, (y + 2) + (selectedOption - 5) * 3, 6);
                goto_xy((x + 5), (y + 3) + (selectedOption - 5) * 3);
                printf("%s", button[selectedOption - 3]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            goto_xy(0, 5);
            printf("%d", selectedOption);
        }
    }
}

void admin_button1()
{
    admin_point1(41, 15, admin_menu_);  // �޴� ����


    if (selectedOption == 4) {
        //�ֹ� ��Ȳ ��ư�� �Էµ� ��
        system("cls");
        order_status1();
    }
    else if (selectedOption == 5) {
        //ȸ�� ã�� ��ư�� �Էµ� ��
        system("cls");
        account_page = 0;
        member_list();
    }
    else if (selectedOption == 6) {
        //�� ������Ȳ ��ư�� �Էµ� ��
        system("cls");
        sales_status1();
    }
    else if (selectedOption == 7) {
        //�� ������Ȳ ��ư�� �Էµ� ��
        system("cls");
        register_membership();
    }
}

void admin_point2(int x, int y, char button[][20]) {

    selectedOption = 0;
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 72 || key == 80) { // ������Ű, ����Ű ������ ���
            
            if ((selectedOption) == 1)
            {
                square_color(18, 2, x, (y + 8) + (selectedOption - 1) * 3, 7);
                goto_xy((x + 5), (y + 9) + (selectedOption - 1) * 4);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 2)
            {
                square_color(18, 2, x, (y + 8) + (selectedOption - 1) * 3, 7);
                goto_xy((x + 5), (y + 9) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 3)
            {
                square_color(18, 2, x, (y + 8) + (selectedOption - 1) * 3, 7);
                goto_xy((x + 5), (y + 9) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 4)
            {
                square_color(18, 2, x, (y + 8) + (selectedOption - 1) * 3, 7);
                goto_xy((x + 5), (y + 9) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 72) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 4 : selectedOption - 1;
            }
            else if (key == 80) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 4) ? 1 : selectedOption + 1;
            }

            if ((selectedOption) == 1)
            {
                square_color(18, 2, x, (y + 8) + (selectedOption - 1) * 3, 6);
                goto_xy((x + 5), (y + 9) + (selectedOption - 1) * 4);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 2)
            {
                square_color(18, 2, x, (y + 8) + (selectedOption - 1) * 3, 6);
                goto_xy((x + 5), (y + 9) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 3)
            {
                square_color(18, 2, x, (y + 8) + (selectedOption - 1) * 3, 6);
                goto_xy((x + 5), (y + 9) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 4)
            {
                square_color(18, 2, x, (y + 8) + (selectedOption - 1) * 3, 6);
                goto_xy((x + 5), (y + 9) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            goto_xy(0, 5);
            printf("%d", selectedOption);
        }
    }
}

void admin_button2()
{
    admin_point2(41, 15, admin_menu_);  // �޴� ����

    if (selectedOption == 1) {
        // �ð� ��ư�� �Էµ� ��
        system("cls");
        free_add_time1();
    }
    else if (selectedOption == 2) {
        // ȸ�� ���� ��ư�� �Էµ� ��
        system("cls");
        member_modification1();
    }
    else if (selectedOption == 3) {
        // ȸ�� ���� ��ư�� �Էµ� ��
        system("cls");
        delete_user(); // ȸ������
    }
    else if (selectedOption == 4) {
        // ó�� ���� ��ư�� �Էµ� ��
        system("cls");
        admin_main1();
    }
}

void admin_button3()
{
    admin_point2(41, 15, admin_menu_);  // �޴� ����

    if (selectedOption == 1) {
        //�ð� �߰� ��ư�� �Էµ� ��
        system("cls");
        return 1;
    }
    else if (selectedOption == 2) {
        //ȸ�� ���� ��ư�� �Էµ� ��
        system("cls");
        member_modification2();
    }
    else if (selectedOption == 3) {
        // ȸ�� ���� ��ư�� �Էµ� ��
        system("cls");
        delete_user_1(); // ȸ������
    }
    else if (selectedOption == 4) {
        // ó�� ���� ��ư�� �Էµ� ��
        system("cls");
        admin_main1();
    }
   
}


void sales_point1(int x, int y, char button[][20]) // ������Ȳ ���ù�ư
{
    selectedOption = 1;
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 72 || key == 80) { // ������Ű, ����Ű ������ ���

            if ((selectedOption) == 1)
            {
                square_color(22, 4, x, y + (selectedOption - 1) * 10, 7);
                goto_xy((x + 6), (y + 2) + (selectedOption - 1) * 10);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 2)
            {
                square_color(22, 4, x, y + (selectedOption - 1) * 10, 7);
                goto_xy((x + 6), (y + 2) + (selectedOption - 1) * 10);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 3)
            {
                square_color(22, 4, x, y + (selectedOption - 1) * 10, 7);
                goto_xy((x + 6), (y + 2) + (selectedOption - 1) * 10);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 4)
            {
                square_color(13, 2, 65, 33, 7);
                goto_xy(68, 34);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }


            if (key == 72) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 4 : selectedOption - 1;
            }
            else if (key == 80) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 4) ? 1 : selectedOption + 1;
            }


            if ((selectedOption) == 1)
            {
                square_color(22, 4, x, y + (selectedOption - 1) * 10, 6);
                goto_xy((x + 6), (y + 2) + (selectedOption - 1) * 10);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }


            else if ((selectedOption) == 2)
            {
                square_color(22, 4, x, y + (selectedOption - 1) * 10, 6);
                goto_xy((x + 6), (y + 2) + (selectedOption - 1) * 10);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 3)
            {
                square_color(22, 4, x, y + (selectedOption - 1) * 10, 6);
                goto_xy((x + 6), (y + 2) + (selectedOption - 1) * 10);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 4)
            {
                square_color(13, 2, 65, 33, 6);
                goto_xy(68, 34);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            goto_xy(0, 5);
            printf("%d", selectedOption);
        }
    }
}

void sales_button1()
{
    sales_point1(39, 6, sales_menu);  // �޴� ����

    if (selectedOption == 1) {
        // �� ������Ȳ
        system("cls");
        sales_status2_1();
    }
    else if (selectedOption == 2) {
        // �� ������Ȳ
        system("cls");
        sales_status2_2();
    }
    else if (selectedOption == 3) {
        // �� ������Ȳ
        system("cls");
        sales_status2_3();
    }
    else if (selectedOption == 4) {
        // ��������
        system("cls");
        admin_main1();
    }
}


// �� ������Ȳ (��, ��, �� ������)
int sales_point2_1(int x, int y, int button[]) // �� ������Ȳ - ���� ���ù�ư
{
    selectedOption = 1;
    for (int i = 0; i < 10; i++)
    {
        year_selected[i] = 2014 + i;
    }

    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77 || key == 72 || key == 80) { // ������Ű(75), ����Ű(77), ��Ű(72), �Ʒ�Ű(80) ������ ���
            //goto_xy(x + 13, y ); //+ (selectedOption - 1) * 2
            if ((selectedOption) >= 1 && (selectedOption) <= 5)
            {
                square_color(9, 2, x + (selectedOption - 1) * 9, y, 7);
                goto_xy((x + 3) + (selectedOption - 1) * 9, y + 1);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 5 && (selectedOption) <= 10)
            {
                square_color(9, 2, x + ((selectedOption - 1) - 5) * 9, y + 4, 7);
                goto_xy((x + 3) + ((selectedOption - 1) - 5) * 9, y + 5);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if (selectedOption == 11)
            {
                square_color(11, 2, x + ((selectedOption - 1) - 4) * 6, y + 12, 7);
                goto_xy((x + 4) + ((selectedOption - 1) - 4) * 6, y + 13);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }



            //printf(" ");  // ���� ���õ� �ɼ��� ȭ��ǥ ����

            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 11 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption); // Ȯ�ο� ���
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 11) ? 1 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 80) {  // �Ʒ� ȭ��ǥ Ű
                selectedOption = (selectedOption + 5 > 10) ? selectedOption % 5 : selectedOption + 5;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                selectedOption = (selectedOption - 5 <= 0) ? selectedOption + 5 : selectedOption - 5;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }

            //gotoxy(x, y + (selectedOption - 1) * 2);
            //goto_xy(x + (selectedOption - 1) * 13, y);
            if ((selectedOption) >= 1 && (selectedOption) <= 5)
            {
                square_color(9, 2, x + (selectedOption - 1) * 9, y, 6);
                goto_xy((x + 3) + (selectedOption - 1) * 9, y + 1);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 5 && (selectedOption) <= 10)
            {
                square_color(9, 2, x + ((selectedOption - 1) - 5) * 9, y + 4, 6);
                goto_xy((x + 3) + ((selectedOption - 1) - 5) * 9, y + 5);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if (selectedOption == 11)
            {
                square_color(11, 2, x + ((selectedOption - 1) - 4) * 6, y + 12, 6);
                goto_xy((x + 4) + ((selectedOption - 1) - 4) * 6, y + 13);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }
        }
    }
    return year_selected[selectedOption - 1];
}
int sales_button2_1()
{
    int number = 0;
    //printMenu(42, 12);  // �޴� ���

    number = sales_point2_1(28, 15, year_selected);
    //test();

    if (selectedOption >= 1 && selectedOption <= 10)
    {
        system("cls");
        temp1 = number; // �⵵�� ���� �޴±���
        itoa(temp1, date, 10); // ���ڿ��� �ٲ۵ڿ� date�� ����

        sales_status5();
    }

    else if (selectedOption == 11) {
        //�������� - ��ҹ�ư ��ư�� �Էµ� ��
        system("cls");
        sales_status1();
    }

    return number;
}

int sales_point2_2(int x, int y, int button[]) // �� ������Ȳ - ���� ���ù�ư
{
    selectedOption = 1;
    for (int i = 0; i < 10; i++)
    {
        year_selected[i] = 2014 + i;
    }

    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77 || key == 72 || key == 80) { // ������Ű(75), ����Ű(77), ��Ű(72), �Ʒ�Ű(80) ������ ���
            if ((selectedOption) >= 1 && (selectedOption) <= 5)
            {
                square_color(9, 2, x + (selectedOption - 1) * 9, y, 7);
                goto_xy((x + 3) + (selectedOption - 1) * 9, y + 1);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 5 && (selectedOption) <= 10)
            {
                square_color(9, 2, x + ((selectedOption - 1) - 5) * 9, y + 4, 7);
                goto_xy((x + 3) + ((selectedOption - 1) - 5) * 9, y + 5);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if (selectedOption == 11)
            {
                square_color(11, 2, x + ((selectedOption - 1) - 4) * 6, y + 12, 7);
                goto_xy((x + 4) + ((selectedOption - 1) - 4) * 6, y + 13);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 11 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption); // Ȯ�ο� ���
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 11) ? 1 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 80) {  // �Ʒ� ȭ��ǥ Ű
                selectedOption = (selectedOption + 5 > 10) ? selectedOption % 5 : selectedOption + 5;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                selectedOption = (selectedOption - 5 <= 0) ? selectedOption + 5 : selectedOption - 5;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }

            if ((selectedOption) >= 1 && (selectedOption) <= 5)
            {
                square_color(9, 2, x + (selectedOption - 1) * 9, y, 6);
                goto_xy((x + 3) + (selectedOption - 1) * 9, y + 1);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 5 && (selectedOption) <= 10)
            {
                square_color(9, 2, x + ((selectedOption - 1) - 5) * 9, y + 4, 6);
                goto_xy((x + 3) + ((selectedOption - 1) - 5) * 9, y + 5);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if (selectedOption == 11)
            {
                square_color(11, 2, x + ((selectedOption - 1) - 4) * 6, y + 12, 6);
                goto_xy((x + 4) + ((selectedOption - 1) - 4) * 6, y + 13);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }
        }
    }
    return year_selected[selectedOption - 1];
}
int sales_button2_2()
{
    int number = 0;
    number = sales_point2_1(28, 15, year_selected);
    //test();

    if (selectedOption >= 1 && selectedOption <= 10)
    {
        system("cls");
        temp1 = number;
        itoa(temp1, date, 10);

        sales_status3_1();
    }

    else if (selectedOption == 11) {
        //�������� - ��ҹ�ư ��ư�� �Էµ� ��
        system("cls");
        sales_status1();
    }
    return number;
}

int sales_point2_3(int x, int y, int button[]) // �� ������Ȳ - ���� ���ù�ư
{
    selectedOption = 1;
    for (int i = 0; i < 10; i++)
    {
        year_selected[i] = 2014 + i;
    }

    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77 || key == 72 || key == 80) { // ������Ű(75), ����Ű(77), ��Ű(72), �Ʒ�Ű(80) ������ ���
            if ((selectedOption) >= 1 && (selectedOption) <= 5)
            {
                square_color(9, 2, x + (selectedOption - 1) * 9, y, 7);
                goto_xy((x + 3) + (selectedOption - 1) * 9, y + 1);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 5 && (selectedOption) <= 10)
            {
                square_color(9, 2, x + ((selectedOption - 1) - 5) * 9, y + 4, 7);
                goto_xy((x + 3) + ((selectedOption - 1) - 5) * 9, y + 5);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if (selectedOption == 11)
            {
                square_color(11, 2, x + ((selectedOption - 1) - 4) * 6, y + 12, 7);
                goto_xy((x + 4) + ((selectedOption - 1) - 4) * 6, y + 13);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 11 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption); // Ȯ�ο� ���
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 11) ? 1 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 80) {  // �Ʒ� ȭ��ǥ Ű
                selectedOption = (selectedOption + 5 > 10) ? selectedOption % 5 : selectedOption + 5;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                selectedOption = (selectedOption - 5 <= 0) ? selectedOption + 5 : selectedOption - 5;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }

            if ((selectedOption) >= 1 && (selectedOption) <= 5)
            {
                square_color(9, 2, x + (selectedOption - 1) * 9, y, 6);
                goto_xy((x + 3) + (selectedOption - 1) * 9, y + 1);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 5 && (selectedOption) <= 10)
            {
                square_color(9, 2, x + ((selectedOption - 1) - 5) * 9, y + 4, 6);
                goto_xy((x + 3) + ((selectedOption - 1) - 5) * 9, y + 5);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if (selectedOption == 11)
            {
                square_color(11, 2, x + ((selectedOption - 1) - 4) * 6, y + 12, 6);
                goto_xy((x + 4) + ((selectedOption - 1) - 4) * 6, y + 13);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }
        }
    }
    return year_selected[selectedOption - 1];
}
int sales_button2_3()
{
    int number = 0;
    number = sales_point2_1(28, 15, year_selected);

    if (selectedOption >= 1 && selectedOption <= 10)
    {
        system("cls");
        temp1 = number;
        itoa(temp1, date, 10);
        sales_status3_2();
    }

    else if (selectedOption == 11) {
        //�������� - ��ҹ�ư ��ư�� �Էµ� ��
        system("cls");
        sales_status1();
    }

    return number;
}

// �� ������Ȳ (��, ��, �� ������)

int sales_point3_1(int x, int y, int button[]) // �� ������Ȳ - �� ���ù�ư
{
    selectedOption = 1;
    for (int i = 0; i < 12; i++)
    {
        month_selected[i] = 1 + i;
    }

    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77 || key == 72 || key == 80) { // ������Ű(75), ����Ű(77), ��Ű(72), �Ʒ�Ű(80) ������ ���
            if ((selectedOption) >= 1 && (selectedOption) <= 6)
            {
                square_color(6, 2, x + (selectedOption - 1) * 7, y, 7);
                goto_xy((x + 3) + (selectedOption - 1) * 7, y + 1);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 6 && (selectedOption) <= 12)
            {
                square_color(6, 2, x + ((selectedOption - 1) - 6) * 7, y + 4, 7);
                goto_xy((x + 3) + ((selectedOption - 1) - 6) * 7, y + 5);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if (selectedOption == 13)
            {
                square_color(11, 2, x + ((selectedOption - 2) - 4) * 5, y + 12, 7);
                goto_xy((x + 4) + ((selectedOption - 2) - 4) * 5, y + 13);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 13 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption); // Ȯ�ο� ���
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 13) ? 1 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 80) {  // �Ʒ� ȭ��ǥ Ű
                selectedOption = (selectedOption + 6 > 10) ? selectedOption % 6 : selectedOption + 6;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                selectedOption = (selectedOption - 6 <= 0) ? selectedOption + 6 : selectedOption - 6;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }

            if ((selectedOption) >= 1 && (selectedOption) <= 6)
            {
                square_color(6, 2, x + (selectedOption - 1) * 7, y, 6);
                goto_xy((x + 3) + (selectedOption - 1) * 7, y + 1);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 6 && (selectedOption) <= 12)
            {
                square_color(6, 2, x + ((selectedOption - 1) - 6) * 7, y + 4, 6);
                goto_xy((x + 3) + ((selectedOption - 1) - 6) * 7, y + 5);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if (selectedOption == 13)
            {
                square_color(11, 2, x + ((selectedOption - 2) - 4) * 5, y + 12, 6);
                goto_xy((x + 4) + ((selectedOption - 2) - 4) * 5, y + 13);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }
        }
    }
    return month_selected[selectedOption - 1];
}
int sales_button3_1()
{
    int number = 0;
    char ctemp[20];
    number = sales_point3_1(29, 15, month_selected);

    if (selectedOption >= 1 && selectedOption <= 12)
    {
        system("cls");
        temp2 = number;
        itoa(temp2, ctemp, 10);
        if (strlen(ctemp) == 1)
        {
            char ttemp[3] = "0";
            strcat(ttemp, ctemp);
            strcpy(ctemp, ttemp);
        }
        strcat(date, ctemp);
        sales_status6();
    }

    else if (selectedOption == 13) {
        //�������� - ��ҹ�ư ��ư�� �Էµ� ��
        system("cls");
        sales_status1();
    }

    return number;
}

int sales_point3_2(int x, int y, int button[]) // �� ������Ȳ - �� ���ù�ư
{
    selectedOption = 1;
    for (int i = 0; i < 12; i++)
    {
        month_selected[i] = 1 + i;
    }

    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77 || key == 72 || key == 80) { // ������Ű(75), ����Ű(77), ��Ű(72), �Ʒ�Ű(80) ������ ���
            if ((selectedOption) >= 1 && (selectedOption) <= 6)
            {
                square_color(6, 2, x + (selectedOption - 1) * 7, y, 7);
                goto_xy((x + 3) + (selectedOption - 1) * 7, y + 1);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 6 && (selectedOption) <= 12)
            {
                square_color(6, 2, x + ((selectedOption - 1) - 6) * 7, y + 4, 7);
                goto_xy((x + 3) + ((selectedOption - 1) - 6) * 7, y + 5);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if (selectedOption == 13)
            {
                square_color(11, 2, x + ((selectedOption - 2) - 4) * 5, y + 12, 7);
                goto_xy((x + 4) + ((selectedOption - 2) - 4) * 5, y + 13);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 13 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption); // Ȯ�ο� ���
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 13) ? 1 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 80) {  // �Ʒ� ȭ��ǥ Ű
                selectedOption = (selectedOption + 6 > 10) ? selectedOption % 6 : selectedOption + 6;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                selectedOption = (selectedOption - 6 <= 0) ? selectedOption + 6 : selectedOption - 6;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }

            if ((selectedOption) >= 1 && (selectedOption) <= 6)
            {
                square_color(6, 2, x + (selectedOption - 1) * 7, y, 6);
                goto_xy((x + 3) + (selectedOption - 1) * 7, y + 1);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 6 && (selectedOption) <= 12)
            {
                square_color(6, 2, x + ((selectedOption - 1) - 6) * 7, y + 4, 6);
                goto_xy((x + 3) + ((selectedOption - 1) - 6) * 7, y + 5);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if (selectedOption == 13)
            {
                square_color(11, 2, x + ((selectedOption - 2) - 4) * 5, y + 12, 6);
                goto_xy((x + 4) + ((selectedOption - 2) - 4) * 5, y + 13);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }
        }
    }
    return month_selected[selectedOption - 1];
}
int sales_button3_2()
{
    char ctemp[20];
    int number = 0;

    number = sales_point3_2(29, 15, month_selected);

    if (selectedOption >= 1 && selectedOption <= 12)
    {
        system("cls");
        temp2 = number;
        itoa(temp2, ctemp, 10);
        if (strlen(ctemp) == 1)
        {
            char ttemp[3] = "0";
            strcat(ttemp, ctemp);
            strcpy(ctemp, ttemp);
        }
        strcat(date, ctemp);

        sales_status4_1();
    }

    else if (selectedOption == 13) {
        //�������� - ��ҹ�ư ��ư�� �Էµ� ��
        system("cls");
        sales_status1();
    }

    return number;
}


// �� ������Ȳ(31��)
int sales_point4_1(int x, int y, int button[]) // �� ������Ȳ - �� ���ù�ư
{
    selectedOption = 1;
    for (int i = 0; i < 31; i++)
    {
        day_selected[i] = 1 + i;
    }

    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77 || key == 72 || key == 80) { // ������Ű(75), ����Ű(77), ��Ű(72), �Ʒ�Ű(80) ������ ���

            if ((selectedOption) >= 1 && (selectedOption) <= 7)
            {
                square_color(6, 2, x + (selectedOption - 1) * 6, y, 7);
                goto_xy((x + 3) + (selectedOption - 1) * 6, (y + 1));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 7 && (selectedOption) <= 14)
            {
                square_color(6, 2, x + (selectedOption - 1 - 7) * 6, y + 4, 7);
                goto_xy((x + 3) + (selectedOption - 1 - 7) * 6, (y + 5));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 14 && (selectedOption) <= 21)
            {
                square_color(6, 2, x + (selectedOption - 1 - 14) * 6, y + 8, 7);
                goto_xy((x + 3) + (selectedOption - 1 - 14) * 6, y + 9);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 21 && (selectedOption) <= 28)
            {
                square_color(6, 2, x + (selectedOption - 1 - 21) * 6, y + 12, 7);
                goto_xy((x + 3) + (selectedOption - 1 - 21) * 6, y + 13);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 28 && (selectedOption) <= 31)
            {
                square_color(6, 2, x + (selectedOption - 1 - 28) * 6, y + 16, 7);
                goto_xy((x + 3) + (selectedOption - 1 - 28) * 6, y + 17);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if (selectedOption == 32)
            {
                square_color(11, 2, x + (selectedOption + 3), y + 22, 7);
                goto_xy((x + 4) + (selectedOption + 3), y + 23);
                printf("���");// button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 32 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption); // Ȯ�ο� ���
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 32) ? 1 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 80) {  // �Ʒ� ȭ��ǥ Ű

                selectedOption = (selectedOption + 7 > 31) ? selectedOption % 7 : selectedOption + 7;

                if (selectedOption == 0)
                {
                    selectedOption = 7;
                }
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                if (selectedOption - 7 <= 0 && selectedOption + 28 <= 31) // 31�Ͽ� �ش��ϴ� ��
                {
                    selectedOption = selectedOption + 28;
                }
                else if (selectedOption - 7 <= 0 && selectedOption + 28 >= 31)
                {
                    selectedOption = selectedOption + 21;
                }
                else
                {
                    selectedOption = selectedOption - 7;
                }
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }

            if ((selectedOption) >= 1 && (selectedOption) <= 7)
            {
                square_color(6, 2, x + (selectedOption - 1) * 6, y, 6);
                goto_xy((x + 3) + (selectedOption - 1) * 6, (y + 1));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 7 && (selectedOption) <= 14)
            {
                square_color(6, 2, x + (selectedOption - 1 - 7) * 6, y + 4, 6);
                goto_xy((x + 3) + (selectedOption - 1 - 7) * 6, (y + 5));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 14 && (selectedOption) <= 21)
            {
                square_color(6, 2, x + (selectedOption - 1 - 14) * 6, y + 8, 6);
                goto_xy((x + 3) + (selectedOption - 1 - 14) * 6, y + 9);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 21 && (selectedOption) <= 28)
            {
                square_color(6, 2, x + (selectedOption - 1 - 21) * 6, y + 12, 6);
                goto_xy((x + 3) + (selectedOption - 1 - 21) * 6, y + 13);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 28 && (selectedOption) <= 31)
            {
                square_color(6, 2, x + (selectedOption - 1 - 28) * 6, y + 16, 6);
                goto_xy((x + 3) + (selectedOption - 1 - 28) * 6, y + 17);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if (selectedOption == 32)
            {
                square_color(11, 2, x + (selectedOption + 3), y + 22, 6);
                goto_xy((x + 4) + (selectedOption + 3), y + 23);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }
        }

    }
    return day_selected[selectedOption - 1];
}
int sales_button4_1()
{
    char ctemp[20];
    int number = 0;

    number = sales_point4_1(29, 9, day_selected);
    //test();

    if (selectedOption >= 1 && selectedOption <= 31)
    {
        system("cls");
        temp3 = number;
        itoa(temp3, ctemp, 10);
        if (strlen(ctemp) == 1)
        {
            char ttemp[3] = "0";
            strcat(ttemp, ctemp);
            strcpy(ctemp, ttemp);
        }
        strcat(date, ctemp);
        sales_status7();
    }

    else if (selectedOption == 32) {
        //�������� - ��ҹ�ư ��ư�� �Էµ� ��
        system("cls");
        sales_status1();
    }

    return number;
}

// �� ������Ȳ(30��)
int sales_point4_2(int x, int y, int button[]) // �� ������Ȳ - �� ���ù�ư
{
    selectedOption = 1;
    for (int i = 0; i < 31; i++)
    {
        day_selected[i] = 1 + i;
    }

    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77 || key == 72 || key == 80) { // ������Ű(75), ����Ű(77), ��Ű(72), �Ʒ�Ű(80) ������ ���
            if ((selectedOption) >= 1 && (selectedOption) <= 7)
            {
                square_color(6, 2, x + (selectedOption - 1) * 6, y, 7);
                goto_xy((x + 3) + (selectedOption - 1) * 6, (y + 1));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 7 && (selectedOption) <= 14)
            {
                square_color(6, 2, x + (selectedOption - 1 - 7) * 6, y + 4, 7);
                goto_xy((x + 3) + (selectedOption - 1 - 7) * 6, (y + 5));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 14 && (selectedOption) <= 21)
            {
                square_color(6, 2, x + (selectedOption - 1 - 14) * 6, y + 8, 7);
                goto_xy((x + 3) + (selectedOption - 1 - 14) * 6, y + 9);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 21 && (selectedOption) <= 28)
            {
                square_color(6, 2, x + (selectedOption - 1 - 21) * 6, y + 12, 7);
                goto_xy((x + 3) + (selectedOption - 1 - 21) * 6, y + 13);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 28 && (selectedOption) <= 30)
            {
                square_color(6, 2, x + (selectedOption - 1 - 28) * 6, y + 16, 7);
                goto_xy((x + 3) + (selectedOption - 1 - 28) * 6, y + 17);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if (selectedOption == 31)
            {
                square_color(11, 2, x + (selectedOption + 4), y + 22, 7);
                goto_xy((x + 4) + (selectedOption + 4), y + 23);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }
            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 31 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption); // Ȯ�ο� ���
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 31) ? 1 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 80) {  // �Ʒ� ȭ��ǥ Ű

                selectedOption = (selectedOption + 7 > 31) ? selectedOption % 7 : selectedOption + 7;

                if (selectedOption == 0)
                {
                    selectedOption = 7;
                }
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                if (selectedOption - 7 <= 0 && selectedOption + 28 <= 30) // 31�Ͽ� �ش��ϴ� ��
                {
                    selectedOption = selectedOption + 28;
                }
                else if (selectedOption - 7 <= 0 && selectedOption + 28 >= 30)
                {
                    selectedOption = selectedOption + 21;
                }
                else
                {
                    selectedOption = selectedOption - 7;
                }
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }

            if ((selectedOption) >= 1 && (selectedOption) <= 7)
            {
                square_color(6, 2, x + (selectedOption - 1) * 6, y, 6);
                goto_xy((x + 3) + (selectedOption - 1) * 6, (y + 1));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 7 && (selectedOption) <= 14)
            {
                square_color(6, 2, x + (selectedOption - 1 - 7) * 6, y + 4, 6);
                goto_xy((x + 3) + (selectedOption - 1 - 7) * 6, (y + 5));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 14 && (selectedOption) <= 21)
            {
                square_color(6, 2, x + (selectedOption - 1 - 14) * 6, y + 8, 6);
                goto_xy((x + 3) + (selectedOption - 1 - 14) * 6, y + 9);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 21 && (selectedOption) <= 28)
            {
                square_color(6, 2, x + (selectedOption - 1 - 21) * 6, y + 12, 6);
                goto_xy((x + 3) + (selectedOption - 1 - 21) * 6, y + 13);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 28 && (selectedOption) <= 30)
            {
                square_color(6, 2, x + (selectedOption - 1 - 28) * 6, y + 16, 6);
                goto_xy((x + 3) + (selectedOption - 1 - 28) * 6, y + 17);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if (selectedOption == 31)
            {
                square_color(11, 2, x + (selectedOption + 4), y + 22, 6);
                goto_xy((x + 4) + (selectedOption + 4), y + 23);
                printf("���");// button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
        }

    }
    return day_selected[selectedOption - 1];
}
int sales_button4_2()
{
    char ctemp[20];
    int number = 0;

    number = sales_point4_2(29, 9, day_selected);

    if (selectedOption >= 1 && selectedOption <= 30)
    {
        system("cls");
        temp3 = number;
        itoa(temp3, ctemp, 10);
        if (strlen(ctemp) == 1)
        {
            char ttemp[3] = "0";
            strcat(ttemp, ctemp);
            strcpy(ctemp, ttemp);
        }
        strcat(date, ctemp);
        sales_status7();
    }

    else if (selectedOption == 31) {
        //�������� - ��ҹ�ư ��ư�� �Էµ� ��
        system("cls");
        sales_status1();
    }

    return number;
}

// �� ������Ȳ (29��)
int sales_point4_3(int x, int y, int button[]) // �� ������Ȳ - �� ���ù�ư
{
    selectedOption = 1;
    for (int i = 0; i < 31; i++)
    {
        day_selected[i] = 1 + i;
    }

    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77 || key == 72 || key == 80) { // ������Ű(75), ����Ű(77), ��Ű(72), �Ʒ�Ű(80) ������ ���

            if ((selectedOption) >= 1 && (selectedOption) <= 7)
            {
                square_color(6, 2, x + (selectedOption - 1) * 6, y, 7);
                goto_xy((x + 3) + (selectedOption - 1) * 6, (y + 1));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 7 && (selectedOption) <= 14)
            {
                square_color(6, 2, x + (selectedOption - 1 - 7) * 6, y + 4, 7);
                goto_xy((x + 3) + (selectedOption - 1 - 7) * 6, (y + 5));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 14 && (selectedOption) <= 21)
            {
                square_color(6, 2, x + (selectedOption - 1 - 14) * 6, y + 8, 7);
                goto_xy((x + 3) + (selectedOption - 1 - 14) * 6, y + 9);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 21 && (selectedOption) <= 28)
            {
                square_color(6, 2, x + (selectedOption - 1 - 21) * 6, y + 12, 7);
                goto_xy((x + 3) + (selectedOption - 1 - 21) * 6, y + 13);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 28 && (selectedOption) <= 29)
            {
                square_color(6, 2, x + (selectedOption - 1 - 28) * 6, y + 16, 7);
                goto_xy((x + 3) + (selectedOption - 1 - 28) * 6, y + 17);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if (selectedOption == 30)
            {
                square_color(11, 2, x + (selectedOption + 5), y + 22, 7);
                goto_xy((x + 4) + (selectedOption + 5), y + 23);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 30 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption); // Ȯ�ο� ���
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 30) ? 1 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 80) {  // �Ʒ� ȭ��ǥ Ű

                selectedOption = (selectedOption + 7 > 30) ? selectedOption % 7 : selectedOption + 7;

                if (selectedOption == 0)
                {
                    selectedOption = 7;
                }
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                if (selectedOption - 7 <= 0 && selectedOption + 28 <= 30) // 31�Ͽ� �ش��ϴ� ��
                {
                    selectedOption = selectedOption + 28;
                }
                else if (selectedOption - 7 <= 0 && selectedOption + 28 >= 30)
                {
                    selectedOption = selectedOption + 21;
                }
                else
                {
                    selectedOption = selectedOption - 7;
                }
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }

            if ((selectedOption) >= 1 && (selectedOption) <= 7)
            {
                square_color(6, 2, x + (selectedOption - 1) * 6, y, 6);
                goto_xy((x + 3) + (selectedOption - 1) * 6, (y + 1));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 7 && (selectedOption) <= 14)
            {
                square_color(6, 2, x + (selectedOption - 1 - 7) * 6, y + 4, 6);
                goto_xy((x + 3) + (selectedOption - 1 - 7) * 6, (y + 5));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 14 && (selectedOption) <= 21)
            {
                square_color(6, 2, x + (selectedOption - 1 - 14) * 6, y + 8, 6);
                goto_xy((x + 3) + (selectedOption - 1 - 14) * 6, y + 9);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 21 && (selectedOption) <= 28)
            {
                square_color(6, 2, x + (selectedOption - 1 - 21) * 6, y + 12, 6);
                goto_xy((x + 3) + (selectedOption - 1 - 21) * 6, y + 13);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 28 && (selectedOption) <= 29)
            {
                square_color(6, 2, x + (selectedOption - 1 - 28) * 6, y + 16, 6);
                goto_xy((x + 3) + (selectedOption - 1 - 28) * 6, y + 17);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if (selectedOption == 30)
            {
                square_color(11, 2, x + (selectedOption + 5), y + 22, 6);
                goto_xy((x + 4) + (selectedOption + 5), y + 23);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }
        }

    }
    return day_selected[selectedOption - 1];
}
int sales_button4_3()
{
    char ctemp[20];
    int number = 0;
    number = sales_point4_3(29, 9, day_selected);

    if (selectedOption >= 1 && selectedOption <= 29)
    {
        system("cls");
        temp3 = number;
        itoa(temp3, ctemp, 10);
        if (strlen(ctemp) == 1)
        {
            char ttemp[3] = "0";
            strcat(ttemp, ctemp);
            strcpy(ctemp, ttemp);
        }
        strcat(date, ctemp);
        sales_status7();
    }

    else if (selectedOption == 30) {
        //�������� - ��ҹ�ư ��ư�� �Էµ� ��
        system("cls");
        sales_status1();
    }

    return number;
}

// �� ������Ȳ (28��)
int sales_point4_4(int x, int y, int button[]) // �� ������Ȳ - �� ���ù�ư
{
    selectedOption = 1;
    for (int i = 0; i < 31; i++)
    {
        day_selected[i] = 1 + i;
    }

    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77 || key == 72 || key == 80) { // ������Ű(75), ����Ű(77), ��Ű(72), �Ʒ�Ű(80) ������ ���

            if ((selectedOption) >= 1 && (selectedOption) <= 7)
            {
                square_color(6, 2, x + (selectedOption - 1) * 6, y, 7);
                goto_xy((x + 3) + (selectedOption - 1) * 6, (y + 1));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 7 && (selectedOption) <= 14)
            {
                square_color(6, 2, x + (selectedOption - 1 - 7) * 6, y + 4, 7);
                goto_xy((x + 3) + (selectedOption - 1 - 7) * 6, (y + 5));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 14 && (selectedOption) <= 21)
            {
                square_color(6, 2, x + (selectedOption - 1 - 14) * 6, y + 8, 7);
                goto_xy((x + 3) + (selectedOption - 1 - 14) * 6, y + 9);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 21 && (selectedOption) <= 28)
            {
                square_color(6, 2, x + (selectedOption - 1 - 21) * 6, y + 12, 7);
                goto_xy((x + 3) + (selectedOption - 1 - 21) * 6, y + 13);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if (selectedOption == 29)
            {
                square_color(11, 2, x + (selectedOption + 6), y + 22, 7);
                goto_xy((x + 4) + (selectedOption + 6), y + 23);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 29 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption); // Ȯ�ο� ���
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 29) ? 1 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 80) {  // �Ʒ� ȭ��ǥ Ű

                selectedOption = (selectedOption + 7 > 29) ? selectedOption % 7 : selectedOption + 7;

                if (selectedOption == 0)
                {
                    selectedOption = 7;
                }
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                selectedOption = (selectedOption - 7 <= 0 ) ? selectedOption + 21 : selectedOption - 7;

                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }

            if ((selectedOption) >= 1 && (selectedOption) <= 7)
            {
                square_color(6, 2, x + (selectedOption - 1) * 6, y, 6);
                goto_xy((x + 3) + (selectedOption - 1) * 6, (y + 1));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) > 7 && (selectedOption) <= 14)
            {
                square_color(6, 2, x + (selectedOption - 1 - 7) * 6, y + 4, 6);
                goto_xy((x + 3) + (selectedOption - 1 - 7) * 6, (y + 5));
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 14 && (selectedOption) <= 21)
            {
                square_color(6, 2, x + (selectedOption - 1 - 14) * 6, y + 8, 6);
                goto_xy((x + 3) + (selectedOption - 1 - 14) * 6, y + 9);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) > 21 && (selectedOption) <= 28)
            {
                square_color(6, 2, x + (selectedOption - 1 - 21) * 6, y + 12, 6);
                goto_xy((x + 3) + (selectedOption - 1 - 21) * 6, y + 13);
                printf("%d", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if (selectedOption == 29)
            {
                square_color(11, 2, x + (selectedOption + 6), y + 22, 6);
                goto_xy((x + 4) + (selectedOption + 6), y + 23);
                printf("���");
                goto_xy(0, 10); // Ŀ�� ����
            }
        }

    }
    return day_selected[selectedOption - 1];
}
int sales_button4_4()
{
    char ctemp[20];
    int number = 0;
    number = sales_point4_4(29, 9, day_selected);

    if (selectedOption >= 1 && selectedOption <= 28)
    {
        system("cls");
        temp3 = number;
        itoa(temp3, ctemp, 10);
        if (strlen(ctemp) == 1)
        {
            char ttemp[3] = "0";
            strcat(ttemp, ctemp);
            strcpy(ctemp, ttemp);
        }
        strcat(date, ctemp);
        sales_status7();
    }

    else if (selectedOption == 29) {
        //�������� - ��ҹ�ư ��ư�� �Էµ� ��
        system("cls");
        sales_status1();
    }

    return number;
}

// ������Ȳ, ó������
void sales_point5(int x, int y, char button[][20]) {
    selectedOption = 0;
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 75 || key == 77) { // ������Ű, ����Ű ������ ���
            if ((selectedOption) == 1)
            {
                square_color(13, 2, x , y, 7);
                goto_xy(x + 3 , y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 2)
            {
                square_color(13, 2, x + 39, y, 7);
                goto_xy((x + 42), y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 75) {  // ���� ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? 2 : selectedOption - 1;
                //printf("%d", selectedOption);
            }
            else if (key == 77) {  // ������ ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 2) ? 1 : selectedOption + 1;
                //printf("%d", selectedOption);
            }

            if ((selectedOption) == 1)
            {
                square_color(13, 2, x, y, 6);
                goto_xy(x + 3, y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 2)
            {
                square_color(13, 2, x + 39, y, 6);
                goto_xy((x + 42), y + 1);
                printf("%s", button[selectedOption - 1]);
                goto_xy(0, 10); // Ŀ�� ����
            }
        }
    }
}
void sales_button5_1()
{
    sales_point5(24, 32, sales_select);  // �޴� ����

    if (selectedOption == 1) 
    {// ���� ��Ȳ
        sales_status1();

    }
    else if (selectedOption == 2) {
        // ó�� ����
        admin_main1();
    }
}

// �ֹ����� ��ư
int order_point(int x, int y, char button[][20])
{
    selectedOption = 0;
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 72 || key == 80) { // ��Ű, �Ʒ�Ű ������ ���
            if ((selectedOption) >= 1 && (selectedOption) <= 7)
            {
                square_color(14, 2, x, y + (selectedOption - 1) * 3, 7);
                goto_xy((x + 3), (y + 1) + (selectedOption - 1) * 3);
                printf("%s", button[0]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 8)
            {
                square_color(14, 2, (x - 35), (y + 1) + (selectedOption - 1) * 3, 7);
                goto_xy((x - 32), (y + 2) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 7]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            
            else if ((selectedOption) == 9)
            {
                square_color(9, 2, (x - 2), (y - 2) + (selectedOption - 1) * 3, 7);
                goto_xy((x + 1), (y - 1) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 7]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 10)
            {
                square_color(9, 2, (x + 8), (y - 5) + (selectedOption - 1) * 3, 7);
                goto_xy((x + 11), (y - 4) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 7]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 80) {  // �Ʒ� ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 10) ? selectedOption - 9 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? selectedOption + 9 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }

            if ((selectedOption) >= 1 && (selectedOption) <= 7)
            {
                square_color(14, 2, x, y + (selectedOption - 1) * 3, 6);
                goto_xy((x + 3), (y + 1) + (selectedOption - 1) * 3);
                printf("%s", button[0]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 8)
            {
                square_color(14, 2, (x - 35), (y + 1) + (selectedOption - 1) * 3, 6);
                goto_xy((x - 32), (y + 2) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 7]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 9)
            {
                square_color(9, 2, (x - 2), (y - 2) + (selectedOption - 1) * 3, 6);
                goto_xy((x + 1), (y - 1) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 7]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            else if ((selectedOption) == 10)
            {
                square_color(9, 2, (x + 8), (y - 5) + (selectedOption - 1) * 3, 6);
                goto_xy((x + 11), (y - 4) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 7]);
                goto_xy(0, 10); // Ŀ�� ����
            }

        }

    }
    return selectedOption;
}

int order_button()
{
    int number = 0;

    number = (order_point(59, 7, order_select)) + (order_page * 7);;  // �޴� ����

    if (selectedOption >= 1 && selectedOption < 8) // 1~7����
    {
        FILE* file = fopen("Order.txt", "r");

        FILE* file1 = fopen("Order_detail.txt", "r");
        int kk = 0;
        goto_xy(0, 10);
        printf("%d\n", order_pc_num[number-1]);
        while (fscanf(file, "%s %s %d %s %d %s %d", admin_order.Order_id, admin_order.id, &admin_order.total, admin_order.pay, &admin_order.pc_num, admin_order.what, &admin_order.state) == 7)
        {
            if (order_pc_num[number-1] == admin_order.pc_num && strcmp(admin_order.what, "FD") == 0) // �����ֹ�(FD) �ΰ͸� ��������
            {
                while (fscanf(file1, "%s %s %d", admin_detail_order.Order_id, admin_detail_order.Menu_id, &admin_detail_order.count) == 3)
                {
                    if (strcmp(order_pc_id[number-1], admin_detail_order.Order_id) == 0)
                    {
                        strcpy(admin_basket[kk].Order_id, admin_detail_order.Order_id);
                        strcpy(admin_basket[kk].Menu_id, admin_detail_order.Menu_id);
                        admin_basket[kk].count = admin_detail_order.count; // ������ ��ٱ��Ͽ� ��Ƶ�
                        strcpy(queue_order.Order_id, admin_basket[kk].Order_id);
                        //printf("%d. %s %s %d\n", kk, admin_basket[kk].Order_id, admin_basket[kk].Menu_id, admin_basket[kk].count);
                        //getch();
                        kk++;
                    }
                }
            }

        }
        fclose(file);
        fclose(file1);

        if (order_pc_num[number - 1] == 0)
        {
            order_status1();
        }

        system("cls");
        order_status2();
    }


    else if (selectedOption == 8) 
    {
        // ���� ȭ�� (������)
        order_page = 0;
        system("cls");
        admin_main1();
    }

    else if (selectedOption == 9)
    {
        // ����
        system("cls");
       
        order_page--;
        if (order_page < 0)
        {
            order_page = 0;
        }
        
        order_status1();
    }

    else if (selectedOption == 10)
    {
        // ����
        system("cls");
        order_page++;


        if (order_pc_num[(order_page * 7)] == 0)
        {
            save_num = 1;
        }
        else
        {
            save_num = 0;
        }


        if (save_num == 1) order_page--;



        order_status1();
    }

    return number;
}

int account_list_point(int x, int y, char button[][20], User account[])
{
    selectedOption = 0;
    char key = 72;
    while (key != 13) {
        key = _getch();
        if (key == 72 || key == 80) { // ��Ű, �Ʒ�Ű ������ ���
            if ((selectedOption) >= 1 && (selectedOption) <= 8)
            {
                square_color1(58, 2, selectedOption - 1, 7);
                int ww = 0;
                for (int j = (account_page * 8); j < (account_page * 8) + 8; j++)
                {
                    goto_xy(24, 5 + (3 * ww));
                    printf("%d", j + 1);
                    ww++;
                }
                goto_xy((x - 27), (y + 3) + (selectedOption - 1) * 3); // 12 18
                printf("%s", account[(account_page * 8) + (selectedOption - 1)].name);
                goto_xy((x - 15), (y + 3) + (selectedOption - 1) * 3);
                printf("%s", account[(account_page * 8) + (selectedOption - 1)].id);
                goto_xy((x + 3), (y + 3) + (selectedOption - 1) * 3);
                if (year[(account_page * 8) + (selectedOption - 1)] != 0)
                {
                    printf("%04d-%02d-%02d", year[(account_page * 8) + (selectedOption - 1)], month[(account_page * 8) + (selectedOption - 1)], day[(account_page * 8) + (selectedOption - 1)]);

                }
                goto_xy(0, 10); // Ŀ�� ����

            }
            else if ((selectedOption) == 9)
            {
                square_color(14, 2, (x - 37), (y + 2) + (selectedOption - 1) * 3, 7);
                goto_xy((x -34), (y + 3) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 9]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 10)
            {
                square_color(9, 2, (x), (y - 1) + (selectedOption - 1) * 3, 7);
                goto_xy((x + 3), (y)+(selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 9]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            
            else if ((selectedOption) == 11)
            {
                square_color(9, 2, (x + 10), (y - 4) + (selectedOption - 1) * 3, 7);
                goto_xy((x + 13), (y - 3) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 9]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            else if ((selectedOption) == 12)
            {
                square_color(15, 2, (x + 4), (y - 3) + (selectedOption - 1) * 3, 7);
                goto_xy((x + 8), (y - 2) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 9]);
                goto_xy(0, 10); // Ŀ�� ����
            }

            if (key == 80) {  // �Ʒ� ȭ��ǥ Ű
                selectedOption = (selectedOption + 1 > 12) ? selectedOption - 11 : selectedOption + 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }
            else if (key == 72) {  // ��Ű ȭ��ǥ Ű
                selectedOption = (selectedOption - 1 <= 0) ? selectedOption + 11 : selectedOption - 1;
                goto_xy(0, 0);
                printf("   ");
                goto_xy(0, 0);
                printf("%d", selectedOption);
            }

            if ((selectedOption) >= 1 && (selectedOption) <= 8)
            {
                square_color1(58, 2 ,selectedOption - 1, 6);
                int ww = 0;
                for (int j = (account_page * 8); j < (account_page * 8) + 8; j++)
                {
                    goto_xy(24, 5 + (3 * ww));
                    printf("%d", j + 1);
                    ww++;
                }
                goto_xy((x - 27), (y + 3) + (selectedOption - 1) * 3); // 12 18
                printf("%s", account[(account_page * 8) + (selectedOption - 1)].name);
                goto_xy((x - 15), (y + 3) + (selectedOption - 1) * 3);
                printf("%s", account[(account_page * 8) + (selectedOption - 1)].id);
                goto_xy((x + 3), (y + 3) + (selectedOption - 1) * 3);
                if (year[(account_page * 8) + (selectedOption - 1)] != 0)
                {
                    printf("%04d-%02d-%02d", year[(account_page * 8) + (selectedOption - 1)], month[(account_page * 8) + (selectedOption - 1)], day[(account_page * 8) + (selectedOption - 1)]);
                }
     
                goto_xy(0, 10); // Ŀ�� ����

            }
            else if ((selectedOption) == 9)
            {
                square_color(14, 2, (x - 37), (y + 2) + (selectedOption - 1) * 3, 6);
                goto_xy((x - 34), (y + 3) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 9]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            
            else if ((selectedOption) == 10)
            {
                square_color(9, 2, (x), (y - 1) + (selectedOption - 1) * 3, 6);
                goto_xy((x + 3), (y) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 9]);
                goto_xy(0, 10); // Ŀ�� ����
            }
            
            else if ((selectedOption) == 11)
            {
                square_color(9, 2, (x + 10), (y - 4) + (selectedOption - 1) * 3, 6);
                goto_xy((x + 13), (y - 3) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 9]);
                goto_xy(0, 10); // Ŀ�� ����
            }
       
            else if ((selectedOption) == 12)
            {
                square_color(15, 2, (x + 4), (y - 3) + (selectedOption - 1) * 3, 6);
                goto_xy((x + 8), (y - 2) + (selectedOption - 1) * 3);
                printf("%s", button[selectedOption - 9]);
                goto_xy(0, 10); // Ŀ�� ����
            }


        }

    }
    return selectedOption;
}

int account_list_button()
{
    int number = 0;
    //printMenu(42, 12);  // �޴� ���

    number = (account_list_point(58, 2, account_check, admin_account)) + (account_page * 8);  // �޴� ����
    //test();

    if (selectedOption >= 1 && selectedOption < 8) // 1~8����
    {
        //��� ȸ�� ��ȸ
        save_num = number-1;
        if (year[save_num] == 0)
        {
            member_list();
        }
        else
        {
            admin_main2();
        }
        
    }

    else if (selectedOption == 9)
    {
        // ȸ�� ã��(������)
        system("cls");
        account_page = 0;
        search_member();
    }

    else if (selectedOption == 10)
    {
        // ����
        system("cls");
        if (account_page <= 0)
        {
            account_page = 0;
        }
        else
        {
            account_page--;
        }
        
        member_list();
    }

    else if (selectedOption == 11)
    {
        // ����
        system("cls");
        if (year[(account_page * 8) + 7] != 0)
        {
            account_page++;
        }

        //account_page++;
        member_list();
    }

    else if (selectedOption == 12)
    {
        // ���ư���
        system("cls");
        admin_main1();
    }

    return number;
}
int account_search_list_button()
{
    int number = 0;
    number = (account_list_point(58, 2, account_check, admin_search_account)) + (account_page * 8);  // �޴� ����

    if (selectedOption >= 1 && selectedOption < 8) // 1~8����
    {
        //��� ȸ�� ��ȸ
        save_num = number - 1;

        if (year[save_num] == 0)
        {
            serched_member_list();
        }
        else
        {
            admin_main3();
        }

    }

    else if (selectedOption == 9)
    {
        // ȸ�� ã��(������)
        system("cls");
        search_member();
    }

    else if (selectedOption == 10)
    {
        // ����
        system("cls");
        if (account_page <= 0)
        {
            account_page = 0;
        }
        else
        {
            account_page--;
        }

        serched_member_list();
    }

    else if (selectedOption == 11)
    {
        // ����
        system("cls");
        if (year[(account_page * 8) + 7] != 0)
        {
            account_page++;
        }

        serched_member_list();
    }

    else if (selectedOption == 12)
    {
        // ���ư���
        system("cls");
        admin_main1();
    }

    return number;
}