package image;

import java.util.*;

public class toolUtil {


	/**
	 * 获取一组随机位置坐标
	 * @return
	 */
	public static int[][] getRandomLocation(int paneWidth,int panelHeight,int blockWidth,int blockHeight,int n){
		int[][] location=new int[n][3]; //x,y,c
		int xStart=(int)(Math.random()*blockWidth);//x开始位置
		int yStart=(int)(Math.random()*blockHeight);//y开始位置
		int scaleX=(int)(blockWidth*1.1);
		int scalaY=(int)(blockHeight*1.1);
		int xMax=(paneWidth-blockWidth)/scaleX; //x数量
		int yMax=(panelHeight-blockHeight)/scalaY;//y数量
		System.out.println(xStart+" "+yStart+" "+xMax+" "+yMax);
		LinkedHashSet hashSetX = new LinkedHashSet();
		LinkedHashSet hashSetY = new LinkedHashSet();
		while (hashSetX.size()<n){
			int x=(int)(Math.random()*xMax);
			hashSetX.add(x);
		}

		while (hashSetY.size()<n){
			int y=(int)(Math.random()*yMax);
			hashSetY.add(y);
		}

		List<Integer> listX = new ArrayList<Integer>();
		Iterator itX = hashSetX.iterator();
		while(itX.hasNext()){
			Object ob=itX.next();
				listX.add(Integer.parseInt(String.valueOf(ob)));
		}

//		List<Integer> listX = new ArrayList<Integer>(hashSetX);
		List<Integer> listY = new ArrayList<Integer>(hashSetY);
		for(int k=0;k<n;k++){
			location[k][0]=listX.get(k)*scaleX+xStart;
			location[k][1]=listY.get(k)*scalaY+yStart;
			int c=(int)(Math.random()*3);
			location[k][2]=c;
		}

		return location;
	}

	public static void test(){
		int paneWidth=600;
		int panelHeight=400;
		int n=10;
		int blockWidth=40;
		int blockHeight=30;
		int[][] location=new int[n][3]; //x,y,c
		int xStart=(int)(Math.random()*blockWidth);//x开始位置
		int yStart=(int)(Math.random()*blockHeight);//y开始位置
		int scaleX=(int)(blockWidth*1.1);
		int scalaY=(int)(blockHeight*1.1);
		int xMax=paneWidth/scaleX; //x数量
		int yMax=panelHeight/scalaY;//y数量
		System.out.println(xStart+" "+yStart+" "+xMax+" "+yMax);
		LinkedHashSet hashSetX = new LinkedHashSet();
		HashSet hashSetY = new HashSet();
		while (hashSetX.size()<n){
			int x=(int)(Math.random()*xMax);
			hashSetX.add(x);
		}
		System.out.println("----------");

		while (hashSetY.size()<n){
			int y=(int)(Math.random()*yMax);
			hashSetY.add(y);
		}
		List<Integer> listX = new ArrayList<Integer>(hashSetX);
		List<Integer> listY = new ArrayList<Integer>(hashSetY);
		for(int k=0;k<n;k++){
			location[k][0]=listX.get(k)*scaleX+xStart;
			location[k][1]=listY.get(k)*scalaY+yStart;
			int c=(int)(Math.random()*3);
			location[k][2]=c;
		}

		for(int i=0;i<location.length;i++){
				System.out.println(location[i][0]+" "+location[i][1]+" "+location[i][2]);
		}

	}

	public static void main(String[] Args){
		int[][] a=getRandomLocation(780,560,40,30,10);
		for(int i=0;i<a.length;i++){
				System.out.println(a[i][0]+" "+a[i][1]+" "+a[i][2]);
		}
//		test();
	}


}


//Object[] values = new Object[20];
//
//Random random = new Random();
//HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
//
//// 生成随机数字并存入HashMap
//for(int i = 0;i < values.length;i++){
//		int number = random.nextInt(100) + 1;
//		hashMap.put(number, i);
//		}
//
//		// 从HashMap导入数组
//		values = hashMap.keySet().toArray();
//
//		// 遍历数组并打印数据
//		for(int i = 0;i < values.length;i++){
//		System.out.print(values[i] + "\t");
//
//		if(( i + 1 ) % 10 == 0){
//		System.out.println("\n");
//		}
//		}