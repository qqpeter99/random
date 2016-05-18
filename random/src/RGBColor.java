public class RGBColor {
    
    private static final int MAX_VALUE = 255;
    
    public int[] getComplement(int[] rgb) {

        int[] compRGB = new int[3];
        int differCount = 0;
        for (int i = 0; i < 3; i++) {
            compRGB[i] = MAX_VALUE - rgb[i];
            if (Math.abs(compRGB[i]-rgb[i]) < 33) {
                differCount++;
            }
        }
        
        if (differCount == 3) {
            for (int i = 0; i < 3; i++) {   
                compRGB[i] = (rgb[i] >= 128) ? rgb[i] - 128 : rgb[i] + 128;
            }
        }
        
        return compRGB;
    }
}