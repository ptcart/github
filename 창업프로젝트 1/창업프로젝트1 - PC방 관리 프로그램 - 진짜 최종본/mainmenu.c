#include <stdio.h>
#include <conio.h>
#include <windows.h>
#include<string.h>
#include<time.h>
#include"header.h"

User user = { 0 };
User new_user = { 0 };
char new_imp[5][20] = { 0 };
//int n_people = 1; // (�ӽ�) ȸ�������� ��
void mainlog() // ���� �α���
{
	//printf("%s\n", user[1].id);
	//printf("%s\n", user[1].pw);


	// fscanf�� ���̵�, ����� ����ü�� �ް� �Է��� id�� pw�� ���ϴ� 
	// �ڵ�� �غ���
	FILE* login = NULL;

	login = fopen("login.txt", "r");
	if (login == NULL)
	{
		printf("���� ���� ����!!!");
		exit(0);
	}

	square(60, 35, 20, 1); // ū Ʋ


	square(30, 4, 35, 4); // �α��� ȭ�� �ڽ�
	goto_xy(47, 6);
	printf("�α���");

	square(30, 2, 38, 11); // ���̵� �Է¹ڽ�
	goto_xy(30, 12);
	printf("���̵�: ");

	square(30, 2, 38, 14); // ��� �Է¹ڽ�
	goto_xy(28, 15);
	printf("��й�ȣ: ");

	square(15, 2, 27, 19); // ȸ������ ȭ��ڽ�
	goto_xy(31, 20);
	printf("ȸ������");

	square(15, 2, 43, 19); // ���̵�ã�� ȭ��ڽ�
	goto_xy(46, 20);
	printf("���̵�ã��");

	square(15, 2, 59, 19); // ��й�ȣã�� ȭ��ڽ�
	goto_xy(61, 20);
	printf("��й�ȣã��");
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
		char id[20] = { 0 }; // ���̵� �Է� - ���߿� ����ü�� �ٲܿ���
		goto_xy(40, 12);
		scanf("%s", id);
		// ��й�ȣ �� ǥ��
		char pw[20] = { 0 };
		char ch;
		int i = 0;
		goto_xy(40, 15);
		while (1) 
		{
			ch = getch();  // Ű �Է��� ȭ�鿡 ǥ������ ����

			if (ch == 13) {  // Enter Ű�� ������ �Է� ����
				pw[i] = '\0';  // ���ڿ� ���� null ���� �߰�
				break;
			}
			else if (ch == 8 && i > 0) {  // Backspace Ű�� ������ ���� ���� ����
				printf("\b \b");
				i--;
			}
			else if (i < 20 - 1) {  // ��й�ȣ�� �ִ� ���̸� �ʰ����� �ʵ���
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
			printf("���� ���� ����!!!");
			exit(0);
		}
		
		while (!feof(Admin))
		{
			fscanf(Admin, "%s %s", admin.id, admin.pw);
			if ((strcmp(id, admin.id) == 0) && strcmp(pw, admin.pw) == 0)
			{
				goto_xy(35, 24);
				printf("������ �α��ο� �����Ͽ����ϴ�.");
				Sleep(1000);
				admin_main1();
			}
		}
		
		
		

		while (!feof(login))
		{

			fscanf(login, "%s %s %s %s %s %d %d", user.id, user.pw, user.name, user.gender, user.birth, &user.second, &user.money); // �������� ���Ͽ��� �о��

			if ((strcmp(id, user.id) == 0) && strcmp(pw, user.pw) == 0)
			{
				pc_login = i;
				goto_xy(38, 24);
				printf("�α��ο� �����Ͽ����ϴ�.");
				Sleep(1000);
				fclose(login);
				//system("cls");
				//_getch();
				in_user = user; // ����ϰ� �ִ� ���� (in_user)�� ���� �ű��.
				make_seat();
			}
		}

	}
	goto_xy(36, 24);
	printf("�α��� ���� �ٽ� �Է����ּ���");
	Sleep(1000);
	mainlog();
	fclose(login);

}

void join_membership()
{
	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	square(30, 4, 35, 4); // ȸ������ ȭ�� �ڽ�
	goto_xy(46, 6);
	printf("ȸ������");

	square(30, 2, 38, 11); // ���̵� �Է¹ڽ�
	goto_xy(30, 12);
	printf("���̵�: ");
	if (flag == 1)
	{
		goto_xy(40, 12);
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 11);
		printf("���̵� �ߺ��Դϴ�.");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
		flag = 0;
	}

	square(30, 2, 38, 14); // ��� �Է¹ڽ�
	goto_xy(28, 15);
	printf("��й�ȣ: ");

	square(30, 2, 38, 17); // �̸� �Է¹ڽ�
	goto_xy(31, 18);
	printf("�̸�: ");

	square(30, 2, 38, 20); // ���� �Է¹ڽ�
	goto_xy(31, 21);
	printf("����: ");

	square(30, 2, 38, 23); // ������� �Է¹ڽ�
	goto_xy(28, 24);
	printf("�������: ");

	square(9, 2, 54, 28); // Ȯ�� ȭ��ڽ�
	goto_xy(57, 29);
	printf("Ȯ��");

	square(9, 2, 64, 28); // ��� ȭ��ڽ�
	goto_xy(67, 29);
	printf("���");

	char key = getch();
	goto_xy(0, 3);
	printf("%d", key);
	
	User check_user;
	FILE* login_1 = NULL;

	login_1 = fopen("login.txt", "a+");
	if (login_1 == NULL)
	{
		printf("���� ���� ����!!!");
		exit(0);
	}

	FILE* login_2 = NULL;

	login_2 = fopen("admin.txt", "r");
	if (login_2 == NULL)
	{
		printf("���� ���� ����!!!");
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
						if (c == 13) { // EnterŰ�� ������ ��
							break;
						}
						else if (c == 8 && i > 0) { // Backspace Ű
							printf("\b \b");
							i--;
						}
						else if (i < 20 - 1) {
							new_imp[bt][i] = c;
							printf("*");
							i++;
						}
					}

					new_imp[bt][i] = '\0'; // ���ڿ� ���� null ���� �߰�
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

void show_join() // ȸ������ �Ϸ�
{
	FILE* login_3 = NULL;

	login_3 = fopen("login.txt", "a+");
	if (login_3 == NULL)
	{
		printf("���� ���� ����!!!");
		exit(0);
	}
	fprintf(login_3, "%s %s %s %s %s %d %d\n", new_user.id, new_user.pw, new_user.name, new_user.gender, new_user.birth, new_user.second, new_user.money);
	fclose(login_3);
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ȸ������ �Ϸ� ȭ�� �ڽ�
	goto_xy(38, 18);
	printf("ȸ�������� �Ϸ�Ǿ����ϴ�.");
	Sleep(2000); // 2�� ������

	mainlog();
}

void go_counter() // ȸ������ �Ϸ�
{
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ȸ������ �Ϸ� ȭ�� �ڽ�
	goto_xy(40, 18);
	printf("ī���Ϳ� �������ּ���.");
	Sleep(2000); // 2�� ������

	mainlog();
}

void make_seat() // �ڸ� ����
{

	system("cls");
	square(80, 30, 20, 1); // ū Ʋ

	square(50, 2, 35, 3); // �ڸ����� ȭ�� �ڽ�
	goto_xy(46, 4);
	printf("���Ͻô� �ڸ��� �������ּ���");

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
	char_color(88, 7, "��� ����", 7);
	char_color(88, 9, "��� ��", 4);

	number = seat_button();

	enter_pc(number);

}

void enter_pc(int number) // pc�� ������
{

	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ȸ������ �Ϸ� ȭ�� �ڽ�


	for (int i = 0; i < 3; i++) // �ε�ȭ��
	{
		goto_xy(40, 18);
		printf("%d�� pc ���� �Ѵ� ��.", number);
		goto_xy(0, 10); // Ŀ�� ����
		Sleep(500); // 0.5�� ������
		goto_xy(40, 18);
		printf("%d�� pc ���� �Ѵ� ��..", number);
		goto_xy(0, 10); // Ŀ�� ����
		Sleep(500); // 0.5�� ������
		goto_xy(40, 18);
		printf("%d�� pc ���� �Ѵ� ��...", number);
		goto_xy(0, 10); // Ŀ�� ����
		Sleep(500); // 0.5�� ������
		goto_xy(40, 18);
		printf("                           ");
	}
	user_main();

}
