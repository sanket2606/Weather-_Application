package mini_project;

import java.util.Arrays;

public class SortArray {
	public static void main(String[] args) {
		int arr[]= {9,3,4,2,3,4,1};
		System.out.println(Arrays.toString(arr));
		int index=1;
		for (int i = 0; i < arr.length; i++) 
		{
			for (int j = i+1; j < arr.length; j++)
			{
				
				if(arr[i]==arr[j])
				{
					arr[j]=0;
				}
				
			}
						
			
		}
		System.out.println(Arrays.toString(arr));
	}

}
