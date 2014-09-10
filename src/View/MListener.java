package View;

import Model.Manager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created with IntelliJ IDEA.
 * User: Martin
 * Date: 9/28/13
 * Time: 8:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class MListener{

    static Manager manager = Manager.getInstance(null);

    public static MouseListener getListener(){
        return mList;
    }
    static MouseListener mList = new MouseListener(){
        @Override
        public void mouseClicked(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mousePressed(MouseEvent e) {
            int xClick =e.getX()/BoardPanel.rectSize;
            int yClick =(e.getY()-22)/BoardPanel.rectSize;
            manager.clicked(xClick, yClick);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void mouseExited(MouseEvent e) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    };

}
