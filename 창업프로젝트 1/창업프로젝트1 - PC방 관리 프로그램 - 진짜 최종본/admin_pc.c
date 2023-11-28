#include <stdio.h>
#include <conio.h>
#include <windows.h>
#include<string.h>
#include<stdlib.h>
#include<time.h>
#include"header.h"
#include"Queue.h"

User user_[5] = { {"ptcart4033", "rlaqjawls4033","김범진", "20010529", "남자", 10 ,1000} };

User exist_user = { 0 };
char ex_imp[5][20] = { 0 };

char sales[3][20] = { "연 매출현황", "월 매출현황", "일 매출현황" };

int syear;
int smonth;
int sday;

int tep_pcnum;
account_page = 0;
order_page = 0; // 주문내역 페이지 수
void admin_main1() // 관리자 메인1 (회원을 안골랐을 경우 - 안내사항)
{
	system("cls");

	square(60, 35, 20, 1); // 큰 틀
	square(50, 18, 25, 3); // 작은 틀

	goto_xy(29, 6);
	printf("※ PC 관리자 유의사항");

	goto_xy(29, 9);
	printf("▶ 오후 10시 이후엔 반드시 신분증 검사 실시");

	goto_xy(29, 12);
	printf("▶ 근무 교대할 때 분리수거 꼼꼼히 실시");

	goto_xy(29, 15);
	printf("▶ PC 작동 안될 시, 즉시 매니저한테 연락하기");

	goto_xy(29, 18);
	printf("▶ 좌석 번호 전부 외워두기");

	square_char(18, 2, 41, 23, "회원 등록"); // 먹거리 주문 버튼

	square_char(18, 2, 41, 26, "주문 관리"); // 먹거리 주문 버튼

	square_char(18, 2, 41, 29, "회원 목록(찾기)"); // 먹거리 주문 버튼

	square_char(18, 2, 41, 32, "매출 현황"); // 먹거리 주문 버튼


	char key = getch();
	goto_xy(0, 3);
	printf("%d", key);

	if (key == 75 || key == 77 || key == -32)
	{
		admin_button1();
	}
}

void admin_main2() // 관리자 메인2 (회원을 골랐을 경우 - 회원정보)
{
	system("cls");

	square(60, 35, 20, 1); // 큰 틀
	square(50, 16, 25, 3); // 작은 틀

	goto_xy(33, 7);
	printf("아이디 : %s", admin_account[save_num].id);

	goto_xy(33, 9);
	printf("성별 : %s", admin_account[save_num].gender);

	goto_xy(33, 11);
	printf("이름 : %s", admin_account[save_num].name);

	goto_xy(33, 13);
	printf("생년월일 : %04d-%02d-%02d", year[save_num], month[save_num], day[save_num]);

	square_char(18, 2, 41, 23, "시간 추가"); // 시간추가 버튼

	square_char(18, 2, 41, 26, "회원 수정"); // 회원수정 버튼

	square_char(18, 2, 41, 29, "회원 삭제"); // 회원삭제 버튼

	square_char(18, 2, 41, 32, "처음 으로"); // 처음으로 버튼

	char key = getch();
	goto_xy(0, 3);
	printf("%d", key);

	if (key == 75 || key == 77 || key == -32)
	{
		admin_button2();
	}
}

void admin_main3() // 회원을 검색 후 조회
{
	system("cls");

	square(60, 35, 20, 1); // 큰 틀
	square(50, 16, 25, 3); // 작은 틀

	goto_xy(33, 7);
	printf("아이디 : %s", admin_search_account[save_num].id);

	goto_xy(33, 9);
	printf("성별 : %s", admin_search_account[save_num].gender);

	goto_xy(33, 11);
	printf("이름 : %s", admin_search_account[save_num].name);

	goto_xy(33, 13);
	printf("생년월일 : %04d-%02d-%02d", year[save_num], month[save_num], day[save_num]);;

	square_char(18, 2, 41, 23, "시간 추가"); // 시간추가 버튼
	
	square_char(18, 2, 41, 26, "회원 수정"); // 시간추가 버튼

	square_char(18, 2, 41, 29, "회원 삭제"); // 시간추가 버튼

	square_char(18, 2, 41, 32, "처음 으로"); // 사용 종료 버튼

	char key = getch();
	goto_xy(0, 3);
	printf("%d", key);

	if (key == 75 || key == 77 || key == -32)
	{
		admin_button3();
	}
}

void member_modification1() // 회원 수정
{

	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square(30, 4, 35, 4); // 회원등록 화면 박스
	goto_xy(37, 6);
	printf("수정할 정보를 선택해주세요.");

	square(30, 2, 38, 11); // 아이디 입력박스
	goto_xy(30, 12);
	printf("아이디: ");
	goto_xy(40, 12);
	printf("%s", admin_account[save_num].id);

	square(30, 2, 38, 14); // 비번 입력박스
	goto_xy(28, 15);
	printf("비밀번호: ");
	goto_xy(40, 15);
	printf("%s", admin_account[save_num].pw);

	square(30, 2, 38, 17); // 이름 입력박스
	goto_xy(31, 18);
	printf("이름: ");
	goto_xy(40, 18);
	printf("%s", admin_account[save_num].name);

	square(30, 2, 38, 20); // 성별 입력박스
	goto_xy(31, 21);
	printf("성별: ");
	goto_xy(40, 21);
	printf("%s", admin_account[save_num].gender);

	square(30, 2, 38, 23); // 생년월일 입력박스
	goto_xy(28, 24);
	printf("생년월일: ");
	goto_xy(40, 24);
	printf("%s", admin_account[save_num].birth);

	square(14, 2, 60, 28); // 확인 화면박스
	goto_xy(63, 29);
	printf("돌아 가기");

	char key = getch();
	goto_xy(0, 3);
	printf("%d", key);

	if (key == 75 || key == 77 || key == -32)
	{
		modify_button1(); // 버튼 집입
	}

}

void member_modification2() // 회원 수정 - 회원검색 후
{

	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square(30, 4, 35, 4); // 회원등록 화면 박스
	goto_xy(37, 6);
	printf("수정할 정보를 선택해주세요.");

	square(30, 2, 38, 11); // 아이디 입력박스
	goto_xy(30, 12);
	printf("아이디: ");
	goto_xy(40, 12);
	printf("%s", admin_search_account[save_num].id);

	square(30, 2, 38, 14); // 비번 입력박스
	goto_xy(28, 15);
	printf("비밀번호: ");
	goto_xy(40, 15);
	printf("%s", admin_search_account[save_num].pw);

	square(30, 2, 38, 17); // 이름 입력박스
	goto_xy(31, 18);
	printf("이름: ");
	goto_xy(40, 18);
	printf("%s", admin_search_account[save_num].name);

	square(30, 2, 38, 20); // 성별 입력박스
	goto_xy(31, 21);
	printf("성별: ");
	goto_xy(40, 21);
	printf("%s", admin_search_account[save_num].gender);

	square(30, 2, 38, 23); // 생년월일 입력박스
	goto_xy(28, 24);
	printf("생년월일: ");
	goto_xy(40, 24);
	printf("%s", admin_search_account[save_num].birth);

	square(14, 2, 60, 28); // 확인 화면박스
	goto_xy(63, 29);
	printf("돌아 가기");

	char key = getch();
	goto_xy(0, 3);
	printf("%d", key);

	if (key == 75 || key == 77 || key == -32)
	{
		modify_button2(); // 버튼 진입
	}

}

void show_modify() // 회원수정 완료
{
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 회원가입 완료 화면 박스
	goto_xy(38, 18);
	printf("회원수정이 완료되었습니다.");
	Sleep(2000); // 2초 보여줌

	member_modification1();
}

void show_modify2() // 회원수정 완료 (회원검색 버전)
{
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 회원가입 완료 화면 박스
	goto_xy(38, 18);
	printf("회원수정이 완료되었습니다.");
	Sleep(2000); // 2초 보여줌

	member_modification2();
}

void delete_user_id(const char* fileName, char* search_id)
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
		if (strcmp(te_user.id, search_id) == 0)
		{ 
			// 해당 아이디 찾으면 쓰지 않기
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

void delete_user() // 회원삭제
{
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 삭제 화면 박스
	goto_xy(37, 18);
	printf("%s님을 삭제하시겠습니까?", admin_account[save_num].name);

	square(15, 2, 32, 27); // 삭제 화면박스
	goto_xy(38, 28);
	printf("삭제");

	square(15, 2, 53, 27); // 취소 화면박스
	goto_xy(59, 28);
	printf("취소");
	cases = 0;

	delete_button(); // 버튼 진입

}

void show_delete() // 회원삭제 완료
{
	system("cls");
	delete_user_id("login.txt", admin_account[save_num].id);
	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 회원가입 완료 화면 박스
	goto_xy(38, 18);
	printf("회원정보를 삭제하였습니다.");
	Sleep(2000); // 2초 보여줌

	admin_main1();
}

void delete_user_1() // 회원삭제(검색 했을 경우)
{
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 삭제 화면 박스
	goto_xy(37, 18);
	printf("%s님을 삭제하시겠습니까?", admin_search_account[save_num].name);

	square(15, 2, 32, 27); // 삭제 화면박스
	goto_xy(38, 28);
	printf("삭제");

	square(15, 2, 53, 27); // 취소 화면박스
	goto_xy(59, 28);
	printf("취소");
	cases = 1;

	delete_button(); // 버튼 진입
}

void show_delete_1() // 회원삭제 완료(검색했을 경우)
{
	system("cls");
	delete_user_id("login.txt", admin_search_account[save_num].id);
	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 회원가입 완료 화면 박스
	goto_xy(38, 18);
	printf("회원정보를 삭제하였습니다.");
	Sleep(2000); // 2초 보여줌

	admin_main1();
}

void register_membership() // 회원 등록
{
	FILE* login_1 = NULL;

	login_1 = fopen("login.txt", "a+");
	if (login_1 == NULL)
	{
		printf("파일 열기 실패!!!");
		exit(0);
	}

	FILE* login_2 = NULL;

	login_2 = fopen("admin.txt", "r");
	if (login_2 == NULL)
	{
		printf("파일 열기 실패!!!");
		exit(0);
	}
	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square(30, 4, 35, 4); // 회원등록 화면 박스
	goto_xy(46, 6);
	printf("회원 등록");

	square(30, 2, 38, 11); // 아이디 입력박스
	goto_xy(30, 12);
	printf("아이디: ");
	if (flag == 1)
	{
		goto_xy(40, 12);
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 11);
		printf("아이디가 중복입니다.");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
		flag = 0;
	}
	square(30, 2, 38, 14); // 비번 입력박스
	goto_xy(28, 15);
	printf("비밀번호: ");

	square(30, 2, 38, 17); // 이름 입력박스
	goto_xy(31, 18);
	printf("이름: ");

	square(30, 2, 38, 20); // 성별 입력박스
	goto_xy(31, 21);
	printf("성별: ");

	square(30, 2, 38, 23); // 생년월일 입력박스
	goto_xy(28, 24);
	printf("생년월일: ");

	square(9, 2, 54, 28); // 확인 화면박스
	goto_xy(57, 29);
	printf("확인");

	square(9, 2, 64, 28); // 취소 화면박스
	goto_xy(67, 29);
	printf("취소");

	User check_user;
	char key = getch();
	goto_xy(0, 3);
	printf("%d", key);

	if (key == 75 || key == 77 || key == -32)
	{
		ok_button3();
	}

	else
	{
		int bt = 0;
		int i = 0;
		char Bt = 0;

		//char id[20] = { 0 };
		// 아이디 입력 - 나중에 구조체로 바꿀예정
		while (1)
		{

			while (1)
			{
				Bt = getch();
				goto_xy(0, 3);
				printf("%d", Bt);
				goto_xy(0, 4);
				printf("%d", bt);

				if (Bt == 27 && bt <= 0)
				{
					bt = 0;
					goto_xy(40, 12);
				}

				else if (Bt == 27 && bt > 0)
				{
					bt -= 1;
					goto_xy(40, 12 + (bt * 3));
					printf("                   ");
					goto_xy(40, 12 + (bt * 3));
				}

				if (Bt != 27)
				{
					break;
				}
			}

			if (bt == 0 && Bt == 27)
			{
				goto_xy(40, 12 + (bt * 3));
				printf("                   ");
				goto_xy(40, 12 + (bt * 3));
				scanf("%s", ex_imp[bt]);
				bt += 1;


			}

			if (Bt == 27)
			{
				bt -= 1;
				goto_xy(40, 12 + (bt * 3));
				printf("                   ");
				goto_xy(40, 12 + (bt * 3));

				scanf("%s", ex_imp[bt]);
				bt += 1;

			}
			else
			{
				int i = 0;
				char c;
				if (bt == 5)
				{

					break;
				}


				if (bt == 1)
				{
					goto_xy(40, 12 + (bt * 3));
					printf("                   ");
					goto_xy(40, 12 + (bt * 3));
					while (1) {

						c = getch();
						if (c == 13) { // Enter키가 눌렸을 때
							break;
						}
						else if (c == 8 && i > 0) { // Backspace 키
							printf("\b \b");
							i--;
						}
						else if (i < 20 - 1) {
							ex_imp[bt][i] = c;
							printf("*");
							i++;
						}
					}

					ex_imp[bt][i] = '\0'; // 문자열 끝에 null 문자 추가
					bt += 1;
				}


				else if (bt == 0)
				{
					goto_xy(40, 12 + (bt * 3));
					printf("                   ");
					goto_xy(40, 12 + (bt * 3));
					scanf("%s", ex_imp[bt]);

					fscanf(login_2, "%s %s %s %s %s %d %d", check_user.id, check_user.pw, check_user.name, check_user.gender, check_user.birth, &check_user.second, &check_user.money);
					if (strcmp(check_user.id, ex_imp[bt]) == 0)
					{
						goto_xy(0, 0);
						flag = 1;
						join_membership();
					}

					while (fscanf(login_1, "%s %s %s %s %s %d %d", check_user.id, check_user.pw, check_user.name, check_user.gender, check_user.birth, &check_user.second, &check_user.money) == 7)
					{

						if (strcmp(check_user.id, ex_imp[bt]) == 0)
						{
							goto_xy(0, 0);
							flag = 1;
							join_membership();
						}
					}

					bt += 1;
				}

				else
				{
					goto_xy(40, 12 + (bt * 3));
					printf("                   ");
					goto_xy(40, 12 + (bt * 3));
					scanf("%s", ex_imp[bt]);
					bt += 1;
				}
			}

		}
		strcpy(exist_user.id, ex_imp[0]);
		strcpy(exist_user.pw, ex_imp[1]);
		strcpy(exist_user.name, ex_imp[2]);
		strcpy(exist_user.gender, ex_imp[3]);
		strcpy(exist_user.birth, ex_imp[4]);


		goto_xy(0, 20);
		



		//fprintf(login_1, "%s %s %s %s %s %d %d\n", exist_user.id, exist_user.pw, exist_user.name, exist_user.gender, exist_user.birth, exist_user.second, exist_user.money);

		fclose(login_1);
		fclose(login_2);
		join_check = 1;

		ok_button3(); // 버튼 진입

	}
}

void show_regist() // 회원수정 완료
{
	FILE* login_4 = NULL;

	login_4 = fopen("login.txt", "a+");
	if (login_4 == NULL)
	{
		printf("파일 열기 실패!!!");
		exit(0);
	}
	fprintf(login_4, "%s %s %s %s %s %d %d\n", exist_user.id, exist_user.pw, exist_user.name, exist_user.gender, exist_user.birth, exist_user.second, exist_user.money);
	fclose(login_4);
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 회원가입 완료 화면 박스
	goto_xy(38, 18);
	printf("회원등록이 완료되었습니다.");
	Sleep(2000); // 2초 보여줌

	admin_main1();
}

void quit_pc3() // 사용 종료(관리자)
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

	quit_button2(); // 버튼 진입
}

void quit_pc4(int number) // pc로 나가는 중...관리자버전
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

	admin_main1(); // 관리자 메인1 진입
}

void save_user_account(char* fileName)
{
	for (int i = 0; i < 1000; i++) 
		// 함수 불러올 때마다 모두 초기화 -> 어짜피 파일에서 불러오니깐 상관없음
		//(수정되면 최신화해주기 위함)
	{
		strcpy(admin_account[i].id, "");
		strcpy(admin_account[i].pw, "");
		strcpy(admin_account[i].name, "");
		strcpy(admin_account[i].gender, "");
		strcpy(admin_account[i].birth, "");
		admin_account[i].money = 0;
		admin_account[i].second = 0;
		year[i] = 0;
		month[i] = 0;
		day[i] = 0;
	}


	FILE* file = NULL;
	file = fopen(fileName, "r");
	//FILE* tempFile = fopen("temp.txt", "w"); // 임시파일
	
	if (file == NULL) {
		printf("File opening failed.\n");
		exit(EXIT_FAILURE);
	}
	int cc = 0;
	while (fscanf(file, "%s %s %s %s %s %d %d", admin_account[cc].id, admin_account[cc].pw, admin_account[cc].name, admin_account[cc].gender
	, admin_account[cc].birth, &admin_account[cc].second, &admin_account[cc].money) == 7)
	{	// admin_account 구조체 배열에 계정을 다 담음
		sscanf(admin_account[cc].birth, "%4d%2d%2d", &year[cc], &month[cc], &day[cc]);
		cc++;
	}

	fclose(file);
}

void member_list()
{
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	goto_xy(23, 3);
	printf("번호");

	goto_xy(32, 3);
	printf("이름");

	goto_xy(44, 3);
	printf("아이디");

	goto_xy(62, 3);
	printf("생년월일");


	for (int i = 0; i < 8; i++)
	{
		square(58, 2, 21, 4 + (3 * i));
		goto_xy(27, 4 + (3 * i));
		printf("┳");

		goto_xy(27, 5 + (3 * i));
		printf("┃");

		goto_xy(27, 6 + (3 * i));
		printf("┻");

		goto_xy(39, 4 + (3 * i));
		printf("┳");

		goto_xy(39, 5 + (3 * i));
		printf("┃");

		goto_xy(39, 6 + (3 * i));
		printf("┻");

		goto_xy(54, 4 + (3 * i));
		printf("┳");

		goto_xy(54, 5 + (3 * i));
		printf("┃");

		goto_xy(54, 6 + (3 * i));
		printf("┻");
	}

	int aa = 0;
	for (int j = (account_page * 8); j < (account_page * 8) + 8; j++)
	{
		goto_xy(24, 5 + (3 * aa));
		printf("%d", j + 1);
		aa++;
	}

	square_char(14, 2, 21, 28, "회원 찾기");

	square_char(9, 2, 58, 28, "이전");

	square_char(9, 2, 68, 28, "다음");

	square_char(15, 2, 62, 32, "돌아가기");

	save_user_account("login.txt"); // 사용자들 계정 받고

	aa = 0;
	for (int i = (account_page * 8); i < (account_page * 8) + 8; i++)
	{
		goto_xy(31, 5 + (3 * aa));
		printf("%s", admin_account[i].name);
		goto_xy(43, 5 + (3 * aa));
		printf("%s", admin_account[i].id);
		goto_xy(61, 5 + (3 * aa));
		if (year[i] == 0)
		{
			break;
		}
		printf("%04d-%02d-%02d", year[i], month[i], day[i]);
		aa++;
	}

	account_list_button();

	//search_member();
}

void serched_member_list() // 검색한 회원들의 리스트
{
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	goto_xy(23, 3);
	printf("번호");

	goto_xy(32, 3);
	printf("이름");

	goto_xy(44, 3);
	printf("아이디");

	goto_xy(62, 3);
	printf("생년월일");


	for (int i = 0; i < 8; i++)
	{
		square(58, 2, 21, 4 + (3 * i));
		goto_xy(27, 4 + (3 * i));
		printf("┳");

		goto_xy(27, 5 + (3 * i));
		printf("┃");

		goto_xy(27, 6 + (3 * i));
		printf("┻");

		goto_xy(39, 4 + (3 * i));
		printf("┳");

		goto_xy(39, 5 + (3 * i));
		printf("┃");

		goto_xy(39, 6 + (3 * i));
		printf("┻");

		goto_xy(54, 4 + (3 * i));
		printf("┳");

		goto_xy(54, 5 + (3 * i));
		printf("┃");

		goto_xy(54, 6 + (3 * i));
		printf("┻");
	}
	int aa = 0;
	for (int j = (account_page * 8); j < (account_page * 8) + 8; j++)
	{
		goto_xy(24, 5 + (3 * aa));
		printf("%d", j + 1);
		aa++;
	}

	square_char(14, 2, 21, 28, "회원 찾기");

	square_char(9, 2, 58, 28, "이전");

	square_char(9, 2, 68, 28, "다음");

	square_char(15, 2, 62, 32, "돌아가기");

	aa = 0;
	for (int i = (account_page * 8); i < (account_page * 8) + 8; i++)
	{
		goto_xy(31, 5 + (3 * aa));
		printf("%s", admin_search_account[i].name);
		goto_xy(43, 5 + (3 * aa));
		printf("%s", admin_search_account[i].id);
		goto_xy(61, 5 + (3 * aa));
		if (year[i] == 0)
		{
			break;
		}
		printf("%04d-%02d-%02d", year[i], month[i], day[i]);
		//printf("%s", admin_account[i].birth);
		aa++;
	}

	account_search_list_button();

	//search_member();
}

void search_member()
{
	char name[20];
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 종료 화면 박스
	goto_xy(32, 15);
	printf("찾고 싶은 회원의 이름을 입력해주세요");

	goto_xy(42, 20);
	printf("━━━━━━━━━━━━━━━━━");

	goto_xy(39, 19);
	printf("→ ");
	goto_xy(47, 19);
	scanf("%s", name);


	for (int i = 0; i < 1000; i++)
		// 함수 불러올 때마다 모두 초기화 -> 어짜피 파일에서 불러오니깐 상관없음
		//(수정되면 최신화해주기 위함)
	{
		strcpy(admin_search_account[i].id, "");
		strcpy(admin_search_account[i].pw, "");
		strcpy(admin_search_account[i].name, "");
		strcpy(admin_search_account[i].gender, "");
		strcpy(admin_search_account[i].birth, "");
		strcpy(admin_account[i].id, "");
		strcpy(admin_account[i].pw, "");
		strcpy(admin_account[i].name, "");
		strcpy(admin_account[i].gender, "");
		strcpy(admin_account[i].birth, "");
		admin_account[i].money = 0;
		admin_account[i].second = 0;
		admin_search_account[i].money = 0;
		admin_search_account[i].second = 0;
		year[i] = 0;
		month[i] = 0;
		day[i] = 0;
	}


	FILE* file = fopen("login.txt", "r");
	if (file == NULL) {
		printf("File opening failed.\n");
		exit(EXIT_FAILURE);
	}
	int ss = 0;
	int pp = 0;
	while (fscanf(file, "%s %s %s %s %s %d %d\n", admin_account[ss].id, admin_account[ss].pw, admin_account[ss].name, admin_account[ss].gender, admin_account[ss].birth, &admin_account[ss].second, &admin_account[ss].money)==7)
	{
		// 검색 문자열이 라인에 포함되어 있는지 확인
		if (strstr(admin_account[ss].name, name) != NULL) {
			// 해당 아이디를 찾아서 라인을 건너뛰고 삭제하는 대신 아래와 같이 수정
			strcpy(admin_search_account[pp].id, admin_account[ss].id);
			strcpy(admin_search_account[pp].pw, admin_account[ss].pw);
			strcpy(admin_search_account[pp].name, admin_account[ss].name);
			strcpy(admin_search_account[pp].gender, admin_account[ss].gender);
			strcpy(admin_search_account[pp].birth, admin_account[ss].birth);
			admin_search_account[pp].second = admin_account[ss].second;
			admin_search_account[pp].money = admin_account[ss].money;

			sscanf(admin_search_account[pp].birth, "%4d%2d%2d", &year[pp], &month[pp], &day[pp]);
			ss++;
			pp++;

			goto_xy(0, 40);
		}
		else {
			ss++;
		}
	}


	
	fclose(file);
	/*
	for (int i = 0; i < pp; i++)
	{
		goto_xy(0, 40);
		printf("%s %s %s %s %s %d %d\n", admin_search_account[i].id, admin_search_account[i].pw, admin_search_account[i].name, admin_search_account[i].gender, admin_search_account[i].birth, admin_search_account[i].second, admin_search_account[i].money);

		_getch();
	}
	*/
	
	serched_member_list();
}

void sales_status1() // 매출현황
{
	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	for (int i = 0; i < 3; i++)
	{
		square_char(22, 4, 39, 6 + (10 * i), sales[i]); // 구조체 이용해서 카테고리 나열
	}
	square_char(13, 2, 65, 33, "이전으로");
	sales_button1();
}

void sales_status2_1() // 연도 선택(연 매출현황)
{
	syear = 2014;
	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square_char(50, 2, 25, 5, "원하시는 연도를 선택해주세요."); // 연도 선택

	square(50, 12, 25, 12); // 연도 선택 박스


	for (int i = 0; i < 2; i++)
	{
		for (int j = 0; j < 5; j++)
		{
			square_int(9, 2, 28 + (j * 9), 15 + (i * 4), syear++);
		}
	}


	square_char(11, 2, 64, 27, "취소"); // 취소 버튼
	sales_button2_1();
}

void sales_status2_2() // 연도 선택(월 선택 시)
{

	syear = 2014;

	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square_char(50, 2, 25, 5, "원하시는 연도를 선택해주세요."); // 연도 선택 버튼

	square(50, 12, 25, 12); // 연도 선택 박스


	for (int i = 0; i < 2; i++)
	{
		for (int j = 0; j < 5; j++)
		{
			square_int(9, 2, 28 + (j * 9), 15 + (i * 4), syear++);
		}
	}
	//goto_xy(32, 15);
	//printf("연도를 입력해주세요");

	square_char(11, 2, 64, 27, "취소"); // 취소 버튼

	sales_button2_2();
}

void sales_status2_3() // 연도 선택(일 선택 시)
{

	syear = 2014;

	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square_char(50, 2, 25, 5, "원하시는 연도를 선택해주세요."); // 연도 선택

	square(50, 12, 25, 12); // 연도 선택 박스


	for (int i = 0; i < 2; i++)
	{
		for (int j = 0; j < 5; j++)
		{
			square_int(9, 2, 28 + (j * 9), 15 + (i * 4), syear++);
		}
	}

	square_char(11, 2, 64, 27, "취소"); // 취소 버튼

	sales_button2_3();
}

void sales_status3_1() // 월 선택(월 매출현황)
{
	smonth = 1;

	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square_char(50, 2, 25, 5, "원하시는 월을 선택해주세요."); // 월 선택

	square(50, 12, 25, 12); // 월 선택 박스

	for (int i = 0; i < 2; i++)
	{
		for (int j = 0; j < 6; j++)
		{
			square_int(6, 2, 29 + (j * 7), 15 + (i * 4), smonth++);
		}
	}

	square_char(11, 2, 64, 27, "취소"); // 취소 버튼

	sales_button3_1();

	_getch();
}

void sales_status3_2() // 월 선택(일 매출현황)
{
	smonth = 1;

	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square_char(50, 2, 25, 5, "원하시는 월을 선택해주세요."); // 사용 종료 버튼

	square(50, 12, 25, 12); // 월 선택 박스

	for (int i = 0; i < 2; i++)
	{
		for (int j = 0; j < 6; j++)
		{
			square_int(6, 2, 29 + (j * 7), 15 + (i * 4), smonth++);
		}
	}

	square_char(11, 2, 64, 27, "취소"); // 취소 버튼
	sales_button3_2();
}

void sales_status4_1() // 일 선택(일 매출현황)
{
	sday = 0;

	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square_char(50, 2, 25, 5, "원하시는 일을 선택해주세요.");

	square(50, 20, 25, 8); // 일 선택 박스

	// if문 사용해서 월마다 일 수 다르게 만들기
	flag = 0;
	for (int i = 0; i < 5; i++)
	{
		goto_xy(0, 40);
		printf("%d", temp2);
		for (int j = 0; j < 7; j++)
		{
			square_int(6, 2, 29 + (j * 6), 9 + (i * 4), ++sday);
			
			if (temp2 == 1 || temp2 == 3 || temp2 == 5 || temp2 == 7
				|| temp2 == 8 || temp2 == 10 || temp2 == 12)
			{
				if (sday == 31)
				{
					flag = 1; // 31일이 마지막일 때
					break;
				}
			}
			else if (temp2 == 4 || temp2 == 6 || temp2 == 9 || temp2 == 11)
			{
				if (sday == 30)
				{
					flag = 2; // 30일이 마지막일 때
					break;
				}
			}

			else if (temp2 == 2)
			{
				goto_xy(0, 41);
				printf("%d", temp1);
				if ((temp1 % 4 == 0 && temp1 % 100 != 0) || (temp1 % 400 == 0))
				{
					if (sday == 29) // 윤년이면 29일
					{
						flag = 3;
						break;

					}
				}
				else
				{
					if (sday == 28) // 평년이면 28일
					{
						flag = 4;
						break;
					}
				}
			}
		}
		if (flag == 3 || flag == 4)
		{
			break;
		}
	}

	square_char(11, 2, 64, 31, "취소"); // 취소 버튼
	
	if (flag == 1)
	{
		sales_button4_1();
	}
	else if (flag == 2)
	{
		sales_button4_2();
	}
	else if (flag == 3)
	{
		sales_button4_3();
	}
	else if (flag == 4)
	{
		printf("평년이오");
		getch();
		sales_button4_4();
	}
}

void sales_status5() // 매출현황 보여주기(연도)
{
	PCsum = 0;
	FDsum = 0;
	FILE* file = fopen("Order.txt", "r");
	// order_id, total, what를 사용함, fscanf로 뽑아내기
	while (fscanf(file, "%s %s %d %s %d %s %d", sales_order.Order_id, sales_order.id, &sales_order.total, sales_order.pay, &sales_order.pc_num, sales_order.what, &sales_order.state) == 7)
	{
		if (strstr(sales_order.Order_id, date) != NULL)
		{
			if (strcmp(sales_order.what, "FD") == 0 && sales_order.state == 1)
			{
				FDsum += sales_order.total;
			}

			else if (strcmp(sales_order.what, "PC") == 0 && sales_order.state == 0)
			{
				PCsum += sales_order.total;
			}
		}

	}
	
	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square(50, 24, 25, 3); // 연도 선택 박스


	square_char(46, 2, 27, 5, "    년 연 매출현황(PC)");
	goto_xy(40, 6);
	printf("%d", temp1);

	goto_xy(42, 11);
	printf("━━━━━━━━━━━━━━━━━");

	goto_xy(39, 11);
	printf("→  ");

	goto_xy(44, 10);
	printf("%d원", PCsum);

	square_char(46, 2, 27, 14, "    년 연 매출현황(음식)");
	goto_xy(39, 15);
	printf("%d", temp1); // 여기서 매출현황 보여줘야지 연도 매출현황

	goto_xy(42, 20);
	printf("━━━━━━━━━━━━━━━━━");

	goto_xy(39, 20);
	printf("→  ");

	goto_xy(44, 19);
	printf("%d원", FDsum);

	goto_xy(28, 25);
	printf("총 합계 : %d원", FDsum + PCsum);

	square_char(13, 2, 24, 32, "매출현황"); // 매출현황 버튼

	square_char(13, 2, 63, 32, "처음으로"); // 초기메뉴 버튼


	sales_button5_1(); // 버튼 진입
}

void sales_status6() // 매출현황 보여주기(월)
{
	PCsum = 0;
	FDsum = 0;
	FILE* file = fopen("Order.txt", "r");
	// order_id, total, what를 사용함, fscanf로 뽑아내기
	while (fscanf(file, "%s %s %d %s %d %s %d", sales_order.Order_id, sales_order.id, &sales_order.total, sales_order.pay, &sales_order.pc_num, sales_order.what, &sales_order.state) == 7)
	{
		if (strstr(sales_order.Order_id, date) != NULL)
		{
			if (strcmp(sales_order.what, "FD") == 0 && sales_order.state == 1)
			{
				FDsum += sales_order.total;
			}

			else if (strcmp(sales_order.what, "PC") == 0 && sales_order.state == 0)
			{
				PCsum += sales_order.total;
			}
		}
	}


	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square(50, 24, 25, 3); // 작은 틀


	square_char(46, 2, 27, 5, "    년   월  매출현황(PC)");
	goto_xy(39, 6);
	//printf("1234");
	printf("%d", temp1);
	goto_xy(46, 6);
	printf("%d", temp2);
	//printf("3");

	goto_xy(42, 11);
	printf("━━━━━━━━━━━━━━━━━");

	goto_xy(39, 11);
	printf("→  ");

	goto_xy(44, 10);
	printf("%d원", PCsum);

	square_char(46, 2, 27, 14, "    년  월  매출현황(음식)");
	goto_xy(38, 15);
	//printf("1234");
	printf("%d", temp1);
	goto_xy(45, 15);
	printf("%d", temp2);
	//printf("4");

	goto_xy(42, 20);
	printf("━━━━━━━━━━━━━━━━━");

	goto_xy(39, 20);
	printf("→  ");

	goto_xy(44, 19);
	printf("%d원", FDsum);

	goto_xy(28, 25);
	printf("총 합계 : %d원", FDsum + PCsum);


	// square_char(11, 2, 62, 24, "재고상세"); // 재고상세 버튼

	square_char(13, 2, 24, 32, "매출현황"); // 매출현황 버튼

	square_char(13, 2, 63, 32, "처음으로"); // 초기메뉴 버튼



	sales_button5_1();

}

void sales_status7() // 매출현황 보여주기(일)
{
	PCsum = 0;
	FDsum = 0;
	FILE* file = fopen("Order.txt", "r");
	// order_id, total, what만 사용할꺼임 fscanf로 다 뽑아내자 일단
	while (fscanf(file, "%s %s %d %s %d %s %d", sales_order.Order_id, sales_order.id, &sales_order.total, sales_order.pay, &sales_order.pc_num, sales_order.what, &sales_order.state) == 7)
	{
		//goto_xy(0, 39);
		//printf("%s", date);
		//getch();
		if (strstr(sales_order.Order_id, date) != NULL)
		{
			if (strcmp(sales_order.what, "FD") == 0 && sales_order.state == 1)
			{
				FDsum += sales_order.total;
			}

			else if (strcmp(sales_order.what, "PC") == 0 && sales_order.state == 0)
			{
				PCsum += sales_order.total;
			}
		}
	}
	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square(50, 24, 25, 3); // 작은 틀


	square_char(46, 2, 27, 5, "    년  월   일 매출현황(PC)");
	goto_xy(37, 6);
	//printf("1234");
	printf("%d", temp1);
	goto_xy(44, 6); 
	printf("%d", temp2);
	//printf("3");
	goto_xy(48, 6);
	printf("%d", temp3);
	//printf("3");

	goto_xy(42, 11);
	printf("━━━━━━━━━━━━━━━━━");

	goto_xy(39, 11);
	printf("→  ");

	goto_xy(44, 10);
	printf("%d원", PCsum);
	square_char(46, 2, 27, 14, "    년  월   일 매출현황(음식)");
	goto_xy(36, 15);
	//printf("1234");
	printf("%d", temp1);
	goto_xy(43, 15);
	printf("%d", temp2);
	//printf("4");
	goto_xy(47, 15);
	printf("%d", temp3);
	//printf("5");

	goto_xy(42, 20);
	printf("━━━━━━━━━━━━━━━━━");

	goto_xy(39, 20);
	printf("→  ");

	goto_xy(44, 19);
	printf("%d원", FDsum);

	goto_xy(28, 25);
	printf("총 합계 : %d원", FDsum + PCsum);

	square_char(13, 2, 24, 32, "매출현황"); // 매출현황 버튼
	square_char(13, 2, 63, 32, "처음으로"); // 처음으로 버튼

	sales_button5_1(); // 버튼 진입
}

void order_status1() // 주문현황
{
	system("cls");
	init(&Q); // 큐 초기화
	square(60, 35, 20, 1); // 큰 틀

	square(50, 24, 25, 4); // 주문현황 화면 박스

	goto_xy(25, 6);
	printf("┠");
	for (int j = 0; j < 48; j++)
	{
		printf("━");
	}
	printf("┫");
	//━
	//┫

	goto_xy(27, 5);
	printf("순서");

	goto_xy(40, 5);
	printf("PC번호");

	goto_xy(62, 5);
	printf("주문내역");


	for (int i = 0; i < 7; i++)
	{
		goto_xy(28, 8 + (i * 3));
		printf("%d", (i + 1) + (order_page *7));

		square_char(14, 2, 59, 7 + (3 * i), "상세 보기");
	}
	

	FILE* file = fopen("Order.txt", "r");	

	int j = 0;
	int kk = 0;
	while (fscanf(file, "%s %s %d %s %d %s %d", admin_order.Order_id, admin_order.id, &admin_order.total, admin_order.pay, &admin_order.pc_num, admin_order.what, &admin_order.state) == 7)
	{
		if (strcmp(admin_order.what, "FD") == 0) // 주문종류가 FD(음식)인 것들을 먼저 뽑아냄
		{
			if (admin_order.state == 0) // 주문 진행 중인 것들을 뽑아냄
			{
				enqueue(&Q, admin_order.Order_id); // 맞는 주문아이디를 큐에 하나씩 집어넣기

				order_pc_num[kk] = admin_order.pc_num;// 하나의 PC번호마다 7개씩 저장하기
				strcpy(order_pc_id[kk], admin_order.Order_id);
				kk++;
			}
		}
	}
	fclose(file);
	
	FILE* file2 = fopen("Order.txt", "r");
	while(fscanf(file2, "%s %s %d %s %d %s %d", admin_order.Order_id, admin_order.id, &admin_order.total, admin_order.pay, &admin_order.pc_num, admin_order.what, &admin_order.state) == 7)
	{
		if (order_pc_num[j + (order_page * 7)] == 0)
		{
			save_num = j + (order_page * 7);
			break;
		}

		if (strcmp(admin_order.what, "FD") == 0)
		{

			goto_xy(42, 8 + (j * 3));
			printf("%d", order_pc_num[j+ (order_page*7)]); // PC번호 구하는 곳, 이제 파일을 이용해서 해야함
			j++;			

			if ((j) % 7 == 0)
			{

				break;
			}

		}
		
	}
	square_char(9, 2, 57, 29, "이전");
	square_char(9, 2, 67, 29, "다음");

	square_char(14, 2, 24, 29, "메인 화면");
	fclose(file2);
	goto_xy(0, 0);
	int get = (order_button() - 1) + (order_page * 7); // 버튼 진입
}

void order_status2()
{
	k = 0;
	system("cls");
	int i = 0;
	int menu_num;

	int all_price = 0; // 총 가격

	square(60, 35, 20, 1); // 큰 틀

	square(50, 2, 25, 6); // 음식카테고리 선택 화면
	goto_xy(29, 7);
	printf("음식 결제가 완료되면 주문완료룰 눌러주세요.");

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

	FILE* file = fopen("Menu.txt", "r");

	int j = 0;
	int kk = 0;

	while (!feof(file))
	{

		if (admin_basket[j].count == 0)
		{
			break;
		}

		fscanf(file, "%s %s %s %d", admin_menu.Menu_id, admin_menu.sort, admin_menu.Name, &admin_menu.price);

		if (strcmp(admin_menu.Menu_id, admin_basket[j].Menu_id) == 0)
		{
			goto_xy(28, 15 + (2 * j));
			printf("%s", admin_menu.Name);
			goto_xy(51, 15 + (2 * j));
			printf("%d", admin_basket[j].count);
			goto_xy(64, 15 + (2 * j));
			printf("%d원", (admin_menu.price) * admin_basket[j].count);
			all_price += (admin_menu.price) * admin_basket[j].count;
			fseek(file, 0, SEEK_SET);
			j++;
		}

		
	}
	fclose(file);
	goto_xy(60, 27);
	printf("총 금액 : %d원", all_price);

	square_char(15, 2, 27, 32, "주문완료");

	square_char(15, 2, 43, 32, "주문취소");

	square_char(15, 2, 59, 32, "이전으로");

	char key = getch();
	flag = 0;
	element queue_peek = peek(&Q);
	goto_xy(0, 0);
	printf("%s", queue_peek);
	
	getch();
	if (strcmp(queue_peek, queue_order.Order_id) != 0)
	{
		flag = 2; // 1과 겹칠 수도 있기 때문
	}

	if (key == 75 || key == 77 || key == -32)
	{
		quit_button3();
	}

	goto_xy(0, 10); //커서빼기

	_getch();
}

void modify_state_File(char* fileName, char* search_order_id, int state)
{

	FILE* file = NULL;
	file = fopen(fileName, "r");
	FILE* tempFile = fopen("temp.txt", "w"); // 임시파일

	if (file == NULL || tempFile == NULL) {
		printf("File opening failed.\n");
		exit(EXIT_FAILURE);
	}
   
	while (fscanf(file, "%s %s %d %s %d %s %d", admin_check_order.Order_id, admin_check_order.id, &admin_check_order.total
		, admin_check_order.pay, &admin_check_order.pc_num, admin_check_order.what, &admin_check_order.state) == 7)
	{	// admin_basket[0].Order_id 은 0~4까지 다 같은 order_id이기 때문에 상관없음
		if (strcmp(search_order_id, admin_check_order.Order_id) == 0)
		{
			admin_check_order.state = state; // 일단 주문완료로 해놓고 나중에 케이스를 나누자
			fprintf(tempFile, "%s %s %d %s %d %s %d\n", admin_check_order.Order_id, admin_check_order.id, admin_check_order.total
				, admin_check_order.pay, admin_check_order.pc_num, admin_check_order.what, admin_check_order.state);
		}
		else // 해당 주문아이디가 아닐경우에는 뽑은거 그대로 tempFile에 출력
		{
			fprintf(tempFile, "%s %s %d %s %d %s %d\n", admin_check_order.Order_id, admin_check_order.id, admin_check_order.total
				, admin_check_order.pay, admin_check_order.pc_num, admin_check_order.what, admin_check_order.state);
		}
	}
	fclose(tempFile);
	fclose(file);
	
	char line[256];
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

void order_status3() // 주문 완료
{
	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 결제방식 화면 박스
	goto_xy(39, 18);
	printf("주문완료 처리되었습니다.");
	Sleep(2000);

	order_status1();
}

void order_status4() // 주문 취소
{
	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 결제방식 화면 박스
	goto_xy(39, 18);
	printf("주문취소 처리되었습니다.");
	Sleep(2000);

	order_status1();
}

void modify_minute_File(char* fileName, char* search_id)
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
		if (strcmp(te_user.id, search_id) == 0) {
			// 해당 아이디를 찾아서 라인을 건너뛰고 삭제하는 대신 아래와 같이 수정
			fprintf(tempFile, "%s %s %s %s %s %d %d\n", admin_account[save_num].id, admin_account[save_num].pw, admin_account[save_num].name, admin_account[save_num].gender, admin_account[save_num].birth, admin_account[save_num].second + free_add_clock.second, admin_account[save_num].money);
			free_add_clock.second = 0;
		}
		else 
		{
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

void change_second_1() // 종료할 시점에 시간을 저장하기 위해 구조체에 저장
{
	free_add_clock.second = (free_add_clock.hour * 3600) + (free_add_clock.minute * 60) + free_add_clock.second;
}

void free_add_time1() // 관리자 - 시간추가
{
	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 결제방식 화면 박스
	goto_xy(36, 15);
	printf("추가하실 시간을 선택해주세요");

	goto_xy(45, 20);
	printf("━━━━━━");
	goto_xy(52, 19);
	printf("분");

	goto_xy(47, 19);
	scanf("%d", &free_add_clock.minute);
	change_second_1();
	printf("%s", admin_account[save_num].id);
	modify_minute_File("login.txt", admin_account[save_num].id);
	
	free_add_time2();

}

void free_add_time2() // 관리자 - 시간추가완료 표시
{
	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 작은 틀
	goto_xy(39, 18);
	printf("시간이 추가되었습니다.");
	Sleep(2000);

	admin_main1();
}