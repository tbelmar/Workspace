import java.util.*;

class Temperatures {
	public static void main(String[] args) {
		System.out.println("TEMPERATURES:\n");
		
		// input tools
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);
		
		// enter the number of weeks and measurements
		System.out.println("number of weeks: ");
		int nofWeeks = in.nextInt();
		System.out.println("number of measurements per week: ");
		int nofMeasurementsPerWeek = in.nextInt();
		
		// storage space for temperature data
		double[][] t = new double[nofWeeks + 1][nofMeasurementsPerWeek + 1];
		
		// read the temperatures
		for(int week = 1; week <= nofWeeks; week++) {
			System.out.println("temperatures - week " + week + ":");
			for(int reading = 1; reading <= nofMeasurementsPerWeek; reading++)
				t[week][reading] = in.nextDouble();
		}
		System.out.println();
		
		// show the temperatures
		System.out.println("the temperatures:");
		for(int week = 1; week <= nofWeeks; week++) {
			for(int reading = 1; reading <= nofMeasurementsPerWeek; reading++)
				System.out.println(t[week][reading] + " ");
			System.out.println();
		}
		System.out.println();
		
		// the greatest, least, and average temperature - weekly
		double[] minT = new double[nofWeeks + 1];
		double[] maxT = new double[nofWeeks + 1];
		double[] sumT = new double[nofWeeks + 1];
		double[] avgT = new double[nofWeeks + 1];
		
		// compute and store the greatest, least, and average temperature for each week
		for(int week = 1; week <= nofWeeks; week++) {
			double minOfWeek = t[week][1];
			double maxOfWeek = t[week][1];
			double sumOfWeek = 0;
			
			for(int i = 1; i <= nofMeasurementsPerWeek; i++) {
				if(t[week][i] < minOfWeek)
					minOfWeek = t[week][i];
				if(t[week][i] > maxOfWeek)
					maxOfWeek = t[week][i];
				sumOfWeek += t[week][i];
			}
			
			minT[week] = minOfWeek;
			maxT[week] = maxOfWeek;
			sumT[week] = sumOfWeek;
			avgT[week] = sumOfWeek / nofMeasurementsPerWeek;
		}
		
		// show the greatest, least, and average temperature for each week
		System.out.println("Least, greatest, and average temperature of each week:");
		for(int week = 1; week <= nofWeeks; week++) {
			System.out.println("Week " + week + ":");
			System.out.println("Min: " + minT[week]);
			System.out.println("Max: " + maxT[week]);
			System.out.println("Average: " + avgT[week] + "\n");	
		}
		
		// the least, greatest, and average temperature for the whole period
		double minTemp = minT[1];
		double maxTemp = maxT[1];
		double sumTemp = 0;
		double avgTemp = 0;
		
		// compute and store the greatest, least, and average temperature for the whole period
		for(int i = 1; i <= nofWeeks; i++) {
			if(minT[i] < minTemp)
				minTemp = minT[i];
			if(maxT[i] > maxTemp)
				maxTemp = maxT[i];
			sumTemp += sumT[i];
		}
		avgTemp = sumTemp / (nofWeeks*nofMeasurementsPerWeek);
		
		// show the greatest, least, and average temperature for the whole period
		System.out.println("Least, greatest, and average temperature of the whole period:");
		System.out.println("Min: " + minTemp);
		System.out.println("Max: " + maxTemp);
		System.out.println("Average: " + avgTemp + "\n");
		
		in.close();
	}
}