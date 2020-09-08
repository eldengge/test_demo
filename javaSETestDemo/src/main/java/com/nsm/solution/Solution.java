package com.nsm.solution;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author NSM
 * @Date 2020/5/25 12:29
 * @Version 1.0
 **/
public class Solution {
    public static int[] twoSum(int[] nums, int target) {
        int[] indexs = new int[2];
        Map<Integer,Integer> temp = new HashMap<>();
        for(int i = 0;i<nums.length;++i) {
            if(temp.get(target-nums[i])!=null){
                indexs[0] = temp.get(target-nums[i]);
                indexs[1] = i;
                return indexs;
            }else{
                temp.put(nums[i],i);
            }
        }
        return null;
    }

    /**********************************************************
     *
     */
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
        ListNode(){}

        public int getVal() {
            return val;
        }

        public void setVal(int val) {
            this.val = val;
        }

        public ListNode getNext() {
            return next;
        }

        public void setNext(ListNode next) {
            this.next = next;
        }
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode node = null;
        ListNode p = node;
        ListNode r = node;
        int temp = 0;
        while (p1!=null||p2!=null){
            if(p1!=null&&p2!=null){
                int num = p1.val+p2.val+temp;
                temp = num/10;
                r=new Solution().new ListNode(num%10);
                if(p == null){
                    node=r;
                    p=r;
                }else{
                    p.next = r;
                    p = p.next;
                }

            }else if(p1 == null){
                if(node!=null){
                    p.next = p2;
                    while(p2!=null){
                        if(temp != 0){
                            int num = p2.val+temp;
                            temp = num/10;
                            p2.val = num%10;
                        }
                        p = p2;
                        p2 = p2.next;
                    }
                }else{
                    node = p2;
                }
                break;
            }else if(p2 == null){
                if(node!=null){
                    p.next = p1;
                    while(p1!=null){
                        if(temp != 0){
                            int num = p1.val+temp;
                            temp = num/10;
                            p1.val = num%10;
                        }
                        p = p1;
                        p1 = p1.next;
                    }
                }else{
                    node = p1;
                }
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        if(temp != 0){
            p.next = new Solution().new ListNode(temp);
            p = p.next;
        }
        return node;
    }

    /********************************************************
     *
     *
     */
    public static int lengthOfLongestSubstring(String s) {
        if(s == null|| "".equals(s))return 0;
        int p=0,r=0,length = 0;
        Set<Character> charSet = new HashSet<>();
        for(int i = 0;i<s.length();++i){
            while(r<s.length()&&!charSet.contains(s.charAt(r))){
                charSet.add(s.charAt(r));
                ++r;
            }
            length = (length>(r-p))?length:(r-p);
            charSet.remove(s.charAt(i));
        }
        return length;
    }

    /**
     * 最长无重复字符子串
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        if(s == null|| "".equals(s))return 0;
        int left=0,max=0;
        Map<Character,Integer> charMap = new HashMap<>();
        for(int i = 0;i<s.length();++i){
            if(charMap.containsKey(s.charAt(i))){
                left = Math.max(left, charMap.get(s.charAt(i))+1);
            }
            charMap.put(s.charAt(i), i);
            max = Math.max(max, (i-left+1));
        }
        return max;
    }

    /**
     * 两个有序数组求中位数
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1.length>nums2.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int n = nums1.length;
        int m = nums2.length;
        int left = 0;
        int right = n;
        int totalLeft = (m+n+1)/2;
        while (left<right){
            int i = left + (right - left+1)/2;
            int j = totalLeft - i;
            if(nums1[i-1]>nums2[j]){
                right = i-1;
            }else{
                left = i;
            }
        }
        int i = left;
        int j = totalLeft - left;
        int leftnums1min = i == 0 ? Integer.MIN_VALUE:nums1[i-1];
        int rightnums1max = i == n ? Integer.MAX_VALUE:nums1[i];
        int leftnums2min = j == 0 ? Integer.MIN_VALUE:nums2[j-1];
        int rightnums2max = j == m ? Integer.MAX_VALUE:nums2[j];

        if((m+n)%2 == 0){
            return (double) (Math.max(leftnums1min, leftnums2min)+Math.min(rightnums1max, rightnums2max))/2;
        }else{
            return Math.max(leftnums1min, leftnums2min);
        }
    }

    /**
     * 中心扩散求最长回文子串
     * @param s
     * @return
     */
    public static String longestPalindrome1(String s) {
        if (s==null || s.length()<=0){return s;}
        String str = ""+s.charAt(0);
        int[] indexs = new int[2];
        int length = 1;
        for (int i = 0;i<s.length();++i){
            if(i<s.length()-1){
                if(s.charAt(i)==s.charAt(i+1)){
                    indexs[0]=i;
                    while(i<s.length()-1&&s.charAt(i)==s.charAt(i+1)) {
                        indexs[1]=++i;
                    }
                }else{
                    indexs[0]=i;
                    indexs[1]=i;
                }
                int j = indexs[0]-1;
                int z = indexs[1]+1;
                while (j>=0&&z<=s.length()-1){
                    if(s.charAt(j) == s.charAt(z)){
                        indexs[0] = j--;
                        indexs[1] = z++;
                    }else{
                        break;
                    }
                }
                int newLength = indexs[1]-indexs[0]+1;
                if(newLength>length){
                    length = newLength;
                    str = s.substring(indexs[0],indexs[1]+1);
                }
            }
        }
        return str;
    }

    /**
     * 动态规划求最长回文子串
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        if (s==null || s.length()<=0){return s;}
        int len = 1;
        String str =""+s.charAt(0);
        boolean[][] status = new boolean[s.length()][s.length()];
        for(int i = s.length()-1;i>=0;--i){
            for(int j = i+1;j<s.length();++j){
                if(j-i<3){
                    status[i][j] = s.charAt(i) == s.charAt(j)?true:false;
                }else{
                    status[i][j] = s.charAt(i) == s.charAt(j)&&status[i+1][j-1];
                }
                if(status[i][j]&&j-i+1>len){
                    len = j-i+1;
                    str = s.substring(i, j+1);
                }
            }
        }
        return str;
    }

    public static boolean checkPalindrome(String str){
        int i = 0,j = str.length()-1;
        while(i<j){
            if(str.charAt(i)!=str.charAt(j)){
                return false;
            }
            ++i;
            --j;
        }
        return true;
    }

    /**
     * 挖矿问题
     *
     * 有一个国家发现了5座金矿，每座金矿的黄金储量不同，需要参与挖掘的工人数也不同。参与挖矿工人的总数是10人。
     * 每座金矿要么全挖，要么不挖，不能派出一半人挖取一半金矿。要求用程序求解出，要想得到尽可能多的黄金，应该
     * 选择挖取哪几座金矿？
     *
     * @param n 金矿数
     * @param w 工人数
     * @param g 黄金量
     * @param p 用工量
     * @return
     */
    public static int[] getMostGold(int n, int w, int[] g, int[] p){
        int[] preResults = new int[w];

        //填充边界格子的值
        for(int i=0; i<w; i++){
            if(i+1 < p[0]){
                preResults[i] = 0;
            }else{
                preResults[i] = g[0];
            }
        }
        System.out.println(Arrays.toString(preResults));
        //填充其余格子的值，外层循环是金矿的数量，内层循环是工人数
        for(int i=1; i<n; i++){
            int[] results = new int[w];
            for(int j=1; j<=w; j++){
                if(j < p[i]){
                    results[j-1] = preResults[j-1];
                }else if(j == p[i]){
                    results[j-1] = Math.max(preResults[j-1], g[i]);
                }else{
                    results[j-1] = Math.max(preResults[j-1], preResults[j-p[i]-1] + g[i]);
                }
            }

            preResults = results;
            System.out.println(Arrays.toString(preResults));
        }

        return preResults;
    }

    /**
     * 盛最多水容器
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int i = 0,j=height.length-1;
        int max = 0;
        int area;
        while(i<j){
            if(height[i]<height[j]){
                area = (j-i)*height[i];
                max = max<area?area:max;
                i++;
            }else{
                area = (j-i)*height[j];
                max = max<area?area:max;
                j--;
            }
        }
        return max;
    }

    /**
     *
     * 快速排序
     * @param left
     * @param right
     * @param nums
     */
    public static void quick(int left,int right,int[] nums){
        if(left >= right)return;
        int i = left;
        int j = right;
        int key = nums[i];
        while(i<j){
              while(i<j&&nums[j]>key){
                  --j;
              }
              if(i<j){
                  nums[i] = nums[j];
                  ++i;
              }
              while(i<j&&nums[i]<key){
                  ++i;
              }
              if(i<j){
                  nums[j] = nums[i];
                  --j;
              }
        }
        nums[i] = key;
        quick(left, i-1, nums);
        quick(i+1, right, nums);
    }
    public static void main(String[] args) {

//        String[] a = new String[2];
//        Object[] b = a;
//        a[0] = "hi";
//        System.out.println(b[1] instanceof Object);
//        System.out.println(b[1] instanceof String);
//        System.out.println(b[1] instanceof Integer);
//
//        System.out.println(Arrays.toString(a));
//        System.out.println(Arrays.toString(b));

//        StringBuilder sb = new StringBuilder("");
//        File file = new File("C:\\Users\\nsmha\\Desktop\\work\\新页面设计\\天气类型\\天气类型\\WeatherList");
//        File[] files = file.listFiles();
//        for (File f:files){
//            if(!f.isDirectory())continue;
//            String picName = f.getName().substring(0, f.getName().indexOf("."));
//            String objName = picName.toUpperCase();
//            if(objName.contains("_")){
//                objName = objName.substring(0, objName.indexOf("_"))+objName.substring(objName.indexOf("_")+1, objName.length());
//            }
//            sb.append(objName+":{name:\"");
//            File[] files1 = f.listFiles();
//            for (File f1:files1){
//                if(f1.getName().contains("@")){
//                    String wname = f1.getName().substring(0,f1.getName().indexOf("@"));
//                    sb.append(wname+"\",pic:\""+picName+".png\"},");
//                    break;
//                }
//            }
//        }
//        System.out.println(sb.toString());
/*
        int n = 5, w = 10;
        int [] g = new int[]{400,500,200,300,350}, p = new int[]{5,5,3,4,3};
        System.out.println(Arrays.toString(getMostGold(n, w, g, p)));
*/
/*        int[] height = new int[]{1,1,1,1};
        System.out.println(maxArea(height));*/
        int[] nums = new int[10];
        Random r = new Random();
        for (int i=0; i<nums.length; ++i){
            nums[i] = r.nextInt(10);
        }
        System.out.println(Arrays.toString(nums));
        quick(0, nums.length-1, nums);
        System.out.println(Arrays.toString(nums));


    }
}
