package com.wayneleo.quickstart.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Convertor {
    public static final String FILL            = "*";
    public static final String EMPTY           = " ";
    public static final int    NARROW          = 1;   //缩小倍数  
    public static final int    FONT_SIZE       = 20;  //文字大小  
    public static final String FONT_TYPE       = "宋体";
    public static final int    IMG_HEIGHT      = 20;
    public static final int    FONT_WIDTH      = 50;  //每个文字在图片中的宽度  
    public static final int    FONT_X          = 0;
    public static final int    FONT_Y          = 20;
    public static final int    COLOR_ALLOWANCE = 100; //颜色容差  

    public static void main( String[] args ) {
        String result = transform( "M" );
        System.out.println( result );
    }

    public static String transform( String input ) {
        StringBuilder sb = new StringBuilder();
        /*根据文字长度，创建指定长度的Image，然后将文字写入到Image中*/
        BufferedImage bi = new BufferedImage( input.length() * FONT_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB );
        Graphics g = bi.getGraphics();
        Font font = new Font( FONT_TYPE, Font.BOLD, FONT_SIZE );
        g.setFont( font );
        g.drawString( input, FONT_X, FONT_Y );
        int width = bi.getWidth();
        int height = bi.getHeight();
        /* 根据图像的长宽，逐个遍历图像的点 */
        for ( int i = 0; i < height; i += NARROW ) {
            for ( int j = 0; j < width; j += NARROW ) {
                Color color = new Color( bi.getRGB( j, i ) );
                int rc = ( int ) ( Math.pow( ( Color.WHITE.getRed() - color.getRed() ), 2 ) +
                        Math.pow( ( Color.WHITE.getGreen() - color.getGreen() ), 2 ) +
                        Math.pow( ( Color.WHITE.getBlue() - color.getBlue() ), 2 ) );
                /* 根据容差值填充字符串 */
                String tmp = rc < COLOR_ALLOWANCE ? FILL : EMPTY;
                sb.append( tmp );
            }
            sb.append( "\n" );
        }
        return sb.toString();
    }
}
