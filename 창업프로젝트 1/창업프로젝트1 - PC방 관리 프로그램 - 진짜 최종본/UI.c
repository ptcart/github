#pragma once
#include"header.h"

void square(int width, int height, int x, int y)
{
    goto_xy(x, y);
    for (int i = 0; i < width; i++) {
        if (i == 0) printf("旨");
        else if (i == width - 1) printf("旬\n");
        else  printf("收");
    }
    goto_xy(x, y + 1);
    for (int j = 0; j < height; j++) {
        printf("早");
        for (int k = 0; k < width - 2; k++) {
            printf(" ");
        }
        printf("早\n");
        goto_xy(x, y + (j + 1));
    }
    for (int m = 0; m < width; m++) {
        if (m == 0) printf("曲");
        else if (m == width - 1) printf("旭\n");
        else printf("收");
    }
    goto_xy(0, 0);
}
void square3(int width, int height, int x, int y)
{
    goto_xy(x, y);
    for (int i = 0; i < width; i++) {
        if (i == 0) printf("曳");
        else if (i == width - 1) printf("此\n");
        else  printf("收");
    }
    goto_xy(x, y + 1);
    for (int j = 0; j < height; j++) {
        printf("早");
        for (int k = 0; k < width - 2; k++) {
            printf(" ");
        }
        printf("早\n");
        goto_xy(x, y + (j + 1));
    }
    for (int m = 0; m < width; m++) {
        if (m == 0) printf("曲");
        else if (m == width - 1) printf("旭\n");
        else printf("收");
    }
    goto_xy(0, 30);
}

void square4(int x, int y, int i)
{

        square(x, y, 21, 4 + (3 * i));
        goto_xy(27, 4 + (3 * i));
        printf("有");

        goto_xy(27, 5 + (3 * i));
        printf("早");

        goto_xy(27, 6 + (3 * i));
        printf("朴");

        goto_xy(39, 4 + (3 * i));
        printf("有");

        goto_xy(39, 5 + (3 * i));
        printf("早");

        goto_xy(39, 6 + (3 * i));
        printf("朴");

        goto_xy(54, 4 + (3 * i));
        printf("有");

        goto_xy(54, 5 + (3 * i));
        printf("早");

        goto_xy(54, 6 + (3 * i));
        printf("朴");
}


void char_color(int x, int y, char* msg, int color)
{
    goto_xy(x, y);
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), color);
    printf("%s", msg);
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
}

void square_color(int width, int height, int x, int y, int color)
{
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), color);
    square(width, height, x, y);
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
}

void square_color1(int x, int y, int i, int color)
{
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), color);
    square4(x, y, i);
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 7);
}

void square_int(int width, int height, int x, int y, int msg)
{
    int count = 0;
    int input;
    input = msg;
    while (input > 0)
    {
        input /= 10;
        count++;
    }

    goto_xy(x, y);
    for (int i = 0; i < width; i++) {
        if (i == 0) printf("旨");
        else if (i == width - 1) printf("旬\n");
        else  printf("收");
    }
    goto_xy(x, y + 1);
    for (int j = 0; j < height; j++) {
        printf("早");
        for (int k = 0; k < width - 2; k++) {
            if (j == height / 2 && k == ((width)-count) / 2) {
                goto_xy(x + ((width)-count) / 2 + 1, y + height / 2);
                printf("%d", msg);
                goto_xy(x + width - 1, y + j);
            }
            else if (j != height / 2) printf(" ");
        }

        printf("早");
        goto_xy(x, y + (j + 1));
    }
    for (int m = 0; m < width; m++) {
        if (m == 0) printf("曲");
        else if (m == width - 1) printf("旭\n");
        else printf("收");
    }
    goto_xy(0, 40);
}

void square_char_2(int width, int height, int x, int y, char* msg1, char* msg2)
{
    goto_xy(x, y);
    for (int i = 0; i < width; i++) {
        if (i == 0) printf("旨");
        else if (i == width - 1) printf("旬\n");
        else  printf("收");
    }
    goto_xy(x, y + 1);
    for (int j = 0; j < height; j++) {
        printf("早");
        for (int k = 0; k < width - 2; k++) {
            if (j == height / 2 && k == ((width)-strlen(msg1)) / 2) {
                goto_xy(x + ((width)-strlen(msg1)) / 2 + 1, y + height / 2);
                printf("%s", msg1);
                goto_xy(x + width - 1, y + j);
            }
            else if (j == height / 2 + 1 && k == ((width)-strlen(msg2)) / 2)
            {
                goto_xy(x + ((width)-strlen(msg1)) / 2, (y + 1) + (height / 2));
                printf("(%s)", msg2);
                goto_xy(x + width - 1, y + j);
            }

            else if (j != height / 2) printf(" ");
        }

        printf("早");
        goto_xy(x, y + (j + 1));
    }
    for (int m = 0; m < width; m++) {
        if (m == 0) printf("曲");
        else if (m == width - 1) printf("旭\n");
        else printf("收");
    }
    goto_xy(0, 40);
}


void square_char(int width, int height, int x, int y, char* msg)
{
    goto_xy(x, y);
    for (int i = 0; i < width; i++) {
        if (i == 0) printf("旨");
        else if (i == width - 1) printf("旬\n");
        else  printf("收");
    }
    goto_xy(x, y + 1);
    for (int j = 0; j < height; j++) {
        printf("早");
        for (int k = 0; k < width - 2; k++) {
            if (j == height / 2 && k == ((width)-strlen(msg)) / 2) {
                goto_xy(x + ((width)-strlen(msg)) / 2 + 1, y + height / 2);
                printf("%s", msg);
                goto_xy(x + width - 1, y + j);
            }
            else if (j != height / 2) printf(" ");
        }

        printf("早");
        goto_xy(x, y + (j + 1));
    }
    for (int m = 0; m < width; m++) {
        if (m == 0) printf("曲");
        else if (m == width - 1) printf("旭\n");
        else printf("收");
    }
    goto_xy(0, 40);
}
void square_char_red(int width, int height, int x, int y, char* msg)
{
    goto_xy(x, y);
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 12);
    for (int i = 0; i < width; i++) {
        if (i == 0) printf("旨");
        else if (i == width - 1) printf("旬\n");
        else  printf("收");
    }
    goto_xy(x, y + 1);
    for (int j = 0; j < height; j++) {
        printf("早");
        for (int k = 0; k < width - 2; k++) {
            if (j == height / 2 && k == ((width)-strlen(msg)) / 2) {
                goto_xy(x + ((width)-strlen(msg)) / 2 + 1, y + height / 2);
                SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 12);
                printf("%s", msg);
                //SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 12);
                goto_xy(x + width - 1, y + j);
            }
            else if (j != height / 2) printf(" ");
        }

        printf("早");
        goto_xy(x, y + (j + 1));
    }
    for (int m = 0; m < width; m++) {
        if (m == 0) printf("曲");
        else if (m == width - 1) printf("旭\n");
        else printf("收");
    }
    goto_xy(0, 40);
}
void square_char_blue(int width, int height, int x, int y, char* msg)
{
    goto_xy(x, y);
    for (int i = 0; i < width; i++) {
        if (i == 0) printf("旨");
        else if (i == width - 1) printf("旬\n");
        else  printf("收");
    }
    goto_xy(x, y + 1);
    for (int j = 0; j < height; j++) {
        printf("早");
        for (int k = 0; k < width - 2; k++) {
            if (j == height / 2 && k == ((width)-strlen(msg)) / 2) {
                goto_xy(x + ((width)-strlen(msg)) / 2 + 1, y + height / 2);
                SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 9);
                printf("%s", msg);
                SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 2);
                goto_xy(x + width - 1, y + j);
            }
            else if (j != height / 2) printf(" ");
        }

        printf("早");
        goto_xy(x, y + (j + 1));
    }
    for (int m = 0; m < width; m++) {
        if (m == 0) printf("曲");
        else if (m == width - 1) printf("旭\n");
        else printf("收");
    }
    goto_xy(0, 40);
}
void square_char1(int width, int height, int x, int y, char* msg) //曳
{
    goto_xy(x, y);
    for (int i = 0; i < width; i++) {
        if (i == 0) printf("曳");
        else if (i == width - 1) printf("旬\n");
        else  printf("收");
    }
    goto_xy(x, y + 1);
    for (int j = 0; j < height; j++) {
        printf("早");
        for (int k = 0; k < width - 2; k++) {
            if (j == height / 2 && k == ((width)-strlen(msg)) / 2) {
                goto_xy(x + ((width)-strlen(msg)) / 2 + j, y + height / 2);
                printf("%s", msg);
                goto_xy(x + width - 1, y + 1);
            }
            else if (j != height / 2) printf(" ");
        }

        printf("早");
        goto_xy(x, y + (j + 1));
    }
    for (int m = 0; m < width; m++) {
        if (m == 0) printf("曲");
        else if (m == width - 1) printf("旭\n");
        else printf("收");
    }
    goto_xy(0, 40);
}
void square_char2(int width, int height, int x, int y, char* msg) // 此
{
    goto_xy(x, y);
    for (int i = 0; i < width; i++) {
        if (i == 0) printf("旨");
        else if (i == width - 1) printf("此\n");
        else  printf("收");
    }
    goto_xy(x, y + 1);
    for (int j = 0; j < height; j++) {
        printf("早");
        for (int k = 0; k < width - 2; k++) {
            if (j == height / 2 && k == ((width)-strlen(msg)) / 2) {
                goto_xy(x + ((width)-strlen(msg)) / 2 + j, y + height / 2);
                printf("%s", msg);
                goto_xy(x + width - 1, y + 1);
            }
            else if (j != height / 2) printf(" ");
        }

        printf("早");
        goto_xy(x, y + (j + 1));
    }
    for (int m = 0; m < width; m++) {
        if (m == 0) printf("曲");
        else if (m == width - 1) printf("旭\n");
        else printf("收");
    }
    goto_xy(0, 40);
}
void square_char3(int width, int height, int x, int y, char* msg) //朽, 曳 謝鼻欽 辦鼻欽 曳  此
{
    {
        goto_xy(x, y);
        for (int i = 0; i < width; i++) {
            if (i == 0) printf("曳");
            else if (i == width - 1) printf("此\n");
            else  printf("收");
        }
        goto_xy(x, y + 1);
        for (int j = 0; j < height; j++) {
            printf("早");
            for (int k = 0; k < width - 2; k++) {
                if (j == height / 2 && k == ((width)-strlen(msg)) / 2) {
                    goto_xy(x + ((width)-strlen(msg)) / 2 + 1, y + height / 2);
                    printf("%s", msg);
                    goto_xy(x + width - 1, y + j);
                }
                else if (j != height / 2) printf(" ");
            }

            printf("早");
            goto_xy(x, y + (j + 1));
        }
        for (int m = 0; m < width; m++) {
            if (m == 0) printf("曲");
            else if (m == width - 1) printf("旭\n");
            else printf("收");
        }
        goto_xy(0, 40);
    }
}
void square_char3_gray(int width, int height, int x, int y, char* msg) //朽, 曳 謝鼻欽 辦鼻欽   曳  此
{
    goto_xy(x, y);
    for (int i = 0; i < width; i++) {
        if (i == 0) printf("曳");
        else if (i == width - 1) printf("此\n");
        else  printf("收");
    }
    goto_xy(x, y + 1);
    for (int j = 0; j < height; j++) {
        printf("早");
        for (int k = 0; k < width - 2; k++) {
            if (j == height / 2 && k == ((width)-strlen(msg)) / 2) {
                goto_xy(x + ((width)-strlen(msg)) / 2 + 1, y + height / 2);
                SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 8);
                printf("%s", msg);
                SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 2);
                goto_xy(x + width - 1, y + j);
            }
            else if (j != height / 2) printf(" ");
        }

        printf("早");
        goto_xy(x, y + (j + 1));
    }
    for (int m = 0; m < width; m++) {
        if (m == 0) printf("曲");
        else if (m == width - 1) printf("旭\n");
        else printf("收");
    }
    goto_xy(0, 40);
}
void square_char3_blue(int width, int height, int x, int y, char* msg) //朽, 曳 謝鼻欽 辦鼻欽  曳  此
{
    goto_xy(x, y);
    for (int i = 0; i < width; i++) {
        if (i == 0) printf("曳");
        else if (i == width - 1) printf("此\n");
        else  printf("收");
    }
    goto_xy(x, y + 1);
    for (int j = 0; j < height; j++) {
        printf("早");
        for (int k = 0; k < width - 2; k++) {
            if (j == height / 2 && k == ((width)-strlen(msg)) / 2) {
                goto_xy(x + ((width)-strlen(msg)) / 2 + 1, y + height / 2);
                SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 9);
                printf("%s", msg);
                SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 2);
                goto_xy(x + width - 1, y + j);
            }
            else if (j != height / 2) printf(" ");
        }

        printf("早");
        goto_xy(x, y + (j + 1));
    }
    for (int m = 0; m < width; m++) {
        if (m == 0) printf("曲");
        else if (m == width - 1) printf("旭\n");
        else printf("收");
    }
    goto_xy(0, 40);
}
void square_char3_red(int width, int height, int x, int y, char* msg) //朽, 曳 謝鼻欽 辦鼻欽
{
    goto_xy(x, y);
    for (int i = 0; i < width; i++) {
        if (i == 0) printf("曳");
        else if (i == width - 1) printf("此\n");
        else  printf("收");
    }
    goto_xy(x, y + 1);
    for (int j = 0; j < height; j++) {
        printf("早");
        for (int k = 0; k < width - 2; k++) {
            if (j == height / 2 && k == ((width)-strlen(msg)) / 2) {
                goto_xy(x + ((width)-strlen(msg)) / 2 + 1, y + height / 2);
                SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 12);
                printf("%s", msg);
                SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), 2);
                goto_xy(x + width - 1, y + j);
            }
            else if (j != height / 2) printf(" ");
        }

        printf("早");
        goto_xy(x, y + (j + 1));
    }
    for (int m = 0; m < width; m++) {
        if (m == 0) printf("曲");
        else if (m == width - 1) printf("旭\n");
        else printf("收");
    }
    goto_xy(0, 40);
}
void square_char4(int width, int height, int x, int y, char* msg) // 謝ж欽曳 此          曳   此
{
    goto_xy(x, y);
    for (int i = 0; i < width; i++) {
        if (i == 0) printf("旨");
        else if (i == width - 1) printf("旬\n");
        else  printf("收");
    }
    goto_xy(x, y + 1);
    for (int j = 0; j < height; j++) {
        printf("早");
        for (int k = 0; k < width - 2; k++) {
            if (j == height / 2 && k == ((width)-strlen(msg)) / 2) {
                goto_xy(x + ((width)-strlen(msg)) / 2 + 1, y + height / 2);
                printf("%s", msg);
                goto_xy(x + width - 1, y + j);
            }
            else if (j != height / 2) printf(" ");
        }

        printf("早");
        goto_xy(x, y + (j + 1));
    }
    for (int m = 0; m < width; m++) {
        if (m == 0) printf("曳");
        else if (m == width - 1) printf("此\n");
        else printf("收");
    }
    goto_xy(0, 40);
}