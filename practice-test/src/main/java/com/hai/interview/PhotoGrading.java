package com.hai.interview;

import java.util.Arrays;
/*
 *  100张参赛照片，有10个评委为每张照片评分，分数为1~100分，照片最后得分为：去掉一个最高分和最低分后其余8个分数的平均值。
 * 	照片分数已经存入grade[100][10]整型数组中。grade[1][3]表示4号评委对2号照片的评分。
 * 1、请编程输出每张照片的得分。
 * 2、同时对评委进行裁判，即在10个评委中找出最公平（评分最接近平均分）和最不公平（与平均分差距最大）的评委。请编程输出最公平的评委号数。
 *	问题1,，也就是遍历每张照片得分，排序出最高分和最低分。然后可求。
 *	问题2，需求出最接近平均分的分数所对应的评委号。最接近平均分的分数由分数减去平均分的绝对值决定，值越小，越接近。同样需要排序求出最小分。
 */
public class PhotoGrading {
	
		private static int grade[][] = new int[100][10];
		
		public static void main(String[] args) {
			System.out.println(grade.length);
			
			makeGrade();
			outPutGrade();
			
		}
		
		/**
		 * 输出每张照片评分
		 */
		public static void outPutGrade(){
			
			for (int i = 0; i < grade.length; i++) {
				int count = 0;
				int[] js = grade[i].clone();
				System.out.println("\n------第"+(i+1)+"张照片------\n");
				//求的总分count
				for (int j = 0; j < js.length; j++) {
					//输出每个评委对这张照片的评分
					//System.out.println("第"+(j+1)+"号评委给分："+grade[i][j]);
					count += js[j];
				}
				//排序求最高分和最低分
				int jp[] = sort(js);
				//输出排序后的评分
				System.out.println("评分排序后："+Arrays.toString(jp));
				double ping = (double)(count -jp[0]-js[jp.length-1])/(jp.length-2);//平均分
				System.out.println("照片最后得分："+ping+"\n");
				
				//求平均分差
				int[] jg = grade[i];
				double[] abs = new double[jg.length];
				for (int j = 0; j < jg.length; j++) {
					int k = jg[j];
					abs[j] = Math.abs(ping-k);//绝对值
				}
				// System.out.println("平均分差："+Arrays.toString(abs));
				
				//标记评委号码，随着平均分差排序，以获取哪个评委最/失/公平
				int flags[] = new int[abs.length];
				for (int j = 0; j < flags.length; j++) {
					flags[j]=j;//顺序0123456789
				}
				//查找平均分差据最大的评委
				for (int j = 0; j < abs.length; j++) {
					for (int k = j+1; k < abs.length; k++) {
						double temp = abs[k];
						int tf = flags[k];
						if (abs[j]>temp) {
							//分差交换
							abs[k] = abs[j];
							abs[j] = temp;
							//评委号交换
							flags[k] = flags[j];
							flags[j] = tf;
						}
					}
				}
				System.out.println("分差排序："+Arrays.toString(abs));
				System.out.println("对应评委索引："+Arrays.toString(flags));
				int gp = jg[flags[0]];//最公平的分数
				int bp = jg[flags[flags.length-1]];//最不公平的分数
				
				System.out.println("\n最公平的分数："+gp+"  最不公平的分数："+bp);
				//分数有可能一样。所以需要遍历
				for (int j = 0; j < jg.length; j++) {
					int k = jg[j];
					if (k==gp) {
						System.out.println("最公平的评委是"+(j+1)+"号");		
					}
					if(k==bp){
						System.out.println("最不公平的评委是"+(j+1)+"号");
					}
					
				}
			}
			
		}
		/**
		 * 生成评分
		 */
		public static void makeGrade(){
			//照片
			for(int i=0;i<grade.length;i++) {
				int[] photo = grade[i];
				//评委
				for(int j=0;j<photo.length;j++) {
					int score = (int) (Math.random()*100+1);
					//j号评委给i号照片打分
					grade[i][j] = score;
					
				}
			}
			//输出每张照片的分数（数组结构）
		/*
		 * for(int i=0;i<grade.length;i++) { int[] photo = grade[i].clone();
		 * System.out.println(Arrays.toString(photo)); }
		 */
		}
		/**
		 * 集合排序（小到大）
		 * @param jp
		 * @return 第一个最小，最后一个最大
		 */
		private static int[] sort(int[] jp){
			for (int j = 0; j < jp.length-1; j++) {
				for (int l = 0; l < jp.length-j-1; l++) {
					if (jp[l]>jp[l+1]) {
						int temp = jp[l];
						jp[l] = jp[l+1];
						jp[l+1] = temp;
					}
				}
			}
			
			return jp;
		}
	}
	

