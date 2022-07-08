package com.chen.huaweiOD;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 标题：服务器广播 | 时间限制：1秒 | 内存限制：262144K | 语言限制：不限
 *
 * 服务器连接方式包括直接相连，间接连接。A和B直接连接，B和C直接连接，则A和C间接连接。直接连接和间接连接都可以发送广播。
 * 给出一个N*N数组，代表N个服务器，matrix[i][j] == 1，则代表i和j直接连接；不等于1时，代表i和j不直接连接。matrix[i][i] == 1，即自己和自己直接连接。matrix[i][j] == matrix[j][i]。
 * 计算初始需要给几台服务器广播，才可以使每个服务器都收到广播。
 *
 * 输入描述:
 * 输入为N行，每行有N个数字，为0或1，由空格分隔，构成N*N的数组，N的范围为 1<=N<=40
 *
 * 输出描述:
 * 输出一个数字，为需要广播的服务器的数量
 *
 * 示例1
 * 输入
 * 1 0 0
 * 0 1 0
 * 0 0 1
 *
 * 输出
 * 3
 *
 * 说明：
 * 3台服务器互不连接，所以需要分别广播这3台服务器
 *
 * 示例2
 * 输入
 * 1 1
 * 1 1
 *
 * 输出
 * 1
 *
 * 说明：
 * 2台服务器相互连接，所以只需要广播其中一台服务器
 */
public class ServerBroadcast {
    public static void main(String[] args) {
        //初始化
        Scanner scanner = new Scanner(System.in);
        String[] firstLine = scanner.nextLine().split(" ");
        int N = firstLine.length;
        int[][] matrix = new int[N][N];

        //接收第一行
        for(int i = 0; i < N; i++){
            matrix[0][i] = Integer.parseInt(firstLine[i]); 
        }
        //接收第2-N行
        int M = 1;
        while (N > M) {
            for (int i = 0; i < N; i++) {
                matrix[M][i] = scanner.nextInt();
            }
            M++;
        }


        //将数据封装到对象中并形成链表
        LinkedList<Server> serverList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            Server currentServer = new Server();
            currentServer.setIndex(i);
            serverList.add(currentServer);
        }

        for (int i = 0; i < N; i++) {
            LinkedList<Server> adjacentServers = new LinkedList<>();
            for (int j = 0; j < N; j++) {
                if (i != j && matrix[i][j] != 0){
                    adjacentServers.add(serverList.get(j));
                }
            }
            serverList.get(i).setAdjacentServers(adjacentServers);
        }

        boolean[] isVisited = new boolean[N];
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (!isVisited[serverList.get(i).getIndex()]) {
                count = bfs(count, serverList.get(i), isVisited);
            }
        }

        System.out.println(count);

        // bfs 广度优先算法
        // 从头开始遍历所有节点，
        // 如果该节点的isVisited属性 为false 进入bfs
        // 每遍历一个就将该节点置为已访问，
        // 如果有相邻节点链表就进入该链表，对该链表进行bfs
        // 如果遍历到没有相邻节点或相邻节点全部已访问，则count++
        // 遍历链表下一个元素；


    }

    public static int bfs(int count, Server server, boolean[] isVisited) {
        isVisited[server.getIndex()] = true;
        if (!server.adjacentServers.isEmpty()) {
            for (int i = 0; i < server.adjacentServers.size(); i++) {
                if (!isVisited[server.getAdjacentServers().get(i).getIndex()]) {
                    bfs(count, server.getAdjacentServers().get(i), isVisited);
                }
            }
        }
        count++;
        return count;
    }

    static class Server {
        private int index;
        private LinkedList<Server> adjacentServers; //与该节点相连的节点集合（链表）

        public Server() {
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public LinkedList<Server> getAdjacentServers() {
            return adjacentServers;
        }

        public void setAdjacentServers(LinkedList<Server> adjacentServers) {
            this.adjacentServers = adjacentServers;
        }
    }
}
