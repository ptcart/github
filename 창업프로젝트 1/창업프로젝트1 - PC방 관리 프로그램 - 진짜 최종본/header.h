#pragma once
#pragma once
#pragma warning(disable : 4996)
#pragma warning(disable : 6031)
#define MAX 10000
#define TOKEN_COUNT 1000    // 토큰의 최대 개수
#include<stdio.h>
#include<Windows.h>
#include<string.h>
#include<time.h>
#include<stdbool.h>

// UI함수
void table(int width, int height, int width_len, int height_len, int x, int y, char* msg[100]);// 가로 개수, 세로 개수, 가로 크기, 세로 크기, x 좌표, y 좌표, (문자?)
void original_table(int width, int height, int width_len, int height_len, int x, int y, char* msg[100]);// 가로 개수, 세로 개수, 가로 크기, 세로 크기, x 좌표, y 좌표, (문자?)
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
// 테이블 함수
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

change_sec test_clock; // 시간 시 분 초 변환 테스트
change_sec free_add_clock; // 관리자가 시간 자유롭게 추가하는 변수

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

// 메뉴아이디, 종류, 이름, 가격
typedef struct
{
    char Menu_id[20];
    char sort[20];
    char Name[20];
    int price;
}Menu;

Menu in_menu[50];



typedef struct // 장바구니에 담기위해서 사용해야할 듯
{
    char Cat_food[20]; // 음식 종류
    char name_food[20]; // 음식이름
    int price; // 가격
    int num_food; // 음식의 갯수
}Basket;

Basket basket_Menu[5]; // 장바구니에 담을 수 있는 크기(최대가 5라서 그렇게 잡음)

// 관리자쪽에 아직 구조체 남아있어서 나머지 다 수정하고 삭제할 듯
typedef struct
{
    Basket food;
    //char pay[10]; // 결제방식 없앨 예정
    char day[10]; // 주문한 날짜
}Order1;

// 주문 내역 구조체
typedef struct // 주문내역
{
    char Order_id[20]; // 주문한 id, (실시간날짜를 읽어와야함)
    char id[20]; //유저아이디 끌고오면 됨 in_user거 끌고 와야되네
    int total; // 총금액
    char pay[20]; // 결제방식
    int pc_num; // PC방 번호
    char what[4]; // PC or FOOD
    int state; // 주문 상태
}Order;

typedef struct // 주문내역
{
    char Order_id[20]; // 주문한 id, (실시간날짜를 읽어와야함)
    char Menu_id[20]; // 메뉴 아이디 가져와야함..
    int count; // 총금액

}Order_detail;

Order_detail order_detail[5]; // 장바구니 최대 5개

User admin_account[1000];
User admin_search_account[1000];
Order admin_order; // 관리자 주문내역
Order admin_check_order; // 관리자 주문내역 완료/쥐소 용도
Order_detail admin_detail_order; // 관리자 주문상세내역
Order_detail admin_basket[5]; // 관리자 장바구니;
Menu admin_menu; // 관리자 메뉴

int order_pc_num[1000];
char order_pc_id[1000][20];
int save_num;


char dateString[20]; // 현재 날짜조회를 할 때
char before_dateString[20]; // 최근마지막에 주문한 날짜조회

Order order;
//Order order[50]; // 주문 내역도 5으로 한정, 시간은 한개씩만 주문내역이 생길꺼고, 장바구니는 최대 5개니깐
Order before_order; // 최근 마지막에 했던 주문 내역

int all_price; // 장바구니 총 가격

int join_check;
int number;
int pc_login;
void make_order_datail(const char* file_path);
void modify_num_File(char* fileName, char* search_id);

// 버튼 함수들
void ok_point(int x, int y, char button[][20]);
void ok_button1(); // 회원가입 확인취소버튼
//void ok_button2(); // 회원정보수정 확인취소버튼
void ok_button3(); // (관리자)회원등록
//나중에 관리자에서 회원가입할 때도 따로 만들어야 할 듯..?

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


void food_menu1(); // 음식메뉴(카테고리)
void food_menu2(); // 음식메뉴(음식이름)
void food_menu3(); // 구매완료
void food_menu4(); // 수량 부족

void food_menu3_1(); // 장바구니 담김

void food_basket(); // 장바구니창
void food_no_basket(); // 장바구니에 음식이 없을 때
int k;



void quit_pc(); // 사용 종료
void quit_pc2(int number); // pc로 나가는 중...
void quit_pc3();
void quit_pc4();


void test();
//char save = 72;
  // 선택된 옵션을 저장할 변수, 기본값으로 1 설정


// 관리자 함수

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

int account_list_point(int x, int y, char button[][20], User account[]); // 계정 목록
int account_list_button();
int account_search_list_button();

void save_user_account(char* fileName);
int year[1000], month[1000], day[1000];



//회원 목록
void member_list(); // 회원목록
void search_member(); // 회원찾기
void serched_member_list();

int temp1; // 임시 연 매출현황 변수
int temp2; // 임시 월 매출현황 변수
int temp3; // 임시 일 매출현황 변수
void sales_status1(); // 매출현황
void sales_status2_1(); // 연도선택(연 매출현황)
void sales_status2_2(); // 연도선택(월 매출현황)
void sales_status2_3(); // 연도선택(일 매출현황)
void sales_status3_1(); // 월 선택(월 매출현황)
void sales_status3_2(); // 월 선택(일 매출현황)
void sales_status4_1(); // 일 선택(일 매출현황)





void sales_status5(); // 매출현황 보여주기(연도)
void sales_status6(); // 매출현황 보여주기(월)
void sales_status7(); // 매출현황 보여주기(일)

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


int order_page; // 주문내역 다음페이지를 대비해서 PC번호와 페이지를 곱한 용도
int account_page; // 회원관리 다음페이지를 대비해서 PC번호와 페이지를 곱한 용도
int cases; // 검색안했으면 0, 했는 상태이면 1로 넘어가게 함

void modify_num_File1(char* fileName, char* search_id, User user[]);
void member_modification2();
void show_modify2();

char date[20]; // 매출현황에 쓰일 날짜
Order sales_order;
int PCsum;
int FDsum;
int flag;
Order queue_order;