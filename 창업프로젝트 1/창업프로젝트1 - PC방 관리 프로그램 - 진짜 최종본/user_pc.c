#include <stdio.h>
#include <conio.h>
#include <windows.h>
#include<string.h>
#include<stdlib.h>
#include<time.h>
#include"header.h"
#include <time.h>
#include "Queue.h"

static int pp = 0;

int basket_count = 0;

char what[4] = "0"; // 무엇을 주문했는지, PC방비를 주문했는지, 음식을 주문했는지를 알기위한 것, PC, FD로 구성
char temp[4] = "0";
int p = 0;
char pay[2][20] = { "현금", "카드" };

int num_id = 1;

int k = 0; // 음식 배열 알 수 있게 반복분 변수 쓰는 용도
int num[5] = { 0 }; // 음식 개당 몇 개 시켰는지 알 수 있는 배열
// 이건 그대로 두자
char cat_food_menu1[7][20] = { "밥  류", "라면류", "과자류", "음료류", "기  타", "취소", "장바구니" };

void modify_num_File1(char* fileName, char* search_id, User user[])
{

	FILE* file = NULL;
	file = fopen(fileName, "r");
	FILE* tempFile = fopen("temp.txt", "w");

	if (file == NULL || tempFile == NULL) {
		printf("File opening failed.\n");
		exit(EXIT_FAILURE);
	}
	User te_user;
	char line[256];
	//while (fgets(line, sizeof(line), file)) 
	while (fscanf(file, "%s %s %s %s %s %d %d", te_user.id, te_user.pw, te_user.name, te_user.gender, te_user.birth, &te_user.second, &te_user.money) == 7)
	{
		// 검색 문자열이 라인에 포함되어 있는지 확인
		if (strcmp(te_user.id, search_id) == 0) {
			// 해당 아이디를 찾아서 라인을 건너뛰고 삭제하는 대신 아래와 같이 수정
			fprintf(tempFile, "%s %s %s %s %s %d %d\n", user[save_num].id, user[save_num].pw, user[save_num].name, user[save_num].gender, user[save_num].birth, user[save_num].second, user[save_num].money);
		}
		else {
			fprintf(tempFile, "%s %s %s %s %s %d %d\n", te_user.id, te_user.pw, te_user.name, te_user.gender, te_user.birth, te_user.second, te_user.money);
		}
	}

	fclose(tempFile);

	fclose(file);
	file = fopen(fileName, "w");
	tempFile = fopen("temp.txt", "r");

	while (fgets(line, sizeof(line), tempFile))
	{
		// 검색 문자열이 라인에 포함되어 있는지 확인
		fprintf(file, "%s", line);
	}

	fclose(tempFile);
	fclose(file);
}

void modify_num_File(char* fileName, char* search_id) 
{

	FILE* file = NULL;
	file = fopen(fileName, "r");
	FILE* tempFile = fopen("temp.txt", "w");

	if (file == NULL || tempFile == NULL) {
		printf("File opening failed.\n");
		exit(EXIT_FAILURE);
	}
	User te_user;
	char line[256];
	//while (fgets(line, sizeof(line), file))
	while(fscanf(file, "%s %s %s %s %s %d %d", te_user.id, te_user.pw, te_user.name, te_user.gender, te_user.birth, &te_user.second, &te_user.money) == 7)
	{
		// 검색 문자열이 라인에 포함되어 있는지 확인
		//if (strstr(line, search_id) != NULL) 
		if(strcmp(te_user.id, search_id) == 0)
		{
			// 해당 아이디를 찾아서 라인을 건너뛰고 삭제하는 대신 아래와 같이 수정
			fprintf(tempFile, "%s %s %s %s %s %d %d\n", in_user.id, in_user.pw, in_user.name, in_user.gender, in_user.birth, in_user.second, in_user.money);
		}
		else {
			fprintf(tempFile, "%s %s %s %s %s %d %d\n", te_user.id, te_user.pw, te_user.name, te_user.gender, te_user.birth, te_user.second, te_user.money);
		}
	}

	fclose(tempFile);

	fclose(file);

	file = fopen(fileName, "w");
	tempFile = fopen("temp.txt", "r");

	while (fgets(line, sizeof(line), tempFile)) 
	{
		// 검색 문자열이 라인에 포함되어 있는지 확인
		fprintf(file, "%s", line);
	}

	fclose(tempFile);
	fclose(file);
}

change_sec change_clock(int second) // 시간 초 변환
{
	change_sec clock;
	clock.hour = second / 3600; // 남은 시간(시간)
	clock.minute = (second % 3600) / 60;
	clock.second = (second % 3600) % 60;

	return clock;
}

void findLinesWithLanguage(const char* file_path, const char* target_language) // 시간 혹은 음식 메뉴를 끌고오는 방식
{
	int i = 0;
	FILE* file = fopen(file_path, "r");

	if (file == NULL) 
	{
		printf("파일 열기 실패!!: %s\n", file_path);
		return;
	}
	while (!feof(file))
	{

		fscanf(file, "%s %s %s %d", in_menu[i].Menu_id, in_menu[i].sort,
			in_menu[i].Name, &in_menu[i].price);// 메뉴아이디, 종류 이름 가격을 끌고옴

		// 대소문자 구분 없이 비교
		if (strstr(in_menu[i].Menu_id, target_language) != NULL) // 해당 문자를 찾았다면?
		{
			// 그대로 놔두기
		}
		else
		{
			strcpy(in_menu[i].Menu_id, "0");
			strcpy(in_menu[i].Name, "0");
			strcpy(in_menu[i].sort, "0");
			in_menu[i].price = 0;
			i--; // 차례대로 값을 받기 위해서
		}

		
		i++;// 배열에 담기 위해서 1씩 더해줌
		

	}
	fclose(file);
}

void change_second() // 종료할 시점에 시간을 저장하기 위해 구조체에 저장
{
	in_user.second = (test_clock.hour * 3600) + (test_clock.minute * 60) + test_clock.second;
}

void make_order_id(const char* file_path) // 주문내역을 만들어주는 함수(시간메뉴한정(아닐수도 있음 해봐야 알 것 같음)/메뉴아이디포함)
{
	
	int O_id = 0;
	FILE* file = fopen(file_path, "r");

	if (file == NULL)
	{
		printf("파일 열기 실패!!: %s\n", file_path);
		return;
	}

	fseek(file, 0, SEEK_END); // 파일 끝으로 이동
	if (ftell(file) != 0) // 파일에 뭐라도 있으면
	{
		fseek(file, 0, SEEK_SET);
		while (!feof(file))		// 파일에서 읽어온 내용을 사용하여 작업 수행
		// 젤 마지막의 값을 구조체로 불러옴
		{

			fscanf(file, "%s %s %d %s %d %s %d", before_order.Order_id, before_order.id, &before_order.total, before_order.pay, &before_order.pc_num, temp, &before_order.state);
			
			//printf("%s %s %d %s\n", before_order.Order_id, before_order.id, before_order.total, before_order.pay);
			//getch();
		}
		// 첫 번째 호출: '_'을 기준으로 문자열을 토큰으로 분리
		char* token = strtok(before_order.Order_id, "_");
		if (token != NULL)
		{
			strcpy(before_dateString, token);
			//printf("1번 토큰분리한 결과 : %s\n", before_dateString);
			//_getch();
			// 두 번째 토큰 가져오기 (첫 번째 토큰은 무시)
			token = strtok(NULL, "_");
		}
		


		// 두 번째 토큰이 존재하는 경우에만 처리

		if (token != NULL) {
			// 정수로 변환하여 출력
			O_id = atoi(token);
			//printf("2번 토큰분리한 결과 : %d", O_id);
			//_getch();

		}
		else {

		}

	}
	fclose(file);

	

	FILE* file1 = fopen(file_path, "a");

	if (file1 == NULL)
	{
		printf("파일 열기 실패!!: %s\n", file_path);
		return;
	}
	//_getch();
	char str_id[100];
	char under_bar[2] = "_";

	time_t timer;
	struct tm* t;
	timer = time(NULL); // 1970년 1월 1일 0시 0분 0초부터 시작하여 현재까지의 초
	t = localtime(&timer); // 포맷팅을 위해 구조체에 넣기	

	strftime(dateString, 9, "%Y%m%d", t); // 현재 날짜를 읽어옴

	if (strcmp(before_dateString, dateString) == 0) // 최근에 조회한 날짜랑, 지금 조회한 날짜가 같으면
	{
		num_id = O_id + 1; // 그 전 주문한거에 +1해서 넣어둠
		itoa(num_id, str_id, 10); // 날짜를 원하는 형식의 문자열로 변환
		strcpy(order.Order_id, dateString);
		strcat(order.Order_id, under_bar);
		strcat(order.Order_id, str_id); // 주문아이디 그대로 만들고 num_id도 1 증가한 값을 넣으면 된다.

		strcpy(order.id, in_user.id); // 해당 사용자 아이디
		order.total = all_price; // 장바구니 총 가격
		strcpy(order.pay, pay[p]); // 결제방식
	}

	else // 서로 날짜가 다르다면
	{
		num_id = 1; // 다시 아이디를 초기화
		itoa(num_id, str_id, 10);
		// 날짜를 원하는 형식의 문자열로 변환

		strcpy(order.Order_id, dateString);
		strcat(order.Order_id, under_bar);
		strcat(order.Order_id, str_id); // 주문아이디 그대로 만들고 num_id도 1 증가한 값을 넣으면 된다.

		strcpy(order.id, in_user.id); // 해당 사용자 아이디
		order.total = all_price; // 장바구니 총 가격
		strcpy(order.pay, pay[p]); // 결제방식
	}

	//printf("%s %s %d %s\n", order.Order_id, order.id, order.total, order.pay);
	//_getch();
	order.state = 0;
	fprintf(file1, "%s %s %d %s %d %s %d\n", order.Order_id, order.id, order.total, order.pay, number, what, order.state); // PC방 번호까지
	pp++; // 다른주문 확인
	num_id++;
	fclose(file1);


	// 큐 부분
	init(&Q);

	FILE* file2 = fopen(file_path, "r");

	if (file2 == NULL)
	{
		printf("파일 열기 실패!!: %s\n", file_path);
		return;
	}
	
	while (fscanf(file2, "%s %s %d %s %d %s %d", order.Order_id, order.id, &order.total, order.pay, &number, what, &order.state) == 7)
	{
		if (strcmp(what, "FD") == 0 && order.state == 0)
		{
			enqueue(&Q, order.Order_id); // 큐 부분에 주문 아이디를 넣어줌
		}
	}
	
	fclose(file2);

}

void make_order_datail(const char* file_path) // 주문내역 안에 주문상세내역을 파일에 입력할 수 있게 만듬
{
	pp = 0;

	int kk = 0;
	
	// 여기서 주문상세내역을 미리 작업해놓아야될 것 같다..
	for (int i = 0; i < 5; i++)
	{
		strcpy(order_detail[i].Order_id, order.Order_id); // 주문아이디를 상세내역에 받음
	}
	
	for (int j = 0; j < 5; j++)
	{
		for (int i = 0; i < 50; i++)
		{
			if (strcmp(in_menu[i].Name, basket_Menu[j].name_food) == 0)
			{
				strcpy(order_detail[kk].Menu_id, in_menu[i].Menu_id); // 메뉴아이디를 받음
				order_detail[kk].count = basket_Menu[j].num_food; // 해당 음식 갯수를 받음 
				kk++;
			}
		}
		
	}
	

	FILE* file = fopen(file_path, "a");

	if (file == NULL)
	{
		printf("파일 열기 실패!!: %s\n", file_path);
		return;
	}
	
	for (int i = 0; i < 5; i++)
	{
		if (order_detail[i].count == 0)
		{
			break;
		}

		fprintf(file, "%s %s %d\n", order_detail[i].Order_id, order_detail[i].Menu_id, order_detail[i].count);
	}
	fclose(file);
	// 확인용

	//for (int i = 0; i < 5; i++)
	//{
	//	goto_xy(0, 50 + i);
	//	printf("%s %s %d", order_detail[i].Order_id, order_detail[i].Menu_id, order_detail[i].count);
	//}
	
	// getch();
}

void user_main()
{
	int hour = 0, minute = 0, second = 0;
	system("cls");

	square(60, 35, 20, 1); // 큰 틀
	square(50, 15, 25, 3); // 작은 틀

	goto_xy(33, 7);
	printf("NO.%d", number); // pc 번호

	goto_xy(33, 9);
	printf("사용금액: %d 원", in_user.money); // 사용금액

	square_char(18, 2, 41, 21, "시간 추가"); // 시간추가 버튼

	square_char(18, 2, 41, 25, "먹거리 주문"); // 먹거리 주문 버튼

	square_char(18, 2, 41, 29, "사용 종료"); // 사용 종료 버튼


	test_clock = change_clock(in_user.second);

	if (in_user.second == 0) // 처음에 들어왔는데 시간이 없는경우에는 시간을 넣을 수 있게 1분정도의 시간을 준다.
	{
		test_clock.minute = 1;
		goto_xy(27, 34);
		printf("남은 시간이 없습니다. 1분안에 시간을 넣어주세요.");
	}

	while (1)
	{
		goto_xy(33, 11);
		printf("사용시간: %02d:%02d:%02d", hour, minute, second); // 사용시간

		goto_xy(33, 13);
		printf("남은시간: %02d:%02d:%02d", test_clock.hour, test_clock.minute, test_clock.second);
		goto_xy(0, 5);
		// 커서빼기 


		Sleep(1000);
		if (test_clock.second > 0 && test_clock.second < 60)
		{
			test_clock.second -= 1;
		}
		else if (test_clock.second == 0)
		{
			if (test_clock.minute == 0)
			{
				if (test_clock.hour == 0)
				{
					// 시간 다됬다고 말씀드린 뒤
					in_user.money = 0; // 해당 넣은 금액도 초기화
					change_second(); // 해당 시,분,초를 다시 초로 변환하여 in_user.second에 기입
					modify_num_File("login.txt", in_user.id);
					quit_pc2(number);
					//로그인 화면
					mainlog();
				}
				test_clock.minute = 60;
				test_clock.hour -= 1;
			}
			test_clock.second = 59;
			test_clock.minute -= 1;
		}

		if (second >= 0 && second <= 58)
		{
			second += 1;
		}
		else if (second == 59)
		{
			second = 0;
			if (minute == 59)
			{
				minute = -1;
				hour += 1;
			}
			minute += 1;
		}
		if (kbhit())
		{
			user_button();
		}

	}
}

void add_time1() // 결제방식 (없앨려고 했는데 다시 살릴예정)
{
	int i = 0;;
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 2, 25, 3); // 결제방식 화면 박스
	goto_xy(34, 4);
	printf("원하시는 결제방식을 선택해주세요.");

	square_char(22, 5, 39, 13, "현금 결제"); // 카드결제 버튼
	square_char(22, 5, 39, 20, "카드 결제"); // 현금결제 버튼

	p = pay_button()-1;
	strcpy(what, "PC"); // PC라고 기록
	make_order_id("Order.txt"); // 주문내역만들어주는 함수
	pp = 0; // 다시 재정렬

	//_getch();
	add_time3();
}

void add_time2() // 가격메뉴
{
	int i = 0;
	int j = 0;
	int pc_price;
	findLinesWithLanguage("Menu.txt", "PC");
	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square(50, 2, 25, 3); // 결제방식 화면 박스
	goto_xy(36, 4);
	printf("원하시는 시간을 선택해주세요.");

	for (j = 0; j < 8; j++)
	{
		if (j < 2)
		{
			square(17, 5, 31 + (19 * j), 7);
			goto_xy(37 + (19 * j), 9);
			// printf("%s", time_money[j]);
			printf("%d 원", in_menu[j].price);
			goto_xy(37 + (19 * j), 10);
			printf("(0%s:00)", in_menu[j].Name);
		}
		else if (j >= 2 && j < 4)
		{
			square(17, 5, 31 + (19 * (j - 2)), 7 + 6);
			goto_xy(37 + (19 * (j - 2)), 9 + 6);
			// printf("%s", time_money[j]);
			printf("%d 원", in_menu[j].price);
			goto_xy(37 + (19 * (j - 2)), 10 + 6);
			printf("(0%s:00)", in_menu[j].Name);
		}
		else if (j >= 4 && j < 6)
		{
			square(17, 5, 31 + (19 * (j - 4)), 7 + 12);
			goto_xy(37 + (19 * (j - 4)), 9 + 12);
			// printf("%s", time_money[j]);
			printf("%d 원", in_menu[j].price);
			goto_xy(37 + (19 * (j - 4)), 10 + 12);
			printf("(0%s:00)", in_menu[j].Name);
		}
		else if (j >= 6 && j < 8)
		{
			square(17, 5, 31 + (19 * (j - 6)), 7 + 18);
			goto_xy(37 + (19 * (j - 6)), 9 + 18);
			// printf("%s", time_money[j]);
			printf("%d 원", in_menu[j].price);
			goto_xy(37 + (19 * (j - 6)), 10 + 18);
			printf("(0%s:00)", in_menu[j].Name);
		}
	}
	pc_price = time_button();
	in_user.money += pc_price;  // 이건 파일 설계할 때 완전 바꿔야 할 것
	all_price = pc_price; // 주문내역이 PC방비 일 수도 있기 때문
	add_time1(); // 결제 방식으로 이동

}

void add_time3() // 거래완료
{
	system("cls");

	modify_num_File("login.txt", in_user.id); // 파일 최신화

	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 결제방식 화면 박스
	goto_xy(40, 18);
	printf("거래가 완료되었습니다.");
	Sleep(2000);
	for (int i = 0; i < 50; i++)
	{
		strcpy(in_menu[i].Menu_id, "0");
		strcpy(in_menu[i].Name, "0");
		strcpy(in_menu[i].sort, "0");
		in_menu[i].price = 0;
	}
	all_price = 0; // 장바구니할 때 유입될 수도 있음
	user_main();
}

void food_menu1() // 음식메뉴(카테고리)
{
	system("cls");
	int i = 0;
	int menu_num; // 메뉴종류 (-류)

	findLinesWithLanguage("Menu.txt", "FD");
	//getch();
	square(60, 35, 20, 1); // 큰 틀

	square(50, 2, 25, 3); // 음식카테고리 선택 화면
	goto_xy(36, 4);
	printf("원하시는 음식을 선택해주세요.");



	for (i = 0; i < 5; i++)
	{
		square_char(22, 4, 39, 7 + (5 * i), cat_food_menu1[i]); // 구조체 이용해서 카테고리 나열
	}

	square_char(15, 2, 62, 32, cat_food_menu1[5]);
	square_char(15, 2, 23, 32, cat_food_menu1[6]);

	menu_num = cat_menu_button(); 

	food_menu2(menu_num);
}

void food_menu2(int number) // 음식메뉴(음식이름)
{
	int i = 0;
	int j = 0;
	//k = 0;
	int name_menu_number;
	square(60, 35, 20, 1); // 큰 틀
	time_t timer;
	struct tm* t;
	timer = time(NULL); // 1970년 1월 1일 0시 0분 0초부터 시작하여 현재까지의 초
	t = localtime(&timer); // 포맷팅을 위해 구조체에 넣기

	
	for (i = 0; i < 8; i++)
	{
		square(33, 2, 33, 5 + (3 * i)); // 음식 메뉴 버튼
	}
	for (i = (number - 1) * 8; i < (8 * number); i++)
	{

		goto_xy(35, 6 + (3 * j));
		//printf("%d", i);  확인용
		printf("%s", in_menu[i].Name); // 음식이름 구조체 넣을 예정
		goto_xy(58, 6 + (3 * j));
		printf("%d원", in_menu[i].price); // 음식가격 구조체 넣을 예정
		j++;
	}
	j = 0;

	name_menu_number = name_menu_button(number) - 1; // 음식 선택 한 번호(1~8)-1 = (0~7)

	//확인용
	//goto_xy(0, 30);
	//printf("%d", name_menu_number);

	goto_xy(38, 30);
	printf("몇 개를 주문하시겠습니까?");
	goto_xy(46, 32);
	printf("→ ");
	scanf("%d", &num[k]);

	strcpy(basket_Menu[k].Cat_food, in_menu[name_menu_number + ((number - 1) * 8)].sort); // 음식 종류 복사(장바구니)
	strcpy(basket_Menu[k].name_food, in_menu[name_menu_number + ((number - 1) * 8)].Name); // 음식이름 복사(장바구니)
	basket_Menu[k].price = in_menu[name_menu_number + ((number - 1) * 8)].price; // 음식 가격 복사(장바구니)

	basket_Menu[k].num_food = num[k]; // 음식 시킨 갯수도 저장!! (장바구니)

	// 장바구니 변수 잘 담겼는지 확인용 코드
	//goto_xy(50, 45);
	//printf("%d", number);
	//goto_xy(50, 46);
	//printf("%d", name_menu_number);
	//goto_xy(50, 47);
	//printf("%d. %s %s %d %d", k, basket_Menu[k].Cat_food, basket_Menu[k].name_food, basket_Menu[k].price, basket_Menu[k].num_food);
	k++;
	if (k > 5)
	{
		system("cls");

		square(60, 35, 20, 1); // 큰 틀

		square(50, 12, 25, 12); // 구매완료 화면 박스
		goto_xy(39, 17);
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 12);
		printf("장바구니가 꽉 찼습니다.");
		goto_xy(36, 18);
		printf("주문완료 후 다시 선택해주세요.");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
		goto_xy(0, 0); // 커서빼기
		Sleep(1500);
		food_menu1();
		k = 5;
	}
	//_getch();
	food_menu3_1();

}

void food_basket()
{

	basket_count = 0;
	
	system("cls");
	int i = 0;
	int menu_num;

	all_price = 0; // 총 가격

	square(60, 35, 20, 1); // 큰 틀

	square(50, 2, 25, 6); // 음식카테고리 선택 화면
	goto_xy(28, 7);
	printf("해당하는 음식이 맞다면 주문하기를 눌러주세요.");

	square(50, 12, 25, 12); // 주문내역 화면 박스

	goto_xy(25, 14);
	printf("┠");
	for (int j = 0; j < 48; j++)
	{
		printf("━");
	}
	printf("┫");

	goto_xy(29, 13);
	printf("음식");

	goto_xy(50, 13);
	printf("갯수");

	goto_xy(65, 13);
	printf("금액");

	for (int j = 0; j < 5; j++) // 주문 종류를 5개로 한정시키는게 맞을듯
	{


		//char compare[10] = "0";
		if (basket_Menu[j].num_food == 0) // 5개 차례대로 가다가 수량이 0개인거 발견하면 보여주지마라
		{
			break;
		}

		goto_xy(28, 15 + (2 * j));
		printf("%s", basket_Menu[j].name_food);
		goto_xy(51, 15 + (2 * j));
		printf("%d", basket_Menu[j].num_food);
		goto_xy(64, 15 + (2 * j));
		printf("%d원", (basket_Menu[j].price) * basket_Menu[j].num_food);

		all_price += (basket_Menu[j].price * basket_Menu[j].num_food);
		basket_count++; // 이걸 장바구니에 담긴 종류 갯수로 생각하자 이게 맞다
	}
	goto_xy(60, 27);
	printf("총 금액 : %d원", all_price);

	square_char(15, 2, 32, 32, "주문");

	square_char(15, 2, 53, 32, "취소");

	char key = getch();

	if (key == 75 || key == 77 || key == -32)
	{
		quit_button4();
	}

	goto_xy(0, 10); //커서빼기

	_getch();
}

void food_no_basket() // 장바구니에 음식이 없을 때
{
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 구매완료 화면 박스
	goto_xy(35, 17);
	printf("장바구니에 주문내역이 없습니다.");
	goto_xy(38, 19);
	printf("다시 메뉴를 선택해주세요.");
	goto_xy(0, 0);
	Sleep(2000);

	food_basket();
}

void food_menu3_1() // 장바구니 담김
{
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 구매완료 화면 박스
	goto_xy(39, 18);
	printf("장바구니에 저장했습니다.");
	goto_xy(0, 0); // 커서빼기
	Sleep(1000);
	food_menu1();

}

void food_menu3() // 구매완료
{
	// Order_id, id, total(최종금액), pay(결제방식)
	
	make_order_datail("Order_detail.txt");
	// 화면 초기화
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 구매완료 화면 박스
	goto_xy(40, 18);
	printf("구매가 완료되었습니다.");
	goto_xy(0, 0);
	Sleep(2000);

	for (int i = 0; i < 50; i++)
	{
		strcpy(in_menu[i].Menu_id, "0");
		strcpy(in_menu[i].Name, "0");
		strcpy(in_menu[i].sort, "0");
		in_menu[i].price = 0;
	}
	all_price = 0; // 장바구니할 때 유입될 수도 있음

	for (int i = 0; i < 5; i++)
	{
		basket_Menu[i].num_food = 0;// 장바구니 비워주기
	}
	k = 0; // k번째 배열로 돌아감 (처음으로 돌아감, 아예 안산걸로 다시)

	user_main();
}

void food_menu4() // 결제방식 (없앨려고 했는데 다시 살릴예정) - 여기에 있는 재고함수는 이제 안쓰기 때문에 이걸로 덮어쓰기
{
	int i = 0;
	//int p = 0;
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 2, 25, 3); // 결제방식 화면 박스
	goto_xy(34, 4);
	printf("원하시는 결제방식을 선택해주세요.");

	square_char(22, 5, 39, 13, "현금 결제"); // 카드결제 버튼
	square_char(22, 5, 39, 20, "카드 결제"); // 현금결제 버튼

	p = pay_button() - 1;
	strcpy(what, "FD");
	make_order_id("Order.txt"); // 주문내역만들어주는 함수
	pp = 0; // 다시 재정렬

	//_getch();
	food_menu3();
}

void quit_pc() // 사용 종료(사용자)
{
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 종료 화면 박스
	goto_xy(40, 18);
	printf("PC를 종료하시겠습니까?");

	square(15, 2, 32, 27); // 종료 화면박스
	goto_xy(38, 28);
	printf("종료");

	square(15, 2, 53, 27); // 취소 화면박스
	goto_xy(59, 28);
	printf("취소");

	quit_button1();
}

void quit_pc2(int number) // pc로 나가는 중...사용자버전
{
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 회원가입 완료 화면 박스


	for (int i = 0; i < 3; i++) // 로딩화면
	{
		goto_xy(40, 18);
		printf("%d번 pc 전원 끄는 중.", number);
		goto_xy(0, 10); // 커서 빼기
		Sleep(500); // 0.5초 보여줌
		goto_xy(40, 18);
		printf("%d번 pc 전원 끄는 중..", number);
		goto_xy(0, 10); // 커서 빼기
		Sleep(500); // 0.5초 보여줌
		goto_xy(40, 18);
		printf("%d번 pc 전원 끄는 중...", number);
		goto_xy(0, 10); // 커서 빼기
		Sleep(500); // 0.5초 보여줌
		goto_xy(40, 18);
		printf("                           ");
	}

	mainlog();

}

