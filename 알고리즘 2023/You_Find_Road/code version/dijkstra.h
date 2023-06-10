#pragma once

void error(char str[]);

#define MAX_VTXS 256

typedef char VtxData;
// ������ �����͸� ��Ÿ���� Ÿ������, ����� char�� ����
int adj[MAX_VTXS][MAX_VTXS]; // ���� ��ķ�, ���� ���� ����ġ ������ ����
int vsize; // �׷����� ���� ������ ����
VtxData vdata[MAX_VTXS]; // ������ �����͸� �����ϴ� �迭

int is_empty_graph(); // �׷����� ����ִ��� Ȯ��
int is_full_graph(); // �׷����� ����á���� Ȯ��
void init_graph(); // �׷��� �ʱ�ȭ

void insert_vertex(VtxData name); // vertex ����
void insert_edge(int u, int v, int val); // ����ġ ����
void insert_edge2(int u, int v, int val);

#define INF 9999
void load_wgraph(char* filename);

int path[MAX_VTXS]; // ���
int dist[MAX_VTXS]; // �Ÿ�
int found[MAX_VTXS]; // 

int print_shortest_path(int start, int end); // ����, ��(vsize��ŭ)

int choose_vertex();

void shortest_path_dijkstra(int start);

