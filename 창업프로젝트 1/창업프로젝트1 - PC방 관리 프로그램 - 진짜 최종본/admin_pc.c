#include <stdio.h>
#include <conio.h>
#include <windows.h>
#include<string.h>
#include<stdlib.h>
#include<time.h>
#include"header.h"
#include"Queue.h"

User user_[5] = { {"ptcart4033", "rlaqjawls4033","�����", "20010529", "����", 10 ,1000} };

User exist_user = { 0 };
char ex_imp[5][20] = { 0 };

char sales[3][20] = { "�� ������Ȳ", "�� ������Ȳ", "�� ������Ȳ" };

int syear;
int smonth;
int sday;

int tep_pcnum;
account_page = 0;
order_page = 0; // �ֹ����� ������ ��
void admin_main1() // ������ ����1 (ȸ���� �Ȱ���� ��� - �ȳ�����)
{
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ
	square(50, 18, 25, 3); // ���� Ʋ

	goto_xy(29, 6);
	printf("�� PC ������ ���ǻ���");

	goto_xy(29, 9);
	printf("�� ���� 10�� ���Ŀ� �ݵ�� �ź��� �˻� �ǽ�");

	goto_xy(29, 12);
	printf("�� �ٹ� ������ �� �и����� �Ĳ��� �ǽ�");

	goto_xy(29, 15);
	printf("�� PC �۵� �ȵ� ��, ��� �Ŵ������� �����ϱ�");

	goto_xy(29, 18);
	printf("�� �¼� ��ȣ ���� �ܿ��α�");

	square_char(18, 2, 41, 23, "ȸ�� ���"); // �԰Ÿ� �ֹ� ��ư

	square_char(18, 2, 41, 26, "�ֹ� ����"); // �԰Ÿ� �ֹ� ��ư

	square_char(18, 2, 41, 29, "ȸ�� ���(ã��)"); // �԰Ÿ� �ֹ� ��ư

	square_char(18, 2, 41, 32, "���� ��Ȳ"); // �԰Ÿ� �ֹ� ��ư


	char key = getch();
	goto_xy(0, 3);
	printf("%d", key);

	if (key == 75 || key == 77 || key == -32)
	{
		admin_button1();
	}
}

void admin_main2() // ������ ����2 (ȸ���� ����� ��� - ȸ������)
{
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ
	square(50, 16, 25, 3); // ���� Ʋ

	goto_xy(33, 7);
	printf("���̵� : %s", admin_account[save_num].id);

	goto_xy(33, 9);
	printf("���� : %s", admin_account[save_num].gender);

	goto_xy(33, 11);
	printf("�̸� : %s", admin_account[save_num].name);

	goto_xy(33, 13);
	printf("������� : %04d-%02d-%02d", year[save_num], month[save_num], day[save_num]);

	square_char(18, 2, 41, 23, "�ð� �߰�"); // �ð��߰� ��ư

	square_char(18, 2, 41, 26, "ȸ�� ����"); // ȸ������ ��ư

	square_char(18, 2, 41, 29, "ȸ�� ����"); // ȸ������ ��ư

	square_char(18, 2, 41, 32, "ó�� ����"); // ó������ ��ư

	char key = getch();
	goto_xy(0, 3);
	printf("%d", key);

	if (key == 75 || key == 77 || key == -32)
	{
		admin_button2();
	}
}

void admin_main3() // ȸ���� �˻� �� ��ȸ
{
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ
	square(50, 16, 25, 3); // ���� Ʋ

	goto_xy(33, 7);
	printf("���̵� : %s", admin_search_account[save_num].id);

	goto_xy(33, 9);
	printf("���� : %s", admin_search_account[save_num].gender);

	goto_xy(33, 11);
	printf("�̸� : %s", admin_search_account[save_num].name);

	goto_xy(33, 13);
	printf("������� : %04d-%02d-%02d", year[save_num], month[save_num], day[save_num]);;

	square_char(18, 2, 41, 23, "�ð� �߰�"); // �ð��߰� ��ư
	
	square_char(18, 2, 41, 26, "ȸ�� ����"); // �ð��߰� ��ư

	square_char(18, 2, 41, 29, "ȸ�� ����"); // �ð��߰� ��ư

	square_char(18, 2, 41, 32, "ó�� ����"); // ��� ���� ��ư

	char key = getch();
	goto_xy(0, 3);
	printf("%d", key);

	if (key == 75 || key == 77 || key == -32)
	{
		admin_button3();
	}
}

void member_modification1() // ȸ�� ����
{

	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	square(30, 4, 35, 4); // ȸ����� ȭ�� �ڽ�
	goto_xy(37, 6);
	printf("������ ������ �������ּ���.");

	square(30, 2, 38, 11); // ���̵� �Է¹ڽ�
	goto_xy(30, 12);
	printf("���̵�: ");
	goto_xy(40, 12);
	printf("%s", admin_account[save_num].id);

	square(30, 2, 38, 14); // ��� �Է¹ڽ�
	goto_xy(28, 15);
	printf("��й�ȣ: ");
	goto_xy(40, 15);
	printf("%s", admin_account[save_num].pw);

	square(30, 2, 38, 17); // �̸� �Է¹ڽ�
	goto_xy(31, 18);
	printf("�̸�: ");
	goto_xy(40, 18);
	printf("%s", admin_account[save_num].name);

	square(30, 2, 38, 20); // ���� �Է¹ڽ�
	goto_xy(31, 21);
	printf("����: ");
	goto_xy(40, 21);
	printf("%s", admin_account[save_num].gender);

	square(30, 2, 38, 23); // ������� �Է¹ڽ�
	goto_xy(28, 24);
	printf("�������: ");
	goto_xy(40, 24);
	printf("%s", admin_account[save_num].birth);

	square(14, 2, 60, 28); // Ȯ�� ȭ��ڽ�
	goto_xy(63, 29);
	printf("���� ����");

	char key = getch();
	goto_xy(0, 3);
	printf("%d", key);

	if (key == 75 || key == 77 || key == -32)
	{
		modify_button1(); // ��ư ����
	}

}

void member_modification2() // ȸ�� ���� - ȸ���˻� ��
{

	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	square(30, 4, 35, 4); // ȸ����� ȭ�� �ڽ�
	goto_xy(37, 6);
	printf("������ ������ �������ּ���.");

	square(30, 2, 38, 11); // ���̵� �Է¹ڽ�
	goto_xy(30, 12);
	printf("���̵�: ");
	goto_xy(40, 12);
	printf("%s", admin_search_account[save_num].id);

	square(30, 2, 38, 14); // ��� �Է¹ڽ�
	goto_xy(28, 15);
	printf("��й�ȣ: ");
	goto_xy(40, 15);
	printf("%s", admin_search_account[save_num].pw);

	square(30, 2, 38, 17); // �̸� �Է¹ڽ�
	goto_xy(31, 18);
	printf("�̸�: ");
	goto_xy(40, 18);
	printf("%s", admin_search_account[save_num].name);

	square(30, 2, 38, 20); // ���� �Է¹ڽ�
	goto_xy(31, 21);
	printf("����: ");
	goto_xy(40, 21);
	printf("%s", admin_search_account[save_num].gender);

	square(30, 2, 38, 23); // ������� �Է¹ڽ�
	goto_xy(28, 24);
	printf("�������: ");
	goto_xy(40, 24);
	printf("%s", admin_search_account[save_num].birth);

	square(14, 2, 60, 28); // Ȯ�� ȭ��ڽ�
	goto_xy(63, 29);
	printf("���� ����");

	char key = getch();
	goto_xy(0, 3);
	printf("%d", key);

	if (key == 75 || key == 77 || key == -32)
	{
		modify_button2(); // ��ư ����
	}

}

void show_modify() // ȸ������ �Ϸ�
{
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ȸ������ �Ϸ� ȭ�� �ڽ�
	goto_xy(38, 18);
	printf("ȸ�������� �Ϸ�Ǿ����ϴ�.");
	Sleep(2000); // 2�� ������

	member_modification1();
}

void show_modify2() // ȸ������ �Ϸ� (ȸ���˻� ����)
{
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ȸ������ �Ϸ� ȭ�� �ڽ�
	goto_xy(38, 18);
	printf("ȸ�������� �Ϸ�Ǿ����ϴ�.");
	Sleep(2000); // 2�� ������

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
		// �˻� ���ڿ��� ���ο� ���ԵǾ� �ִ��� Ȯ��
		if (strcmp(te_user.id, search_id) == 0)
		{ 
			// �ش� ���̵� ã���� ���� �ʱ�
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
		// �˻� ���ڿ��� ���ο� ���ԵǾ� �ִ��� Ȯ��
		fprintf(file, "%s", line);
	}

	fclose(tempFile);
	fclose(file);
}

void delete_user() // ȸ������
{
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ���� ȭ�� �ڽ�
	goto_xy(37, 18);
	printf("%s���� �����Ͻðڽ��ϱ�?", admin_account[save_num].name);

	square(15, 2, 32, 27); // ���� ȭ��ڽ�
	goto_xy(38, 28);
	printf("����");

	square(15, 2, 53, 27); // ��� ȭ��ڽ�
	goto_xy(59, 28);
	printf("���");
	cases = 0;

	delete_button(); // ��ư ����

}

void show_delete() // ȸ������ �Ϸ�
{
	system("cls");
	delete_user_id("login.txt", admin_account[save_num].id);
	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ȸ������ �Ϸ� ȭ�� �ڽ�
	goto_xy(38, 18);
	printf("ȸ�������� �����Ͽ����ϴ�.");
	Sleep(2000); // 2�� ������

	admin_main1();
}

void delete_user_1() // ȸ������(�˻� ���� ���)
{
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ���� ȭ�� �ڽ�
	goto_xy(37, 18);
	printf("%s���� �����Ͻðڽ��ϱ�?", admin_search_account[save_num].name);

	square(15, 2, 32, 27); // ���� ȭ��ڽ�
	goto_xy(38, 28);
	printf("����");

	square(15, 2, 53, 27); // ��� ȭ��ڽ�
	goto_xy(59, 28);
	printf("���");
	cases = 1;

	delete_button(); // ��ư ����
}

void show_delete_1() // ȸ������ �Ϸ�(�˻����� ���)
{
	system("cls");
	delete_user_id("login.txt", admin_search_account[save_num].id);
	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ȸ������ �Ϸ� ȭ�� �ڽ�
	goto_xy(38, 18);
	printf("ȸ�������� �����Ͽ����ϴ�.");
	Sleep(2000); // 2�� ������

	admin_main1();
}

void register_membership() // ȸ�� ���
{
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
	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	square(30, 4, 35, 4); // ȸ����� ȭ�� �ڽ�
	goto_xy(46, 6);
	printf("ȸ�� ���");

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
		// ���̵� �Է� - ���߿� ����ü�� �ٲܿ���
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
						if (c == 13) { // EnterŰ�� ������ ��
							break;
						}
						else if (c == 8 && i > 0) { // Backspace Ű
							printf("\b \b");
							i--;
						}
						else if (i < 20 - 1) {
							ex_imp[bt][i] = c;
							printf("*");
							i++;
						}
					}

					ex_imp[bt][i] = '\0'; // ���ڿ� ���� null ���� �߰�
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

		ok_button3(); // ��ư ����

	}
}

void show_regist() // ȸ������ �Ϸ�
{
	FILE* login_4 = NULL;

	login_4 = fopen("login.txt", "a+");
	if (login_4 == NULL)
	{
		printf("���� ���� ����!!!");
		exit(0);
	}
	fprintf(login_4, "%s %s %s %s %s %d %d\n", exist_user.id, exist_user.pw, exist_user.name, exist_user.gender, exist_user.birth, exist_user.second, exist_user.money);
	fclose(login_4);
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ȸ������ �Ϸ� ȭ�� �ڽ�
	goto_xy(38, 18);
	printf("ȸ������� �Ϸ�Ǿ����ϴ�.");
	Sleep(2000); // 2�� ������

	admin_main1();
}

void quit_pc3() // ��� ����(������)
{
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ���� ȭ�� �ڽ�
	goto_xy(40, 18);
	printf("PC�� �����Ͻðڽ��ϱ�?");

	square(15, 2, 32, 27); // ���� ȭ��ڽ�
	goto_xy(38, 28);
	printf("����");

	square(15, 2, 53, 27); // ��� ȭ��ڽ�
	goto_xy(59, 28);
	printf("���");

	quit_button2(); // ��ư ����
}

void quit_pc4(int number) // pc�� ������ ��...�����ڹ���
{
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ȸ������ �Ϸ� ȭ�� �ڽ�

	for (int i = 0; i < 3; i++) // �ε�ȭ��
	{
		goto_xy(40, 18);
		printf("%d�� pc ���� ���� ��.", number);
		goto_xy(0, 10); // Ŀ�� ����
		Sleep(500); // 0.5�� ������
		goto_xy(40, 18);
		printf("%d�� pc ���� ���� ��..", number);
		goto_xy(0, 10); // Ŀ�� ����
		Sleep(500); // 0.5�� ������
		goto_xy(40, 18);
		printf("%d�� pc ���� ���� ��...", number);
		goto_xy(0, 10); // Ŀ�� ����
		Sleep(500); // 0.5�� ������
		goto_xy(40, 18);
		printf("                           ");
	}

	admin_main1(); // ������ ����1 ����
}

void save_user_account(char* fileName)
{
	for (int i = 0; i < 1000; i++) 
		// �Լ� �ҷ��� ������ ��� �ʱ�ȭ -> ��¥�� ���Ͽ��� �ҷ����ϱ� �������
		//(�����Ǹ� �ֽ�ȭ���ֱ� ����)
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
	//FILE* tempFile = fopen("temp.txt", "w"); // �ӽ�����
	
	if (file == NULL) {
		printf("File opening failed.\n");
		exit(EXIT_FAILURE);
	}
	int cc = 0;
	while (fscanf(file, "%s %s %s %s %s %d %d", admin_account[cc].id, admin_account[cc].pw, admin_account[cc].name, admin_account[cc].gender
	, admin_account[cc].birth, &admin_account[cc].second, &admin_account[cc].money) == 7)
	{	// admin_account ����ü �迭�� ������ �� ����
		sscanf(admin_account[cc].birth, "%4d%2d%2d", &year[cc], &month[cc], &day[cc]);
		cc++;
	}

	fclose(file);
}

void member_list()
{
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	goto_xy(23, 3);
	printf("��ȣ");

	goto_xy(32, 3);
	printf("�̸�");

	goto_xy(44, 3);
	printf("���̵�");

	goto_xy(62, 3);
	printf("�������");


	for (int i = 0; i < 8; i++)
	{
		square(58, 2, 21, 4 + (3 * i));
		goto_xy(27, 4 + (3 * i));
		printf("��");

		goto_xy(27, 5 + (3 * i));
		printf("��");

		goto_xy(27, 6 + (3 * i));
		printf("��");

		goto_xy(39, 4 + (3 * i));
		printf("��");

		goto_xy(39, 5 + (3 * i));
		printf("��");

		goto_xy(39, 6 + (3 * i));
		printf("��");

		goto_xy(54, 4 + (3 * i));
		printf("��");

		goto_xy(54, 5 + (3 * i));
		printf("��");

		goto_xy(54, 6 + (3 * i));
		printf("��");
	}

	int aa = 0;
	for (int j = (account_page * 8); j < (account_page * 8) + 8; j++)
	{
		goto_xy(24, 5 + (3 * aa));
		printf("%d", j + 1);
		aa++;
	}

	square_char(14, 2, 21, 28, "ȸ�� ã��");

	square_char(9, 2, 58, 28, "����");

	square_char(9, 2, 68, 28, "����");

	square_char(15, 2, 62, 32, "���ư���");

	save_user_account("login.txt"); // ����ڵ� ���� �ް�

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

void serched_member_list() // �˻��� ȸ������ ����Ʈ
{
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	goto_xy(23, 3);
	printf("��ȣ");

	goto_xy(32, 3);
	printf("�̸�");

	goto_xy(44, 3);
	printf("���̵�");

	goto_xy(62, 3);
	printf("�������");


	for (int i = 0; i < 8; i++)
	{
		square(58, 2, 21, 4 + (3 * i));
		goto_xy(27, 4 + (3 * i));
		printf("��");

		goto_xy(27, 5 + (3 * i));
		printf("��");

		goto_xy(27, 6 + (3 * i));
		printf("��");

		goto_xy(39, 4 + (3 * i));
		printf("��");

		goto_xy(39, 5 + (3 * i));
		printf("��");

		goto_xy(39, 6 + (3 * i));
		printf("��");

		goto_xy(54, 4 + (3 * i));
		printf("��");

		goto_xy(54, 5 + (3 * i));
		printf("��");

		goto_xy(54, 6 + (3 * i));
		printf("��");
	}
	int aa = 0;
	for (int j = (account_page * 8); j < (account_page * 8) + 8; j++)
	{
		goto_xy(24, 5 + (3 * aa));
		printf("%d", j + 1);
		aa++;
	}

	square_char(14, 2, 21, 28, "ȸ�� ã��");

	square_char(9, 2, 58, 28, "����");

	square_char(9, 2, 68, 28, "����");

	square_char(15, 2, 62, 32, "���ư���");

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

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ���� ȭ�� �ڽ�
	goto_xy(32, 15);
	printf("ã�� ���� ȸ���� �̸��� �Է����ּ���");

	goto_xy(42, 20);
	printf("����������������������������������");

	goto_xy(39, 19);
	printf("�� ");
	goto_xy(47, 19);
	scanf("%s", name);


	for (int i = 0; i < 1000; i++)
		// �Լ� �ҷ��� ������ ��� �ʱ�ȭ -> ��¥�� ���Ͽ��� �ҷ����ϱ� �������
		//(�����Ǹ� �ֽ�ȭ���ֱ� ����)
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
		// �˻� ���ڿ��� ���ο� ���ԵǾ� �ִ��� Ȯ��
		if (strstr(admin_account[ss].name, name) != NULL) {
			// �ش� ���̵� ã�Ƽ� ������ �ǳʶٰ� �����ϴ� ��� �Ʒ��� ���� ����
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

void sales_status1() // ������Ȳ
{
	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	for (int i = 0; i < 3; i++)
	{
		square_char(22, 4, 39, 6 + (10 * i), sales[i]); // ����ü �̿��ؼ� ī�װ� ����
	}
	square_char(13, 2, 65, 33, "��������");
	sales_button1();
}

void sales_status2_1() // ���� ����(�� ������Ȳ)
{
	syear = 2014;
	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	square_char(50, 2, 25, 5, "���Ͻô� ������ �������ּ���."); // ���� ����

	square(50, 12, 25, 12); // ���� ���� �ڽ�


	for (int i = 0; i < 2; i++)
	{
		for (int j = 0; j < 5; j++)
		{
			square_int(9, 2, 28 + (j * 9), 15 + (i * 4), syear++);
		}
	}


	square_char(11, 2, 64, 27, "���"); // ��� ��ư
	sales_button2_1();
}

void sales_status2_2() // ���� ����(�� ���� ��)
{

	syear = 2014;

	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	square_char(50, 2, 25, 5, "���Ͻô� ������ �������ּ���."); // ���� ���� ��ư

	square(50, 12, 25, 12); // ���� ���� �ڽ�


	for (int i = 0; i < 2; i++)
	{
		for (int j = 0; j < 5; j++)
		{
			square_int(9, 2, 28 + (j * 9), 15 + (i * 4), syear++);
		}
	}
	//goto_xy(32, 15);
	//printf("������ �Է����ּ���");

	square_char(11, 2, 64, 27, "���"); // ��� ��ư

	sales_button2_2();
}

void sales_status2_3() // ���� ����(�� ���� ��)
{

	syear = 2014;

	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	square_char(50, 2, 25, 5, "���Ͻô� ������ �������ּ���."); // ���� ����

	square(50, 12, 25, 12); // ���� ���� �ڽ�


	for (int i = 0; i < 2; i++)
	{
		for (int j = 0; j < 5; j++)
		{
			square_int(9, 2, 28 + (j * 9), 15 + (i * 4), syear++);
		}
	}

	square_char(11, 2, 64, 27, "���"); // ��� ��ư

	sales_button2_3();
}

void sales_status3_1() // �� ����(�� ������Ȳ)
{
	smonth = 1;

	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	square_char(50, 2, 25, 5, "���Ͻô� ���� �������ּ���."); // �� ����

	square(50, 12, 25, 12); // �� ���� �ڽ�

	for (int i = 0; i < 2; i++)
	{
		for (int j = 0; j < 6; j++)
		{
			square_int(6, 2, 29 + (j * 7), 15 + (i * 4), smonth++);
		}
	}

	square_char(11, 2, 64, 27, "���"); // ��� ��ư

	sales_button3_1();

	_getch();
}

void sales_status3_2() // �� ����(�� ������Ȳ)
{
	smonth = 1;

	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	square_char(50, 2, 25, 5, "���Ͻô� ���� �������ּ���."); // ��� ���� ��ư

	square(50, 12, 25, 12); // �� ���� �ڽ�

	for (int i = 0; i < 2; i++)
	{
		for (int j = 0; j < 6; j++)
		{
			square_int(6, 2, 29 + (j * 7), 15 + (i * 4), smonth++);
		}
	}

	square_char(11, 2, 64, 27, "���"); // ��� ��ư
	sales_button3_2();
}

void sales_status4_1() // �� ����(�� ������Ȳ)
{
	sday = 0;

	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	square_char(50, 2, 25, 5, "���Ͻô� ���� �������ּ���.");

	square(50, 20, 25, 8); // �� ���� �ڽ�

	// if�� ����ؼ� ������ �� �� �ٸ��� �����
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
					flag = 1; // 31���� �������� ��
					break;
				}
			}
			else if (temp2 == 4 || temp2 == 6 || temp2 == 9 || temp2 == 11)
			{
				if (sday == 30)
				{
					flag = 2; // 30���� �������� ��
					break;
				}
			}

			else if (temp2 == 2)
			{
				goto_xy(0, 41);
				printf("%d", temp1);
				if ((temp1 % 4 == 0 && temp1 % 100 != 0) || (temp1 % 400 == 0))
				{
					if (sday == 29) // �����̸� 29��
					{
						flag = 3;
						break;

					}
				}
				else
				{
					if (sday == 28) // ����̸� 28��
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

	square_char(11, 2, 64, 31, "���"); // ��� ��ư
	
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
		printf("����̿�");
		getch();
		sales_button4_4();
	}
}

void sales_status5() // ������Ȳ �����ֱ�(����)
{
	PCsum = 0;
	FDsum = 0;
	FILE* file = fopen("Order.txt", "r");
	// order_id, total, what�� �����, fscanf�� �̾Ƴ���
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
	square(60, 35, 20, 1); // ū Ʋ

	square(50, 24, 25, 3); // ���� ���� �ڽ�


	square_char(46, 2, 27, 5, "    �� �� ������Ȳ(PC)");
	goto_xy(40, 6);
	printf("%d", temp1);

	goto_xy(42, 11);
	printf("����������������������������������");

	goto_xy(39, 11);
	printf("��  ");

	goto_xy(44, 10);
	printf("%d��", PCsum);

	square_char(46, 2, 27, 14, "    �� �� ������Ȳ(����)");
	goto_xy(39, 15);
	printf("%d", temp1); // ���⼭ ������Ȳ ��������� ���� ������Ȳ

	goto_xy(42, 20);
	printf("����������������������������������");

	goto_xy(39, 20);
	printf("��  ");

	goto_xy(44, 19);
	printf("%d��", FDsum);

	goto_xy(28, 25);
	printf("�� �հ� : %d��", FDsum + PCsum);

	square_char(13, 2, 24, 32, "������Ȳ"); // ������Ȳ ��ư

	square_char(13, 2, 63, 32, "ó������"); // �ʱ�޴� ��ư


	sales_button5_1(); // ��ư ����
}

void sales_status6() // ������Ȳ �����ֱ�(��)
{
	PCsum = 0;
	FDsum = 0;
	FILE* file = fopen("Order.txt", "r");
	// order_id, total, what�� �����, fscanf�� �̾Ƴ���
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
	square(60, 35, 20, 1); // ū Ʋ

	square(50, 24, 25, 3); // ���� Ʋ


	square_char(46, 2, 27, 5, "    ��   ��  ������Ȳ(PC)");
	goto_xy(39, 6);
	//printf("1234");
	printf("%d", temp1);
	goto_xy(46, 6);
	printf("%d", temp2);
	//printf("3");

	goto_xy(42, 11);
	printf("����������������������������������");

	goto_xy(39, 11);
	printf("��  ");

	goto_xy(44, 10);
	printf("%d��", PCsum);

	square_char(46, 2, 27, 14, "    ��  ��  ������Ȳ(����)");
	goto_xy(38, 15);
	//printf("1234");
	printf("%d", temp1);
	goto_xy(45, 15);
	printf("%d", temp2);
	//printf("4");

	goto_xy(42, 20);
	printf("����������������������������������");

	goto_xy(39, 20);
	printf("��  ");

	goto_xy(44, 19);
	printf("%d��", FDsum);

	goto_xy(28, 25);
	printf("�� �հ� : %d��", FDsum + PCsum);


	// square_char(11, 2, 62, 24, "����"); // ���� ��ư

	square_char(13, 2, 24, 32, "������Ȳ"); // ������Ȳ ��ư

	square_char(13, 2, 63, 32, "ó������"); // �ʱ�޴� ��ư



	sales_button5_1();

}

void sales_status7() // ������Ȳ �����ֱ�(��)
{
	PCsum = 0;
	FDsum = 0;
	FILE* file = fopen("Order.txt", "r");
	// order_id, total, what�� ����Ҳ��� fscanf�� �� �̾Ƴ��� �ϴ�
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
	square(60, 35, 20, 1); // ū Ʋ

	square(50, 24, 25, 3); // ���� Ʋ


	square_char(46, 2, 27, 5, "    ��  ��   �� ������Ȳ(PC)");
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
	printf("����������������������������������");

	goto_xy(39, 11);
	printf("��  ");

	goto_xy(44, 10);
	printf("%d��", PCsum);
	square_char(46, 2, 27, 14, "    ��  ��   �� ������Ȳ(����)");
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
	printf("����������������������������������");

	goto_xy(39, 20);
	printf("��  ");

	goto_xy(44, 19);
	printf("%d��", FDsum);

	goto_xy(28, 25);
	printf("�� �հ� : %d��", FDsum + PCsum);

	square_char(13, 2, 24, 32, "������Ȳ"); // ������Ȳ ��ư
	square_char(13, 2, 63, 32, "ó������"); // ó������ ��ư

	sales_button5_1(); // ��ư ����
}

void order_status1() // �ֹ���Ȳ
{
	system("cls");
	init(&Q); // ť �ʱ�ȭ
	square(60, 35, 20, 1); // ū Ʋ

	square(50, 24, 25, 4); // �ֹ���Ȳ ȭ�� �ڽ�

	goto_xy(25, 6);
	printf("��");
	for (int j = 0; j < 48; j++)
	{
		printf("��");
	}
	printf("��");
	//��
	//��

	goto_xy(27, 5);
	printf("����");

	goto_xy(40, 5);
	printf("PC��ȣ");

	goto_xy(62, 5);
	printf("�ֹ�����");


	for (int i = 0; i < 7; i++)
	{
		goto_xy(28, 8 + (i * 3));
		printf("%d", (i + 1) + (order_page *7));

		square_char(14, 2, 59, 7 + (3 * i), "�� ����");
	}
	

	FILE* file = fopen("Order.txt", "r");	

	int j = 0;
	int kk = 0;
	while (fscanf(file, "%s %s %d %s %d %s %d", admin_order.Order_id, admin_order.id, &admin_order.total, admin_order.pay, &admin_order.pc_num, admin_order.what, &admin_order.state) == 7)
	{
		if (strcmp(admin_order.what, "FD") == 0) // �ֹ������� FD(����)�� �͵��� ���� �̾Ƴ�
		{
			if (admin_order.state == 0) // �ֹ� ���� ���� �͵��� �̾Ƴ�
			{
				enqueue(&Q, admin_order.Order_id); // �´� �ֹ����̵� ť�� �ϳ��� ����ֱ�

				order_pc_num[kk] = admin_order.pc_num;// �ϳ��� PC��ȣ���� 7���� �����ϱ�
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
			printf("%d", order_pc_num[j+ (order_page*7)]); // PC��ȣ ���ϴ� ��, ���� ������ �̿��ؼ� �ؾ���
			j++;			

			if ((j) % 7 == 0)
			{

				break;
			}

		}
		
	}
	square_char(9, 2, 57, 29, "����");
	square_char(9, 2, 67, 29, "����");

	square_char(14, 2, 24, 29, "���� ȭ��");
	fclose(file2);
	goto_xy(0, 0);
	int get = (order_button() - 1) + (order_page * 7); // ��ư ����
}

void order_status2()
{
	k = 0;
	system("cls");
	int i = 0;
	int menu_num;

	int all_price = 0; // �� ����

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 2, 25, 6); // ����ī�װ� ���� ȭ��
	goto_xy(29, 7);
	printf("���� ������ �Ϸ�Ǹ� �ֹ��Ϸ�� �����ּ���.");

	square(50, 12, 25, 12); // �ֹ����� ȭ�� �ڽ�

	goto_xy(25, 14);
	printf("��");
	for (int j = 0; j < 48; j++)
	{
		printf("��");
	}
	printf("��");

	goto_xy(29, 13);
	printf("����");

	goto_xy(50, 13);
	printf("����");

	goto_xy(65, 13);
	printf("�ݾ�");

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
			printf("%d��", (admin_menu.price) * admin_basket[j].count);
			all_price += (admin_menu.price) * admin_basket[j].count;
			fseek(file, 0, SEEK_SET);
			j++;
		}

		
	}
	fclose(file);
	goto_xy(60, 27);
	printf("�� �ݾ� : %d��", all_price);

	square_char(15, 2, 27, 32, "�ֹ��Ϸ�");

	square_char(15, 2, 43, 32, "�ֹ����");

	square_char(15, 2, 59, 32, "��������");

	char key = getch();
	flag = 0;
	element queue_peek = peek(&Q);
	goto_xy(0, 0);
	printf("%s", queue_peek);
	
	getch();
	if (strcmp(queue_peek, queue_order.Order_id) != 0)
	{
		flag = 2; // 1�� ��ĥ ���� �ֱ� ����
	}

	if (key == 75 || key == 77 || key == -32)
	{
		quit_button3();
	}

	goto_xy(0, 10); //Ŀ������

	_getch();
}

void modify_state_File(char* fileName, char* search_order_id, int state)
{

	FILE* file = NULL;
	file = fopen(fileName, "r");
	FILE* tempFile = fopen("temp.txt", "w"); // �ӽ�����

	if (file == NULL || tempFile == NULL) {
		printf("File opening failed.\n");
		exit(EXIT_FAILURE);
	}
   
	while (fscanf(file, "%s %s %d %s %d %s %d", admin_check_order.Order_id, admin_check_order.id, &admin_check_order.total
		, admin_check_order.pay, &admin_check_order.pc_num, admin_check_order.what, &admin_check_order.state) == 7)
	{	// admin_basket[0].Order_id �� 0~4���� �� ���� order_id�̱� ������ �������
		if (strcmp(search_order_id, admin_check_order.Order_id) == 0)
		{
			admin_check_order.state = state; // �ϴ� �ֹ��Ϸ�� �س��� ���߿� ���̽��� ������
			fprintf(tempFile, "%s %s %d %s %d %s %d\n", admin_check_order.Order_id, admin_check_order.id, admin_check_order.total
				, admin_check_order.pay, admin_check_order.pc_num, admin_check_order.what, admin_check_order.state);
		}
		else // �ش� �ֹ����̵� �ƴҰ�쿡�� ������ �״�� tempFile�� ���
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
		// �˻� ���ڿ��� ���ο� ���ԵǾ� �ִ��� Ȯ��
		fprintf(file, "%s", line);
	}
	fclose(tempFile);
	fclose(file);
}

void order_status3() // �ֹ� �Ϸ�
{
	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ������� ȭ�� �ڽ�
	goto_xy(39, 18);
	printf("�ֹ��Ϸ� ó���Ǿ����ϴ�.");
	Sleep(2000);

	order_status1();
}

void order_status4() // �ֹ� ���
{
	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ������� ȭ�� �ڽ�
	goto_xy(39, 18);
	printf("�ֹ���� ó���Ǿ����ϴ�.");
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
		// �˻� ���ڿ��� ���ο� ���ԵǾ� �ִ��� Ȯ��
		if (strcmp(te_user.id, search_id) == 0) {
			// �ش� ���̵� ã�Ƽ� ������ �ǳʶٰ� �����ϴ� ��� �Ʒ��� ���� ����
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
		// �˻� ���ڿ��� ���ο� ���ԵǾ� �ִ��� Ȯ��
		fprintf(file, "%s", line);
	}

	fclose(tempFile);
	fclose(file);
}

void change_second_1() // ������ ������ �ð��� �����ϱ� ���� ����ü�� ����
{
	free_add_clock.second = (free_add_clock.hour * 3600) + (free_add_clock.minute * 60) + free_add_clock.second;
}

void free_add_time1() // ������ - �ð��߰�
{
	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ������� ȭ�� �ڽ�
	goto_xy(36, 15);
	printf("�߰��Ͻ� �ð��� �������ּ���");

	goto_xy(45, 20);
	printf("������������");
	goto_xy(52, 19);
	printf("��");

	goto_xy(47, 19);
	scanf("%d", &free_add_clock.minute);
	change_second_1();
	printf("%s", admin_account[save_num].id);
	modify_minute_File("login.txt", admin_account[save_num].id);
	
	free_add_time2();

}

void free_add_time2() // ������ - �ð��߰��Ϸ� ǥ��
{
	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ���� Ʋ
	goto_xy(39, 18);
	printf("�ð��� �߰��Ǿ����ϴ�.");
	Sleep(2000);

	admin_main1();
}