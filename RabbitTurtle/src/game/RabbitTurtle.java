package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class RabbitTurtle {
	public static int Mcount=0; //������ Ƚ��
	public static void main(String[] args) {
		
		final JFrame frame = new JFrame();
		JLabel MoveCount = new JLabel("0",JLabel.CENTER); //�÷��̾��� �̵� Ƚ��
        final ImageIcon successIcon = new ImageIcon("successIcon.png"); //���� Ŭ���� �� �̹���
		final ImageIcon backGround = new ImageIcon("backGround.png"); //��� �̹���
		final ImageIcon rab = new ImageIcon("rab.png"); //�䳢(����)
		final ImageIcon turD = new ImageIcon("turD.png"); //�÷��̾�(�ź���(�Ʒ�����))
		final ImageIcon turL = new ImageIcon("turL.png"); //�÷��̾�(�ź���(���ʹ���))
		final ImageIcon turR = new ImageIcon("turR.png"); //�÷��̾�(�ź���(�����ʹ���))
		final ImageIcon turU = new ImageIcon("turU.png"); //�÷��̾�(�ź���(���ʹ���))
        
        final JButton button = new JButton(successIcon); // ���� ��
		final CardLayout card = new CardLayout(); //
		final JDialog dialog = new JDialog(); // ���� Ŭ���� ȭ��

		random = new Random(); //������ �䳢�� ���� �������� �̵��Ѵ�. ���� ��ü ����

		dialog.setSize(650,650); //Ŭ���� ȭ��
		dialog.setVisible(false); //Ŭ���� �� ���;� �ϱ� ������ �� ���̵��� ó���Ѵ�.

		turH=12;  turW=7;  rabH=7;  rabW=7;  start=2;  temp=backGround;
        // �⺻ ���� ��ǥ


		final JLabel[][] f = new JLabel[13][13]; //���� ȭ���� 13x13 ��ǥ�� Ȱ���Ѵ�.

		for (int i=0; i<13; i++) {
			for(int j=0; j<13; j++) {
				f[i][j] = new JLabel(); //���� ȭ���� ��ǥ�� ���̺��� �̷��� �̷������.
			}
		}

		class Blistener implements ActionListener { //���� ���� �޼ҵ�
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
            }
		}


		class TListener implements ActionListener {   //timer?????? ActionListener
			public void actionPerformed(ActionEvent event) //�䳢(����)�� �̵� �̺�Ʈ ó�� �޼ҵ�
			{
				if(start<=0) where = 1+random.nextInt(4); //1���� 4 ������ ���� �� �߻�
				else { where = 1; start--; }
					switch(where) { //where�� ���� ���� �Ʒ� ���̽��� �б�
						case 1:
								temp1=f[rabH-1][rabW].getIcon(); //�̵�'��' ��ǥ�� �������� �����ͼ�
								f[rabH-1][rabW].setIcon(rab); //�̵�'��' ��ǥ�� �䳢(����)�� �������� ����
								f[rabH][rabW].setIcon(temp); //���� ��ǥ�� �� �������� ó��
								temp=temp1; //���� �� ������ ���ο� �� �������� ó��
								rabH--; //�䳢�� ���� ��ǥ���� 1 ����
							break;
						case 2: //���� �ٸ� ��ȣ�� ���ؼ��� ���� ����� �� �ش�.
								temp2=f[rabH+1][rabW].getIcon();
								f[rabH+1][rabW].setIcon(rab);
								f[rabH][rabW].setIcon(temp);
								temp=temp2;
								rabH++;
							break;
						case 3:
								temp3=f[rabH][rabW-1].getIcon();
								f[rabH][rabW-1].setIcon(rab);
								f[rabH][rabW].setIcon(temp);
								temp=temp3;
								rabW--;
							break;
						case 4:
								temp4=f[rabH][rabW+1].getIcon();
								f[rabH][rabW+1].setIcon(rab);
								f[rabH][rabW].setIcon(temp);
								temp=temp4;
								rabW++;
							break;
					}
					if(rabH==turH && rabW==turW) { //�¸� ����
						f[rabH][rabW].setIcon(rab);
						dialog.add(button); //���̾�α׿� ��ư �߰�
						dialog.setVisible(true); //�¸� ���� ���̾�α�
					}

				if(rabH==5 && rabW==7) { f[6][7].setIcon(backGround); } //�⺻ ���� ��ǥ�� 2
				System.out.println(where);
			}
		}
		TListener tListener = new TListener(); 
		class KListener extends KeyAdapter{  //???????????? ???? KeyListener
			public void keyPressed(KeyEvent e) { //�÷��̾�(�ź���)�� �̵� �̺�Ʈ ó�� �޼ҵ�
				int key = e.getKeyCode(); //����Ű�� �̵��ϵ��� �����ϴ� �޼ҵ�
				int Mcount = Integer.parseInt(MoveCount.getText());
                Mcount += 1;
                MoveCount.setText(String.valueOf(Mcount));
				switch(key) {
					case KeyEvent.VK_UP: //���� ����Ű
                        tListener.actionPerformed(null);
                            //
							f[turH-1][turW].setIcon(turU);
                            //�� ��ġ�� �÷��̾� ������ ��ġ
							f[turH][turW].setIcon(backGround);
                            //���� �ڸ��� ��׶��� ���������� ��ü
							turH--; //�÷��̾��� ��ǥ�� ���� ->���� ���� ������ ��

						if((f[turH-2][turW].getIcon()).equals(rab)) { //�¸� ����. ���� �÷��̾�(�ź���)�� �䳢(����)�� ��ǥ�� ���ٸ� �¸�
							f[rabH][rabW].setIcon(rab);
							dialog.add(button); //�¸� ���� ���̾�α׿� ��ư �߰�
							dialog.setVisible(true); //���̾�α� ���̰� �����
						}
						break; //���� �ٸ� ����Ű�� ���ؼ��� ���� ���
                        
					case KeyEvent.VK_DOWN:
                        tListener.actionPerformed(null);
							f[turH+1][turW].setIcon(turD);
							f[turH][turW].setIcon(backGround);
							turH++;
                        
						if((f[turH+1][turW].getIcon()).equals(rab)){
							f[rabH][rabW].setIcon(rab);
							dialog.add(button);
							dialog.setVisible(true);
						}
						break;
                        
					case KeyEvent.VK_LEFT:
                        tListener.actionPerformed(null);
							f[turH][turW-1].setIcon(turL);
							f[turH][turW].setIcon(backGround);
							turW--;
						if((f[turH][turW-1].getIcon()).equals(rab)){
							f[rabH][rabW].setIcon(rab);
							dialog.add(button);
							dialog.setVisible(true);
						}
						break;
					case KeyEvent.VK_RIGHT:
                        tListener.actionPerformed(null);
							f[turH][turW+1].setIcon(turR);
							f[turH][turW].setIcon(backGround);
							turW++;
						if((f[turH][turW+1].getIcon()).equals(rab)) {
							f[rabH][rabW].setIcon(rab);
							dialog.add(button);
							dialog.setVisible(true);
						}
						break;
				}

			}

		}
		KListener listener = new KListener(); //Ű ������ ó�� �޼ҵ�

		button.addActionListener(new Blistener());


		JPanel panel = new JPanel(); //���� ȭ�� �г�
		JPanel countpanel = new JPanel();//ī��Ʈ�� �� �г�
		panel.setLayout(new GridLayout(13,13)); //13x13 ũ���� �׸��� ����
		countpanel.setLayout(new GridLayout(13,1));
		frame.requestFocus();
		frame.addKeyListener(new KListener());


		for(int i=0; i<13; i++) {
			for(int j=0; j<13; j++) {
				f[i][j].setIcon(backGround);
				f[i][j].addKeyListener(listener);
				panel.add(f[i][j]);
			}
		}
		
		f[0][0].setIcon(backGround); f[0][1].setIcon(backGround);f[0][2].setIcon(backGround);f[0][3].setIcon(backGround);f[0][4].setIcon(backGround);f[0][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[0][7].setIcon(backGround);f[0][8].setIcon(backGround);f[0][9].setIcon(backGround);f[0][10].setIcon(backGround);f[0][11].setIcon(backGround);f[0][12].setIcon(backGround);
		f[1][0].setIcon(backGround); f[1][1].setIcon(backGround);f[1][2].setIcon(backGround);f[1][3].setIcon(backGround);f[1][4].setIcon(backGround);f[1][5].setIcon(backGround);
		f[1][6].setIcon(backGround);f[1][7].setIcon(backGround);f[1][8].setIcon(backGround);f[1][9].setIcon(backGround);f[1][10].setIcon(backGround);f[1][11].setIcon(backGround);f[1][12].setIcon(backGround);
		f[2][0].setIcon(backGround); f[2][1].setIcon(backGround);f[2][2].setIcon(backGround);f[2][3].setIcon(backGround);f[2][4].setIcon(backGround);f[2][5].setIcon(backGround);
		f[2][6].setIcon(backGround);f[2][7].setIcon(backGround);f[2][8].setIcon(backGround);f[2][9].setIcon(backGround);f[2][10].setIcon(backGround);f[2][11].setIcon(backGround);f[2][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[3][1].setIcon(backGround);f[3][2].setIcon(backGround);f[3][3].setIcon(backGround);f[3][4].setIcon(backGround);f[3][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[3][7].setIcon(backGround);f[3][8].setIcon(backGround);f[3][9].setIcon(backGround);f[3][10].setIcon(backGround);f[3][11].setIcon(backGround);f[3][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[4][1].setIcon(backGround);f[4][2].setIcon(backGround);f[4][3].setIcon(backGround);f[4][4].setIcon(backGround);f[4][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[4][7].setIcon(backGround);f[4][8].setIcon(backGround);f[4][9].setIcon(backGround);f[4][10].setIcon(backGround);f[4][11].setIcon(backGround);f[4][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[5][1].setIcon(backGround);f[5][2].setIcon(backGround);f[5][3].setIcon(backGround);f[5][4].setIcon(backGround);f[5][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[5][7].setIcon(backGround);f[5][8].setIcon(backGround);f[5][9].setIcon(backGround);f[5][10].setIcon(backGround);f[5][11].setIcon(backGround);f[5][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[6][1].setIcon(backGround);f[6][2].setIcon(backGround);f[6][3].setIcon(backGround);f[6][4].setIcon(backGround);f[6][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[6][7].setIcon(backGround);f[6][8].setIcon(backGround);f[6][9].setIcon(backGround);f[6][10].setIcon(backGround);f[6][11].setIcon(backGround);f[6][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[7][1].setIcon(backGround);f[7][2].setIcon(backGround);f[7][3].setIcon(backGround);f[7][4].setIcon(backGround);f[7][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[7][7].setIcon(rab);f[7][8].setIcon(backGround);f[7][9].setIcon(backGround);f[7][10].setIcon(backGround);f[7][11].setIcon(backGround);f[7][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[8][1].setIcon(backGround);f[8][2].setIcon(backGround);f[8][3].setIcon(backGround);f[8][4].setIcon(backGround);f[8][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[8][7].setIcon(backGround);f[8][8].setIcon(backGround);f[8][9].setIcon(backGround);f[8][10].setIcon(backGround);f[8][11].setIcon(backGround);f[8][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[9][1].setIcon(backGround);f[9][2].setIcon(backGround);f[9][3].setIcon(backGround);f[9][4].setIcon(backGround);f[9][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[9][7].setIcon(backGround);f[9][8].setIcon(backGround);f[9][9].setIcon(backGround);f[9][10].setIcon(backGround);f[9][11].setIcon(backGround);f[9][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[10][1].setIcon(backGround);f[10][2].setIcon(backGround);f[10][3].setIcon(backGround);f[10][4].setIcon(backGround);f[10][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[10][7].setIcon(backGround);f[10][8].setIcon(backGround);f[10][9].setIcon(backGround);f[10][10].setIcon(backGround);f[10][11].setIcon(backGround);f[10][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[11][1].setIcon(backGround);f[11][2].setIcon(backGround);f[11][3].setIcon(backGround);f[11][4].setIcon(backGround);f[11][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[11][7].setIcon(backGround);f[11][8].setIcon(backGround);f[11][9].setIcon(backGround);f[11][10].setIcon(backGround);f[11][11].setIcon(backGround);f[11][12].setIcon(backGround);
		f[0][0].setIcon(backGround); f[12][1].setIcon(backGround);f[12][2].setIcon(backGround);f[12][3].setIcon(backGround);f[12][4].setIcon(backGround);f[12][5].setIcon(backGround);
		f[0][6].setIcon(backGround);f[12][7].setIcon(turD);f[12][8].setIcon(backGround);f[12][9].setIcon(backGround);f[12][10].setIcon(backGround);f[12][11].setIcon(backGround);f[12][12].setIcon(backGround);
		
		countpanel.add(MoveCount,BorderLayout.CENTER);
		JPanel Allpanel = new JPanel();
		Allpanel.setLayout(new BoxLayout(Allpanel,BoxLayout.Y_AXIS));
		Allpanel.add(panel);
		Allpanel.add(countpanel);
		frame.add(Allpanel); //�����ӿ� �г� �߰�
		frame.setTitle("Go! Go! Turtle!"); //���� Ÿ��Ʋ
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���� ���� �ɼ�
		frame.setVisible(true); //���� ȭ���̴� �̰� ���� ���� ������ ��
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT); //������ ������ ����
	}

	private static Random random; //�䳢�� �������� �������� �ϱ⿡ ���� ���� ����
	private static int turH, turW, rabH, rabW, where, start; //���� ��ǥ
    private static int turU, turD, turR, turL, rab, backGround; //�÷��̾�, �䳢, ��� ����
    //turU, turD, turR, turL, rab, backGround -> ����? �ι� ����
	private static int fieldMin=0, fieldMax=13; //�ʵ� ����� ���� �ּڰ�
	private static Icon temp1, temp2, temp3, temp4, temp;

	private static final int FRAME_WIDTH = 650; //�ʺ� 660
	private static final int FRAME_HEIGHT = 710; //���� 650
}