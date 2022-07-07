package com.chen.huaweiOD;

import java.util.*;

/**
 * 标题：跳格子游戏 | 时间限制：1秒 | 内存限制：262144K | 语言限制：不限
 * <p>
 * 地上共有N个格子，你需要跳完地上所有的格子，但是格子间是有强依赖关系的，跳完前一个格子后，后续的格子才会被开启，格子间的依赖关系由多组steps数组给出，steps[0]表示前一个格子,steps[1]表示steps[0]可以开启的格子:
 * 比如[0,1]表示从跳完第0个格子以后第1个格子就开启了，比如[2,1]，[2,3]表示跳完第2个格子后第1个格子和第3个格子就被开启了
 * 请你计算是否能由给出的steps数组跳完所有的格子,如果可以输出yes，否则输出no
 * <p>
 * 说明：
 * 1.你可以从一个格子跳到任意一个开启的格子
 * 2.没有前置依赖条件的格子默认就是开启的
 * 3.如果总数是N，则所有的格子编号为[0,1,2,3…N-1]连续的数组
 * <p>
 * 输入描述:
 * 输入一个整数N表示总共有多少个格子，接着输入多组二维数组steps表示所有格子之间的依赖关系
 * <p>
 * 输出描述:
 * 如果能按照steps给定的依赖顺序跳完所有的格子输出yes， 否则输出no
 * <p>
 * 示例1
 * 输入
 * 3
 * 0 1
 * 0 2
 * <p>
 * 输出：yes
 * <p>
 * 说明：
 * 总共有三个格子[0,1,2]，跳完0个格子后第1个格子就开启了，跳到第0个格子后第2个格子也被开启了，按照0->1->2或者0->2->1的顺序都可以跳完所有的格子
 * <p>
 * 示例2
 * 输入
 * 2
 * 1 0
 * 0 1
 * <p>
 * 输出：no
 * <p>
 * 说明：
 * 总共有2个格子，第1个格子可以开启第0格子，但是第1个格子又需要第0个格子才能开启，相互依赖，因此无法完成
 * <p>
 * 示例3
 * 输入
 * 6
 * 0 1
 * 0 2
 * 0 3
 * 0 4
 * 0 5
 * <p>
 * 输出：yes
 * <p>
 * 说明：
 * 总共有6个格子，第0个格子可以开启第1,2,3,4,5个格子，所以跳完第0个格子之后其他格子都被开启了，之后按任何顺序可以跳完剩余的格子
 * <p>
 * 示例4
 * 输入
 * 5
 * 4 3
 * 0 4
 * 2 1
 * 3 2
 * <p>
 * 输出：yes
 * <p>
 * 说明：
 * 跳完第0个格子可以开启格子4，跳完格子4可以开启格子3，跳完格子3可以开启格子2，跳完格子2可以开启格子1，按照0->4->3->2->1这样就跳完所有的格子
 * <p>
 * 示例5：
 * 输入
 * 4
 * 1 2
 * 1 0
 * <p>
 * 输出 yes
 * <p>
 * 说明：
 * 总共4个格子[0,1,2,3]，格子1和格子3没有前置条件所以默认开启，格子1可以开启格子0和格子2，所以跳到格子1之后就可以开启所有的格子，因此可以跳完所有格子
 */
public class Hopscotch {
    /**
     * 先判断一个格子是否开启，如果开启，则照下一个各子
     * 如果没有，找到这个各子的前置格子判断其是否开启，
     */

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        LinkedList<Grid> grids = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Grid grid = new Grid(i);
            grid.setOpen(true);
            grids.add(grid);
        }
        while (scanner.hasNextLine()) {
            int[] step = new int[2];

            step[0] = scanner.nextInt();
            step[1] = scanner.nextInt();

            if (step[0] == -1) {
                break;
            }

            grids.get(step[1]).setPreGrid(grids.get(step[0]));
            grids.get(step[1]).setOpen(false);

        }

        boolean[] isVisited = new boolean[n];
        boolean result = false;

        for (int i = 0; i < n; i++) {
            if (!grids.get(i).isOpen) {
                result = dfs(grids.get(i).getPreGrid(), isVisited);
            }
            if (isVisited[i] && !grids.get(i).isOpen) {
                break;
            }
        }
        String enable = result? "yes" : "no";
        System.out.println(enable);
    }

    public static boolean dfs(Grid grid, boolean[] isVisited) {
        isVisited[grid.getIndex()] = true;
        while (!grid.isOpen) {
            if (!isVisited[grid.getPreGrid().getIndex()]) {
                grid.isOpen = dfs(grid.getPreGrid(), isVisited);
            } else {
                return grid.isOpen;
            }
        }
        return true;
    }

    static class Grid {
        private int index;
        private Grid preGrid;
        public boolean isOpen;

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public Grid getPreGrid() {
            return preGrid;
        }

        public void setPreGrid(Grid preGrid) {
            this.preGrid = preGrid;
        }

        public boolean isOpen() {
            return isOpen;
        }

        public void setOpen(boolean open) {
            isOpen = open;
        }

        public Grid(int index) {
            this.index = index;
        }
    }
}
