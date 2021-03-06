package EnergyCalculator;

import edu.princeton.cs.algs4.Picture;

/**
 * A class used to calculate the energy of the pixels of a given picture.
 * 
 * @author Donisius Wigie
 *
 */
public class EnergyCalculator {

	private final Picture pic;
	private final int height;
	private final int width;
	private double[][] pixelEnergy;
	
	/**
	 * Constructor for EnergyCalculator class.
	 * 
	 * @param pic Picture to calculate the energy of pixels for.
	 */
	public EnergyCalculator(Picture pic) {
		
		this.pic = pic;
		this.height = pic.height();
		this.width = pic.width();
		pixelEnergy = new double[width][height];
		
		/*
		 * Calculate the energy of all pixels in the given picture.
		 */
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				pixelEnergy[i][j] = CalculatePixelEnergy(i, j);
			}
		}
	}
	
	/**
	 * Getter for the pixelEnergy graph containing the calculated 
	 * energy of all the pixels in a given picture.
	 * 
	 * @return pixelEnergy Two-Dimensional array containing the calculated 
	 * energy of every pixel in a given picture.
	 */
	public double[][] getPixelEnergy(){
		return pixelEnergy;
	}
	
	/**
	 * Calculates the energy of a pixel based on its position relative to other pixels in the picture.
	 * 
	 * @param col Column of the pixel to find energy for.
	 * @param row Row of the pixel to find energy for.
	 * @return
	 */
	private double CalculatePixelEnergy(int col, int row) {
		
		double xredLeft, xredRight, xgreenLeft, xgreenRight, xblueLeft, xblueRight, deltaX;
		double yredDown, yredUp, ygreenDown, ygreenUp, yblueDown, yblueUp, deltaY;
		
		/*
		 * When the column given is in the left most edge of the graph.
		 */
		if(col - 1 < 0) {
			xredLeft = pic.get(width - 1, row).getRed();
			xgreenLeft = pic.get(width - 1, row).getGreen();
			xblueLeft = pic.get(width - 1, row).getBlue();
		}
		
		/*
		 * Calculate the RGB values of the pixel to the left.
		 */
		else {
			xredLeft = pic.get(col - 1, row).getRed();
			xgreenLeft = pic.get(col - 1, row).getGreen();
			xblueLeft = pic.get(col - 1, row).getBlue();
		}
		
		/*
		 * When the column is on the right most edge of the picture.
		 */
		if(col + 1 > width - 1) {
			xredRight = pic.get(0, row).getRed();
			xgreenRight = pic.get(0, row).getGreen();
			xblueRight = pic.get(0, row).getBlue();
		}
		
		/*
		 * Calculate the RGB values of the pixel to the right.
		 */
		else {
			xredRight = pic.get(col + 1, row).getRed();
			xgreenRight = pic.get(col + 1, row).getGreen();
			xblueRight = pic.get(col + 1, row).getBlue();
		}
		
		/*
		 * When the row given is at the top most edge of the Picture.
		 */
		if(row - 1 < 0) {
			yredDown = pic.get(col, height - 1).getRed();
			ygreenDown = pic.get(col, height - 1).getGreen();
			yblueDown = pic.get(col, height - 1).getBlue();
		}
		
		/*
		 * Calculate the RGB values of the pixel on top.
		 */
		else {
			yredDown = pic.get(col, row - 1).getRed();
			ygreenDown = pic.get(col, row - 1).getGreen();
			yblueDown = pic.get(col, row - 1).getBlue();
		}
		
		/*
		 * When the row given is on the bottom most edge of the graph.
		 */
		if(row + 1 > height - 1) {
			yredUp = pic.get(col, 0).getRed();
			ygreenUp = pic.get(col, 0).getGreen();
			yblueUp = pic.get(col, 0).getBlue();
		}
		
		/*
		 * Calculate the RGB values of the pixel below.
		 */
		else {
			yredUp = pic.get(col, row + 1).getRed();
			ygreenUp = pic.get(col, row + 1).getGreen();
			yblueUp = pic.get(col, row + 1).getBlue();
		}
		
		/*
		 * Calculate the total horizontal energy.
		 */
		deltaX = Math.pow((xredLeft - xredRight), 2) + Math.pow((xgreenLeft - xgreenRight), 2) + Math.pow((xblueLeft - xblueRight), 2);
		
		/*
		 * Calculate the total vertical energy.
		 */
		deltaY = Math.pow((yredDown - yredUp), 2) + Math.pow((ygreenDown - ygreenUp), 2) + Math.pow((yblueDown - yblueUp), 2);
		
		return deltaX + deltaY;
	}
	
}
