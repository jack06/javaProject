package com.icloud.util;

import java.util.Comparator;

import com.icloud.model.Raise;

public class RaiseComparator implements Comparator<Raise>{
	
	//进行中的活动拍在前面,然后按时间排序
	//1:3,0:2,3:2,2,
	//位置1=3
	//位置0=2
	//位置2=1
	//位置3=0
	private static int[] arry = {2,3,1,0};
	
	public int compare(Raise o1, Raise o2) {
		
		int index1 = Integer.parseInt(o1.getCurrentStatus());
		int index2 = Integer.parseInt(o2.getCurrentStatus());
		
		if(arry[index1]>arry[index2]){
			return -1;
		}else if(arry[index1]<arry[index2]){
			return 1;
		}else{
			return 0;
		}
//			else{
//			if(o1.getOutTime().compareTo(o2.getOutTime())>0){
//				return -1;
//			}else if(o1.getOutTime().compareTo(o2.getOutTime())<0){
//				return 1;
//			}
//			return 0;
//		}
	}

}
