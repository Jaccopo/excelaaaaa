
package javaapplication16;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetGroup;

import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

 
public class PieChart_AWT extends ApplicationFrame {
   
   public PieChart_AWT( String title ) {
      super( title ); 
      setContentPane(createDemoPanel( ));
   }
   
   private XYSeries setXYSeries(String nombre,float datos[] ) {
      XYSeries  dataset = new XYSeries(nombre);
       for (int i = 0; i < 100; i++) {
           dataset.add(i+1,datos[i]);
       }
      return dataset;         
   }
   
   
   private JFreeChart createChart( ) {

       XYSeriesCollection xy = new XYSeriesCollection();

      JFreeChart chart = ChartFactory.createXYLineChart(
                        "funcion",
                        "x",
                        "y",
                        xy);  
      return chart;
   }
   
   public JPanel createDemoPanel( ) {
      JFreeChart chart =  createChart( );  
      return new ChartPanel( chart ); 
   }

   public static void main( String[ ] args ) {
      PieChart_AWT demo = new PieChart_AWT( "Mobile Sales" );  
      demo.setSize( 560 , 367 );       
      demo.setVisible( true ); 
   }
}