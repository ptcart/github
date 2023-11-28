#pragma once
#pragma once
#pragma warning(disable : 4996)
#pragma warning(disable : 6031)
#define MAX 10000
#define TOKEN_COUNT 1000    // ��ū�� �ִ� ����
#include<stdio.h>
#include<Windows.h>
#include<string.h>
#include<time.h>
#include<stdbool.h>

// UI�Լ�
void table(int width, int height, int width_len, int height_len, int x, int y, char* msg[100]);// ���� ����, ���� ����, ���� ũ��, ���� ũ��, x ��ǥ, y ��ǥ, (����?)
void original_table(int width, int height, int width_len, int height_len, int x, int y, char* msg[100]);// ���� ����, ���� ����, ���� ũ��, ���� ũ��, x ��ǥ, y ��ǥ, (����?)
void square(int width, int height, int x, int y);
void square3(int width, int height, int x, int y);
void square_char(int width, int height, int x, int y, char* msg);
void square_char_red(int width, int height, int x, int y, char* msg);
void square_char_blue(int width, int height, int x, int y, char* msg);
void square_char1(int width, int height, int x, int y, char* msg);
void square_char2(int width, int height, int x, int y, char* msg);
void square_char3(int width, int height, int x, int y, char* msg);
void square_char3_gray(int width, int height, int x, int y, char* msg);
void square_char3_blue(int width, int height, int x, int y, char* msg);
void square_char3_red(int width, int height, int x, int y, char* msg);
void square_char4(int width, int height, int x, int y, char* msg);
// ���̺� �Լ�
void goto_xy(int x, int y);

typedef struct
{
    int hour;
    int minute;
    int second;
}change_sec;

typedef struct
{
    char id[20];
    char pw[20];
    char name[20];
    char birth[10];
    char gender[4];
    int second;
    int money;
}User;


change_sec change_clock(int second);

void change_second();
void change_second_1();

change_sec test_clock; // �ð� �� �� �� ��ȯ �׽�Ʈ
change_sec free_add_clock; // �����ڰ� �ð� �����Ӱ� �߰��ϴ� ����

//User user;

User in_user;

int use_hour;
int use_minute;

typedef struct
{
    char id[20];
    char pw[20];
}Admin;

Admin admin;

// �޴����̵�, ����, �̸�, ����
typedef struct
{
    char Menu_id[20];
    char sort[20];
    char Name[20];
    int price;
}Menu;

Menu in_menu[50];



typedef struct // ��ٱ��Ͽ� ������ؼ� ����ؾ��� ��
{
    char Cat_food[20]; // ���� ����
    char name_food[20]; // �����̸�
    int price; // ����
    int num_food; // ������ ����
}Basket;

Basket basket_Menu[5]; // ��ٱ��Ͽ� ���� �� �ִ� ũ��(�ִ밡 5�� �׷��� ����)

// �������ʿ� ���� ����ü �����־ ������ �� �����ϰ� ������ ��
typedef struct
{
    Basket food;
    //char pay[10]; // ������� ���� ����
    char day[10]; // �ֹ��� ��¥
}Order1;

// �ֹ� ���� ����ü
typedef struct // �ֹ�����
{
    char Order_id[20]; // �ֹ��� id, (�ǽð���¥�� �о�;���)
    char id[20]; //�������̵� ������� �� in_user�� ���� �;ߵǳ�
    int total; // �ѱݾ�
    char pay[20]; // �������
    int pc_num; // PC�� ��ȣ
    char what[4]; // PC or FOOD
    int state; // �ֹ� ����
}Order;

typedef struct // �ֹ�����
{
    char Order_id[20]; // �ֹ��� id, (�ǽð���¥�� �о�;���)
    char Menu_id[20]; // �޴� ���̵� �����;���..
    int count; // �ѱݾ�

}Order_detail;

Order_detail order_detail[5]; // ��ٱ��� �ִ� 5��

User admin_account[1000];
User admin_search_account[1000];
Order admin_order; // ������ �ֹ�����
Order admin_check_order; // ������ �ֹ����� �Ϸ�/��� �뵵
Order_detail admin_detail_order; // ������ �ֹ��󼼳���
Order_detail admin_basket[5]; // ������ ��ٱ���;
Menu admin_menu; // ������ �޴�

int order_pc_num[1000];
char order_pc_id[1000][20];
int save_num;


char dateString[20]; // ���� ��¥��ȸ�� �� ��
char before_dateString[20]; // �ֱٸ������� �ֹ��� ��¥��ȸ

Order order;
//Order order[50]; // �ֹ� ������ 5���� ����, �ð��� �Ѱ����� �ֹ������� ���沨��, ��ٱ��ϴ� �ִ� 5���ϱ�
Order before_order; // �ֱ� �������� �ߴ� �ֹ� ����

int all_price; // ��ٱ��� �� ����

int join_check;
int number;
int pc_login;
void make_order_datail(const char* file_path);
void modify_num_File(char* fileName, char* search_id);

// ��ư �Լ���
void ok_point(int x, int y, char button[][20]);
void ok_button1(); // ȸ������ Ȯ����ҹ�ư
//void ok_button2(); // ȸ���������� Ȯ����ҹ�ư
void ok_button3(); // (������)ȸ�����
//���߿� �����ڿ��� ȸ�������� ���� ���� ������ �� ��..?

void modify_point(int x, int y, User button[]);
void modify_button1();


void mainpoint(int x, int y, char button[][20]);
void main_button();

int seat_point(int x, int y, int button[][20]);
int seat_button();

void user_point(int x, int y, char button[][20]);
void user_button();

int pay_point(int x, int y, char button[][20]);
int pay_button();

void quit_point(int x, int y, char button[][20]);
void quit_button1();
void quit_button2();
void quit_button3();
void quit_button4();


void delete_button();

int cat_menu_point(int x, int y, char button[][20]);
int cat_menu_button();

int name_menu_point(int x, int y, Menu button[], int number);
int name_menu_button(int number);

void food_basket();

void square_color(int width, int height, int x, int y, int color);

void square_char_2(int width, int height, int x, int y, char* msg1, char* msg2);


void mainlog();
void show_join();
void join_membership();
void make_seat();
void enter_pc(int number);

int time_point(int x, int y, char button1[][20], char button2[][20]);
int time_button();

void go_counter();

void char_color(int x, int y, char* msg, int color);
void square_int(int width, int height, int x, int y, int msg);

void user_main();
void add_time1();


void add_time2();
void add_time3();


void food_menu1(); // ���ĸ޴�(ī�װ�)
void food_menu2(); // ���ĸ޴�(�����̸�)
void food_menu3(); // ���ſϷ�
void food_menu4(); // ���� ����

void food_menu3_1(); // ��ٱ��� ���

void food_basket(); // ��ٱ���â
void food_no_basket(); // ��ٱ��Ͽ� ������ ���� ��
int k;



void quit_pc(); // ��� ����
void quit_pc2(int number); // pc�� ������ ��...
void quit_pc3();
void quit_pc4();


void test();
//char save = 72;
  // ���õ� �ɼ��� ������ ����, �⺻������ 1 ����


// ������ �Լ�

void admin_main1();
void admin_main2();
void member_modification1();

void show_modify();
void delete_user_id(const char* fileName, char* search_id);
void show_delete();
void delete_user();
void delete_user_1();
void show_delete_1();
void register_membership();
void show_regist();

void admin_point1(int x, int y, char button[][20]);
void admin_button1();
void admin_point2(int x, int y, char button[][20]);
void admin_button2();
void admin_button3();


void sales_point1(int x, int y, char button[][20]);
void sales_button1();
int sales_point2_1(int x, int y, int button[]);
int sales_button2_1();
int sales_point2_2(int x, int y, int button[]);
int sales_button2_2();
int sales_point2_3(int x, int y, int button[]);
int sales_button2_3();
int sales_point3_1(int x, int y, int button[]);
int sales_button3_1();
int sales_point3_2(int x, int y, int button[]);
int sales_button3_2();
int sales_point4_1(int x, int y, int button[]);
int sales_button4_1();
int sales_point4_2(int x, int y, int button[]);
int sales_button4_2();
int sales_point4_3(int x, int y, int button[]);
int sales_button4_3();
int sales_point4_4(int x, int y, int button[]);
int sales_button4_4();
void sales_point5(int x, int y, char button[][20]);
void sales_button5_1();

int order_point(int x, int y, char button[][20]);
int order_button();

int account_list_point(int x, int y, char button[][20], User account[]); // ���� ���
int account_list_button();
int account_search_list_button();

void save_user_account(char* fileName);
int year[1000], month[1000], day[1000];



//ȸ�� ���
void member_list(); // ȸ�����
void search_member(); // ȸ��ã��
void serched_member_list();

int temp1; // �ӽ� �� ������Ȳ ����
int temp2; // �ӽ� �� ������Ȳ ����
int temp3; // �ӽ� �� ������Ȳ ����
void sales_status1(); // ������Ȳ
void sales_status2_1(); // ��������(�� ������Ȳ)
void sales_status2_2(); // ��������(�� ������Ȳ)
void sales_status2_3(); // ��������(�� ������Ȳ)
void sales_status3_1(); // �� ����(�� ������Ȳ)
void sales_status3_2(); // �� ����(�� ������Ȳ)
void sales_status4_1(); // �� ����(�� ������Ȳ)





void sales_status5(); // ������Ȳ �����ֱ�(����)
void sales_status6(); // ������Ȳ �����ֱ�(��)
void sales_status7(); // ������Ȳ �����ֱ�(��)

void modify_state_File(char* fileName, char* search_id);

void order_status1();
void order_status2();
void order_status3();
void order_status4();

int getLimitedInput(char* inputString, int maxDigits);
void free_add_time1();
void free_add_time2();


Order1 order1[10]; //= { {0},"0" };

void make_order_id(const char* file_path);


int order_page; // �ֹ����� ������������ ����ؼ� PC��ȣ�� �������� ���� �뵵
int account_page; // ȸ������ ������������ ����ؼ� PC��ȣ�� �������� ���� �뵵
int cases; // �˻��������� 0, �ߴ� �����̸� 1�� �Ѿ�� ��

void modify_num_File1(char* fileName, char* search_id, User user[]);
void member_modification2();
void show_modify2();

char date[20]; // ������Ȳ�� ���� ��¥
Order sales_order;
int PCsum;
int FDsum;
int flag;
Order queue_order;