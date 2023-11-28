#include <stdio.h>
#include <conio.h>
#include <windows.h>
#include<string.h>
#include<time.h>
#include"header.h"

User user = { 0 };
User new_user = { 0 };
char new_imp[5][20] = { 0 };
//int n_people = 1; // (임시) 회원가입자 수
void mainlog() // 메인 로그인
{
	//printf("%s\n", user[1].id);
	//printf("%s\n", user[1].pw);


	// fscanf로 아이디, 비번을 구조체로 받고 입력한 id와 pw를 비교하는 
	// 코드로 해볼듯
	FILE* login = NULL;

	login = fopen("login.txt", "r");
	if (login == NULL)
	{
		printf("파일 열기 실패!!!");
		exit(0);
	}

	square(60, 35, 20, 1); // 큰 틀


	square(30, 4, 35, 4); // 로그인 화면 박스
	goto_xy(47, 6);
	printf("로그인");

	square(30, 2, 38, 11); // 아이디 입력박스
	goto_xy(30, 12);
	printf("아이디: ");

	square(30, 2, 38, 14); // 비번 입력박스
	goto_xy(28, 15);
	printf("비밀번호: ");

	square(15, 2, 27, 19); // 회원가입 화면박스
	goto_xy(31, 20);
	printf("회원가입");

	square(15, 2, 43, 19); // 아이디찾기 화면박스
	goto_xy(46, 20);
	printf("아이디찾기");

	square(15, 2, 59, 19); // 비밀번호찾기 화면박스
	goto_xy(61, 20);
	printf("비밀번호찾기");
	goto_xy(0, 0);


	char key = getch();
	goto_xy(0, 3);
	printf("%d", key);

	if (key == 75 || key == 77 || key == -32)
	{
		main_button();
	}
	else
	{
		char id[20] = { 0 }; // 아이디 입력 - 나중에 구조체로 바꿀예정
		goto_xy(40, 12);
		scanf("%s", id);
		// 비밀번호 별 표시
		char pw[20] = { 0 };
		char ch;
		int i = 0;
		goto_xy(40, 15);
		while (1) 
		{
			ch = getch();  // 키 입력을 화면에 표시하지 않음

			if (ch == 13) {  // Enter 키를 누르면 입력 종료
				pw[i] = '\0';  // 문자열 끝에 null 문자 추가
				break;
			}
			else if (ch == 8 && i > 0) {  // Backspace 키를 누르면 이전 문자 삭제
				printf("\b \b");
				i--;
			}
			else if (i < 20 - 1) {  // 비밀번호의 최대 길이를 초과하지 않도록
				pw[i] = ch;
				printf("*");
				i++;
			}
		}
		goto_xy(0, 3);

		FILE* Admin = NULL;

		Admin = fopen("admin.txt", "r");
		if (Admin == NULL)
		{
			printf("파일 열기 실패!!!");
			exit(0);
		}
		
		while (!feof(Admin))
		{
			fscanf(Admin, "%s %s", admin.id, admin.pw);
			if ((strcmp(id, admin.id) == 0) && strcmp(pw, admin.pw) == 0)
			{
				goto_xy(35, 24);
				printf("관리자 로그인에 성공하였습니다.");
				Sleep(1000);
				admin_main1();
			}
		}
		
		
		

		while (!feof(login))
		{

			fscanf(login, "%s %s %s %s %s %d %d", user.id, user.pw, user.name, user.gender, user.birth, &user.second, &user.money); // 개인정보 파일에서 읽어옴

			if ((strcmp(id, user.id) == 0) && strcmp(pw, user.pw) == 0)
			{
				pc_login = i;
				goto_xy(38, 24);
				printf("로그인에 성공하였습니다.");
				Sleep(1000);
				fclose(login);
				//system("cls");
				//_getch();
				in_user = user; // 사용하고 있는 유저 (in_user)에 값을 옮긴다.
				make_seat();
			}
		}

	}
	goto_xy(36, 24);
	printf("로그인 실패 다시 입력해주세요");
	Sleep(1000);
	mainlog();
	fclose(login);

}

void join_membership()
{
	system("cls");
	square(60, 35, 20, 1); // 큰 틀

	square(30, 4, 35, 4); // 회원가입 화면 박스
	goto_xy(46, 6);
	printf("회원가입");

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

	char key = getch();
	goto_xy(0, 3);
	printf("%d", key);
	
	User check_user;
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

	if (key == 75 || key == 77 || key == -32)
	{
		ok_button1();
	}

	else
	{
		int bt = 0;
		int i = 0;
		char Bt = 0;
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
				scanf("%s", new_imp[bt]);
				bt += 1;


			}

			if (Bt == 27)
			{
				bt -= 1;
				goto_xy(40, 12 + (bt * 3));
				printf("                   ");
				goto_xy(40, 12 + (bt * 3));

				scanf("%s", new_imp[bt]);
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
							new_imp[bt][i] = c;
							printf("*");
							i++;
						}
					}

					new_imp[bt][i] = '\0'; // 문자열 끝에 null 문자 추가
					bt += 1;
				}
				else if (bt == 0)
				{
					goto_xy(40, 12 + (bt * 3));
					printf("                   ");
					goto_xy(40, 12 + (bt * 3));
					scanf("%s", new_imp[bt]);
					

					fscanf(login_2, "%s %s %s %s %s %d %d", check_user.id, check_user.pw, check_user.name, check_user.gender, check_user.birth, &check_user.second, &check_user.money);
					if (strcmp(check_user.id, new_imp[bt]) == 0)
					{
						goto_xy(0, 0);
						flag = 1;
						join_membership();
					}

					while (fscanf(login_1, "%s %s %s %s %s %d %d", check_user.id, check_user.pw, check_user.name, check_user.gender, check_user.birth, &check_user.second, &check_user.money) == 7)
					{

						if (strcmp(check_user.id, new_imp[bt]) == 0)
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
					scanf("%s", new_imp[bt]);
					bt += 1;
				}
			}

		}
		strcpy(new_user.id, new_imp[0]);
		strcpy(new_user.pw, new_imp[1]);
		strcpy(new_user.name, new_imp[2]);
		strcpy(new_user.gender, new_imp[3]);
		strcpy(new_user.birth, new_imp[4]);


		goto_xy(0, 20);

		//fprintf(login_1, "%s %s %s %s %s %d %d\n", new_user.id, new_user.pw, new_user.name, new_user.gender, new_user.birth, new_user.second, new_user.money);
		fclose(login_1);
		fclose(login_2);
		join_check = 1;
		ok_button1();

	}
}

void show_join() // 회원가입 완료
{
	FILE* login_3 = NULL;

	login_3 = fopen("login.txt", "a+");
	if (login_3 == NULL)
	{
		printf("파일 열기 실패!!!");
		exit(0);
	}
	fprintf(login_3, "%s %s %s %s %s %d %d\n", new_user.id, new_user.pw, new_user.name, new_user.gender, new_user.birth, new_user.second, new_user.money);
	fclose(login_3);
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 회원가입 완료 화면 박스
	goto_xy(38, 18);
	printf("회원가입이 완료되었습니다.");
	Sleep(2000); // 2초 보여줌

	mainlog();
}

void go_counter() // 회원가입 완료
{
	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 회원가입 완료 화면 박스
	goto_xy(40, 18);
	printf("카운터에 문의해주세요.");
	Sleep(2000); // 2초 보여줌

	mainlog();
}

void make_seat() // 자리 선택
{

	system("cls");
	square(80, 30, 20, 1); // 큰 틀

	square(50, 2, 35, 3); // 자리선택 화면 박스
	goto_xy(46, 4);
	printf("원하시는 자리를 선택해주세요");

	int i;
	int pc_num = 1;
	int j;

	for (i = 0; i < 2; i++)
	{
		for (j = 0; j < 10; j++)
		{
			square_int(6, 2, 24 + (6 * j), 9 + (i * 3), pc_num);
			pc_num++;
		}
	}

	for (i = 2; i < 4; i++)
	{
		for (j = 0; j < 10; j++)
		{
			square_int(6, 2, 24 + (6 * j), 10 + (i * 3), pc_num);
			pc_num++;
		}
	}

	for (i = 4; i < 6; i++)
	{
		for (j = 0; j < 10; j++)
		{
			square_int(6, 2, 24 + (6 * j), 11 + (i * 3), pc_num);
			pc_num++;
		}
	}
	char_color(88, 7, "사용 가능", 7);
	char_color(88, 9, "사용 중", 4);

	number = seat_button();

	enter_pc(number);

}

void enter_pc(int number) // pc로 들어가는중
{

	system("cls");

	square(60, 35, 20, 1); // 큰 틀

	square(50, 12, 25, 12); // 회원가입 완료 화면 박스


	for (int i = 0; i < 3; i++) // 로딩화면
	{
		goto_xy(40, 18);
		printf("%d번 pc 전원 켜는 중.", number);
		goto_xy(0, 10); // 커서 빼기
		Sleep(500); // 0.5초 보여줌
		goto_xy(40, 18);
		printf("%d번 pc 전원 켜는 중..", number);
		goto_xy(0, 10); // 커서 빼기
		Sleep(500); // 0.5초 보여줌
		goto_xy(40, 18);
		printf("%d번 pc 전원 켜는 중...", number);
		goto_xy(0, 10); // 커서 빼기
		Sleep(500); // 0.5초 보여줌
		goto_xy(40, 18);
		printf("                           ");
	}
	user_main();

}
