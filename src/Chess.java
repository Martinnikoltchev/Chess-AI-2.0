import View.ActiveUI;

/**
 * Created with IntelliJ IDEA.
 * User: Martin
 * Date: 9/16/13
 * Time: 5:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class Chess {

    public static void main(String args[]){
        ActiveUI view = new ActiveUI("Chess", Integer.valueOf(args[0])==1);
        view.setVisible(true);
        view.repaint();
    }
}
