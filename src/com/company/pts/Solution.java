package com.company.pts;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution {


    public static void main(String[] args) {


        int[] nums = {1, 2, 3, 4};


        long time = System.currentTimeMillis();
        List<List<Integer>> perm = new Solution().permute3(nums);
        System.out.println(perm);
        System.out.println(System.currentTimeMillis() - time);
    }

    public List<List<Integer>> permute(int[] nums) {
        System.out.println("test 7");
        List<List<Integer>> solutions = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        add(nums, solution, solutions, nums.length, -1);
        return solutions;
    }

    public void add(int[] numbers, List<Integer> solution, List<List<Integer>> solutions, int length, int index) {

        if (solution.size() == length) {
            solutions.add(new ArrayList<>(solution));
            return;
        }
        if (!solution.isEmpty()) numbers[index] = 11;

        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] != 11) {
                solution.add(numbers[i]);
                add(numbers, solution, solutions, length, i);
                solution.remove(solution.size() - 1);
            }
        }
        if (!solution.isEmpty()) numbers[index] = solution.get(solution.size() - 1);
    }


    public List<List<Integer>> permute3(int[] nums) {
        int n = nums.length;
        List<List<Integer>> results = new ArrayList();

        recurse(nums, 0, nums.length, results);

        return results;
    }


    private void recurse(int[] nums, int pos, int n, List<List<Integer>> results) {
        if (pos == n - 1) {
            List<Integer> list = new ArrayList();
            for (int num : nums) list.add(num);
            results.add(list);
            return;
        }

        for (int i = 0; i < n - pos; i++) {
            swap(nums, pos, pos + i);
            recurse(nums, pos + 1, n, results);
            swap(nums, pos, pos + i);
        }
    }

    private void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }


    public List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> solutions = new ArrayList<>();

        int fac = fac(nums.length);

        solutions.add(IntStream.of(nums).boxed().collect(Collectors.toList()));
        for (int i = 1; i < fac; i++) {
            nextPermutation(nums);
            solutions.add(IntStream.of(nums).boxed().collect(Collectors.toList()));
        }

        System.out.println(fac);
        return solutions;
    }


    public void nextPermutation(int[] nums) {
        int tmp = -1;

        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                int p = nums[i - 1];
                for (int j = i, k = nums.length - 1; j < i + ((nums.length - i) >> 1); j++, k--) {
                    tmp = nums[k];
                    nums[k] = nums[j];
                    nums[j] = tmp;
                }
                for (int j = i; j <= nums.length - 1; j++) {
                    if (nums[j] > p) {
                        tmp = nums[j];
                        nums[j] = p;
                        nums[i - 1] = tmp;
                        break;
                    }
                }

                return;
            }
        }

        for (int i = 0, j = nums.length - 1; i < nums.length >> 1; i++, j--) {
            tmp = nums[j];
            nums[j] = nums[i];
            nums[i] = tmp;
        }
    }

    private int fac(int value) {
        int fac = 1;
        for (int i = 1; i <= value; i++) fac = fac * i;
        return fac;
    }
}