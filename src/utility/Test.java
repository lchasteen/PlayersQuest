/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author lchastee
 */
public class Test {
    
        private static String FirstName;

    
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }
                
        
        
        static {
            FirstName = "Joe";
            System.out.println(new Test().getFirstName());
            new Test().setFirstName("Fred");
            System.out.println(new Test().getFirstName());
                
	}
	/**
     *
     * @param args
     */
    /*public static void main(String args[] ){
		System.out.println(new Test().getFirstName());
                
	}
        */ 
}
