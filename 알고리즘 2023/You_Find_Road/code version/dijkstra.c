#include <stdio.h>
#include <stdlib.h>
#include "menu1.h"
#define MAX_VTXS 256

// ���ͽ�Ʈ�� �˰���

#define _CRT_SECURE_NO_WARNINGS

void error(char str[])
{ // ���� �޼����� ����ϰ� ���α׷��� �����ϴ� �Լ�
	printf("%s\n", str);
	exit(1);
}

typedef struct VtxData
{
	char name[50];
} VtxData;

// ������ �����͸� ��Ÿ���� Ÿ������, ����� char�� ����
int adj[MAX_VTXS][MAX_VTXS]; // ���� ��ķ�, ���� ���� ����ġ ������ ����
int vsize; // �׷����� ���� ������ ����
VtxData vdata[MAX_VTXS]; // ������ �����͸� �����ϴ� �迭

int is_empty_graph() // �׷����� ����ִ��� Ȯ��
{
	return (vsize == 0);
}

int is_full_graph() // �׷����� ����á���� Ȯ��
{
	return (vsize >= MAX_VTXS);
}

void init_graph() // �׷��� �ʱ�ȭ
{
	int i, j;
	vsize = 0;
	for (i = 0; i < MAX_VTXS; i++) 
	{
		for (j = 0; j < MAX_VTXS; j++)
		{
			adj[i][j] = 0;
		}
	}
}

void insert_vertex(VtxData name) // vertex ����
{
	if (is_full_graph())
	{
		error("Error: �׷��� ������ ���� �ʰ�");
	}
	else
		vdata[vsize++] = name;
	// ������ �����͸� vdata �迭�� �����ϰ�, 'vsize'���� ������Ŵ
}

void insert_edge(int u, int v, int val) // ����ġ ����
{
	adj[u][v] = val;
}

void insert_edge2(int u, int v, int val)
{
	adj[u][v] = adj[v][u] = val; // ���� �ݴ뵵 val������ �Ȱ���.
}

#define INF 9999
void load_wgraph(char* filename)
{
	int i, j, val, n;
	VtxData str[80];
	FILE* fp = fopen(filename, "r");
	if (fp != NULL)
	{
		init_graph(); // �׷����� �ʱ�ȭ
		fscanf(fp, "%d", &n); // ������ ù ��° �ٿ��� �׷����� ũ�� 'n'�� �ҷ���
		for (i = 0; i < n; i++)
		{
			fscanf(fp, "%s", str[i].name); // �� ��°���� ���ڿ��� str�� �޾ƿ�
			insert_vertex(str[i]); // �� ������ �����͸� insert_vertex�� ���� ����
			// str[i] ����ü�� insert_vertex �Լ��� ����
			for (j = 0; j < n; j++)
			{
				fscanf(fp, "%d", &val); // ���ڿ� ������ �ִ� ���ڸ� val�� ����
				if (i != j && val == 0) // val ���� 0�̰�, i, j�� ���� �ٸ��ٸ� 
				{
					adj[i][j] = INF;
				}
				else // �� �� �ϳ��� �ƴ϶��
				{
					adj[i][j] = val; // val ���� ����
				}
			}
		}
		fclose(fp); // ���� ����
	}
}

int path[MAX_VTXS]; // ���
int dist[MAX_VTXS]; // �Ÿ�
int found[MAX_VTXS];


void print_step(int step)
{
	int i;
	printf("\n");
	printf(" Step%2d:", step);
	for (i = 0; i < vsize; i++)
	{
		if (dist[i] == INF)
		{
			printf("  INF");
		}
		else
		{
			printf("%5d", dist[i]);
		}
	}
	printf("\n");
}

int choose_vertex()
{
	int i, min = INF, minpos = -1;

	for (i = 0; i < vsize; i++)
	{
		if (dist[i] < min && !found[i]) // �Ÿ��� ������������ �ִܰŸ��� �� �۰� ���õ��� ���� ���̸�
		{
			min = dist[i]; // dist[i](�ִܰŸ�)�� min�� ����
			//printf("%d ", dist[i]);
			minpos = i; // �� �� ° �������� minpos�� ����
		}
	}
	return minpos; // minpos�� ��ȯ
}

void shortest_path_dijkstra(int start)
{
	int i, u, w;
	for (i = 0; i < vsize; i++)
	{
		path[i] = -1; // -1�� �ʱ�ȭ�Ͽ� �̹湮 ���·� ǥ��
	}
	
	for (i = 0; i < vsize; i++) // �׷����� ������ŭ
	{
		dist[i] = adj[start][i]; // adj[0][i]�� dist[i]�� ����
		// ���� �������κ����� �ʱ� �Ÿ��� �����ϴ� �ܰ�
		path[i] = start; // �ִ� ��� ������ ���� �ʱ� ����
		found[i] = 0; // �ش� ������ ���� �湮���� �ʾҴٴ� ���� ��Ÿ��
	}
	found[start] = 1; // ���������� �̹� �湮�� ���̱� ������ 1�� ó��
	dist[start] = 0; // ���� ���� �Ÿ��� 0���� ���� (D)

	for (i = 0; i < vsize; i++)
	{
		//print_step(i + 1);
		u = choose_vertex(); // �ִܰŸ� ���� ��
		found[u] = 1; // �� ��ġ�� ã�Ҵٰ� ǥ���س���
		
		for (w = 0; w < vsize; w++)
		{
			if (found[w] == 0) // ���� �湮���� ���� ���� �ִٸ�
			{
				if (dist[u] + adj[u][w] < dist[w]) 
				// ���� ���� �Ÿ��� adj(�������)�� ���� ���� 
				//	���� �ִ� �Ÿ�(dist[w])���� ������
				{
					dist[w] = dist[u] + adj[u][w]; // �ִܰŸ��� �����Ѵ�.
					path[w] = u; // ���� w�� ���� ������ u�� ������
				}
			}
		}

	}
}

int print_shortest_path(int start, int end) // ����, ��(vsize��ŭ)
{
	shortest_path_dijkstra(start);

	return dist[end];
	
}