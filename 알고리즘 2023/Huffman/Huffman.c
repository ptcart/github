#include <stdio.h>
#include <stdlib.h>

#define MAX_CHAR 128

typedef struct Node 
{
    char character;
    int frequency;
    struct Node* left;
    struct Node* right;
} Node;

Node* createNode(char character, int frequency)
{
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->character = character;
    newNode->frequency = frequency;
    newNode->left = NULL;
    newNode->right = NULL;
    return newNode;
}

Node* buildHuffmanTree(int frequency[]) 
{
    // 빈도수에 따라 정렬된 노드 배열 생성
    Node* nodeList[MAX_CHAR] = { NULL };
    int numNodes = 0;
    for (int i = 0; i < MAX_CHAR; i++) {
        if (frequency[i] > 0) {
            nodeList[numNodes++] = createNode((char)i, frequency[i]);
        }
    }

    while (numNodes > 1) //만약에 numNodes = 3 이라고 가정한다면 
    { 
        // 최소 빈도수를 가진 두 노드 선택
        Node* minNode1 = nodeList[0];
        Node* minNode2 = nodeList[1]; 
        int minIndex1 = 0;
        int minIndex2 = 1;

        for (int i = 2; i < numNodes; i++) 
        { // 0번 째, 1번 째 제외한 2번 째부터 3번째까지 i++
            if (nodeList[i]->frequency < minNode1->frequency) 
            { // minNode1의 빈도수보다 nodeList[i]의 빈도수가 더 작으면
                minNode2 = minNode1; // 제일 작았던 노드는 두번 째로 밀려나고
                minIndex2 = minIndex1; // 인덱스값도 밀려나고
                minNode1 = nodeList[i]; // nodeList[i]를 minNode1에 대입(구조체단위)
                minIndex1 = i; // 첫 번 째 인덱스에 i를 넣어줌
            }
            else if (nodeList[i]->frequency < minNode2->frequency) 
            { // nodeList[i]가 minNode1 보단 큰데 minNode2 보단 작을경우
                minNode2 = nodeList[i]; // minNode에 nodeList[i]를 대입
                minIndex2 = i; // 두 번 째 인덱스에 i를 넣어줌
            }
        } // if, else if를 사용했기 때문에 minNode1 과 minNode2를 동시에 비교할 수 없게 되어있고 비교한다고 해도 
        // minNode1부터 비교하기 때문에 문제가 없다.

        // 새로운 노드 생성하여 두 노드 연결
        Node* newNode = createNode('\0', minNode1->frequency + minNode2->frequency);
        // minNode1을 왼쪽노드에, minnode2를 오른쪽노드에 연결(구조체 단위)
        newNode->left = minNode1;
        newNode->right = minNode2;

        // 배열에서 선택된 노드 제거하고 새로운 노드 추가
        nodeList[minIndex1] = newNode;
        nodeList[minIndex2] = nodeList[numNodes - 1]; // 끝에 있는 노드를 2번 째 인덱스에 당겨오고
        numNodes--; // 배열 중 맨 끝에 있는 배열 하나를 지운다.
    }

    // 트리의 루트 노드 반환
    return nodeList[0];
}

void encodeCharacter(Node* root, char character, char* code, int depth, FILE* outputFile) 
{  // chararcter값을 부호화 시켜주는 함수
    if (root == NULL) {
        return;
    }

    if (root->character == character) 
    { // 찾고 싶은 character값을 찾았다면
        code[depth] = '\0';  // 코드의 끝을 표시
        fprintf(outputFile, "%s", code);  // 코드를 파일에 출력
        return;
    }
    //못찾았다면
    code[depth] = '0'; // 왼쪽노드를 가면서 '0'을 대입한 후, depth + 1
    encodeCharacter(root->left, character, code, depth + 1, outputFile);

    code[depth] = '1'; // 오른쪽노드를 가면서 '0'을 대입한 후, depth + 1
    encodeCharacter(root->right, character, code, depth + 1, outputFile);
}
void encodeText(Node* root, char* text, FILE* outputFile)
{
    char code[MAX_CHAR];
    int i = 0;

    while (text[i] != '\0') 
    { // text[i]가 끝이 날 때까지 
        encodeCharacter(root, text[i], code, 0, outputFile); // 부호화 해놓은 것들을 파일에 모두 출력
        i++;
    }
}

Node* buildHuffmanTreeFromFile(const char* statsFileName) 
// 허프만 stats.txt파일을 저장해줄 함수
{
    FILE* statsFile = fopen(statsFileName, "r"); // 읽기 파일로 열기
    if (statsFile == NULL) {
        printf("빈도수 저장 파일을 열 수 없습니다.\n");
        return NULL;
    }

    // 빈도수 배열 초기화
    int frequency[MAX_CHAR] = { 0 };

    // 빈도수 파일로부터 빈도수 읽어오기
    char line[MAX_CHAR];
    while (fgets(line, sizeof(line), statsFile) != NULL) 
    { // line 배열값이 NULL이 안뜰 때동안
      // (중요)fgets : \n이 나오면 해당 문자열까지만 읽음
        char character; // 문자
        int freq; // 빈도수
        sscanf(line, "%c\t%d", &character, &freq);
        // %c\t%d 형식으로 character과 freq에 저장한다.
   
        frequency[character] = freq;
        /*  (중요)
            ASCII 코드에서 대문자 'A'의 값은 65이므로, frequency['A']와 frequency[65]는 동일한 의미를 갖습니다.

            즉, frequency 배열에서 ['A']는 65와 동일한 인덱스를 나타내며, 
            해당 인덱스에 저장된 값은 대문자 'A'의 빈도수를 나타냅니다. 
            마찬가지로 frequency['B']는 frequency[66]과 같은 의미이고, 
            frequency['C']는 frequency[67]과 같은 의미입니다.
        */
    }

    fclose(statsFile);

    // Huffman Tree 구성
    return buildHuffmanTree(frequency);
}

void decodeText(Node* root, const char* encodedFileName, const char* outputFileName)
// 암호를 해독 역할해주는 함수 (트리, 부호화한 파일 '이름', 해독할(압축해제할)파일 '이름')
{
    FILE* encodedFile = fopen(encodedFileName, "r"); // 부호화한 파일(읽기모드로) 열기
    if (encodedFile == NULL) {
        printf("인코딩된 파일을 열 수 없습니다.\n");
        return;
    }

    FILE* outputFile = fopen(outputFileName, "w"); // 해독할 파일(쓰기모드로) 열기
    if (outputFile == NULL) {
        printf("출력 파일을 열 수 없습니다.\n");
        fclose(encodedFile);
        return;
    }

    Node* currentNode = root;
    int bit;

    while ((bit = fgetc(encodedFile)) != EOF) 
    {  // 부호화한 파일을 한 문자씩 읽었을 때(그 값을 bit에 넣음), 파일이 끝이 나지 않을 동안
        if (bit == '0')  // bit값이 0이 나오면
        {
            currentNode = currentNode->left; // 왼쪽 자식으로 가봄
        }
        else if (bit == '1') // bit값이 1이 나오면 
        {
            currentNode = currentNode->right; // 오른쪽 자식으로 가봄
        }

        if (currentNode->left == NULL && currentNode->right == NULL) 
        { // 왼쪽 자식, 오른쪽 자식 둘 다 NULL일 경우
            fprintf(outputFile, "%c", currentNode->character); // 해당 노드의 문자를 출력함
            currentNode = root; // 다시 처음으로 감
        }
    }

    fclose(encodedFile);
    fclose(outputFile);
}

void freeHuffmanTree(Node* root)
{
    if (root == NULL) {
        return;
    }

    freeHuffmanTree(root->left);
    freeHuffmanTree(root->right);
    free(root); // 허프만 노드 생성~
}

void decodeHuffmanFile(const char* statsFileName, const char* encodedFileName, const char* outputFileName)
{   // 파일에 있는 암호를 통합해서 해독하는 함수(stats파일 이름, 부호화된 파일 이름, 해독할 파일 이름)
    // stats.txt 파일에서 Huffman Tree 재구성
    Node* root = buildHuffmanTreeFromFile(statsFileName);

    if (root == NULL) {
        printf("Huffman Tree를 재구성하는데 실패했습니다.\n");
        return;
    }

    // 인코딩된 파일 디코딩하여 결과를 output.txt에 저장
    decodeText(root, encodedFileName, outputFileName);

    // Huffman Tree 메모리 해제
    freeHuffmanTree(root);
}

int main()
{
    FILE* inputFile, * statsFile, * outputFile;  // outputFile 추가
    char ch;
    int frequency[MAX_CHAR] = { 0 };
    char code[MAX_CHAR] = { 0 };  // Huffman 코드를 저장하기 위한 배열

    // 입력 파일 열기
    inputFile = fopen("input.txt", "r");
    if (inputFile == NULL) {
        printf("입력 파일을 열 수 없습니다.\n");
        return 1;
    }

    // 빈도수 계산
    while ((ch = fgetc(inputFile)) != EOF) {
        if (ch >= 0 && ch < MAX_CHAR) {
            frequency[ch]++;
        }
    }

    // 입력 파일 닫기
    fclose(inputFile);

    // 빈도수 저장 파일 열기
    statsFile = fopen("stats.txt", "w");
    if (statsFile == NULL) {
        printf("빈도수 저장 파일을 열 수 없습니다.\n");
        return 1;
    }

    // 빈도수 저장
    for (int i = 0; i < MAX_CHAR; i++) {
        if (frequency[i] > 0) {
            fprintf(statsFile, "%c\t%d\n", (char)i, frequency[i]);
        }
    }

    // 빈도수 저장 파일 닫기
    fclose(statsFile);

    // Huffman Tree 구성
    Node* root = buildHuffmanTree(frequency);

    // Huffman 압축
    outputFile = fopen("output.huf", "wb");  // outputFile 열기

    if (outputFile == NULL) {
        printf("출력 파일을 열 수 없습니다.\n");
        return 1;
    }

    inputFile = fopen("input.txt", "r");

    if (inputFile == NULL) {
        printf("입력 파일을 열 수 없습니다.\n");
        fclose(outputFile);
        return 1;
    }

    // 입력 파일 압축하여 출력 파일에 저장
    while ((ch = fgetc(inputFile)) != EOF) {
        if (ch >= 0 && ch < MAX_CHAR) {
            encodeCharacter(root, ch, code, 0, outputFile);
        }
    }

    // 파일 닫기
    fclose(inputFile);
    fclose(outputFile);

    // Huffman 파일 디코딩하여 결과를 output.txt에 저장
    decodeHuffmanFile("stats.txt", "output.huf", "output.txt");

    // Huffman Tree 메모리 해제
    freeHuffmanTree(root);

    return 0;
}
