#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <windows.h>
#include <time.h> 
#include "dijkstra.h"
#include "menu.h"
#include "menu1.h"

#define _CRT_SECURE_NO_WARNINGS

void load_road_Region(char* filename);
void You_Find_Road();
int start_point();
int end_point();
int Transport();
void T_R_Result();
void save_recent_path(char start[], char finish[]);


void load_road_Region(char* filename)
{
	int i;
	char Reg[100];
	FILE* fp = fopen(filename, "r");
	if (fp != NULL)
	{
		//printf("���� ���� ����\n");
	}
	else
	{
		//printf("���� ���� ����\n");
		return; // ���� ���� ���� �� �Լ� ����
	}

	for (i = 0; i < 10; i++)
	{
		if (fgets(Reg, sizeof(Reg) + 1, fp) != NULL) // �� �پ� �б�
		{
			char* token = strtok(Reg, "("); // "(" ������ token���� ����
			if (token != NULL) // �о�������
				strncpy(R[i].region, token, sizeof(R[i].region)); // ���ڿ� ����

			token = strtok(NULL, ")");
			if (token != NULL)
				strncpy(R[i].nickname, token, sizeof(R[i].nickname));
		}
		else
		{
			break; // ������ ���� ������ ��� �ݺ��� ����
		}
	}
	fclose(fp); // ���� �ݱ�
}


void You_Find_Road()
{
	char* start;
	char* finish;

	load_wgraph("weight.txt");

	load_road_Region("Region.txt");
	printf("\n");
	start = start_point();

	finish = end_point();

	T_R_Result(start, finish);

}

int start_point()
{
	char* start_reg[5];
	int i;
	int start_pos = -1;

	gotoxy(42, 6);
	printf("            ����� : ");
	scanf("%s", start_reg);

	for (i = 0; i < vsize; i++)
	{
		if (strcmp(start_reg, R[i].region) == 0 || strcmp(start_reg, R[i].nickname) == 0)
		{
			start_pos = i;
			//printf("����� �� ��ġ : %d\n", start_pos);
		}
	}
	if (start_pos == -1)
	{

		gotoxy(60, 6);
		printf("                  ");


		gotoxy(40, 25);
		printf("�� ������ U_R �ʿ� �ش����� �ʴ� �����Դϴ�.");
		gotoxy(52, 26);
		printf("�ٽ� �Է����ֽʽÿ�.\n");
		start_point();
	}
	else
	{
		gotoxy(40, 25);
		printf("                                               ");
		gotoxy(52, 26);
		printf("                             ");

		return start_pos;
	}
}

int end_point()
{
	char* finish_reg[5];
	int i;
	int finish_pos = -1;
	

	gotoxy(42, 7);
	printf("            ������ : ");
	scanf("%s", finish_reg);

	for (i = 0; i < vsize; i++)
	{
		if (strcmp(finish_reg, R[i].region) == 0 || strcmp(finish_reg, R[i].nickname) == 0)
		{
			finish_pos = i;
		}
	}
	if (finish_pos == -1)
	{
		
		gotoxy(60, 7);
		printf("                  ");

		gotoxy(40, 25);
		printf("�� ������ U_R �ʿ� �ش����� �ʴ� �����Դϴ�.");
		gotoxy(52, 26);
		printf("�ٽ� �Է����ֽʽÿ�.\n");

		end_point();
	}
	else
	{
		gotoxy(40, 25);
		printf("                                               ");
		gotoxy(52, 26);
		printf("                             ");
		
		return finish_pos;
	}
}

int Transport()
{
	int num = 0;

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
	gotoxy(42, 14);
	printf(">> ");
	scanf("%d", &num);

	if (num == 1 || num == 2 || num == 3 || num == 4)
	{
		return num;
	}
	else
	{
		gotoxy(40, 25);
		printf("��ȣ�� �߸��Է��ϼ̽��ϴ�. �ٽ� �Է����ֽʽÿ�.");

		gotoxy(44, 14);
		printf("  ");
		gotoxy(44, 13);
		Transport();
		gotoxy(40, 25);
		printf("                                               "); 
	}
}

void T_R_Result(int start, int end)
{
	int Trans_num = Transport();
	char Trans[20] = "\0";
	int dist = print_shortest_path(start, end);

	time_t timer = time(NULL); // ����ð� ������ִ� �ð��Լ�
	struct tm* t = localtime(&timer);

	double minute = 0.6 * (double)dist * 1.0;// ������� �� ����� ���ϱ� ���ؼ�
	int hour = minute / 60;

	minute = minute - (hour * 60);

	// ������� �� ����� ���ϸ鼭 �Ÿ� ���
	if (Trans_num == 1)
	{
		strcpy(Trans, "����");
		hour *= 15;
		// ���⼭ ���� �Ÿ��� ��� �ɸ����� ���ϸ� �����ָ� ��
		// �ϴ� �׳� ���� �ڵ��� �������� 1.5km�� 1�� �ɸ��ٰ� ����
		// �������� �����ε� �ڵ����� �������� �ٸ��� ���̴°� ���� ���� �ִ�.
	}
	else if (Trans_num == 2)
	{
		strcpy(Trans, "�ڵ���");
		hour *= 1.0;
	}
	else if (Trans_num == 3)
	{
		strcpy(Trans, "���߱���");
		hour *= 1.5;
		// �������� 3������ ������� ��ó�� ������ ������ 10���� ���Ѵ�.
		// �ð� �� ���� ����� �н�
	}
	else if (Trans_num == 4)
	{
		strcpy(Trans, "������");
		hour *= 5;
	}

	gotoxy(42, 15);
	printf("========================================"); // ���â UI�� �Ű��ִ� Ŀ���Լ�
	gotoxy(42, 16);

	if (strcmp(R[start].nickname, " ") == 0) // �� ���� ����
	{
		if (strcmp(R[end].nickname, " ") == 0) // �� ���� ����
			printf("%s �� %s���� ", R[start].region, R[end].region);
		else // �� ���� ����
			printf("%s �� %s(%s)���� ", R[start].region, R[end].nickname, R[end].region);
	}
	else // �� ���� ����
	{
		if (strcmp(R[end].nickname, " ") == 0)   // �� ���� ����
			printf("%s(%s) �� %s���� ", R[start].nickname, R[start].region, R[end].region);
		else // �� ���� ����
			printf("%s(%s) �� %s(%s)���� ", R[start].nickname, R[start].region, R[end].nickname, R[end].region);
	}
	gotoxy(42, 17);
	printf("%s �̵� ��, �Ÿ� %dkm, �ҿ�ð� %d�ð� %.0lf��\n\n", Trans, dist, hour, minute);
	// minute�� ����� �� ������ �ٲ� ����

	int new_min = (t->tm_min) + (int)minute;
	int new_hour = ((t->tm_hour) + hour + (new_min / 60)) % 24;
	new_min = new_min % 60;

	gotoxy(42, 18);
	printf("���� �ð� : %d�� %d��, ���� �ð� : %d�� %d��", t->tm_hour, t->tm_min, new_hour, new_min);

	save_recent_path(start, end, hour, minute);

	_getch();

	return 0;
}

void save_recent_path(int start, int finish, int hour, double minute)
{
	FILE* fp = fopen("Recent Paths.txt", "a");
	if (fp != NULL)
	{
		fprintf(fp, "%s -> %s / �ҿ�ð� %d�ð� %.0lf��\n", R[start].region, R[finish].region, hour, minute);
		fclose(fp);
		gotoxy(42, 20);
		printf("�ֱ� ��ΰ� ���������� ����Ǿ����ϴ�.\n");
	}
	else
	{
		printf("������ �� �� �����ϴ�. �ֱ� ��θ� �������� ���߽��ϴ�.\n");
	}
}

void menu1()
{
	You_Find_Road();
}