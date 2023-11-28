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

char what[4] = "0"; // ������ �ֹ��ߴ���, PC��� �ֹ��ߴ���, ������ �ֹ��ߴ����� �˱����� ��, PC, FD�� ����
char temp[4] = "0";
int p = 0;
char pay[2][20] = { "����", "ī��" };

int num_id = 1;

int k = 0; // ���� �迭 �� �� �ְ� �ݺ��� ���� ���� �뵵
int num[5] = { 0 }; // ���� ���� �� �� ���״��� �� �� �ִ� �迭
// �̰� �״�� ����
char cat_food_menu1[7][20] = { "��  ��", "����", "���ڷ�", "�����", "��  Ÿ", "���", "��ٱ���" };

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
		// �˻� ���ڿ��� ���ο� ���ԵǾ� �ִ��� Ȯ��
		if (strcmp(te_user.id, search_id) == 0) {
			// �ش� ���̵� ã�Ƽ� ������ �ǳʶٰ� �����ϴ� ��� �Ʒ��� ���� ����
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
		// �˻� ���ڿ��� ���ο� ���ԵǾ� �ִ��� Ȯ��
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
		// �˻� ���ڿ��� ���ο� ���ԵǾ� �ִ��� Ȯ��
		//if (strstr(line, search_id) != NULL) 
		if(strcmp(te_user.id, search_id) == 0)
		{
			// �ش� ���̵� ã�Ƽ� ������ �ǳʶٰ� �����ϴ� ��� �Ʒ��� ���� ����
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
		// �˻� ���ڿ��� ���ο� ���ԵǾ� �ִ��� Ȯ��
		fprintf(file, "%s", line);
	}

	fclose(tempFile);
	fclose(file);
}

change_sec change_clock(int second) // �ð� �� ��ȯ
{
	change_sec clock;
	clock.hour = second / 3600; // ���� �ð�(�ð�)
	clock.minute = (second % 3600) / 60;
	clock.second = (second % 3600) % 60;

	return clock;
}

void findLinesWithLanguage(const char* file_path, const char* target_language) // �ð� Ȥ�� ���� �޴��� ������� ���
{
	int i = 0;
	FILE* file = fopen(file_path, "r");

	if (file == NULL) 
	{
		printf("���� ���� ����!!: %s\n", file_path);
		return;
	}
	while (!feof(file))
	{

		fscanf(file, "%s %s %s %d", in_menu[i].Menu_id, in_menu[i].sort,
			in_menu[i].Name, &in_menu[i].price);// �޴����̵�, ���� �̸� ������ �����

		// ��ҹ��� ���� ���� ��
		if (strstr(in_menu[i].Menu_id, target_language) != NULL) // �ش� ���ڸ� ã�Ҵٸ�?
		{
			// �״�� ���α�
		}
		else
		{
			strcpy(in_menu[i].Menu_id, "0");
			strcpy(in_menu[i].Name, "0");
			strcpy(in_menu[i].sort, "0");
			in_menu[i].price = 0;
			i--; // ���ʴ�� ���� �ޱ� ���ؼ�
		}

		
		i++;// �迭�� ��� ���ؼ� 1�� ������
		

	}
	fclose(file);
}

void change_second() // ������ ������ �ð��� �����ϱ� ���� ����ü�� ����
{
	in_user.second = (test_clock.hour * 3600) + (test_clock.minute * 60) + test_clock.second;
}

void make_order_id(const char* file_path) // �ֹ������� ������ִ� �Լ�(�ð��޴�����(�ƴҼ��� ���� �غ��� �� �� ����)/�޴����̵�����)
{
	
	int O_id = 0;
	FILE* file = fopen(file_path, "r");

	if (file == NULL)
	{
		printf("���� ���� ����!!: %s\n", file_path);
		return;
	}

	fseek(file, 0, SEEK_END); // ���� ������ �̵�
	if (ftell(file) != 0) // ���Ͽ� ���� ������
	{
		fseek(file, 0, SEEK_SET);
		while (!feof(file))		// ���Ͽ��� �о�� ������ ����Ͽ� �۾� ����
		// �� �������� ���� ����ü�� �ҷ���
		{

			fscanf(file, "%s %s %d %s %d %s %d", before_order.Order_id, before_order.id, &before_order.total, before_order.pay, &before_order.pc_num, temp, &before_order.state);
			
			//printf("%s %s %d %s\n", before_order.Order_id, before_order.id, before_order.total, before_order.pay);
			//getch();
		}
		// ù ��° ȣ��: '_'�� �������� ���ڿ��� ��ū���� �и�
		char* token = strtok(before_order.Order_id, "_");
		if (token != NULL)
		{
			strcpy(before_dateString, token);
			//printf("1�� ��ū�и��� ��� : %s\n", before_dateString);
			//_getch();
			// �� ��° ��ū �������� (ù ��° ��ū�� ����)
			token = strtok(NULL, "_");
		}
		


		// �� ��° ��ū�� �����ϴ� ��쿡�� ó��

		if (token != NULL) {
			// ������ ��ȯ�Ͽ� ���
			O_id = atoi(token);
			//printf("2�� ��ū�и��� ��� : %d", O_id);
			//_getch();

		}
		else {

		}

	}
	fclose(file);

	

	FILE* file1 = fopen(file_path, "a");

	if (file1 == NULL)
	{
		printf("���� ���� ����!!: %s\n", file_path);
		return;
	}
	//_getch();
	char str_id[100];
	char under_bar[2] = "_";

	time_t timer;
	struct tm* t;
	timer = time(NULL); // 1970�� 1�� 1�� 0�� 0�� 0�ʺ��� �����Ͽ� ��������� ��
	t = localtime(&timer); // �������� ���� ����ü�� �ֱ�	

	strftime(dateString, 9, "%Y%m%d", t); // ���� ��¥�� �о��

	if (strcmp(before_dateString, dateString) == 0) // �ֱٿ� ��ȸ�� ��¥��, ���� ��ȸ�� ��¥�� ������
	{
		num_id = O_id + 1; // �� �� �ֹ��Ѱſ� +1�ؼ� �־��
		itoa(num_id, str_id, 10); // ��¥�� ���ϴ� ������ ���ڿ��� ��ȯ
		strcpy(order.Order_id, dateString);
		strcat(order.Order_id, under_bar);
		strcat(order.Order_id, str_id); // �ֹ����̵� �״�� ����� num_id�� 1 ������ ���� ������ �ȴ�.

		strcpy(order.id, in_user.id); // �ش� ����� ���̵�
		order.total = all_price; // ��ٱ��� �� ����
		strcpy(order.pay, pay[p]); // �������
	}

	else // ���� ��¥�� �ٸ��ٸ�
	{
		num_id = 1; // �ٽ� ���̵� �ʱ�ȭ
		itoa(num_id, str_id, 10);
		// ��¥�� ���ϴ� ������ ���ڿ��� ��ȯ

		strcpy(order.Order_id, dateString);
		strcat(order.Order_id, under_bar);
		strcat(order.Order_id, str_id); // �ֹ����̵� �״�� ����� num_id�� 1 ������ ���� ������ �ȴ�.

		strcpy(order.id, in_user.id); // �ش� ����� ���̵�
		order.total = all_price; // ��ٱ��� �� ����
		strcpy(order.pay, pay[p]); // �������
	}

	//printf("%s %s %d %s\n", order.Order_id, order.id, order.total, order.pay);
	//_getch();
	order.state = 0;
	fprintf(file1, "%s %s %d %s %d %s %d\n", order.Order_id, order.id, order.total, order.pay, number, what, order.state); // PC�� ��ȣ����
	pp++; // �ٸ��ֹ� Ȯ��
	num_id++;
	fclose(file1);


	// ť �κ�
	init(&Q);

	FILE* file2 = fopen(file_path, "r");

	if (file2 == NULL)
	{
		printf("���� ���� ����!!: %s\n", file_path);
		return;
	}
	
	while (fscanf(file2, "%s %s %d %s %d %s %d", order.Order_id, order.id, &order.total, order.pay, &number, what, &order.state) == 7)
	{
		if (strcmp(what, "FD") == 0 && order.state == 0)
		{
			enqueue(&Q, order.Order_id); // ť �κп� �ֹ� ���̵� �־���
		}
	}
	
	fclose(file2);

}

void make_order_datail(const char* file_path) // �ֹ����� �ȿ� �ֹ��󼼳����� ���Ͽ� �Է��� �� �ְ� ����
{
	pp = 0;

	int kk = 0;
	
	// ���⼭ �ֹ��󼼳����� �̸� �۾��س��ƾߵ� �� ����..
	for (int i = 0; i < 5; i++)
	{
		strcpy(order_detail[i].Order_id, order.Order_id); // �ֹ����̵� �󼼳����� ����
	}
	
	for (int j = 0; j < 5; j++)
	{
		for (int i = 0; i < 50; i++)
		{
			if (strcmp(in_menu[i].Name, basket_Menu[j].name_food) == 0)
			{
				strcpy(order_detail[kk].Menu_id, in_menu[i].Menu_id); // �޴����̵� ����
				order_detail[kk].count = basket_Menu[j].num_food; // �ش� ���� ������ ���� 
				kk++;
			}
		}
		
	}
	

	FILE* file = fopen(file_path, "a");

	if (file == NULL)
	{
		printf("���� ���� ����!!: %s\n", file_path);
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
	// Ȯ�ο�

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

	square(60, 35, 20, 1); // ū Ʋ
	square(50, 15, 25, 3); // ���� Ʋ

	goto_xy(33, 7);
	printf("NO.%d", number); // pc ��ȣ

	goto_xy(33, 9);
	printf("���ݾ�: %d ��", in_user.money); // ���ݾ�

	square_char(18, 2, 41, 21, "�ð� �߰�"); // �ð��߰� ��ư

	square_char(18, 2, 41, 25, "�԰Ÿ� �ֹ�"); // �԰Ÿ� �ֹ� ��ư

	square_char(18, 2, 41, 29, "��� ����"); // ��� ���� ��ư


	test_clock = change_clock(in_user.second);

	if (in_user.second == 0) // ó���� ���Դµ� �ð��� ���°�쿡�� �ð��� ���� �� �ְ� 1�������� �ð��� �ش�.
	{
		test_clock.minute = 1;
		goto_xy(27, 34);
		printf("���� �ð��� �����ϴ�. 1�оȿ� �ð��� �־��ּ���.");
	}

	while (1)
	{
		goto_xy(33, 11);
		printf("���ð�: %02d:%02d:%02d", hour, minute, second); // ���ð�

		goto_xy(33, 13);
		printf("�����ð�: %02d:%02d:%02d", test_clock.hour, test_clock.minute, test_clock.second);
		goto_xy(0, 5);
		// Ŀ������ 


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
					// �ð� �ى�ٰ� �����帰 ��
					in_user.money = 0; // �ش� ���� �ݾ׵� �ʱ�ȭ
					change_second(); // �ش� ��,��,�ʸ� �ٽ� �ʷ� ��ȯ�Ͽ� in_user.second�� ����
					modify_num_File("login.txt", in_user.id);
					quit_pc2(number);
					//�α��� ȭ��
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

void add_time1() // ������� (���ٷ��� �ߴµ� �ٽ� �츱����)
{
	int i = 0;;
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 2, 25, 3); // ������� ȭ�� �ڽ�
	goto_xy(34, 4);
	printf("���Ͻô� ��������� �������ּ���.");

	square_char(22, 5, 39, 13, "���� ����"); // ī����� ��ư
	square_char(22, 5, 39, 20, "ī�� ����"); // ���ݰ��� ��ư

	p = pay_button()-1;
	strcpy(what, "PC"); // PC��� ���
	make_order_id("Order.txt"); // �ֹ�����������ִ� �Լ�
	pp = 0; // �ٽ� ������

	//_getch();
	add_time3();
}

void add_time2() // ���ݸ޴�
{
	int i = 0;
	int j = 0;
	int pc_price;
	findLinesWithLanguage("Menu.txt", "PC");
	system("cls");
	square(60, 35, 20, 1); // ū Ʋ

	square(50, 2, 25, 3); // ������� ȭ�� �ڽ�
	goto_xy(36, 4);
	printf("���Ͻô� �ð��� �������ּ���.");

	for (j = 0; j < 8; j++)
	{
		if (j < 2)
		{
			square(17, 5, 31 + (19 * j), 7);
			goto_xy(37 + (19 * j), 9);
			// printf("%s", time_money[j]);
			printf("%d ��", in_menu[j].price);
			goto_xy(37 + (19 * j), 10);
			printf("(0%s:00)", in_menu[j].Name);
		}
		else if (j >= 2 && j < 4)
		{
			square(17, 5, 31 + (19 * (j - 2)), 7 + 6);
			goto_xy(37 + (19 * (j - 2)), 9 + 6);
			// printf("%s", time_money[j]);
			printf("%d ��", in_menu[j].price);
			goto_xy(37 + (19 * (j - 2)), 10 + 6);
			printf("(0%s:00)", in_menu[j].Name);
		}
		else if (j >= 4 && j < 6)
		{
			square(17, 5, 31 + (19 * (j - 4)), 7 + 12);
			goto_xy(37 + (19 * (j - 4)), 9 + 12);
			// printf("%s", time_money[j]);
			printf("%d ��", in_menu[j].price);
			goto_xy(37 + (19 * (j - 4)), 10 + 12);
			printf("(0%s:00)", in_menu[j].Name);
		}
		else if (j >= 6 && j < 8)
		{
			square(17, 5, 31 + (19 * (j - 6)), 7 + 18);
			goto_xy(37 + (19 * (j - 6)), 9 + 18);
			// printf("%s", time_money[j]);
			printf("%d ��", in_menu[j].price);
			goto_xy(37 + (19 * (j - 6)), 10 + 18);
			printf("(0%s:00)", in_menu[j].Name);
		}
	}
	pc_price = time_button();
	in_user.money += pc_price;  // �̰� ���� ������ �� ���� �ٲ�� �� ��
	all_price = pc_price; // �ֹ������� PC��� �� ���� �ֱ� ����
	add_time1(); // ���� ������� �̵�

}

void add_time3() // �ŷ��Ϸ�
{
	system("cls");

	modify_num_File("login.txt", in_user.id); // ���� �ֽ�ȭ

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ������� ȭ�� �ڽ�
	goto_xy(40, 18);
	printf("�ŷ��� �Ϸ�Ǿ����ϴ�.");
	Sleep(2000);
	for (int i = 0; i < 50; i++)
	{
		strcpy(in_menu[i].Menu_id, "0");
		strcpy(in_menu[i].Name, "0");
		strcpy(in_menu[i].sort, "0");
		in_menu[i].price = 0;
	}
	all_price = 0; // ��ٱ����� �� ���Ե� ���� ����
	user_main();
}

void food_menu1() // ���ĸ޴�(ī�װ�)
{
	system("cls");
	int i = 0;
	int menu_num; // �޴����� (-��)

	findLinesWithLanguage("Menu.txt", "FD");
	//getch();
	square(60, 35, 20, 1); // ū Ʋ

	square(50, 2, 25, 3); // ����ī�װ� ���� ȭ��
	goto_xy(36, 4);
	printf("���Ͻô� ������ �������ּ���.");



	for (i = 0; i < 5; i++)
	{
		square_char(22, 4, 39, 7 + (5 * i), cat_food_menu1[i]); // ����ü �̿��ؼ� ī�װ� ����
	}

	square_char(15, 2, 62, 32, cat_food_menu1[5]);
	square_char(15, 2, 23, 32, cat_food_menu1[6]);

	menu_num = cat_menu_button(); 

	food_menu2(menu_num);
}

void food_menu2(int number) // ���ĸ޴�(�����̸�)
{
	int i = 0;
	int j = 0;
	//k = 0;
	int name_menu_number;
	square(60, 35, 20, 1); // ū Ʋ
	time_t timer;
	struct tm* t;
	timer = time(NULL); // 1970�� 1�� 1�� 0�� 0�� 0�ʺ��� �����Ͽ� ��������� ��
	t = localtime(&timer); // �������� ���� ����ü�� �ֱ�

	
	for (i = 0; i < 8; i++)
	{
		square(33, 2, 33, 5 + (3 * i)); // ���� �޴� ��ư
	}
	for (i = (number - 1) * 8; i < (8 * number); i++)
	{

		goto_xy(35, 6 + (3 * j));
		//printf("%d", i);  Ȯ�ο�
		printf("%s", in_menu[i].Name); // �����̸� ����ü ���� ����
		goto_xy(58, 6 + (3 * j));
		printf("%d��", in_menu[i].price); // ���İ��� ����ü ���� ����
		j++;
	}
	j = 0;

	name_menu_number = name_menu_button(number) - 1; // ���� ���� �� ��ȣ(1~8)-1 = (0~7)

	//Ȯ�ο�
	//goto_xy(0, 30);
	//printf("%d", name_menu_number);

	goto_xy(38, 30);
	printf("�� ���� �ֹ��Ͻðڽ��ϱ�?");
	goto_xy(46, 32);
	printf("�� ");
	scanf("%d", &num[k]);

	strcpy(basket_Menu[k].Cat_food, in_menu[name_menu_number + ((number - 1) * 8)].sort); // ���� ���� ����(��ٱ���)
	strcpy(basket_Menu[k].name_food, in_menu[name_menu_number + ((number - 1) * 8)].Name); // �����̸� ����(��ٱ���)
	basket_Menu[k].price = in_menu[name_menu_number + ((number - 1) * 8)].price; // ���� ���� ����(��ٱ���)

	basket_Menu[k].num_food = num[k]; // ���� ��Ų ������ ����!! (��ٱ���)

	// ��ٱ��� ���� �� ������ Ȯ�ο� �ڵ�
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

		square(60, 35, 20, 1); // ū Ʋ

		square(50, 12, 25, 12); // ���ſϷ� ȭ�� �ڽ�
		goto_xy(39, 17);
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 12);
		printf("��ٱ��ϰ� �� á���ϴ�.");
		goto_xy(36, 18);
		printf("�ֹ��Ϸ� �� �ٽ� �������ּ���.");
		SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
		goto_xy(0, 0); // Ŀ������
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

	all_price = 0; // �� ����

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 2, 25, 6); // ����ī�װ� ���� ȭ��
	goto_xy(28, 7);
	printf("�ش��ϴ� ������ �´ٸ� �ֹ��ϱ⸦ �����ּ���.");

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

	for (int j = 0; j < 5; j++) // �ֹ� ������ 5���� ������Ű�°� ������
	{


		//char compare[10] = "0";
		if (basket_Menu[j].num_food == 0) // 5�� ���ʴ�� ���ٰ� ������ 0���ΰ� �߰��ϸ� ������������
		{
			break;
		}

		goto_xy(28, 15 + (2 * j));
		printf("%s", basket_Menu[j].name_food);
		goto_xy(51, 15 + (2 * j));
		printf("%d", basket_Menu[j].num_food);
		goto_xy(64, 15 + (2 * j));
		printf("%d��", (basket_Menu[j].price) * basket_Menu[j].num_food);

		all_price += (basket_Menu[j].price * basket_Menu[j].num_food);
		basket_count++; // �̰� ��ٱ��Ͽ� ��� ���� ������ �������� �̰� �´�
	}
	goto_xy(60, 27);
	printf("�� �ݾ� : %d��", all_price);

	square_char(15, 2, 32, 32, "�ֹ�");

	square_char(15, 2, 53, 32, "���");

	char key = getch();

	if (key == 75 || key == 77 || key == -32)
	{
		quit_button4();
	}

	goto_xy(0, 10); //Ŀ������

	_getch();
}

void food_no_basket() // ��ٱ��Ͽ� ������ ���� ��
{
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ���ſϷ� ȭ�� �ڽ�
	goto_xy(35, 17);
	printf("��ٱ��Ͽ� �ֹ������� �����ϴ�.");
	goto_xy(38, 19);
	printf("�ٽ� �޴��� �������ּ���.");
	goto_xy(0, 0);
	Sleep(2000);

	food_basket();
}

void food_menu3_1() // ��ٱ��� ���
{
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ���ſϷ� ȭ�� �ڽ�
	goto_xy(39, 18);
	printf("��ٱ��Ͽ� �����߽��ϴ�.");
	goto_xy(0, 0); // Ŀ������
	Sleep(1000);
	food_menu1();

}

void food_menu3() // ���ſϷ�
{
	// Order_id, id, total(�����ݾ�), pay(�������)
	
	make_order_datail("Order_detail.txt");
	// ȭ�� �ʱ�ȭ
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 12, 25, 12); // ���ſϷ� ȭ�� �ڽ�
	goto_xy(40, 18);
	printf("���Ű� �Ϸ�Ǿ����ϴ�.");
	goto_xy(0, 0);
	Sleep(2000);

	for (int i = 0; i < 50; i++)
	{
		strcpy(in_menu[i].Menu_id, "0");
		strcpy(in_menu[i].Name, "0");
		strcpy(in_menu[i].sort, "0");
		in_menu[i].price = 0;
	}
	all_price = 0; // ��ٱ����� �� ���Ե� ���� ����

	for (int i = 0; i < 5; i++)
	{
		basket_Menu[i].num_food = 0;// ��ٱ��� ����ֱ�
	}
	k = 0; // k��° �迭�� ���ư� (ó������ ���ư�, �ƿ� �Ȼ�ɷ� �ٽ�)

	user_main();
}

void food_menu4() // ������� (���ٷ��� �ߴµ� �ٽ� �츱����) - ���⿡ �ִ� ����Լ��� ���� �Ⱦ��� ������ �̰ɷ� �����
{
	int i = 0;
	//int p = 0;
	system("cls");

	square(60, 35, 20, 1); // ū Ʋ

	square(50, 2, 25, 3); // ������� ȭ�� �ڽ�
	goto_xy(34, 4);
	printf("���Ͻô� ��������� �������ּ���.");

	square_char(22, 5, 39, 13, "���� ����"); // ī����� ��ư
	square_char(22, 5, 39, 20, "ī�� ����"); // ���ݰ��� ��ư

	p = pay_button() - 1;
	strcpy(what, "FD");
	make_order_id("Order.txt"); // �ֹ�����������ִ� �Լ�
	pp = 0; // �ٽ� ������

	//_getch();
	food_menu3();
}

void quit_pc() // ��� ����(�����)
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

	quit_button1();
}

void quit_pc2(int number) // pc�� ������ ��...����ڹ���
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

	mainlog();

}

